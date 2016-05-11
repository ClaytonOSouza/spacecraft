package br.com.elo7.spacecraft.data.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisFactoryConfiguration {
	
	@Value("${spacecraft.redis.host}")
	private String redisHost;
	
	@Value("${spacecraft.redis.port}")
	private int redisPort;
	
	@Value("${spacecraft.redis.use.pool}")
	private boolean usePool;
	
	@Value("${spacecraft.redis.timeout}")
	private Long redisTimeout;
	
	@Value("${spacecraft.redis.MaxTotal}")
	private int redisMaxTotal;
	
	@Value("${spacecraft.redis.MinIdle}")
	private int redisMinIdle;
	
	@Value("${spacecraft.redis.MaxIdle}")
	private int redisMaxIdle;
	
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(redisHost);
		jedisConnectionFactory.setPort(redisPort);
		jedisConnectionFactory.setPoolConfig(this.getJedisPoolConfig());
		jedisConnectionFactory.setUsePool(usePool);
		jedisConnectionFactory.setTimeout(redisTimeout.intValue());
		jedisConnectionFactory.afterPropertiesSet();
		
		return jedisConnectionFactory;
	}
	
	private JedisPoolConfig getJedisPoolConfig() {
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		
		poolConfig.setMaxWaitMillis(redisTimeout);
		poolConfig.setMaxTotal(redisMaxTotal);
		poolConfig.setMinIdle(redisMinIdle);
		poolConfig.setMaxIdle(redisMaxIdle);
		
		return poolConfig;
	}
	
}
