package br.com.elo7.spacecraft.business.strategy;

import java.util.HashMap;
import java.util.Map;

import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.WindRose;

public enum CommandsStrategy {

	Move("M") {
		
		@Override
		public void executeCommand(Spacecraft spacecraft) {
			
			MoveStrategy move = MoveStrategy.getMoveStrategy(spacecraft.getCardinalPoint());
			
			move.toMove(spacecraft);
			
			spacecraft.verifyPlateuCoordinates();
		}
		
	},
	
	Left("L") {

		@Override
		public void executeCommand(Spacecraft spacecraft) {
			
			WindRose windRose = WindRose.getWindRose(spacecraft.getCardinalPoint());
			
			spacecraft.setCardinalPoint(windRose.getLeft());
		}
		
	},
	
	Right("R") {
		
		@Override
		public void executeCommand(Spacecraft spacecraft) {
			
			WindRose windRose = WindRose.getWindRose(spacecraft.getCardinalPoint());
			
			spacecraft.setCardinalPoint(windRose.getRight());
		}
		
	};
	
	private String command;
	
	private static Map<String, CommandsStrategy> map = new HashMap<>();
	
	static {
		
		for(CommandsStrategy commands : values()) {
			map.put(commands.getCommand().toUpperCase(), commands);
			map.put(commands.getCommand().toLowerCase(), commands);
		}
		
	}
	
	public abstract void executeCommand(Spacecraft spacecraft);
	
	private CommandsStrategy(String command) {
		this.command = command;
	}
	
	private String getCommand() {
		return command;
	}
	
	public static CommandsStrategy getCommandsStrategy(String command) {
		return map.get(command);
	}
	
}
