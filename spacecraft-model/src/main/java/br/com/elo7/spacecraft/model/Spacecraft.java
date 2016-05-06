package br.com.elo7.spacecraft.model;

import java.util.List;

import br.com.elo7.spacecraft.commons.strategy.MoveStrategy;
import br.com.elo7.spacecraft.model.repository.SpacecraftRepository;

public class Spacecraft {
	
	private Integer coordinateX;
	
	private Integer coordinateY;
	
	private String cardinalPoint;
	
	private List<String> commands;
	
	private Plateau plateau;
	
	private SpacecraftRepository spacecraftRepository;
	
	public Spacecraft(){}
	
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
	
	public void move(){
		
		MoveStrategy move = MoveStrategy.getMoveStrategy(cardinalPoint);
		
		move.toMove(coordinateX, coordinateY);
		
		this.coordinateX = move.getCoordinateX();
		this.coordinateY = move.getCoordinateY();
		
		this.verifyPlateuCoordinates();
	}
	
	private void verifyPlateuCoordinates() {
		
		if(coordinateX > plateau.getUpperRightX() || coordinateX < 0) {
			//TODO: implementar exception especializada
			throw new RuntimeException();
		}
		
		if(coordinateY > plateau.getUpperRightY() || coordinateY < 0) {
			//TODO: implementar exception especializada
			throw new RuntimeException();
		}
	}
	
	public void left() {
		
		WindRose windRose = WindRose.getWindRose(cardinalPoint);
		
		this.cardinalPoint = windRose.getLeft();
	}
	
	public void right() {
		
		WindRose windRose = WindRose.getWindRose(cardinalPoint);
		
		this.cardinalPoint = windRose.getRight();
	}
	
	public Spacecraft getSpacecraftByCoordenates() {
		return spacecraftRepository.getSpacecraftByCoordenates(this); 
	}
	
	public void persist() {
		spacecraftRepository.persist(this);
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

