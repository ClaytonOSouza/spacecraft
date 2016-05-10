package br.com.elo7.spacecraft.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.business.strategy.CommandsStrategy;
import br.com.elo7.spacecraft.commons.exception.CrashException;
import br.com.elo7.spacecraft.commons.validation.BeanValidator;
import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.commons.validation.groups.ExecuteCommands;
import br.com.elo7.spacecraft.data.SpacecraftDAO;
import br.com.elo7.spacecraft.model.Spacecraft;

@Service
public class SpacecraftBOImpl implements SpacecraftBO {
	
	
	private SpacecraftDAO spacecraftDAO;
	
	private BeanValidator beanValidator;
	
	
	@Override
	public Spacecraft executeCommands(Spacecraft spacecraft) {
		
		beanValidator.validate(spacecraft, ExecuteCommands.class);
		
		spacecraft.getCommands().forEach(command -> {
			
			CommandsStrategy commandsStrategy = CommandsStrategy.getCommandsStrategy(command);
			
			commandsStrategy.executeCommand(spacecraft);
			
		});
		
		this.validateCrash(spacecraft);
		
		spacecraftDAO.persist(spacecraft);
		
		return spacecraft;
	}
	
	private void validateCrash(Spacecraft spacecraft) {
		
		Spacecraft spacecraftReturned = spacecraftDAO.getSpacecraftByCoordenates(spacecraft);
		
		if(spacecraftReturned != null) {
			throw new CrashException(ErrorExceptionCode.CRASH_ERROR);
		}
	}
	
	@Autowired
	public void setSpacecraftDAO(SpacecraftDAO spacecraftDAO) {
		this.spacecraftDAO = spacecraftDAO;
	}
	
	@Autowired
	public void setBeanValidator(BeanValidator beanValidator) {
		this.beanValidator = beanValidator;
	}
	
}
