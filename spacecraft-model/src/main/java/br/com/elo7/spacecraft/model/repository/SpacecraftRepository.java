package br.com.elo7.spacecraft.model.repository;

import br.com.elo7.spacecraft.model.Spacecraft;

public interface SpacecraftRepository {
	
	
	public Spacecraft getSpacecraftByCoordenates(Spacecraft spacecraft);

	public void persist(Spacecraft spacecraft);
	
}
