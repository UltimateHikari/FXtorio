package com.hikari.hellofx;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import com.hikari.hellofx.Entities.MinerView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GameScene extends GridPane{
	private final Collection<MinerView> entityViews = new CopyOnWriteArrayList<MinerView>();
	private final GameController gcontroller = new GameController(this);
	private final GameField gameField = new GameField(gcontroller);
	
	public GameScene(SceneController controller) {
		setAlignment(Pos.TOP_LEFT);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(25,25,25,25));
		add(gameField, 0, 0);
		add(new SpawnMenu(controller, gcontroller), 0, 1, 2, 1);
		add(new InfoMenu(controller),1,0);
	}
	
	public void spawn(Double x, Double y) {
		MinerView spawned = new MinerView(x,y);
		entityViews.add(spawned); //зачем?
		gameField.add(spawned, x, y);
		
	}
}
