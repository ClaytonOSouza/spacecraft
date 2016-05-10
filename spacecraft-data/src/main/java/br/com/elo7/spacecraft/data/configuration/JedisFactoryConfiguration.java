package br.com.elo7.spacecraft.data.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisFactoryConfiguration {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("localhost");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setPoolConfig(this.getJedisPoolConfig());
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setTimeout(2000);
		jedisConnectionFactory.afterPropertiesSet();
		
		return jedisConnectionFactory;
	}
	
	private JedisPoolConfig getJedisPoolConfig() {
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		
		poolConfig.setMaxWaitMillis(2000);
		poolConfig.setMaxTotal(100);
		poolConfig.setMinIdle(5);
		poolConfig.setMaxIdle(10);
		
		return poolConfig;
	}
	
}
