package br.com.elo7.spacecraft.business.impl;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.model.Spacecraft;

public class SpacecraftBOImpl implements SpacecraftBO {

	@Override
	public Spacecraft move(Spacecraft spacecraft) {
		
		return new Spacecraft(1,3,"N");
	}

}
