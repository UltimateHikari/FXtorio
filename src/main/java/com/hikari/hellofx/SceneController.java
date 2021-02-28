package com.hikari.hellofx;

public class SceneController {
	private AppModel model;
	
	public SceneController(AppModel model_) {
		model = model_;
	}
	
	public void changeCurrentScene(String nextSceneName){
		try {
			model.setCurrentScene(nextSceneName);
		} catch (Exception e) {
			System.out.println("Error changing scene: " + e.getMessage());
		}
	}
}
