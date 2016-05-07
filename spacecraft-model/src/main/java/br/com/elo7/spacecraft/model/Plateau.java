package br.com.elo7.spacecraft.model;

public class Plateau {

	private Integer upperRightX;
	
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
