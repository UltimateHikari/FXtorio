package com.hikari.hellofx.Game.View;

import com.hikari.hellofx.MenuView;
import com.hikari.hellofx.SceneController;
import com.hikari.hellofx.Entities.EntityClassPair;
import com.hikari.hellofx.Entities.Connectable.Constructor.ConstructorModel;
import com.hikari.hellofx.Entities.Connectable.Constructor.ConstructorView;
import com.hikari.hellofx.Entities.Connectable.Miner.MinerModel;
import com.hikari.hellofx.Entities.Connectable.Miner.MinerView;
import com.hikari.hellofx.Entities.Connectable.Storage.StorageModel;
import com.hikari.hellofx.Entities.Connectable.Storage.StorageView;
import com.hikari.hellofx.Game.GameController;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SpawnMenu extends HBox {
	public SpawnMenu(SceneController controller, GameController gcontroller) {
		add(new Text("spawn menu here"));
		setSpacing(10);
		add(new Text("and here"));
		EntityClassPair constructorPair = new EntityClassPair(ConstructorModel.class, ConstructorView.class);
		EntityClassPair minerPair = new EntityClassPair(MinerModel.class, MinerView.class);
		EntityClassPair storagePair = new EntityClassPair(StorageModel.class, StorageView.class);
		
		add(new SpawnButton(gcontroller, "Constructor", constructorPair, ConstructorView.getColor()));
		add(new SpawnButton(gcontroller, "Miner", minerPair, MinerView.getColor()));
		add(new SpawnButton(gcontroller, "Storage", storagePair, StorageView.getColor()));
		add(new ConnectionButton(gcontroller, "Connect"));
		add(new NavButton(controller, "Main menu", MenuView.class.getName()));
		add(new CancelButton(gcontroller, "LazyCancel"));
	}

	/*
	 * Syntax sugar
	 */
	public void add(Node child) {
		getChildren().add(child);
	}
}
