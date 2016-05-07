package br.com.elo7.spacecraft.business;

import static br.com.elo7.spacecraft.model.template.loader.BaseLoader.loadTemplatesForFixture;
import static br.com.six2six.fixturefactory.Fixture.from;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import br.com.elo7.spacecraft.business.impl.SpacecraftBOImpl;
import br.com.elo7.spacecraft.commons.exception.CoordinateException;
import br.com.elo7.spacecraft.commons.exception.CrashException;
import br.com.elo7.spacecraft.commons.validation.BeanValidator;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.repository.SpacecraftRepository;
import br.com.elo7.spacecraft.model.template.SpacecraftTemplate;

public class SpacecraftBOTest {
	
	@Spy
	private SpacecraftBOImpl spacecraftBO;
	
	@Mock
	private SpacecraftRepository spacecraftRepository;
	
	@Mock
	private BeanValidator beanValidator;
	
	@Before
	public void before() {
		loadTemplatesForFixture();
		initMocks(this);
		spacecraftBO.setSpacecraftRepository(spacecraftRepository);
		spacecraftBO.setBeanValidator(beanValidator);
	}
	
	@Test
	public void mustValidateCoordinateX() throws Exception {
		
		Spacecraft spacecraft = from(Spacecraft.class).gimme(SpacecraftTemplate.VALIDATE_COORDINATE_X);
		
		when(spacecraftRepository.getSpacecraftByCoordenates(spacecraft)).thenReturn(null);
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(spacecraft);
		
		assertThat(spacecraftReturn.getCoordinateX(), equalTo(1));
		verify(spacecraftRepository, times(1)).persist(spacecraft);
	}
	
	@Test
	public void mustValidateCoordinateY() throws Exception {
		
		Spacecraft spacecraft = from(Spacecraft.class).gimme(SpacecraftTemplate.VALIDATE_COORDINATE_Y);
		
		when(spacecraftRepository.getSpacecraftByCoordenates(spacecraft)).thenReturn(null);
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(spacecraft);
		
		assertThat(spacecraftReturn.getCoordinateY(), equalTo(1));
		verify(spacecraftRepository, times(1)).persist(spacecraft);
	}
	
	@Test
	public void mustValidateCardinalPoint() throws Exception {
		
		Spacecraft spacecraft = from(Spacecraft.class).gimme(SpacecraftTemplate.VALIDATE_CARDINAL_POINT);
		
		when(spacecraftRepository.getSpacecraftByCoordenates(spacecraft)).thenReturn(null);
		
		Spacecraft spacecraftReturn = spacecraftBO.executeCommands(spacecraft);
		
		assertThat(spacecraftReturn.getCardinalPoint(), equalTo("S"));
		verify(spacecraftRepository, times(1)).persist(spacecraft);
	}
	
	@Test(expected = CoordinateException.class)
	public void mustValidateUpperRightX() throws Exception {
		
		Spacecraft spacecraft = from(Spacecraft.class).gimme(SpacecraftTemplate.VALIDATE_UPPER_RIGHT_X);
		
		spacecraftBO.executeCommands(spacecraft);
	}
	
	@Test(expected = CoordinateException.class)
	public void mustValidateLowerLeftX() throws Exception {
		
		Spacecraft spacecraft = from(Spacecraft.class).gimme(SpacecraftTemplate.VALIDATE_LOWER_LEFT_X);
		
		spacecraftBO.executeCommands(spacecraft);
	}
	
	@Test(expected = CoordinateException.class)
	public void mustValidateUpperRightY() throws Exception {
		
		Spacecraft spacecraft = from(Spacecraft.class).gimme(SpacecraftTemplate.VALIDATE_UPPER_RIGHT_Y);
		
		spacecraftBO.executeCommands(spacecraft);
	}
	
	@Test(expected = CoordinateException.class)
	public void mustValidateLowerLeftY() throws Exception {
		
		Spacecraft spacecraft = from(Spacecraft.class).gimme(SpacecraftTemplate.VALIDATE_LOWER_LEFT_Y);
		
		spacecraftBO.executeCommands(spacecraft);
	}
	
	@Test(expected = CrashException.class)
	public void mustValidateCrash() throws Exception {
		
		Spacecraft spacecraft = from(Spacecraft.class).gimme(SpacecraftTemplate.VALIDATE_CRASH);
		
		when(spacecraftRepository.getSpacecraftByCoordenates(spacecraft)).thenReturn(spacecraft);
		
		spacecraftBO.executeCommands(spacecraft);
	}
	
}
