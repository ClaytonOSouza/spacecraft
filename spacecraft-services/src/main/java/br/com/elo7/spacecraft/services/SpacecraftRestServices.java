package br.com.elo7.spacecraft.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.commons.validation.BeanValidator;
import br.com.elo7.spacecraft.commons.validation.groups.Payload;
import br.com.elo7.spacecraft.model.Plateau;
import br.com.elo7.spacecraft.model.Plateau.PlateauBuilder;
import br.com.elo7.spacecraft.model.Spacecraft;
import br.com.elo7.spacecraft.model.Spacecraft.SpacecrafBuilder;
import br.com.elo7.spacecraft.model.request.SpacecraftPayload;
import br.com.elo7.spacecraft.model.request.SpacecraftRequest;
import br.com.elo7.spacecraft.services.util.MediaTypeExtends;

@Controller
@Path("/spacecraft")
public class SpacecraftRestServices {
	
	@Autowired
	private SpacecraftBO spacecraftBO;
	
	@Autowired
	private BeanValidator beanvalidator;
	
	@POST
	@Path("/commands")
	@Consumes({MediaTypeExtends.APPLICATION_JSON, MediaTypeExtends.APPLICATION_JSON_UTF8})
	@Produces({MediaTypeExtends.APPLICATION_JSON, MediaTypeExtends.APPLICATION_JSON_UTF8})
	public Response executeCommands(SpacecraftPayload spacecraftPayload) {
		
		beanvalidator.validate(spacecraftPayload, Payload.class);
		
		List<Spacecraft> listResponse = new ArrayList<>();
		
		spacecraftPayload.getListSpacecraft().forEach(request -> {
			
			Plateau plateau = this.loadPlateau(request);
			
			Spacecraft spacecraft = this.loadSpacecraft(request, plateau);
			
			Spacecraft spacecraftReturnded = spacecraftBO.executeCommands(spacecraft);
			
			listResponse.add(spacecraftReturnded);
		});
		
		return Response.ok(listResponse).build();
	}
	
	private Plateau loadPlateau(SpacecraftRequest request) {
		
		Plateau plateau = PlateauBuilder.create().
				upperRightX(request.getUpperRightX()).
				upperRightY(request.getUpperRightY()).
				build();
		
		return plateau;
	}
	
	private Spacecraft loadSpacecraft(SpacecraftRequest request, Plateau plateau) {
		
		Spacecraft spacecraft = SpacecrafBuilder.create().
				coordinateX(request.getCoordinateX()).
				coordinateY(request.getCoordinateY()).
				cardinalPoint(request.getCardinalPoint()).
				commands(request.getListCommands()).
				plateau(plateau).
				build();
		
		return spacecraft;
	}
	
}
