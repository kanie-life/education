package com.kanie.education.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;

/**
 * 统一事务切面
 *
 * //设置手动回滚
 * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
 *
 * @author ：kanie
 * @date ：Created in 2020/11/10 11:54
 */
@Aspect
@Configuration
public class TransactionAspect {

    @Autowired
    private DataSource dataSource;

    @Bean
    public TransactionManager txManager () {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionInterceptor txAdvice(TransactionManager txManager) {
        DefaultTransactionAttribute required = new DefaultTransactionAttribute();
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute required_readonly = new DefaultTransactionAttribute();
        required_readonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        required_readonly.setReadOnly(true);

        //只写事务配置
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("init*", required);
        source.addTransactionalMethod("insert*", required);
        source.addTransactionalMethod("update*", required);
        source.addTransactionalMethod("save*", required);
        source.addTransactionalMethod("add*", required);
        source.addTransactionalMethod("create*", required);
        source.addTransactionalMethod("del*", required);
        source.addTransactionalMethod("delete*", required);
        source.addTransactionalMethod("remove*", required);
        source.addTransactionalMethod("batch*", required);
        source.addTransactionalMethod("close*", required);

        // 只读事务配置
        source.addTransactionalMethod("get*", required_readonly);
        source.addTransactionalMethod("load*", required_readonly);
        source.addTransactionalMethod("list*", required_readonly);
        source.addTransactionalMethod("find*", required_readonly);
        source.addTransactionalMethod("select*", required_readonly);
        source.addTransactionalMethod("query*", required_readonly);
        source.addTransactionalMethod("count*", required_readonly);
        source.addTransactionalMethod("criteria*", required_readonly);
        return new TransactionInterceptor(txManager, source);
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(TransactionInterceptor txAdvice) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(txAdvice);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.kanie.education..dao.*.*(..))");
        advisor.setPointcut(pointcut);
        return advisor;
    }
}
