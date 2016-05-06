package br.com.elo7.spacecraft.model.template;

import br.com.elo7.spacecraft.model.Plateau;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class PlateauTemplate {
	
	public static final String PLATEAU_5_5 = "PLATEAU_5_5";
	
	public static final String PLATEAU_6_6 = "PLATEAU_6_6";
	
	public static final String PLATEAU_3_4 = "PLATEAU_3_4";
	
	public static final String PLATEAU_4_3 = "PLATEAU_4_3";
	
	public static void load() {
		
		Fixture.of(Plateau.class).addTemplate(PLATEAU_5_5,
		new Rule() {{
			
			add("upperRightX", 5);
			add("upperRightY", 5);
			
		}});
		
		Fixture.of(Plateau.class).addTemplate(PLATEAU_6_6,
		new Rule() {{
			
			add("upperRightX", 6);
			add("upperRightY", 6);
			
		}});
		
		Fixture.of(Plateau.class).addTemplate(PLATEAU_3_4,
		new Rule() {{
			
			add("upperRightX", 3);
			add("upperRightY", 4);
			
		}});
		
		Fixture.of(Plateau.class).addTemplate(PLATEAU_4_3,
		new Rule() {{
			
			add("upperRightX", 4);
			add("upperRightY", 3);
			
		}});
	}
}
