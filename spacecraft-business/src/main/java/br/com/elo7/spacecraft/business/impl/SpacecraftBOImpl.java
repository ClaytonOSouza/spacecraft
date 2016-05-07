package br.com.elo7.spacecraft.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.repository.SpacecraftRepository;

@Service
public class SpacecraftBOImpl implements SpacecraftBO {
	
	private SpacecraftRepository spacecraftRepository;
	
	@Override
	public Spacecraft executeCommands(Spacecraft spacecraft) {
		
		spacecraft.setSpacecraftRepository(spacecraftRepository);
		
		spacecraft.getCommands().forEach(command -> {
			
			if("M".equalsIgnoreCase(command)) {
				spacecraft.move();
			}
			
			if("R".equalsIgnoreCase(command)) {
				spacecraft.right();
			}
			
			if("L".equalsIgnoreCase(command)) {
				spacecraft.left();
			}
			
		});
		
		this.validateCrash(spacecraft);
		
		spacecraft.persist();
		
		return spacecraft;
	}
	
	private void validateCrash(Spacecraft spacecraft) {
		
		Spacecraft spacecraftReturned = spacecraft.getSpacecraftByCoordenates();
		
		if(spacecraftReturned != null) {
			throw new RuntimeException();
		}
	}
	
	@Autowired
	public void setSpacecraftRepository(SpacecraftRepository spacecraftRepository) {
		this.spacecraftRepository = spacecraftRepository;
	}
	
}
