package br.com.elo7.spacecraft.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.elo7.spacecraft.commons.exception.CoordinateException;
import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.commons.validation.groups.ExecuteCommands;
import br.com.elo7.spacecraft.model.repository.SpacecraftRepository;

public class Spacecraft {
	
	@NotNull(groups={ExecuteCommands.class})
	private Integer coordinateX;
	
	@NotNull(groups={ExecuteCommands.class})
	private Integer coordinateY;
	
	@NotBlank(groups={ExecuteCommands.class})
	private String cardinalPoint;
	
	@NotEmpty(groups={ExecuteCommands.class})
	private List<String> commands;
	
	@Valid
	private Plateau plateau;
	
	private SpacecraftRepository spacecraftRepository;
	
	private Spacecraft(Integer coordinateX, Integer coordinateY, 
						String cardinalPoint, List<String> commands, Plateau plateau) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.cardinalPoint = cardinalPoint;
		this.commands = commands;
		this.plateau = plateau;
	}
	
	
	public Integer getCoordinateX() {
		return coordinateX;
	}
	
	public Integer getCoordinateY() {
		return coordinateY;
	}
	
	public String getCardinalPoint() {
		return cardinalPoint;
	}
	
	public List<String> getCommands() {
		return commands;
	}
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	public void verifyPlateuCoordinates() {
		
		if(coordinateX > plateau.getUpperRightX() || coordinateX < 0) {
			throw new CoordinateException(ErrorExceptionCode.COORDINATE_ERROR);
		}
		
		if(coordinateY > plateau.getUpperRightY() || coordinateY < 0) {
			throw new CoordinateException(ErrorExceptionCode.COORDINATE_ERROR);
		}
	}
	
	public Spacecraft getSpacecraftByCoordenates() {
		return spacecraftRepository.getSpacecraftByCoordenates(this); 
	}
	
	public void persist() {
		spacecraftRepository.persist(this);
	}
	
	public void setCoordinateX(Integer coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	public void setCoordinateY(Integer coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	public void setCardinalPoint(String cardinalPoint) {
		this.cardinalPoint = cardinalPoint;
	}
	
	public void setSpacecraftRepository(SpacecraftRepository spacecraftRepository) {
		this.spacecraftRepository = spacecraftRepository;
	}
	
	
	public static class SpacecrafBuilder {
		
		private Integer coordinateX;
		
		private Integer coordinateY;
		
		private String cardinalPoint;
		
		private List<String> commands;
		
		private Plateau plateau;
		
		
		private SpacecrafBuilder(){}
		
		public static SpacecrafBuilder create() {
			return new SpacecrafBuilder();
		}
		
		
		public SpacecrafBuilder coordinateX(Integer coordinateX) {
			
			this.coordinateX = coordinateX;
			
			return this;
		}
		
		public SpacecrafBuilder coordinateY(Integer coordinateY) {
			
			this.coordinateY = coordinateY;
			
			return this;
		}
		
		public SpacecrafBuilder cardinalPoint(String cardinalPoint) {
			
			this.cardinalPoint = cardinalPoint;
			
			return this;
		}
		
		public SpacecrafBuilder commands(List<String> commands) {
			
			this.commands = commands;
			
			return this;
		}
		
		public SpacecrafBuilder plateau(Plateau plateau) {
			
			this.plateau = plateau;
			
			return this;
		}
		
		public Spacecraft build() {
			return new Spacecraft(coordinateX, coordinateY, 
					cardinalPoint, commands, plateau);
		}
		
	}
	
}

