package br.com.elo7.spacecraft.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import br.com.elo7.spacecraft.commons.validation.groups.ExecuteCommands;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plateau implements Serializable {
	
	private static final long serialVersionUID = -4057145436631509328L;
	
	
	@NotNull(groups={ExecuteCommands.class})
	private Integer upperRightX;
	
	@NotNull(groups={ExecuteCommands.class})
	private Integer upperRightY;
	
	private Plateau(Integer upperRightX, Integer upperRightY) {
		this.upperRightX = upperRightX;
		this.upperRightY = upperRightY;
	}
	
	public Integer getUpperRightX() {
		return upperRightX;
	}

	public Integer getUpperRightY() {
		return upperRightY;
	}

	public static class PlateauBuilder {
		
		private Integer upperRightX;
		
		private Integer upperRightY;
		
		private PlateauBuilder(){}
		
		public static PlateauBuilder create() {
			return new PlateauBuilder();
		}
		
		public PlateauBuilder upperRightX(Integer upperRightX) {
			
			this.upperRightX = upperRightX;
			
			return this;
		}
		
		public PlateauBuilder upperRightY(Integer upperRightY) {
			
			this.upperRightY = upperRightY;
			
			return this;
		}
		
		public Plateau build() {
			return new Plateau(upperRightX, upperRightY);
		}
		
	}
	
}
