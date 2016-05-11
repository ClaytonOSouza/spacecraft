package br.com.elo7.spacecraft.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.elo7.spacecraft.commons.exception.CoordinateException;
import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.commons.validation.groups.ExecuteCommands;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Spacecraft implements Serializable {
	
	private static final Logger LOG = LoggerFactory.getLogger(Spacecraft.class);
	
	private static final long serialVersionUID = -7040631087110362298L;
	
	
	@NotNull(groups={ExecuteCommands.class})
	private Integer coordinateX;
	
	@NotNull(groups={ExecuteCommands.class})
	private Integer coordinateY;
	
	@NotBlank(groups={ExecuteCommands.class})
	private String cardinalPoint;
	
	@JsonIgnore
	@NotEmpty(groups={ExecuteCommands.class})
	private List<String> commands;
	
	@JsonIgnore
	@Valid
	private Plateau plateau;
	
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
	
	@JsonIgnore
	public void verifyPlateuCoordinates() {
		
		if(coordinateX > plateau.getUpperRightX() || coordinateX < 0) {
			LOG.error("Bad Request. Invalid coordinates - errorCode:{}", ErrorExceptionCode.COORDINATE_ERROR.getCode());
			throw new CoordinateException(ErrorExceptionCode.COORDINATE_ERROR);
		}
		
		if(coordinateY > plateau.getUpperRightY() || coordinateY < 0) {
			LOG.error("Bad Request. Invalid coordinates - errorCode:{}", ErrorExceptionCode.COORDINATE_ERROR.getCode());
			throw new CoordinateException(ErrorExceptionCode.COORDINATE_ERROR);
		}
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

