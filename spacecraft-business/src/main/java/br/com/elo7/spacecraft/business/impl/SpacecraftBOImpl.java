package br.com.elo7.spacecraft.business.impl;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.model.Spacecraft;

public class SpacecraftBOImpl implements SpacecraftBO {

	@Override
	public Spacecraft executeCommands(Spacecraft spacecraft) {
		
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
		
		return spacecraft;
	}

}
