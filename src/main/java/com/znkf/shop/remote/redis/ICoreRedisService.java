package com.znkf.shop.remote.redis;

import java.util.List;

/**
 * @author 武海升
 * @date 2018/7/10 18:39
 */
public interface ICoreRedisService {

    /**
     * 验证redis是否存在key
     * @param key
     * @return
     */
    boolean keyExists(String key);

    /**
     * 设置redis值
     * @param key
     * @param value
     */
    void addToRedis(String key, String value);

    /**
     * 设置redis值并设置存活时间
     * @param key
     * @param value
     * @param expire 秒 key存活时间
     */
    void addToRedis(String key, String value, int expire);

    /**
     * 通过key获取redis值
     * @param key
     * @return
     */
    String getRedisValueByKey(String key);

    /**
     * 通过key删除value
     * @param key
     * @return
     */
    boolean deleteRedisValueByKey(String key);

    /**
     * 存放Object数据
     * @param key
     * @param value
     */
    public void addObjectData(String key, Object value);
    /**
     * 获取Object数据
     * @param key
     * @return
     */
    public Object getObjectData(String key);

    public String hget(String key, String field);

    public Long hset(String key, String field, String value);

    public Long expire(String key, int seconds);

    /**
     * 添加list
     * @param key
     * @param value
     */
    public void addListToRedis(String key, String value);

    /**
     * 获取List
     * @param key
     * @param min 角标下限
     * @param max 角标上限
     * @return
     */
    public List<String> getListFromRedis(String key, Long min, Long max);

    /**
     * 添加list
     * @param key
     * @param value
     */
    public void addSetToRedis(String key, String value);

    /**
     * 获取List
     * @param key
     * @return
     */
    public List<String> getSetFromRedis(String key);

    /**
     * redis计数增加量
     * @param key
     * @return
     */
    public boolean addCount(String key);

    /**
     * redis计数增加量,时间限制
     * @param key
     * @param time 秒
     * @return
     */
    public boolean addCount(String key, int time);


    /**
     * redis计数减少量
     * @param key
     * @return
     */
    public boolean decreaseCount(String key);

    /**
     * 获取计数量
     * @param key
     * @return
     */
    public Integer getCount(String key);

}
