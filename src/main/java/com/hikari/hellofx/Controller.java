package com.hikari.hellofx;

public class Controller {
	private Model model;
	
	public Controller(Model model_) {
		model = model_;
	}
	
	public void changeCurrentScene(String nextSceneName){
		try {
			model.setCurrentScene(nextSceneName);
		} catch (Exception e) {
			
		}
	}
}
