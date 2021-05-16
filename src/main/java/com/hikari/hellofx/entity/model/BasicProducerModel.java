package com.hikari.hellofx.entity.model;

import java.util.List;
import java.util.NoSuchElementException;

import com.hikari.hellofx.entity.IProducer;
import com.hikari.hellofx.entity.Items;
import com.hikari.hellofx.entity.Recipe;
import com.hikari.hellofx.entity.RecipeManager;

import lombok.Getter;

public abstract class BasicProducerModel extends BasicEntityModel implements IProducer{
	@Getter
	private Recipe currentRecipe;
	
	@Override
	public void setCurrentRecipe(Items item) throws NoSuchElementException{
		List<Recipe> list =  RecipeManager.instance().getRecipeList(this.getClass());
		currentRecipe = list.stream().filter(r -> r.produce() == item).findFirst().orElseThrow();
	}
}
