package com.hikari.hellofx;

import com.hikari.hellofx.Base.IModelInfo;
import com.hikari.hellofx.Entities.IConnectable;
import com.hikari.hellofx.Entities.EntityShadow;
import com.hikari.hellofx.Views.ConnectableInfo;
import com.hikari.hellofx.Views.ConstructorView;
import com.hikari.hellofx.Views.EntityShadowView;
import com.hikari.hellofx.Views.InfoMenu;
import com.hikari.hellofx.Views.GameScene.GameField;
import com.hikari.hellofx.Views.GameScene.SpawnMenu;

import javafx.scene.Node;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameView extends GridPane{
	private final GameController gController;
	private final GameField gameField;
	private EntityShadowView shadowView = null;
	private VBox infoMenu;
	
	public GameView(SceneController controller) {		
		setAlignment(Pos.TOP_LEFT);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(25,25,25,25));
		gController = new GameController(this);
		gameField = new GameField(gController);
		
		infoMenu = new InfoMenu();
		gController.subscribeGameField(gameField);

		add(gameField, 0, 0);		
		add(new SpawnMenu(controller, gController), 0, 1, 2, 1);
		add(infoMenu,1,0);
	}
	
	public void showSpawned(Node spawned) {
		gameField.add(spawned);
	}
	
	public void showInfo(IConnectable model, ConnectableInfo info) {
		if(infoMenu instanceof IModelInfo) {
			((IModelInfo)infoMenu).disable();
		}
		getChildren().remove(infoMenu);
		infoMenu = info;
		add(infoMenu, 1, 0);
	}

	public void showShadow(EntityShadow shadow) {
		shadowView = new EntityShadowView(gController);
		shadow.subscribe(shadowView);
		gameField.add(shadowView/*, shadow.getPosition().getX(), shadow.getPosition().getY()*/);
	}

	public void hideShadow(EntityShadow shadow) {
		shadow.unsubscribe(shadowView);
		gameField.remove(shadowView);
	}
}
