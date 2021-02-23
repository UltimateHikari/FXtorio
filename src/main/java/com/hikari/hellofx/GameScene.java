package com.hikari.hellofx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class GameScene extends GridPane{
  public GameScene(Controller controller) {
	  setAlignment(Pos.TOP_LEFT);
	  setVgap(10);
	  setHgap(10);
  	  setPadding(new Insets(25,25,25,25));
  	  add(new GameField(), 0, 0);
  	  add(new SpawnMenu(), 0, 1, 2, 1);
  	  add(new InfoMenu(),1,0);
  }
}
