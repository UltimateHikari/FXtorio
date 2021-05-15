package com.hikari.hellofx.entity;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public enum Items {
	IRON(Color.SILVER, ""),
	COPPER(Color.SANDYBROWN, ""),
	IRON_PLATE(Color.SILVER, "P"),
	IRON_ROD(Color.SILVER, "R"),
	SCREW(Color.SLATEBLUE, "S"),
	WIRE(Color.SANDYBROWN, "W"),
	CABLE(Color.TURQUOISE, "C"),
	FRAME(Color.DARKCYAN, "F");
	
	@Setter
	private Color color;
	@Setter
	private String tag;
}
