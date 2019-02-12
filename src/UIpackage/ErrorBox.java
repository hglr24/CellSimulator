package UIpackage;

import javafx.scene.control.Alert;

public class ErrorBox extends Alert {

    public ErrorBox(String type, String message) {
        super(AlertType.ERROR);
        this.setTitle("Error");
        this.setHeaderText(type);
        this.setContentText(message);
    }

    public void display() {
        this.showAndWait();
    }
}
