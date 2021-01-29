package com.kanie.education.security.config;

import com.kanie.education.security.component.*;
import com.kanie.education.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 对SpringSecurity的配置的扩展，支持自定义白名单资源路径和查询用户逻辑
 * @author ：kanie
 * @date ：Created in 2020/10/21 17:57
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //通过向http.authorizeRequests()方法添加多个子项来指定URL的自定义要求
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        //不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig().getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        //允许跨域请求的options请求
        registry.antMatchers(HttpMethod.OPTIONS).permitAll();
        registry
                //任何请求需要通过身份认证
                .and()
                //通过URL模式
                .authorizeRequests()
                //映射任何请求
                .anyRequest()
                //指定任何经过身份验证的用户都允许使用URL
                .authenticated()

                //关闭跨站请求防护及不使用session
                .and()
                //使用{@link WebSecurityConfigurerAdapter}的默认构造函数时，默认情况下会激活此功能
                .csrf()
                //禁用csrf
                .disable()
                //允许配置会话管理
                .sessionManagement()
                //STATELESS不使用session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //自定义权限拒绝处理类
                .and()
                //允许配置异常处理。使用时会自动应用
                .exceptionHandling()
                //指定要使用的{@link AccessDeniedHandler}
                .accessDeniedHandler(this.accessDeniedHandlerCustomize())
                //设置要使用的{@link AuthenticationEntryPoint}。
                .authenticationEntryPoint(this.authenticationEntryPointCustomize())

                //自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //有动态权限配置时添加动态权限校验过滤器
        if(dynamicSecurityService != null){
            registry.and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        }
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPointCustomize() {
        return new AuthenticationEntryPointCustomize();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandlerCustomize() {
        return new AccessDeniedHandlerCustomize();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IgnoreUrlsProperties ignoreUrlsConfig() {
        return new IgnoreUrlsProperties();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }
}
