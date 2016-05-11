package br.com.elo7.spacecraft.services.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import br.com.elo7.spacecraft.commons.exception.InternalServerErrorException;

@Component
@Provider
public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalServerErrorException> {

	@Override
	public Response toResponse(InternalServerErrorException exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaTypeExtends.APPLICATION_JSON_UTF8).entity(exception.getErrors()).build();
	}

}
