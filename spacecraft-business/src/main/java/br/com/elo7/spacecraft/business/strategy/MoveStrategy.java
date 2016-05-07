package br.com.elo7.spacecraft.business.strategy;

import java.util.HashMap;
import java.util.Map;

import br.com.elo7.spacecraft.model.Spacecraft;

public enum MoveStrategy {
	
	North("N") {
		
		@Override
		public void toMove(Spacecraft spacecraft) {
			
			int coordinateY = spacecraft.getCoordinateY();
			
			spacecraft.setCoordinateY(++coordinateY);
		}
	},
	
	East("E") {
		
		@Override
		public void toMove(Spacecraft spacecraft) {
			
			int coordinateX = spacecraft.getCoordinateX();
			
			spacecraft.setCoordinateX(++coordinateX);
		}
	},
	
	South("S") {
		
		@Override
		public void toMove(Spacecraft spacecraft) {
			
			int coordinateY = spacecraft.getCoordinateY();
			
			spacecraft.setCoordinateY(--coordinateY);
		}
	},
	
	West("W") {
		
		@Override
		public void toMove(Spacecraft spacecraft) {
			
			int coordinateX = spacecraft.getCoordinateX();
			
			spacecraft.setCoordinateX(--coordinateX);
		}
	};
	
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
	
	public abstract void toMove(Spacecraft spacecraft);
	
	public static MoveStrategy getMoveStrategy(String cardinalPoint) {
		return map.get(cardinalPoint);
	}
	
	private String getCardinalPoint() {
		return cardinalPoint;
	}
	
}
