package com.hikari.hellofx.game.view;

import com.hikari.hellofx.SceneClass;
import com.hikari.hellofx.SceneController;
import com.hikari.hellofx.entity.view.AssemblerView;
import com.hikari.hellofx.entity.view.ConstructorView;
import com.hikari.hellofx.entity.view.MergerView;
import com.hikari.hellofx.entity.view.MinerView;
import com.hikari.hellofx.entity.view.SplitterView;
import com.hikari.hellofx.entity.view.StorageView;
import com.hikari.hellofx.game.GameController;
import com.hikari.hellofx.game.classpack.ConnectionClassPack;
import com.hikari.hellofx.game.classpack.EntityClassPack;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class BottomMenu extends HBox {
	public BottomMenu(SceneController controller, GameController gcontroller) {
		setSpacing(10);


		add(new SpawnButton(gcontroller, "Miner", EntityClassPack.MINER, MinerView.getColor()));
		add(new SpawnButton(gcontroller, "Constructor", EntityClassPack.CONSTRUCTOR, ConstructorView.getColor()));
		add(new SpawnButton(gcontroller, "Assembler", EntityClassPack.ASSEMBLER, AssemblerView.getColor()));
		add(new SpawnButton(gcontroller, "Storage", EntityClassPack.STORAGE, StorageView.getColor()));
		add(new SpawnButton(gcontroller, "Splitter", EntityClassPack.SPLITTER, SplitterView.getColor()));
		add(new SpawnButton(gcontroller, "Merger", EntityClassPack.MERGER, MergerView.getColor()));
		add(new ConnectionButton(gcontroller, "Conveyor", ConnectionClassPack.CONVEYOR));
		add(new ConnectionButton(gcontroller, "Belt", ConnectionClassPack.BELT));
		add(new NavButton(controller, "Main menu", SceneClass.MENU));
		add(new CancelButton(gcontroller, "LazyCancel"));
	}

	/*
	 * Syntax sugar
	 */
	public void add(Node child) {
		getChildren().add(child);
	}
}
