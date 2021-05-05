package com.hikari.hellofx.Game.View;

import com.hikari.hellofx.MenuView;
import com.hikari.hellofx.SceneController;
import com.hikari.hellofx.Entities.ClassPairs.ConnectionClassPair;
import com.hikari.hellofx.Entities.ClassPairs.EntityClassPair;
import com.hikari.hellofx.Entities.Connectable.Assembler.AssemblerModel;
import com.hikari.hellofx.Entities.Connectable.Assembler.AssemblerView;
import com.hikari.hellofx.Entities.Connectable.Constructor.ConstructorModel;
import com.hikari.hellofx.Entities.Connectable.Constructor.ConstructorView;
import com.hikari.hellofx.Entities.Connectable.Miner.MinerModel;
import com.hikari.hellofx.Entities.Connectable.Miner.MinerView;
import com.hikari.hellofx.Entities.Connectable.Storage.StorageModel;
import com.hikari.hellofx.Entities.Connectable.Storage.StorageView;
import com.hikari.hellofx.Entities.Connectable.Utility.Splitter.SplitterModel;
import com.hikari.hellofx.Entities.Connectable.Utility.Splitter.SplitterView;
import com.hikari.hellofx.Entities.Connection.Belt.Belt;
import com.hikari.hellofx.Entities.Connection.Belt.BeltView;
import com.hikari.hellofx.Entities.Connection.Conveyor.Conveyor;
import com.hikari.hellofx.Entities.Connection.Conveyor.ConveyorView;
import com.hikari.hellofx.Game.GameController;

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
