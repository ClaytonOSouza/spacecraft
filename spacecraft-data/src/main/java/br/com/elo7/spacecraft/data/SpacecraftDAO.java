package br.com.elo7.spacecraft.data;

import org.springframework.stereotype.Repository;

import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.repository.SpacecraftRepository;

@Repository
public class SpacecraftDAO implements SpacecraftRepository {

	@Override
	public Spacecraft getSpacecraftByCoordenates(Spacecraft spacecraft) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Spacecraft spacecraft) {
		// TODO Auto-generated method stub
		
	}

}
