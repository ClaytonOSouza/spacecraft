package br.com.elo7.spacecraft.business;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.elo7.spacecraft.business.impl.SpacecraftBOImpl;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.Spacecraft.SpacecrafBuilder;

public class SpacecraftBOTest {
	
	private SpacecraftBO spacecraftBO;
	
	@Before
	public void before() {
		spacecraftBO = new SpacecraftBOImpl();
	}
	
	@Test
	public void mustValidateCoordinateX() throws Exception {
		
		List<String> commands = Arrays.asList("L", "M", "L", "M", "L", "M", "L", "M", "M");
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
											coordinateX(1).
											coordinateY(2).
											cardinalPoint("N").
											commands(commands).
											build();
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(spacecraft);
		
		assertThat(spacecraftReturn.getCoordinateX(), equalTo(1));
	}
	
	@Test
	public void mustValidateCoordinateY() throws Exception {
		
		List<String> commands = Arrays.asList("M", "M", "R", "M", "M", "R", "M", "R", "R", "M");
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
											coordinateX(3).
											coordinateY(3).
											cardinalPoint("E").
											commands(commands).
											build();
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(spacecraft);
		
		assertThat(spacecraftReturn.getCoordinateY(), equalTo(1));
	}
	
	@Test
	public void mustValidateCardinalPoint() throws Exception {
		
		List<String> commands = Arrays.asList("L", "L", "M", "M", "R", "M", "L", "M", "R", "M", "M");
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
											coordinateX(2).
											coordinateY(4).
											cardinalPoint("W").
											commands(commands).
											build();
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(spacecraft);
		
		assertThat(spacecraftReturn.getCardinalPoint(), equalTo("S"));
	}
	
}
