package com.znkf.shop.common.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author 武海升
 * @date 2018/7/10 18:43
 */
public class PassportRedis {

    private static JedisPool pool;

    public static JedisPool getPool() {
        if (pool == null) {
            String host = Global.getConfig("redis_host");
            Integer port = Integer.parseInt(Global.getConfig("redis_port"));
            Integer timeOut = Integer.parseInt(Global.getConfig("redis_timeout"));
            Integer maxTotal = Integer.parseInt(Global.getConfig("redis_maxTotal"));
            //访问密码
            String AUTH = Global.getConfig("redis_password");
            JedisPoolConfig config = new JedisPoolConfig();
            // 在获取Jedis连接时，自动检验连接是否可用
            config.setTestOnBorrow(true);
            // 自动测试池中的空闲连接是否都是可用连接
            config.setTestWhileIdle(true);
            // 在将连接放回池中前，自动检验连接是否有效
            config.setTestOnReturn(true);
            // 最大空闲连接数
            config.setMaxIdle(10);
            // 获取Jedis连接的最大等待时间（50秒）
            config.setMaxWaitMillis(50 * 1000);
            // 设置最大连接数
            config.setMaxTotal(maxTotal);

            pool = new JedisPool(config, host, port, timeOut,AUTH);
        }
        return pool;
    }
    /**
     * 初始化Redis连接池
     */
    private static void initializePool() {
        String host = Global.getConfig("redis_host");
        Integer port = Integer.parseInt(Global.getConfig("redis_port"));
        Integer timeOut = Integer.parseInt(Global.getConfig("redis_timeout"));
        Integer maxTotal = Integer.parseInt(Global.getConfig("redis_maxTotal"));
        JedisPoolConfig config = new JedisPoolConfig();
        // config.setMaxWaitMillis(1000);
        // 在获取Jedis连接时，自动检验连接是否可用
        config.setTestOnBorrow(true);
        // 自动测试池中的空闲连接是否都是可用连接
        config.setTestWhileIdle(true);
        // 在将连接放回池中前，自动检验连接是否有效
        config.setTestOnReturn(true);
        // 最大空闲连接数
        config.setMaxIdle(10);
        // 获取Jedis连接的最大等待时间（50秒）
        config.setMaxWaitMillis(50 * 1000);
        // 设置最大连接数
        config.setMaxTotal(maxTotal);

        pool = new JedisPool(config, host, port, timeOut);
    }

    /**
     * 多线程环境同步初始化（保证项目中有且仅有一个连接池）
     */
    private static synchronized void poolInit() {
        if (null == pool) {
            initializePool();
        }
    }

    /**
     * 获取Jedis实例
     */
    public static Jedis getJedis() {
        if (null == pool) {
            poolInit();
        }

        int timeoutCount = 0;
        while (true) {
            try {
                if (null != pool) {
                    return pool.getResource();
                }
            } catch (Exception e) {
                if (e instanceof JedisConnectionException) {
                    timeoutCount++;
                    if (timeoutCount > 3) {
                        break;
                    }
                } else {
                    break;
                }
            }
            break;
        }
        return null;
    }
    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            // pool.returnResource(jedis);
            jedis.close();
        }

    }

}
