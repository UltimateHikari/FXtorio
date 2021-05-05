package com.hikari.hellofx.game.view;

import com.hikari.hellofx.MenuView;
import com.hikari.hellofx.SceneController;
import com.hikari.hellofx.entity.view.AssemblerView;
import com.hikari.hellofx.entity.view.ConstructorView;
import com.hikari.hellofx.entity.view.MinerView;
import com.hikari.hellofx.entity.view.SplitterView;
import com.hikari.hellofx.entity.view.StorageView;
import com.hikari.hellofx.entity.view.belt.BeltView;
import com.hikari.hellofx.entity.view.conveyor.ConveyorView;
import com.hikari.hellofx.entity.classpair.ConnectionClassPair;
import com.hikari.hellofx.entity.classpair.EntityClassPair;
import com.hikari.hellofx.entity.model.AssemblerModel;
import com.hikari.hellofx.entity.model.ConstructorModel;
import com.hikari.hellofx.entity.model.MinerModel;
import com.hikari.hellofx.entity.model.SplitterModel;
import com.hikari.hellofx.entity.model.StorageModel;
import com.hikari.hellofx.entity.model.belt.Belt;
import com.hikari.hellofx.entity.model.conveyor.Conveyor;
import com.hikari.hellofx.game.GameController;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class BottomMenu extends HBox {
	public BottomMenu(SceneController controller, GameController gcontroller) {
		setSpacing(10);
		EntityClassPair constructorPair = new EntityClassPair(ConstructorModel.class, ConstructorView.class);
		EntityClassPair minerPair = new EntityClassPair(MinerModel.class, MinerView.class);
		EntityClassPair storagePair = new EntityClassPair(StorageModel.class, StorageView.class);
		EntityClassPair assemblerPair = new EntityClassPair(AssemblerModel.class, AssemblerView.class);
		EntityClassPair splitterPair = new EntityClassPair(SplitterModel.class, SplitterView.class);
		
		ConnectionClassPair conveyorPair = new ConnectionClassPair(Conveyor.class, ConveyorView.class);
		ConnectionClassPair beltPair = new ConnectionClassPair(Belt.class, BeltView.class);

		add(new SpawnButton(gcontroller, "Miner", minerPair, MinerView.getColor()));
		add(new SpawnButton(gcontroller, "Constructor", constructorPair, ConstructorView.getColor()));
		add(new SpawnButton(gcontroller, "Assembler", assemblerPair, AssemblerView.getColor()));
		add(new SpawnButton(gcontroller, "Storage", storagePair, StorageView.getColor()));
		add(new SpawnButton(gcontroller, "Splitter", splitterPair, SplitterView.getColor()));
		add(new ConnectionButton(gcontroller, "Conveyor", conveyorPair));
		add(new ConnectionButton(gcontroller, "Belt", beltPair));
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
