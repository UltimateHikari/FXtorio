package com.hikari.hellofx.game.classpack;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;

import javafx.scene.layout.Pane;

public interface ClassPack {
	public Class<? extends BaseModel> getModel();
	public Class<? extends BaseService> getService();
	public Class<? extends Pane> getView();
}
