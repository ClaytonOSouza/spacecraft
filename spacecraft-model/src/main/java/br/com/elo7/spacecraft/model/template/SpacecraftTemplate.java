package br.com.elo7.spacecraft.model.template;

import java.util.Arrays;

import br.com.elo7.spacecraft.model.Plateau;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class SpacecraftTemplate {
	
	public static final String VALIDATE_COORDINATE_X = "VALIDATE_COORDINATE_X";
	
	public static final String VALIDATE_COORDINATE_Y = "VALIDATE_COORDINATE_Y";
	
	public static final String VALIDATE_CARDINAL_POINT = "VALIDATE_CARDINAL_POINT";
	
	public static final String VALIDATE_UPPER_RIGHT_X = "VALIDATE_UPPER_RIGHT_X";
	
	public static final String VALIDATE_LOWER_LEFT_X = "VALIDATE_LOWER_LEFT_X";
	
	public static final String VALIDATE_UPPER_RIGHT_Y = "VALIDATE_UPPER_RIGHT_Y";
	
	public static final String VALIDATE_LOWER_LEFT_Y = "VALIDATE_LOWER_LEFT_Y";
	
	public static final String VALIDATE_CRASH = "VALIDATE_CRASH";
	
	public static void load() {
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_COORDINATE_X,
		new Rule() {{
			
			add("coordinateX", 1);
			add("coordinateY", 2);
			add("cardinalPoint", "N");
			add("commands", Arrays.asList("L", "M", "L", "M", "L", "M", "L", "M", "M"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_5_5));
			
		}});
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_COORDINATE_Y,
		new Rule() {{
			
			add("coordinateX", 3);
			add("coordinateY", 3);
			add("cardinalPoint", "E");
			add("commands", Arrays.asList("M", "M", "R", "M", "M", "R", "M", "R", "R", "M"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_5_5));
			
		}});
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_CARDINAL_POINT, 
		new Rule(){{
			
			add("coordinateX", 2);
			add("coordinateY", 4);
			add("cardinalPoint", "W");
			add("commands", Arrays.asList("L", "L", "M", "M", "R", "M", "L", "M", "R", "M", "M"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_5_5));
			
		}});
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_UPPER_RIGHT_X, 
		new Rule(){{
			
			add("coordinateX", 3);
			add("coordinateY", 2);
			add("cardinalPoint", "S");
			add("commands", Arrays.asList("L", "M", "M", "L", "M", "R", "M", "R", "M", "L", "M", "R"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_6_6));
			
		}});
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_UPPER_RIGHT_X, 
		new Rule(){{
			
			add("coordinateX", 3);
			add("coordinateY", 2);
			add("cardinalPoint", "S");
			add("commands", Arrays.asList("L", "M", "M", "L", "M", "R", "M", "R", "M", "L", "M", "R"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_6_6));
			
		}});
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_LOWER_LEFT_X, 
		new Rule(){{
			
			add("coordinateX", 1);
			add("coordinateY", 3);
			add("cardinalPoint", "N");
			add("commands", Arrays.asList("L", "M", "R", "M", "L", "M"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_3_4));
			
		}});
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_UPPER_RIGHT_Y, 
		new Rule(){{
			
			add("coordinateX", 0);
			add("coordinateY", 0);
			add("cardinalPoint", "W");
			add("commands", Arrays.asList("L", "L", "M", "M", "L", "M", "M", "R", "M", "L", "L", "M", "R", "M", "M"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_4_3));
			
		}});
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_LOWER_LEFT_Y, 
		new Rule(){{
			
			add("coordinateX", 4);
			add("coordinateY", 2);
			add("cardinalPoint", "W");
			add("commands", Arrays.asList("R", "L", "L", "M", "M", "R", "M", "L", "M"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_3_4));
			
		}});
		
		Fixture.of(Spacecraft.class).addTemplate(VALIDATE_CRASH, 
		new Rule(){{
			
			add("coordinateX", 2);
			add("coordinateY", 4);
			add("cardinalPoint", "W");
			add("commands", Arrays.asList("L", "L", "M", "M", "R", "M", "L", "M", "R", "M", "M"));
			add("plateau", one(Plateau.class, PlateauTemplate.PLATEAU_5_5));
			
		}});
		
	}
}
