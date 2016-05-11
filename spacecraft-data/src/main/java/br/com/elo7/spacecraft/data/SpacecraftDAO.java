package br.com.elo7.spacecraft.data;

import br.com.elo7.spacecraft.commons.exception.InternalServerErrorException;
import br.com.elo7.spacecraft.model.Spacecraft;

public interface SpacecraftDAO {
	
	
	public Spacecraft getSpacecraftByCoordinates(Spacecraft spacecraft) throws InternalServerErrorException;

	public void persist(Spacecraft spacecraft) throws InternalServerErrorException;
	
}
