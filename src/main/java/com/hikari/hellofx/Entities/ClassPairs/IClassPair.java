package com.hikari.hellofx.Entities.ClassPairs;

public interface IClassPair<T> {
	//TODO how to put here getViewInstance with depending args??
	//public BaseModel getModelInstance() throws Exception;
	public Class<? extends T> getViewClass();
}
