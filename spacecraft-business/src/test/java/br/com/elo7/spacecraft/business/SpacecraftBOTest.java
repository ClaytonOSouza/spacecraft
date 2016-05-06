package br.com.elo7.spacecraft.business;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import br.com.elo7.spacecraft.business.impl.SpacecraftBOImpl;
import br.com.elo7.spacecraft.model.Plateau;
import br.com.elo7.spacecraft.model.Plateau.PlateauBuilder;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.SpacecraftRepository;
import br.com.elo7.spacecraft.model.Spacecraft.SpacecrafBuilder;

public class SpacecraftBOTest {
	
	@Spy
	private SpacecraftBOImpl spacecraftBO;
	
	@Mock
	private SpacecraftRepository spacecraftRepository;
	
	
	@Before
	public void before() {
		initMocks(this);
		spacecraftBO.setSpacecraftRepository(spacecraftRepository);
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
		
		Plateau plateu = PlateauBuilder.create().
										upperRightX(5).
										upperRightY(5).
										build();
		
		when(spacecraftRepository.getSpacecraftByCoordenates(spacecraft)).thenReturn(null);
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(plateu,spacecraft);
		
		assertThat(spacecraftReturn.getCoordinateX(), equalTo(1));
		verify(spacecraftRepository, times(1)).persist(spacecraft);
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
		
		Plateau plateu = PlateauBuilder.create().
										upperRightX(5).
										upperRightY(5).
										build();
		
		when(spacecraftRepository.getSpacecraftByCoordenates(spacecraft)).thenReturn(null);
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(plateu, spacecraft);
		
		assertThat(spacecraftReturn.getCoordinateY(), equalTo(1));
		verify(spacecraftRepository, times(1)).persist(spacecraft);
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
		
		Plateau plateu = PlateauBuilder.create().
										upperRightX(5).
										upperRightY(5).
										build();
		
		when(spacecraftRepository.getSpacecraftByCoordenates(spacecraft)).thenReturn(null);
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(plateu, spacecraft);
		
		assertThat(spacecraftReturn.getCardinalPoint(), equalTo("S"));
		verify(spacecraftRepository, times(1)).persist(spacecraft);
	}
	
	@Test(expected = RuntimeException.class)
	public void mustValidateUpperRightX() throws Exception {
		
		List<String> commands = Arrays.asList("L", "M", "M", "L", "M", "R", "M", "R", "M", "L", "M", "R");
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
											coordinateX(3).
											coordinateY(2).
											cardinalPoint("S").
											commands(commands).
											build();
		
		Plateau plateu = PlateauBuilder.create().
										upperRightX(6).
										upperRightY(6).
										build();
		
		spacecraftBO.executeCommands(plateu, spacecraft);
	}
	
	@Test(expected = RuntimeException.class)
	public void mustValidateLowerLeftX() throws Exception {
		
		List<String> commands = Arrays.asList("L", "M", "R", "M", "L", "M");
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
											coordinateX(1).
											coordinateY(3).
											cardinalPoint("N").
											commands(commands).
											build();
		
		Plateau plateu = PlateauBuilder.create().
										upperRightX(3).
										upperRightY(4).
										build();
		
		spacecraftBO.executeCommands(plateu, spacecraft);
	}
	
	@Test(expected = RuntimeException.class)
	public void mustValidateUpperRightY() throws Exception {
		
		List<String> commands = Arrays.asList("L", "L", "M", "M", "L", "M", "M", "R", "M", "L", "L", "M", "R", "M", "M");
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
											coordinateX(0).
											coordinateY(0).
											cardinalPoint("W").
											commands(commands).
											build();
		
		Plateau plateu = PlateauBuilder.create().
										upperRightX(4).
										upperRightY(3).
										build();
		
		spacecraftBO.executeCommands(plateu, spacecraft);
	}
	
	@Test(expected = RuntimeException.class)
	public void mustValidateLowerLeftY() throws Exception {
		
		List<String> commands = Arrays.asList("R", "L", "L", "M", "M", "R", "M", "L", "M");
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
											coordinateX(4).
											coordinateY(2).
											cardinalPoint("W").
											commands(commands).
											build();
		
		Plateau plateu = PlateauBuilder.create().
										upperRightX(4).
										upperRightY(3).
										build();
		
		spacecraftBO.executeCommands(plateu, spacecraft);
	}
	
	@Test(expected = RuntimeException.class)
	public void mustValidateCrash() throws Exception {
		
		List<String> commands = Arrays.asList("L", "L", "M", "M", "R", "M", "L", "M", "R", "M", "M");
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
											coordinateX(2).
											coordinateY(4).
											cardinalPoint("W").
											commands(commands).
											build();
		
		Plateau plateu = PlateauBuilder.create().
										upperRightX(5).
										upperRightY(5).
										build();
		
		when(spacecraftRepository.getSpacecraftByCoordenates(spacecraft)).thenReturn(spacecraft);
		
		spacecraftBO.executeCommands(plateu, spacecraft);
	}
	
}
