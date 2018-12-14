package com.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);

        // vlaue 使用默认的jdk序列化
//        redisTemplate.setValueSerializer(stringSerializer);
//        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }
    /********************************** common **********************************/

    /**
     * 设置缓存失效时间
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存失效时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断缓存是否存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    public void del(Set<String> keys) {
        redisTemplate.delete(keys);
    }

    public Set<String> keys(String key) {
        if (key != null && key.length() > 0) {
            return redisTemplate.keys(key);
        } else {
            return new HashSet<>();
        }
    }


    /********************************** object **********************************/

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }


    /********************************** map **********************************/
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void hmset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public void hmset(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (time > 0) expire(key, time);
    }

    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /********************************** set **********************************/
    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0) expire(key, time);
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }


    /********************************** list **********************************/
    public List<Object> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public void lSet(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    public void lSet(String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        if (time > 0) expire(key, time);
    }

    /**
     * 获取第index条数据
     *
     * @param key
     * @param index
     * @return
     */
    public Object lGetIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 更新第index条数据
     *
     * @param key
     * @param index
     * @param value
     */
    public void lUpdateIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    public long lGetListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

}
