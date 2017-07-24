package name.lizhe.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisPoolConfig;  

@RestController  
@RequestMapping(value="/api") 
@EnableRedisHttpSession
public class HelloContorller {       
   @RequestMapping(value="/hello")  
   public String hello(HttpServletRequest req){  
       System.out.println("spring mvc hello world!");  
       req.getSession().setAttribute("testKey", "testValue");
       return "hello world";  
   }  
   
   
	@Autowired
	private JedisPoolConfig jpc;
	
	@Bean
	public JedisPoolConfig getJedisPoolConfig() {
		return new redis.clients.jedis.JedisPoolConfig();
	}
	
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory(){
		JedisConnectionFactory jcf = new org.springframework.data.redis.connection.jedis.JedisConnectionFactory();
		jcf.setHostName("172.28.128.4");
		jcf.setPort(6379);
		jcf.setPassword("lz12345");
		jcf.setUsePool(true);
		jcf.setPoolConfig(jpc);
		return jcf;
	}
}  