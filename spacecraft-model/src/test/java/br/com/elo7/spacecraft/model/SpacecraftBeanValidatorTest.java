package br.com.elo7.spacecraft.model;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import br.com.elo7.spacecraft.commons.exception.BeanValidatorException;
import br.com.elo7.spacecraft.commons.validation.BeanValidator;
import br.com.elo7.spacecraft.commons.validation.groups.ExecuteCommands;
import br.com.elo7.spacecraft.model.Plateau.PlateauBuilder;
import br.com.elo7.spacecraft.model.Spacecraft.SpacecrafBuilder;

@RunWith(value = Parameterized.class)
public class SpacecraftBeanValidatorTest {
	
	private String message;
	
	private int errors;
	
	private List<?> validators;
	
	private Spacecraft spacecraft;
	
	private static BeanValidator beanValidator;
	
	@BeforeClass
	public static void beforeClass() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
		        .configure()
		        .buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		beanValidator = new BeanValidator(validator);
	}
	
	public SpacecraftBeanValidatorTest(String message, int errors, List<?> validators, Spacecraft spacecraft) {
		this.message = message;
		this.errors = errors;
		this.validators = validators;
		this.spacecraft = spacecraft;
	}
	
	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> data() {
		
		Plateau plateau = PlateauBuilder.create().upperRightX(5).upperRightY(5).build();
		
		Object[][] data = new Object[][] {
			
			{"mustValidateSpacecraft", 0,
				asList(ExecuteCommands.class),
				SpacecrafBuilder.create().
				coordinateX(3).
				coordinateY(4).
				cardinalPoint("N").
				commands(asList("L","M","R","M")).
				plateau(plateau).
				build()
			},
			
			{"mustValidateSpacecraftWithCoordinateXNull", 1,
				asList(ExecuteCommands.class),
				SpacecrafBuilder.create().
				coordinateY(4).
				cardinalPoint("N").
				commands(asList("L","M","R","M")).
				plateau(plateau).
				build()
			},
			
			{"mustValidateSpacecraftWithCoordinateYNull", 1,
				asList(ExecuteCommands.class),
				SpacecrafBuilder.create().
				coordinateX(3).
				cardinalPoint("N").
				commands(asList("L","M","R","M")).
				plateau(plateau).
				build()
			},
			
			{"mustValidateSpacecraftWithCardinalPointNull", 1,
				asList(ExecuteCommands.class),
				SpacecrafBuilder.create().
				coordinateX(3).
				coordinateY(4).
				commands(asList("L","M","R","M")).
				plateau(plateau).
				build()
			},
			
			{"mustValidateSpacecraftWithEmptyCardinalPoint", 1,
				asList(ExecuteCommands.class),
				SpacecrafBuilder.create().
				coordinateX(3).
				coordinateY(4).
				cardinalPoint("").
				commands(asList("L","M","R","M")).
				plateau(plateau).
				build()
			},
			
			{"mustValidateSpacecraftWithCommandsNull", 1,
				asList(ExecuteCommands.class),
				SpacecrafBuilder.create().
				coordinateX(3).
				coordinateY(4).
				cardinalPoint("N").
				plateau(plateau).
				build()
			},
			
			{"mustValidateSpacecraftWithEmptyCommands", 1,
				asList(ExecuteCommands.class),
				SpacecrafBuilder.create().
				coordinateX(3).
				coordinateY(4).
				cardinalPoint("N").
				commands(asList()).
				plateau(plateau).
				build()
			},
			
			{"mustValidateSpacecraftWithPlateauNull", 1,
				asList(ExecuteCommands.class),
				SpacecrafBuilder.create().
				coordinateX(3).
				coordinateY(4).
				cardinalPoint("N").
				commands(asList("L","M","R","M")).
				plateau(null).
				build()
			}
			
		};
		
		return asList(data);
	}
	
	@Test
	public void mustValidateSpacecraft() throws Exception {
		try {
			beanValidator.validate(spacecraft, validators.toArray(new Class[validators.size()]));
		} catch (BeanValidatorException e) {
			assertEquals(format("%s: %s", message, e.getErrors().toString()), errors, e.getErrors().size());
		}
	}
	
}
