package com.hikari.hellofx;


import com.hikari.hellofx.base.BaseModel;

public class AppModel extends BaseModel {
	private SceneClass currentSceneName = SceneClass.MENU;

	public SceneClass getCurrentScene() {
		return currentSceneName;
	}

	public void setCurrentScene(SceneClass nextSceneName) {
		currentSceneName = nextSceneName;
		super.notifySubs();
	}

}
