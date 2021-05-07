package com.hikari.hellofx;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SceneController {
	private final AppModel model;
	
	public SceneController(AppModel model_) {
		model = model_;
	}
	
	public void changeCurrentScene(String nextSceneName){
		try {
			model.setCurrentScene(nextSceneName);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error changing scene: " + e.getMessage());
		}
	}
}
