package br.com.elo7.spacecraft.business.impl;

import org.springframework.stereotype.Service;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.business.strategy.CommandsStrategy;
import br.com.elo7.spacecraft.commons.exception.CrashException;
import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.repository.SpacecraftRepository;

@Service
public class SpacecraftBOImpl implements SpacecraftBO {
	
	private SpacecraftRepository spacecraftRepository;
	
	@Override
	public Spacecraft executeCommands(Spacecraft spacecraft) {
		
		spacecraft.setSpacecraftRepository(spacecraftRepository);
		
		spacecraft.getCommands().forEach(command -> {
			
			CommandsStrategy commandsStrategy = CommandsStrategy.getCommandsStrategy(command);
			
			commandsStrategy.executeCommand(spacecraft);
			
		});
		
		this.validateCrash(spacecraft);
		
		spacecraft.persist();
		
		return spacecraft;
	}
	
	private void validateCrash(Spacecraft spacecraft) {
		
		Spacecraft spacecraftReturned = spacecraft.getSpacecraftByCoordenates();
		
		if(spacecraftReturned != null) {
			throw new CrashException(ErrorExceptionCode.CRASH_ERROR);
		}
	}
	
	//@Autowired
	public void setSpacecraftRepository(SpacecraftRepository spacecraftRepository) {
		this.spacecraftRepository = spacecraftRepository;
	}
	
}
