package br.com.elo7.spacecraft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.model.Spacecraft;

@Controller
public class SpacecraftServices {
	
	@Autowired
	private SpacecraftBO spacecraftBO;
	
	
	public Spacecraft executeCommands() {
		
		Spacecraft spacecraft = spacecraftBO.executeCommands(null);
		
		return spacecraft;
	}
	
}
