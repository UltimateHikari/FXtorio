package com.hikari.hellofx.entity.view.info;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelInfo;
import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.entity.IProducer;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.RecipeManager;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.extern.log4j.Log4j2;
import javafx.application.Platform;
import javafx.scene.Node;

@Log4j2
public class ConnectableInfo extends VBox implements IModelInfo {
	private final Text text = new Text("");
	private final SuspendButton btn;
	BindingController controller;

	public ConnectableInfo(BindingController bController) {
		controller = bController;
		add(text);
		btn = new SuspendButton(bController, "");
		add(btn);
		add(new DespawnButton(bController, "deconstruct"));
	}

	@Override
	public void modelChanged(BaseModel model) {
		//controller notifies before actually showing
		if(text.getText().equals("") && model instanceof IProducer producer) {
			initRecipeButtons(producer);
		}
		
		String state = "My state is " + ((ISuspendable) model).isOn();
		Platform.runLater(() -> {
			text.setText(state + "\n I am " + model);
			btn.setText("turn " + stringifyReversePowerState((ISuspendable) model));
			log.debug(state);
		});
	}

	private void initRecipeButtons(IProducer model) {
		//TODO: now it is like Minecraft recipes - you need to remember ingredients
		//at least placement doesnt matter due to usage of Set
		log.info(model.getClass().getName(), model);
		for(Item i : RecipeManager.instance().getAllPossibleProducables(model.getClass())) {
			add(new RecipeButton(controller, i.toString(), i));
		}
	}

	private String stringifyReversePowerState(ISuspendable model) {
		if (model.isOn()) {
			return "off";
		}
		return "on";
	}

	public void disable() {
		controller.breakBond(this);
	}

	private void add(Node node) {
		getChildren().add(node);
	}
}
