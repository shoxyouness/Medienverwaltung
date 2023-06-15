module pk
{
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	opens pk1.mv.gui to javafx.controls, javafx.base, javafx.graphics;
}
