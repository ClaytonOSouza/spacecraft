package br.com.elo7.spacecraft.services.util;

import javax.ws.rs.core.MediaType;

public class MediaTypeExtends extends MediaType {
	
	public final static String APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON + ";charset=UTF-8";
	
	public final static MediaTypeExtends APPLICATION_JSON_UTF8_TYPE = new MediaTypeExtends("application", "json;charset=UTF-8");

	public MediaTypeExtends(String type, String subtype) {
		super(type, subtype);
	}
	
}
