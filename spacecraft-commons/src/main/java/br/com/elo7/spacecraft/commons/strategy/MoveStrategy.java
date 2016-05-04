package br.com.elo7.spacecraft.commons.strategy;

import java.util.HashMap;
import java.util.Map;

public enum MoveStrategy {
	
	North("N") {
		
		@Override
		public void toMove(Integer x, Integer y) {
			
			coordinateX = x;
			coordinateY = ++y;
		}
	},
	
	East("E") {
		
		@Override
		public void toMove(Integer x, Integer y) {
			
			coordinateY = y;
			coordinateX = ++x;
		}
	},
	
	South("S") {
		
		@Override
		public void toMove(Integer x, Integer y) {
			
			coordinateX = x;
			coordinateY = --y;
		}
	},
	
	West("W") {
		
		@Override
		public void toMove(Integer x, Integer y) {
			
			coordinateY = y;
			coordinateX = --x;
		}
	};
	
	private static Integer coordinateX;
	
	private static Integer coordinateY;
	
	private String cardinalPoint;
	
	private static Map<String, MoveStrategy> map = new HashMap<>();
	
	static {
		
		for (MoveStrategy moveStrategy : values()) {
			map.put(moveStrategy.getCardinalPoint().toUpperCase(), moveStrategy);
			map.put(moveStrategy.getCardinalPoint().toLowerCase(), moveStrategy);
		}
	}
	
	private MoveStrategy(String cardinalPoint) {
		this.cardinalPoint = cardinalPoint;
	}
	
	public abstract void toMove(Integer x, Integer y);
	
	public Integer getCoordinateX() {
		return coordinateX;
	}
	
	public Integer getCoordinateY() {
		return coordinateY;
	}
	
	public static MoveStrategy getMoveStrategy(String cardinalPoint) {
		return map.get(cardinalPoint);
	}
	
	private String getCardinalPoint() {
		return cardinalPoint;
	}
}
