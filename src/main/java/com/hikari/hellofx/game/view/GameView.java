package com.hikari.hellofx.game.view;

import com.hikari.hellofx.SceneController;
import com.hikari.hellofx.entity.model.EntityShadow;
import com.hikari.hellofx.entity.view.EntityShadowView;
import com.hikari.hellofx.entity.view.info.ConnectableInfo;
import com.hikari.hellofx.game.GameController;

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
		add(new BottomMenu(controller, gController), 0, 1, 2, 1);
		add(infoMenu,1,0);
	}
	
	public void showSpawned(Node spawned) {
		gameField.add(spawned);
	}
	
	public void removeOrphan(Node orphanedNode) {
		gameField.remove(orphanedNode);
	}
	
	public VBox getInfo() {
		return infoMenu;
	}
	
	public void showInfo(ConnectableInfo info) {
		getChildren().remove(infoMenu);
		infoMenu = info;
		add(infoMenu, 1, 0);
	}

	public void showShadow(EntityShadow shadow) {
		tryInitShadow();
		shadow.subscribe(shadowView);
		gameField.add(shadowView/*, shadow.getPosition().getX(), shadow.getPosition().getY()*/);
		shadowView.enable();
	}

	public void tryInitShadow() {
		if(shadowView == null) {
			shadowView = new EntityShadowView(gController);
		}
	}
	
	public void hideShadow(EntityShadow shadow) {
		shadow.unsubscribe(shadowView);
		gameField.remove(shadowView);
		shadowView.disable();
	}
}
