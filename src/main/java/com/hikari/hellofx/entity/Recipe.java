package com.hikari.hellofx.entity;

import java.util.Set;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Recipe {
	private Set<Item> ingredients = null;
	private Item result;
	
	public Recipe(Item result) {
		this.result = result;
	}
	
	public boolean test(Set<Item> items) {
		return items.equals(ingredients);
	}
	
	public Item produce() {
		return result;
	}
}
