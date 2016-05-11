package br.com.elo7.spacecraft.data.redis;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class SpacecraftRedisTemplate {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpacecraftRedisTemplate.class);	
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	public void put(final String key, final Object obj) {
		
		try {
			
			if(StringUtils.isNotBlank(key) && obj != null) {
				
				redisTemplate.executePipelined(new RedisCallback<Object>() {
					
					@Override
					public Object doInRedis(RedisConnection connection) throws DataAccessException {
						
						byte[] keySerialize = redisTemplate.getKeySerializer().serialize(key);
						
						connection.set(keySerialize, redisTemplate.getValueSerializer().serialize(obj));
						
						return null;
					}
					
				});
			}
			
		} catch(Exception e) {
			LOG.error(String.format("Error on Redis PUT for key[%s] and value[%s]", key, obj));
		}
	}
	
	public Object get(final String key) {
		
		Object value = null;
		
		try {
			
			if(StringUtils.isNotBlank(key)) {
				
				value = redisTemplate.opsForValue().get(key);
			}
			
		} catch(Exception e) {
			LOG.error(String.format("Error on Redis GET for key[%s]", key));
		}
		
		return value;
	}
	
	public String encodeKey(String key) {
		
		String keyEncoded = null;
		
		try {
			
			if(StringUtils.isNotBlank(key)) {
				
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				
				final byte[] digest = messageDigest.digest(key.getBytes("UTF-8"));
				
				keyEncoded = this.encodeHex(digest);
			}
			
		} catch (Exception e) {
			LOG.error(String.format("Error on encode key for key[%s]", key));
		}
		
		return keyEncoded;
	}
	
	private String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}
	
}
