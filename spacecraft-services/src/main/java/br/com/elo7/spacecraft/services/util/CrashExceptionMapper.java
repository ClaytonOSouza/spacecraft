package br.com.elo7.spacecraft.services.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import br.com.elo7.spacecraft.commons.exception.CrashException;

@Component
@Provider
public class CrashExceptionMapper implements ExceptionMapper<CrashException> {
	
	@Override
	public Response toResponse(CrashException exception) {
		return Response.status(Status.BAD_REQUEST).type(MediaTypeExtends.APPLICATION_JSON_UTF8).entity(exception.getErrors()).build();
	}
	
}
