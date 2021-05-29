package com.hikari.hellofx.entity.model.basic;

import java.util.List;
import java.util.NoSuchElementException;

import com.hikari.hellofx.entity.IProducer;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.Recipe;
import com.hikari.hellofx.entity.RecipeManager;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BasicProducerModel extends BasicEntityModel implements IProducer{
	@Getter
	private Recipe currentRecipe;
	
	//TODO override turnOn for getting first recipe
	
	@Override
	public void setCurrentRecipe(Item item) throws NoSuchElementException{
		List<Recipe> list =  RecipeManager.instance().getRecipeList(this.getClass());
		currentRecipe = list.stream().filter(r -> r.produce() == item).findFirst().orElseThrow();
		log.info(currentRecipe.produce().toString());
	}
}
