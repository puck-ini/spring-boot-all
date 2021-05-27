package org.zchzh.redis.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zengchzh
 * @date 2020/9/18 22:02
 * @description 不应该当作一个util使用
 */


@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定key失效时间
     * @param key key值
     * @param time 时间（秒）
     * @return
     */
    public boolean setKeyExpireTime(String key, long time){
        try {
            if (time > 0){
                //秒
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过key获取key的失效时间
     * @param key key值
     * @return 返回秒
     */
    public long getKeyExpireTime(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断redis中是否存在key
     * @param key key值
     * @return true 存在 false 不存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除缓存
     * @param key key值 可以传一个或多个值
     */
    public void deleteKey(String... key){
        if (StrUtil.isAllNotEmpty(key)){
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //string (key，value)

    /**
     * 通过key获取redis string类型的value
     * @param key key值
     * @return key值为空返回null
     */
    public Object getString(String key){
        return StrUtil.isEmpty(key) ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置string类型的key value
     * @param key
     * @param value
     * @return
     */
    public boolean setString(String key, Object value){
        try{
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置string类型的key value 失效时间time
     * @param key
     * @param value
     * @param time 失效时间（秒）
     * @return
     */
    public boolean setString (String key, Object value, long time){
        try {
            if (time <= 0){
                return this.setString(key, value);
            }
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //list (key, value(...))


    /**
     * 通过key获取list类型的所有值
     * @param key
     * @return
     */
    public List<Object> getListValue(String key){
        try {
            long start = 0L;
            //结束 0 到 -1代表所有值
            long end = redisTemplate.opsForList().size(key);
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的内容
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> getListValue(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过key获取list长度
     * @param key
     * @return
     */
    public long getListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 通过key和索引获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object getListIndexValue(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置list key和值
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean setList(String key, Object value){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean setList(String key, Object value, long time){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time <= 0){
                System.out.println("setlist time <= 0");
            }
            return setKeyExpireTime(key, time);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置list key和值
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean setList(String key, List<Object> value){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean setList(String key, List<Object> value, long time){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time <= 0){
                System.out.println("setlist time <= 0");
            }
            return setKeyExpireTime(key, time);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean updateListIndexValue(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long removeListIndexValue(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }




    //hash (key，field， value)

    /**
     * 通过key和field获取value
     * @param key
     * @param field
     * @return
     */
    public Object getHashValue(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 通过key获取所有键值对
     * @param key
     * @return
     */
    public Map<Object, Object> getHashMap(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean setHash(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean setHash(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time < 0) {
                System.out.println("setHashMap time < 0");
            }
            return setKeyExpireTime(key, time);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 设置hash类型的键值
     * @param key
     * @param map
     * @return
     */
    public boolean setHashMap(String key, Map<String, Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置hash类型的键值以及失效时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean setHashMap(String key, Map<String, Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time < 0) {
                System.out.println("setHashMap time < 0");
            }
            return setKeyExpireTime(key, time);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void deleteHashValue(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hasHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }
    
    //set (key,value)


    /**
     * 根据key获取set中的所有值
     * @param key
     * @return
     */
    public Set<Object> getSetValue(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long setSetValue(String key, Object... values){
        try{
            return redisTemplate.opsForSet().add(key, values);
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }


    /**
     * 将数据放入set缓存,并设置失效时间
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long setSetValue(String key, long time, Object... values){
        try{
            long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0){
                setKeyExpireTime(key, time);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 通过key获取set的长度
     * @param key
     * @return
     */
    public long getSetSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    public long removeSet(String key, Object... values){
        try {
            return redisTemplate.opsForSet().remove(key, values);
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 判断value是否存在set中
     * @param key
     * @param value
     * @return
     */
    public boolean hasSetKey(String key, Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //pub/sub


    /**
     * 推送消息
     * @param channel 管道名
     * @param msg 消息体
     * @return
     */
    public boolean sendChanelMsg(String channel, String msg){
        try {
            redisTemplate.convertAndSend(channel,msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 接受消息方法
     * @param msg
     * @return
     */
    public String receiveMessage(String msg){
        return msg;
    }

    //geo



    //
}
