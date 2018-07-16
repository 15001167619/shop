package com.znkf.shop.remote.redis.impl;

import com.znkf.shop.common.config.PassportRedis;
import com.znkf.shop.common.utils.SerializeUtil;
import com.znkf.shop.common.utils.StringUtils;
import com.znkf.shop.remote.redis.ICoreRedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author 武海升
 * @date 2018/7/10 18:40
 */
@Service("redisService")
public class CoreRedisServiceImpl implements ICoreRedisService {

    @Override
    public boolean keyExists(String key) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public void addToRedis(String key, String value) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }

    }

    @Override
    public void addToRedis(String key, String value, int expire) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, value);
            jedis.expire(key, expire);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public String getRedisValueByKey(String key) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String value = jedis.get(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return null;
    }

    @Override
    public boolean deleteRedisValueByKey(String key) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return true;
    }


    @Override
    public void addObjectData(String key, Object value) {
        if(value==null)
            return;
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key.getBytes(), SerializeUtil.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public Object getObjectData(String key) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        Object objectData = new Object();
        try {
            jedis = pool.getResource();
            byte[] r = jedis.get(key.getBytes());
            objectData = (Object)SerializeUtil.unserialize(r);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }

        return objectData;
    }

    @Override
    public String hget(String key, String field) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            result = jedis.hget(key ,field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = pool.getResource();
            result =jedis.hset(key,field,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = pool.getResource();
            result = jedis.expire(key,seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return result;
    }

    @Override
    public void addListToRedis(String key, String value) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.lpush(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }

    }

    @Override
    public List<String> getListFromRedis(String key, Long min, Long max) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        List<String> lrange = null;
        try {
            jedis = pool.getResource();
            lrange = jedis.lrange(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return lrange;
    }

    @Override
    public void addSetToRedis(String key, String value) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.sadd(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public List<String> getSetFromRedis(String key) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        List<String> lrange = null;
        try {
            jedis = pool.getResource();
            lrange = jedis.srandmember(key,Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return lrange;
    }

    @Override
    public boolean addCount(String key) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.incr(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return false;
    }

    @Override
    public boolean addCount(String key, int time) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String count = jedis.get(key);
            if(count == null || count.trim().length() == 0)
                this.addToRedis(key,"1",time);
            else{
                int countInteger = Integer.parseInt(count);
                countInteger = countInteger + 1;
                this.addToRedis(key,String.valueOf(countInteger),time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return false;
    }

    @Override
    public boolean decreaseCount(String key) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.decr(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return false;
    }

    @Override
    public Integer getCount(String key) {
        JedisPool pool = PassportRedis.getPool();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String value = jedis.get(key);
            if (!StringUtils.isBlank(value)) {
                return Integer.parseInt(value);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return 0;
    }
}
