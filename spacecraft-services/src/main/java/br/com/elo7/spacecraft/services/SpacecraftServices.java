package br.com.elo7.spacecraft.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.elo7.spacecraft.business.SpacecraftBO;
import br.com.elo7.spacecraft.model.Spacecraft;

@Controller
@Path("/spacecraft")
public class SpacecraftServices {
	
	@Autowired
	private SpacecraftBO spacecraftBO;
	
	@POST
	@Path("")
	@Consumes
	@Produces
	public Response executeCommands() {
		
		Spacecraft spacecraft = spacecraftBO.executeCommands(null);
		
		return Response.ok(spacecraft).build();
	}
	
}
