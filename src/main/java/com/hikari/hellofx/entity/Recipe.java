package com.hikari.hellofx.entity;

import java.util.Set;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Recipe {
	private Set<Items> ingredients = null;
	private Items result;
	
	public Recipe(Items result_) {
		result = result_;
	}
	
	public boolean test(Set<Items> items) {
		return items.equals(ingredients);
	}
	
	public Items produce() {
		return result;
	}
}
