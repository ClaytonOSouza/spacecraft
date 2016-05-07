package br.com.elo7.spacecraft.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.commons.exception.CrashException;
import br.com.elo7.spacecraft.commons.validation.ErrorExceptionCode;
import br.com.elo7.spacecraft.model.Plateau;
import br.com.elo7.spacecraft.model.Plateau.PlateauBuilder;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.Spacecraft.SpacecrafBuilder;
import br.com.elo7.spacecraft.model.request.SpacecraftRequest;
import br.com.elo7.spacecraft.services.util.MediaTypeExtends;

@Controller
@Path("/spacecraft")
public class SpacecraftRestServices {
	
	@Autowired
	private SpacecraftBO spacecraftBO;
	
	@POST
	@Path("/commands")
	@Consumes({MediaTypeExtends.APPLICATION_JSON, MediaTypeExtends.APPLICATION_JSON_UTF8})
	@Produces({MediaTypeExtends.APPLICATION_JSON, MediaTypeExtends.APPLICATION_JSON_UTF8})
	public Response executeCommands(SpacecraftRequest spacecraftRequest) {
		
		Plateau plateau = PlateauBuilder.create().
										upperRightX(spacecraftRequest.getUpperRightX()).
										upperRightY(spacecraftRequest.getUpperRightY()).
										build();
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
										coordinateX(spacecraftRequest.getCoordinateX()).
										coordinateY(spacecraftRequest.getCoordinateY()).
										cardinalPoint(spacecraftRequest.getCardinalPoint()).
										commands(spacecraftRequest.getListCommands()).
										plateau(plateau).
										build();
		
		Spacecraft spacecraftReturnded = spacecraftBO.executeCommands(spacecraft);
		
		return Response.ok(spacecraftReturnded).build();
	}
	
	@GET
	@Path("/{id}")
	@Consumes({MediaTypeExtends.APPLICATION_JSON, MediaTypeExtends.APPLICATION_JSON_UTF8})
	@Produces({MediaTypeExtends.APPLICATION_JSON, MediaTypeExtends.APPLICATION_JSON_UTF8})
	public Response myTest(@PathParam("id") Long id) {
		
		throw new CrashException(ErrorExceptionCode.COORDINATE_ERROR);
		
//		StringBuilder builder = new StringBuilder("{\"id\" : ");
//		builder.append(id + "}");
//		
//		return Response.ok(builder.toString()).build();
	}
	
}
