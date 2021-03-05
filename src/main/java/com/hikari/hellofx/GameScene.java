package com.hikari.hellofx;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import com.hikari.hellofx.Entities.ConstructorModel;
import com.hikari.hellofx.Entities.ConstructorView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class GameScene extends GridPane{
	//private final Collection<ConstructorModel> entityModels = new CopyOnWriteArrayList<ConstructorModel>();
	private final Collection<ConstructorModel> entityModels = new CopyOnWriteArrayList<ConstructorModel>();
	private final GameController gController = new GameController(this);
	private final GameField gameField = new GameField(gController);
	
	public GameScene(SceneController controller) {
		setAlignment(Pos.TOP_LEFT);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(25,25,25,25));
		add(gameField, 0, 0);
		add(new SpawnMenu(controller, gController), 0, 1, 2, 1);
		add(new InfoMenu(controller),1,0);
	}
	
	public void spawn(Double x, Double y) {
		ConstructorModel model = new ConstructorModel();
		BindingController bController = new BindingController(gController, model);
		ConstructorView spawned = new ConstructorView(x,y, bController);
		model.subscribe(spawned);
		entityModels.add(model); //зачем?
		gameField.add(spawned, x, y);
		
	}
}
