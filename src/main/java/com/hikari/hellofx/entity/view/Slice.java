package com.hikari.hellofx.entity.view;
import javafx.scene.paint.Color;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Slice {
	@Getter
	private final Double height;
	@Getter
	private final Color color;
	@Override
	public String toString() {
		return height.toString();
	}
}
