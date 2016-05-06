package br.com.elo7.spacecraft.business.impl;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.model.Plateau;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.SpacecraftRepository;

public class SpacecraftBOImpl implements SpacecraftBO {

	private SpacecraftRepository spacecraftRepository;
	
	@Override
	public Spacecraft executeCommands(Plateau plateau, Spacecraft spacecraft) {
		
		spacecraft.setSpacecraftRepository(spacecraftRepository);
		
		spacecraft.getCommands().forEach(command -> {
			
			if("M".equalsIgnoreCase(command)) {
				spacecraft.move(plateau);
			}
			
			if("R".equalsIgnoreCase(command)) {
				spacecraft.right();
			}
			
			if("L".equalsIgnoreCase(command)) {
				spacecraft.left();
			}
			
		});
		
		this.validateCrash(plateau, spacecraft);
		
		spacecraft.persist();
		
		return spacecraft;
	}
	
	private void validateCrash(Plateau plateau, Spacecraft spacecraft) {
		
		Spacecraft spacecraftReturned = spacecraft.getSpacecraftByCoordenates();
		
		if(spacecraftReturned != null) {
			throw new RuntimeException();
		}
	}
	
	public void setSpacecraftRepository(SpacecraftRepository spacecraftRepository) {
		this.spacecraftRepository = spacecraftRepository;
	}
	
}
