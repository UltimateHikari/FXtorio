package com.hikari.hellofx;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelInfo;
import com.hikari.hellofx.Entities.Connectable;
import com.hikari.hellofx.Entities.ConstructorModel;
import com.hikari.hellofx.Entities.EntityShadow;
import com.hikari.hellofx.Views.ConnectableInfo;
import com.hikari.hellofx.Views.ConstructorView;
import com.hikari.hellofx.Views.EntityShadowView;
import com.hikari.hellofx.Views.InfoMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameScene extends GridPane{
	private final Collection<ConstructorModel> entityModels = new CopyOnWriteArrayList<ConstructorModel>();
	GameFieldModel gameFieldModel = new GameFieldModel();
	private final GameController gController = new GameController(this, gameFieldModel);
	private final GameField gameField = new GameField(gController);
	private VBox infoMenu;
	
	public GameScene(SceneController controller) {
		setAlignment(Pos.TOP_LEFT);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(25,25,25,25));
		
		gameFieldModel.subscribe(gameField);
		add(gameField, 0, 0);
		
		infoMenu = new InfoMenu();
		add(new SpawnMenu(controller, gController), 0, 1, 2, 1);
		add(infoMenu,1,0);
	}
	
	public void spawn(Double x, Double y) {
		ConstructorModel model = new ConstructorModel();
		BindingController bController = new BindingController(gController, model);
		ConstructorView spawned = new ConstructorView(x,y, bController);
		model.subscribe(spawned);
		entityModels.add(model); //зачем?
		gameField.add(spawned, x, y);
		
	}
	
	public void showInfo(Connectable model) {
		//probably snowball with subs here; //fixed here
		if(infoMenu instanceof IModelInfo) {
			((IModelInfo)infoMenu).disable();
		}
		BindingController bController = new BindingController(gController, (BaseModel) model);
		ConnectableInfo info = new ConnectableInfo(bController);
		((BaseModel)model).subscribe(info);
		((BaseModel)model).notifySubs();
		getChildren().remove(infoMenu);
		infoMenu = info;
		add(infoMenu, 1, 0);
	}

	public void showShadow(EntityShadow shadow) {
		EntityShadowView shadowView = new EntityShadowView();
		shadow.subscribe(shadowView);
		gameField.add(shadowView, shadow.getPosition().getX(), shadow.getPosition().getY());
		
	}
}
