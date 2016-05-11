package br.com.elo7.spacecraft.data.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.elo7.spacecraft.commons.exception.InternalServerErrorException;
import br.com.elo7.spacecraft.data.SpacecraftDAO;
import br.com.elo7.spacecraft.data.redis.SpacecraftRedisTemplate;
import br.com.elo7.spacecraft.model.Spacecraft;

@Repository
public class SpacecraftDAOImpl implements SpacecraftDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpacecraftDAOImpl.class);
	
	private SpacecraftRedisTemplate spacecraftRedisTemplate;
	
	
	@Override
	public Spacecraft getSpacecraftByCoordinates(Spacecraft spacecraft) throws InternalServerErrorException {
		
		try {
			
			LOG.info("Geting spacecraft by coordinates - coordinateX:{} - coordinateY:{}",
					spacecraft.getCoordinateX(), spacecraft.getCoordinateY());
			
			String key = spacecraft.getCoordinateX() + "/" + spacecraft.getCoordinateY();
			
			key = spacecraftRedisTemplate.encodeKey(key);
			
			Spacecraft spacecraftReturnable = (Spacecraft) spacecraftRedisTemplate.get(key);
			
			return spacecraftReturnable;
			
		} catch(Exception e) {
			LOG.error("Unexpected error in get spacecraft by coordinates.");
			throw new InternalServerErrorException();
		}
	}

	@Override
	public void persist(Spacecraft spacecraft) throws InternalServerErrorException {
		
		try {
			
			String key = spacecraft.getCoordinateX() + "/" + spacecraft.getCoordinateY();
			
			key = spacecraftRedisTemplate.encodeKey(key);
			
			LOG.info("Persisting spacecraft - Key:{}", key);
			
			spacecraftRedisTemplate.put(key, spacecraft);
			
		} catch(Exception e) {
			LOG.error("Unexpected error in persist spacecraft.");
			throw new InternalServerErrorException();
		}
	}
	
	@Autowired
	public void setSpacecraftRedisTemplate(SpacecraftRedisTemplate spacecraftRedisTemplate) {
		this.spacecraftRedisTemplate = spacecraftRedisTemplate;
	}
	
}
