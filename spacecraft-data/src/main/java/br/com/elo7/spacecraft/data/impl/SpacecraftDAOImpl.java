package br.com.elo7.spacecraft.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.elo7.spacecraft.data.SpacecraftDAO;
import br.com.elo7.spacecraft.data.redis.SpacecraftRedisTemplate;
import br.com.elo7.spacecraft.model.Spacecraft;

@Repository
public class SpacecraftDAOImpl implements SpacecraftDAO {
	
	
	private SpacecraftRedisTemplate spacecraftRedisTemplate;
	
	
	@Override
	public Spacecraft getSpacecraftByCoordenates(Spacecraft spacecraft) {
		
		String key = spacecraft.getCoordinateX() + "/" + spacecraft.getCoordinateY();
		
		key = spacecraftRedisTemplate.encodeKey(key);
		
		Spacecraft spacecraftReturnable = (Spacecraft) spacecraftRedisTemplate.get(key);
		
		return spacecraftReturnable;
	}

	@Override
	public void persist(Spacecraft spacecraft) {
		
		String key = spacecraft.getCoordinateX() + "/" + spacecraft.getCoordinateY();
		
		key = spacecraftRedisTemplate.encodeKey(key);
		
		spacecraftRedisTemplate.put(key, spacecraft);
	}
	
	@Autowired
	public void setSpacecraftRedisTemplate(SpacecraftRedisTemplate spacecraftRedisTemplate) {
		this.spacecraftRedisTemplate = spacecraftRedisTemplate;
	}
	
}
