package br.com.elo7.spacecraft.model;

import java.util.HashMap;
import java.util.Map;

public enum WindRose {
	
	N("W", "E"),
	W("S", "N"),
	S("E", "W"),
	E("N", "S");

	private String left;
	
	private String right;
	
	private static Map<String, WindRose> map = new HashMap<>();
	
	static {
		
		for(WindRose windRose : values()) {
			map.put(windRose.name().toUpperCase(), windRose);
			map.put(windRose.name().toLowerCase(), windRose);
		}
		
	}
	
	private WindRose(String left, String right) {
		this.left = left;
		this.right = right;
	}
	
	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}
	
	public static WindRose getWindRose(String cardinalPoint) {
		return map.get(cardinalPoint);
	}
	
}
