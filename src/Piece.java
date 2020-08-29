import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Piece {
	private Pane _piecesPane;
	private TetrisSquare[] _tetrisPieces; // delcared as tetrisSquare array


	public Piece(Pane gamePane) {
		_piecesPane = gamePane; // associate pieces class to Game class
		_tetrisPieces = new TetrisSquare[4];
	}

	private Piece makeTetrisPiece(double[][] posSquares, Color color) {
		_tetrisPieces[0] = new TetrisSquare(posSquares[0][0],posSquares[0][1], color);
		_tetrisPieces[1] = new TetrisSquare(posSquares[1][0],posSquares[1][1], color);
		_tetrisPieces[2] = new TetrisSquare(posSquares[2][0],posSquares[2][1], color);
		_tetrisPieces[3] = new TetrisSquare(posSquares[3][0],posSquares[3][1], color);
		_piecesPane.getChildren().addAll(_tetrisPieces[0].getSquare(), _tetrisPieces[1].getSquare(), _tetrisPieces[2].getSquare(), _tetrisPieces[3].getSquare());
		return this;
	}

	public TetrisSquare[] getSquares() {
		return _tetrisPieces;  //get and stores all 4 squares in 1-D array
	}


//	private Piece makeTetrisPieceFaster(double[][] posSquares, Color color) {
//		for (int i = 0; i < 4; i++) {
//			_tetrisLinePiece[i] = new TetrisSquare(posSquares[i][0],posSquares[i][1], color);
//			_piecesPane.getChildren().add(_tetrisLinePiece[i].getSquare());
//		}
//		return this;
//	}

	//generate and make new piece
	public Piece generateShape() {
		int pieceNum = (int) (Math.random() * 7);
		switch (pieceNum) {
			case 0: // Blue Piece (divideRight)
				return this.makeTetrisPiece(Constant.BLUEPIECE, Color.BLUE); // constant.bluepiece is a 2-D array stores the x & y position of each single piece

			case 1: // LightBlue piece (divideLeft)
				return this.makeTetrisPiece(Constant.LIGHTBLUEPIECE,Color.LIGHTBLUE);

			case 2:  // red piece (line)
				return this.makeTetrisPiece(Constant.REDPIECE, Color.RED);

			case 3: // purple piece (box shape)
				return this.makeTetrisPiece(Constant.PURPLEPIECE, Color.PURPLE);

			case 4: //orange piece (middle right)
				return this.makeTetrisPiece(Constant.ORANGEPIECE, Color.ORANGE);

			case 5: // yellow piece (line Right)
				return  this.makeTetrisPiece(Constant.YELLOWPIECE, Color.YELLOW);

			case 6: // green piece (line left)
				return this.makeTetrisPiece(Constant.GREENPIECE, Color.GREEN);
			default:
				System.out.println("Invalid Piece. Try again");
		}
		return null;
	}

	public void setPieceYPos(int y) {
		for (int i = 0; i < _tetrisPieces.length; i++) {
			_tetrisPieces[i].setYLoc(_tetrisPieces[i].getYLocation() + y);
		}
	}

	// can change to set the x position of each square insdie the piece
	public void setPieceXPos(int x) {
		for(int i = 0; i< _tetrisPieces.length; i++) {
			_tetrisPieces[i].setXLoc(_tetrisPieces[i].getXLocation() - x);
		}
	}

	//moves every square inside the piece down by 25
	public void moveDown() {
		for (TetrisSquare tetrisSquare : _tetrisPieces) {
			tetrisSquare.setYLoc(tetrisSquare.getYLocation() + 25);
		}
	}


	public void rotateShape() {
		double centerOfRotationX = _tetrisPieces[0].getXLocation(); // the pos of X which is first square that we willn't change
		double centerOfRotationY = _tetrisPieces[0].getYLocation(); // // the pos of y which is first square that we willn't change
		for (int i = 1; i < 4; i++ ) {
			double locX = _tetrisPieces[i].getXLocation();
			double locY = _tetrisPieces[i].getYLocation();
			double newXLoc = centerOfRotationX - centerOfRotationY + locY;
			double newYloc = centerOfRotationY + centerOfRotationX - locX;
			_tetrisPieces[i].setXLoc(newXLoc);
			_tetrisPieces[i].setYLoc(newYloc); // changing the position of x & y of the three squares to rotate around the first square
		}
	}

}


