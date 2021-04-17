package com.hikari.hellofx.Base;

public interface ILoggable {
	default void log(String s) {
		System.out.println(this.getClass().getSimpleName() + ": " + s);
	}
}
