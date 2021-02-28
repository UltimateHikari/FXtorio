package com.hikari.hellofx.Entities;

import javafx.scene.shape.Rectangle;

public class MinerView extends Rectangle{
	public MinerView(Double x, Double y) {
		super(x,y,40,40);
		System.out.println(x + " " + y);
		setOnMouseClicked((event) -> System.out.println("Mining lol"));
	}
}
