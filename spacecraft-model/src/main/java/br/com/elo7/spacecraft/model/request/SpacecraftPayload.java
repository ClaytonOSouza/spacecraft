package br.com.elo7.spacecraft.model.request;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.elo7.spacecraft.commons.validation.groups.Payload;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpacecraftPayload {
	
	@NotEmpty(groups = {Payload.class})
	private List<SpacecraftRequest> listSpacecraft;
	
	public SpacecraftPayload() {}
	
	public List<SpacecraftRequest> getListSpacecraft() {
		return listSpacecraft;
	}
	
	public void setListSpacecraft(List<SpacecraftRequest> listSpacecraft) {
		this.listSpacecraft = listSpacecraft;
	}
	
}
