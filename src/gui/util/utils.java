package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class utils {

	public static Stage currentState(ActionEvent event) {

		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	public static Integer tryParseToTint(String str) {

		try {

			return Integer.parseInt(str);
			
		} catch (NumberFormatException error) {

			return null;
		}
	}

}
