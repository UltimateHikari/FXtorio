module com.hikari.hellofx {
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
	requires java.desktop;
	requires lombok;
	requires org.apache.logging.log4j;
	exports com.hikari.hellofx;
	exports com.hikari.hellofx.Base;
}