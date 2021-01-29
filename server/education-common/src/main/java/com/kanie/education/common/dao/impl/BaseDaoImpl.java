package com.kanie.education.common.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.kanie.education.common.dao.IBaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kanie on 2021/1/28 15:29
 */
@Repository
public class BaseDaoImpl implements IBaseDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public <T> T selectById(String key, Long id) {
        return (T)sqlSessionTemplate.selectOne(key, id);
    }

    @Override
    public <T> List<T> selectBy(String key, Map<String, Object> param) {
        return sqlSessionTemplate.selectList(key, param);
    }

    @Override
    public <T> List<T> selectBy(String key, String property, Object value) {
        Map<String, Object> param = new HashMap<>();
        param.put(property, value);
        return this.selectBy(key, param);
    }

    @Override
    public <T> T selectSingleBy(String key, String property, Object value) {
        List<T> list = this.selectBy(key, property, value);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public <T> T selectSingleBy(String key, Map<String, Object> param) {
        List<T> list = this.selectBy(key, param);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public <T> int insert(String key, T t) {
        return sqlSessionTemplate.insert(key, t);
    }

    @Override
    public <T> int delete(String key, T t) {
        return sqlSessionTemplate.delete(key, t);
    }

    @Override
    public <T> int update(String key, T t) {
        return sqlSessionTemplate.update(key, t);
    }
}
