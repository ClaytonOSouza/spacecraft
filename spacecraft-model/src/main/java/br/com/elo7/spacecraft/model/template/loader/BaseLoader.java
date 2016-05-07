package br.com.elo7.spacecraft.model.template.loader;

import br.com.elo7.spacecraft.model.template.PlateauTemplate;
import br.com.elo7.spacecraft.model.template.SpacecraftTemplate;

public class BaseLoader {
	
	public static void loadTemplatesForFixture() {
		SpacecraftTemplate.load();
		PlateauTemplate.load();
	}
	
}
