package br.com.elo7.spacecraft.model;

import java.util.List;

public class Spacecraft {
	
	private Integer coordinateX;
	
	private Integer coordinateY;
	
	private String cardinalPoint;
	
	private List<String> commands;
	
	
	private Spacecraft(Integer coordinateX, Integer coordinateY, String cardinalPoint, List<String> commands) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.cardinalPoint = cardinalPoint;
		this.commands = commands;
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
	
	public void move(){
		
		if("N".equalsIgnoreCase(cardinalPoint)) {
			coordinateY++; 
		}
		
		if("E".equalsIgnoreCase(cardinalPoint)) {
			coordinateX++;
		}
		
		if("S".equalsIgnoreCase(cardinalPoint)) {
			coordinateY--;
		}
		
		if("W".equalsIgnoreCase(cardinalPoint)) {
			coordinateX--;
		}
		
	}
	
	public void left() {
		
		if("N".equalsIgnoreCase(cardinalPoint)) {
			cardinalPoint = "W";
		
		} else if("W".equalsIgnoreCase(cardinalPoint)) {
			cardinalPoint = "S";
		
		} else if("S".equalsIgnoreCase(cardinalPoint)) {
			cardinalPoint = "E";
		
		} else if("E".equalsIgnoreCase(cardinalPoint)) {
			cardinalPoint = "N";
		}
		
	}
	
	public void right() {
		
		if("N".equalsIgnoreCase(cardinalPoint)) {
			cardinalPoint = "E";
		
		} else if("E".equalsIgnoreCase(cardinalPoint)) {
			cardinalPoint = "S";
		
		} else if("S".equalsIgnoreCase(cardinalPoint)) {
			cardinalPoint = "W";
		
		} else if("W".equalsIgnoreCase(cardinalPoint)) {
			cardinalPoint = "N";
		}
	}
	
	public static class SpacecrafBuilder {
		
		private Integer coordinateX;
		
		private Integer coordinateY;
		
		private String cardinalPoint;
		
		private List<String> commands;
		
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
		
		public Spacecraft build() {
			return new Spacecraft(coordinateX, coordinateY, cardinalPoint, commands);
		}
		
	}
	
}

