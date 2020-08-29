import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PaneOrganizer {
	private BorderPane _root;
	private Label _label;

	public PaneOrganizer() {
		_root = new BorderPane();
		Pane gamePane = new Pane();
		this.setUpButton();
		Game game = new Game(gamePane);
		_root.getChildren().add(gamePane);

	}
	public void setUpButton() {
		BorderPane bottomButtons = new BorderPane();
		Button quitButton = new Button("Quit");
		quitButton.setOnAction(new ClickHandler());
		quitButton.setFocusTraversable(false);
		bottomButtons.setLeft(quitButton);
		_label = new Label("Press P to pause and unpause");
		_root.setBottom(bottomButtons);
		bottomButtons.setRight(_label);

	}


	public Pane getRoot() { return _root; }



	// quit
	private class ClickHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			System.exit(0);

		}
	}

}
