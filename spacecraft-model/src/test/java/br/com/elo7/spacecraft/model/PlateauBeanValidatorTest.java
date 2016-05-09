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

@RunWith(value = Parameterized.class)
public class PlateauBeanValidatorTest {
	
	private String message;
	
	private int errors;
	
	private List<?> validators;
	
	private Plateau plateau;
	
	private static BeanValidator beanValidator;
	
	@BeforeClass
	public static void beforeClass() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
		        .configure()
		        .buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		beanValidator = new BeanValidator(validator);
	}
	
	public PlateauBeanValidatorTest(String message, int errors, List<?> validators, Plateau plateau) {
		this.message = message;
		this.errors = errors;
		this.validators = validators;
		this.plateau = plateau;
	}
	
	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> data() {
		
		Object[][] data = new Object[][] {
			
			{"mustValidatePlateau", 0,
				asList(ExecuteCommands.class),
				PlateauBuilder.create().
				upperRightX(5).
				upperRightY(5).
				build()
			},
			
			{"mustValidatePlateauWithUpperRightXNull", 1,
				asList(ExecuteCommands.class),
				PlateauBuilder.create().
				upperRightY(5).
				build()
			},
			
			{"mustValidatePlateauWithUpperRightYNull", 1,
				asList(ExecuteCommands.class),
				PlateauBuilder.create().
				upperRightX(5).
				build()
			}
			
		};
		
		return asList(data);
	}
	
	@Test
	public void mustValidatePlateau() throws Exception {
		try {
			beanValidator.validate(plateau, validators.toArray(new Class[validators.size()]));
		} catch (BeanValidatorException e) {
			assertEquals(format("%s: %s", message, e.getErrors().toString()), errors, e.getErrors().size());
		}
	}
	
}
