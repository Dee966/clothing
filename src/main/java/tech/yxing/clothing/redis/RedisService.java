package tech.yxing.clothing.redis;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class RedisService {

//    @Autowired
//    private JedisPool jedisPool;

    /**
     * 获取单个对象
     * @return
     */
//    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            //生成真正的key
//            String realKey = prefix.getPrefix() + key;
//            String str = jedis.get(realKey);
//            System.out.println(str);
//            T t = stringToBean(str, clazz);
//            return t;
//        } finally {
//            returnToPool(jedis);
//        }
//    }
//
//    /**
//     * 获取集合
//     */
//    public  <T> List<T> getList(KeyPrefix prefix, String key, Class<T> clazz){
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            //生成真正的key
//            String realKey = prefix.getPrefix() + key;
//            String str = jedis.get(realKey);
//            List<T> list = JSONObject.parseArray(str, clazz);
//            return list;
//        } finally {
//            returnToPool(jedis);
//        }
//
//    }
//
//    /**
//     * 设置对象
//     */
//    public <T> boolean set(KeyPrefix prefix, String key, T value) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            String str = beanToString(value);
//            if (str == null || str.length() <= 0) {
//                return false;
//            }
//            //生成真正的key
//            String realKey = prefix.getPrefix() + key;
//            int seconds = prefix.expireSeconds();
//            if (seconds <= 0) {
//                jedis.set(realKey, str);
//            } else {
//                jedis.setex(realKey, seconds, str);
//            }
//            return true;
//        } finally {
//            returnToPool(jedis);
//        }
//    }


    /*
     *   将对象转换成json
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }


    /*
     *   将json转换成对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        }
        else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /*
     *   关闭连接
     */
//    private void returnToPool(Jedis jedis) {
//        if (jedis != null) {
//            jedis.close();
//            System.out.println("关闭连接");
//        }
//    }
}
