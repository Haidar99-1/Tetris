import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Game {
	private Pane _gamePane;
	private TetrisSquare[][] _board; //making 2-d array of tetrisSquare
	private Piece _piece;
	private Timeline _timeline;
	private Label _label;
	private boolean _gameOver;
	private boolean _paused;

	public Game(Pane gamePane) {
		_gamePane = gamePane;
		this.makeBoard();
		_piece = new Piece(_gamePane); // shows the piece graphically
		this.setUpTimeLine();
		_gameOver = false;
		_paused = false; // the game is still running
		_piece = _piece.generateShape(); // the game class tells the piece call call on generate method of declare type Piece
		_gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new MoveHandler());
		_gamePane.setFocusTraversable(true);
		_gamePane.requestFocus();
	}
	// makes the outer boarder in gray color.
	public void makeBoard() {
		_board = new TetrisSquare[26][16];
		for (int row = 0; row < 26; row++) {
			for (int col = 0; col < 16; col++) {
				if (row == 0 || row == 1 || row == 24 || row == 25 || col == 0 || col == 1 || col == 14 || col == 15) {
					TetrisSquare newSquare = new TetrisSquare(Constant.SQUARE_WIDTH * col, Constant.SQUARE_HEIGHT * row, Color.GRAY);
					_board[row][col] = newSquare; // add it logically
					_gamePane.getChildren().add(newSquare.getSquare()); //add it graphically
				}
			}
		}
	}

	public void setUpTimeLine() {
		KeyFrame kf = new KeyFrame(Duration.seconds(Constant.DURATION), new TimeHandler());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}


	/**
	 * iterate throught each square inside the piece. calculate the row by current position divided by the width of
	 * the sqaure. check if moving left is legal move or not.
	 *
	 */
	public boolean canMoveLeft() {
		for (TetrisSquare tetrisSquare : _piece.getSquares()) {
			int row = (int) tetrisSquare.getYLocation() / 25;
			int col = (int) tetrisSquare.getXLocation() / 25;
			if (_board[row][col - 1] != null) {
				return false;
			}
		}
		return true;
	}


	public boolean canMoveRight() {
		for (TetrisSquare tetrisSquare : _piece.getSquares()) {
			int row = (int) tetrisSquare.getYLocation() / 25;
			int col = (int) tetrisSquare.getXLocation() / 25;
			if (_board[row][col + 1] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the next move down is not null. if next move is null the move is legal.
	 */
	public boolean canMoveDown() {
		for (TetrisSquare tetrisSquare : _piece.getSquares()) {
			int row = (int) tetrisSquare.getYLocation() / 25;
			int col = (int) tetrisSquare.getXLocation() / 25;
			if (_board[row + 1][col] != null) { // if there is a piece
				return false;
			}
		}
		return true;
	}

	/**
	 * adds the squares of the piece inside the bored.
	 * In other words, the bored knows where the piece is at.
	 */
	public void pieceStaysOnBoard() {
		for (TetrisSquare tetrisSquare : _piece.getSquares()) {
			int row = (int) tetrisSquare.getYLocation() / 25;
			int col = (int) tetrisSquare.getXLocation() / 25;
			_board[row][col] = tetrisSquare;
		}
	}


	public boolean canRotate() {
		for (int i = 0; i < _piece.getSquares().length; i++) {
			if (_piece.getSquares()[2].getXLocation() == _board[4][1].getXLocation() + 25 && _piece.getSquares()[3].getXLocation() == _board[4][1].getXLocation() + 25) {
				return false;
			}
			if (_piece.getSquares()[1].getXLocation() == _board[4][1].getXLocation() + 50) {
				return false;
			}
			if (_piece.getSquares()[3].getXLocation() == _board[4][1].getXLocation()) {
				return false;
			}
			if (_piece.getSquares()[2].getXLocation() == _board[4][1].getXLocation() + 25) {
				return false;
			}
			if (_piece.getSquares()[2].getXLocation() == _board[4][1].getXLocation()) {
				return false;
			}
		}
		return true;
	}

	// MoveHandler class allows us to move the pieces
	private class MoveHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent keyEvent) {
			switch (keyEvent.getCode()) {
				case RIGHT:
					if (canMoveRight() && !_gameOver) {
						_piece.setPieceXPos(-Constant.MOVEPIECE);
					}
					break;
				case LEFT:
					if (canMoveLeft() && !_gameOver) {
						_piece.setPieceXPos(Constant.MOVEPIECE);
					}
					break;
				case DOWN:
					if (canMoveDown() && !_gameOver) {
						_piece.moveDown();
					}
					break;
				case SPACE:
					while (Game.this.canMoveDown()) {
						_piece.moveDown();
					}
					break;
				case UP:
					if (canRotate() && !_gameOver) {
						_piece.rotateShape();
						break;
					}
					break;
				case P:
					if (!_paused) { // _paused == false
						_timeline.pause();
						_paused = true;
					} else {
						_timeline.play();
						_paused = false;

					}
			}
		}
	}

	private class TimeHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (Game.this.canMoveDown()) { // if the next move is legal then move 25 down
				_piece.moveDown();
			} else { // if the pieces is illegal to move down
				Game.this.pieceStaysOnBoard(); // the board knows where the piece is at
				if (!isGameOver()) { // if the game is NOT over then generate new piece
					_piece.generateShape();
				}

			}
			for (int row = 2 ; row < _board.length - 2; row++) {
				if (this.isRowFilled(row) ) {
					this.deleteRow(row);
					this.moveOneRowDown(row);
				}
			}

			if (isGameOver()) {
				_label = new Label("Game Over");
				_label.setFont(new Font(45));
				_label.setTextFill(Color.BLACK);
				_gamePane.getChildren().add(_label);
				_timeline.stop();
			}
		}


		private boolean isRowFilled(int row) {
			// return true if the last row of each col filled ( not null)
			// return false if the last row of reach col is NOT filled, which is null
			for (int col = 2; col < 14; col++) {
				if (_board[row][col] == null) {
					return false;
				}
			}
			return true;
		}

		private void deleteRow(int row) {
			for (int col = 2; col < 14; col++) {
				// loop throught the columns to remove the last row graphically and logically
				_gamePane.getChildren().remove(_board[row][col].getSquare()); //remove last row graphically
				_board[row][col] = null; //remove the last row logically
			}
		}


		private void moveOneRowDown(int filledRow) {
			for (int row = filledRow - 1; row >= 2; row--) {
				for (int col = 2; col < 14; col++) {
					// looping through the rows and column to find if the there is a square
					//at some row and column
					if (_board[row][col] != null) { // there is something at this row and column
						//if there is something there then get the Y position of that row&column then add 25
						_board[row][col].setYLoc(_board[row][col].getYLocation() + Constant.SQUARE_HEIGHT);
						//replacing the the row&column logically
						_board[row + 1][col] = _board[row][col];
						_board[row][col] = null; // making the previous row to null
					}
				}
			}
		}
		// checks if the second row at some column has something there.
		//return true if there is something there.
		private boolean isGameOver() {
			for (int col = 2; col < 14; col++) {
				if (_board[2][col] != null) {
					_gameOver = true;
					return true;
				}
			}
			return false;
		}


	}
}

