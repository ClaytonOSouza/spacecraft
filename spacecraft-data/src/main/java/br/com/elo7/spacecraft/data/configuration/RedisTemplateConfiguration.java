package br.com.elo7.spacecraft.data.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@SuppressWarnings({"rawtypes", "unchecked"})
public class RedisTemplateConfiguration {
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Bean
	public RedisTemplate redisTemplate() {
		
		RedisTemplate redisTemplate = new RedisTemplate();
		
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.setKeySerializer(this.stringRedisSerializer());
		redisTemplate.setHashKeySerializer(this.stringRedisSerializer());
		
		return redisTemplate;
	}
	
	private StringRedisSerializer stringRedisSerializer() {
		
		StringRedisSerializer StringRedisSerializer = new StringRedisSerializer();
		
		return StringRedisSerializer;
	}
	
}
