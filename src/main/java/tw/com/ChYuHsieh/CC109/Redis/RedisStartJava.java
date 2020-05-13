package tw.com.ChYuHsieh.CC109.Redis;

import redis.clients.jedis.Jedis;

public class RedisStartJava {

  public static void main(String[] args) {
    //Connecting to Redis server on localhost
    Jedis jedis = new Jedis("localhost", 6379);
    System.out.println("Connection to server sucessfully");
    //check whether server is running or not
    System.out.println("Server is running: "+jedis.ping());
    System.out.println("Server is running: "+jedis.get("Test"));

  }

}
