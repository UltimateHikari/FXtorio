package com.hikari.hellofx.entity;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Item {
	IRON(Color.SILVER, ""),
	COPPER(Color.SANDYBROWN, ""),
	POLYMER(Color.DARKBLUE, ""),
	IRON_PLATE(Color.SILVER, "P"),
	IRON_ROD(Color.SILVER, "R"),
	SCREW(Color.SLATEBLUE, "S"),
	WIRE(Color.SANDYBROWN, "W"),
	CABLE(Color.TURQUOISE, "C"),
	FRAME(Color.DARKCYAN, "F");
	
	@Getter
	private Color color;
	@Getter
	private String tag;
}
