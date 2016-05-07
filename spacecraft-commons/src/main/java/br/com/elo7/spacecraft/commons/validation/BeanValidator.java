package br.com.elo7.spacecraft.commons.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.elo7.spacecraft.commons.exception.BeanValidatorException;

@Component
public class BeanValidator {
	
	@Autowired
	private Validator validator;
	
	public BeanValidator(){}
	
	public void validate(Object object, Class<?>... groups) throws BeanValidatorException {
		checkConstraints(object, false, groups);
	}
	
	private void checkConstraints(Object object,boolean useDefault, Class<?>... groups) {
		
		Set<ConstraintViolation<Object>> validate = this.validator.validate(object, groups);
		
		if (!validate.isEmpty()) {
			//LOG.error("Bad Request - groups: {}", Arrays.asList(groups).toString());
			throw new BeanValidatorException(validate);
		}
	}
	
}
