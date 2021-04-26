package com.xjx.springboot.xjxspring;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class cs {

	 private static JedisPool jedisPool = null;

//	    //初始化redis连接池
//	    static{
//	        JedisPoolConfig config = new JedisPoolConfig();
//	        //配置最大jedis实例数
//	        config.setMaxTotal(1000);
//	        //配置资源池最大闲置数
//	        config.setMaxIdle(200);
//	        //等待可用连接的最大时间
//	        config.setMaxWaitMillis(10000);
//	        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
//	        config.setTestOnBorrow(true);
//	        jedisPool = new JedisPool("139.186.166.111",6379);
//	    }

	    //获取Jedis实例
	    public synchronized static Jedis getJedis(){
	        if(jedisPool != null){
	            Jedis resource = jedisPool.getResource();
	            return resource;
	        }else{
	            return null;
	        }
	    }

	    //释放Jedis资源
	    public static void returnResource(final Jedis jedis){
	        if(jedis != null){
	            jedisPool.close();
	        }
	    }


	    public static void main(String[] args){
	    	JedisPool jedisPool = new JedisPool("139.186.166.111",6379);
	    	
	        Jedis jedis =  jedisPool.getResource();
	        jedis.set("test", "hello JedisPool.");
	        jedis.set("hello", "JedisPool");
	        System.out.println(jedis.get("test"));
	        
	        System.out.println(jedis.get("hello"));
	    }
	 
	 
}
