package br.com.elo7.spacecraft.business;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.elo7.spacecraft.business.impl.SpacecraftBOImpl;
import br.com.elo7.spacecraft.model.Spacecraft;

public class SpacecraftBOTest {
	
	private SpacecraftBO spacecraftBO;
	
	@Before
	public void before() {
		spacecraftBO = new SpacecraftBOImpl();
	}
	
	@Test
	public void mustDoMoveSpacecraft() throws Exception {
		
		List<String> commands = Arrays.asList("L", "M", "L", "M", "L", "M", "L", "M", "M");
		
		Spacecraft spacecraft = new Spacecraft(1,2,"N",commands);
		
		Spacecraft spacecraftReturn = spacecraftBO.move(spacecraft);
		
		assertTrue(this.verifyMustDoMoveSpacecraft(spacecraftReturn));
	}

	private boolean verifyMustDoMoveSpacecraft(Spacecraft spacecraftReturn) {
		
		boolean verify = spacecraftReturn.getCoordinateX() == 1 &&
				spacecraftReturn.getCoordinateY() == 3 &&
				"N".equalsIgnoreCase(spacecraftReturn.getCardinalPoint());
		
		return verify;
	}
	
}
