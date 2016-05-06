package br.com.elo7.spacecraft.model;

public interface SpacecraftRepository {
	
	
	public Spacecraft getSpacecraftByCoordenates(Spacecraft spacecraft);

	public void persist(Spacecraft spacecraft);
	
}
