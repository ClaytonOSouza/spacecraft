package br.com.elo7.spacecraft.data;

import br.com.elo7.spacecraft.model.Spacecraft;

public interface SpacecraftDAO {
	
	
	public Spacecraft getSpacecraftByCoordenates(Spacecraft spacecraft);

	public void persist(Spacecraft spacecraft);
	
}
