package br.com.elo7.spacecraft.model.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpacecraftRequest implements Serializable {
	
	private static final long serialVersionUID = -4946748875416788318L;
	
	private Integer coordinateX;
	
	private Integer coordinateY;
	
	private String cardinalPoint;
	
	private String commands;
	
	private Integer upperRightX;
	
	private Integer upperRightY;
	
	
	public SpacecraftRequest() {}
	
	
	public Integer getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(Integer coordinateX) {
		this.coordinateX = coordinateX;
	}

	public Integer getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(Integer coordinateY) {
		this.coordinateY = coordinateY;
	}

	public String getCardinalPoint() {
		return cardinalPoint;
	}

	public void setCardinalPoint(String cardinalPoint) {
		this.cardinalPoint = cardinalPoint;
	}

	public String getCommands() {
		return commands;
	}

	public void setCommands(String commands) {
		this.commands = commands;
	}

	public Integer getUpperRightX() {
		return upperRightX;
	}

	public void setUpperRightX(Integer upperRightX) {
		this.upperRightX = upperRightX;
	}

	public Integer getUpperRightY() {
		return upperRightY;
	}

	public void setUpperRightY(Integer upperRightY) {
		this.upperRightY = upperRightY;
	}
	
}
