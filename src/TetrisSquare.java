import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.time.Year;

/**
 * Tetris Square class knows how to make a single square.
 * Tetris Square has some method that can change X or y position and sets the location
 */

public class TetrisSquare {
	private Rectangle _square;
	private Color _primaryColor = Color.GREEN;

	public TetrisSquare(double xPosition, double yPosition,Color primaryColor ) {
		_square = new Rectangle(Constant.SQUARE_WIDTH, Constant.SQUARE_HEIGHT);
		_square.setX(xPosition);
		_square.setY(yPosition);
		_square.setFill(primaryColor);
		_square.setStroke(Color.BLACK);
	}

	public Rectangle getSquare() {
		return _square;
	} // it returns the square shape

	public void setSquare(Rectangle square) {
		_square = square;
	}
	public void setColor(Color color) {
		_square.setFill(color);
	}

	public void setXLoc(double x) {
		_square.setX(x);
	}

	public void setYLoc(double y) {
		_square.setY(y);
	}

	public double getXLocation() {
		return _square.getX();
	}

	public int getYLocation() {
		return (int) _square.getY();
	}

}
