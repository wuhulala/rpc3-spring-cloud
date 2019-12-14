package com.wuhulala.cache.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Transaction;
import java.util.Set;

/**
 * @author wuhulala<br>
 * @date 2019/12/3<br>
 * @since v1.0<br>
 */
public class RedisCacheTest {

    private Jedis jedis;

    @Before
    public void init() {
        JedisPool jedisPool = new JedisPool("192.168.1.101", 6379);
        jedis = jedisPool.getResource();
    }

    @Test
    public void testSortedSet() {
        jedis.zadd("rank-list", 1, "赵又廷");
        jedis.zadd("rank-list", 11, "Uzi");
        jedis.zadd("rank-list", 11, "胡");
        jedis.zadd("rank-list", 21, "黄海波");
        jedis.zadd("rank-list", 133, "高圆圆");
        // 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
        printRankList();

        // 有序集合中对指定成员的分数加上增量 increment
        jedis.zincrby("rank-list", 40, "黄海波");
        jedis.zincrby("rank-list", 40, "黄海波");
        jedis.zincrby("rank-list", 40, "黄海波");
        jedis.zincrby("rank-list", 40, "黄海波");
        printRankList();
    }

    private void printRankList() {
        Set<String> rangList = jedis.zrevrange("rank-list", 0, 1);
        System.out.println("rank list" + rangList);
    }

    // 在 Redis 里面，每个 HyperLogLog 键只需要花费 12 KB 内存，就可以计算接近 2^64 个不同元素的基 数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。
    @Test
    public void testHyperLogLog() {
        // 添加指定元素到 HyperLogLog 中。
        String key = "hyperLogLog-test";
        jedis.pfadd(key, "1", "3", "5", "7", "9", "11");

//        返回给定 HyperLogLog 的基数估算值。
        System.out.println("基数就是：：" + jedis.pfcount(key));


    }

    ////////////////////////////// 测试发布订阅 ///////////////////////////

    /**
     * 1. 重启消费者之后，不会消费遗漏的消息
     * 2. 消费这个可以订阅多个topic
     */
    @Test
    public void testPubSub() {
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(message);
                super.onMessage(channel, message);
            }
        }, "topic-1");

    }

    @Test
    public void testPublish() {
        jedis.publish("topic-1", "wuhulala1");
    }

    ///////////////////////////////////  事务 ///////////////////////////////
    // 单个 Redis 命令的执行是原子性的，但 Redis 没有在事务上增加任何维持原子性的机制，所以 Redis 事务的执行并不是原子性的。
    //
    //事务可以理解为一个打包的批量执行脚本，但批量指令并非原子化的操作，中间某条指令的失败不会导致前面已做指令的回滚，也不会造成后续的指令不做。

    @Test
    public void testTransactional() {
        // 开启事务
        Transaction transaction = jedis.multi();
        System.out.println(" open transactional !");
        // 当开始了 multi之后， jedis 不能进行操作，必须要使用transaction对象操作
        try {
            transaction.set("a", "aaa");
            Thread.sleep(6000);
            transaction.set("b", "b");

            transaction.exec();
            System.out.println(" close transactional !");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.discard();
            System.out.println(" rollback transactional !");
        }

    }
}