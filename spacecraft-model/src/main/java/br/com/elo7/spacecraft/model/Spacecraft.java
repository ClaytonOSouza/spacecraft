package br.com.elo7.spacecraft.model;

import java.util.List;

public class Spacecraft {
	
	private Integer coordinateX;
	
	private Integer coordinateY;
	
	private String cardinalPoint;
	
	private List<String> commands;
	
	public Spacecraft(Integer coordinateX, Integer coordinateY, String cardinalPoint, List<String> commands) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.cardinalPoint = cardinalPoint;
		this.commands = commands;
	}
	
	public Spacecraft(Integer coordinateX, Integer coordinateY, String cardinalPoint) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.cardinalPoint = cardinalPoint;
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
	
}
