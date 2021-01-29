package com.kanie.education.common.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by kanie on 2021/1/28 15:28
 */
public interface IBaseDao {

    /**
     * 根据id查询对象
     * @param key
     * @param id
     * @param <T>
     * @return
     */
    <T> T selectById(String key, Long id);

    /**
     * 根据条件查询集合
     * @param key
     * @param param
     * @param <T>
     * @return
     */
    <T> List<T> selectBy(String key, Map<String, Object> param);

    /**
     * 根据条件查询集合
     * @param key
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    <T> List<T> selectBy(String key, String property, Object value);

    /**
     * 根据条件查询一个对象
     * @param key
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    <T> T selectSingleBy(String key, String property, Object value);

    /**
     * 根据条件查询一个对象
     * @param key
     * @param param
     * @param <T>
     * @return
     */
    <T> T selectSingleBy(String key, Map<String, Object> param);

    /**
     * 插入记录
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    <T> int insert(String key, T t);

    /**
     * 删除记录
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    <T> int delete(String key, T t);

    /**
     * 修改记录
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    <T> int update(String key, T t);
}
