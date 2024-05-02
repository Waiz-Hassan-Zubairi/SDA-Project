package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Practice12Controller {
    
    @FXML
    private TextField name;

    @FXML
    private TextField age;

    @FXML
    private TextField contact;

    @FXML
    private TextField address;
    
    @FXML
    private TextField option1;

    String n;
    String a;
    String c;
    String add;
    String ser;

    @FXML
    private void display(javafx.event.ActionEvent event) {
        try {
            n = name.getText();
            a = age.getText();
            c = contact.getText();
            add = address.getText();
            ser = option1.getText();
            
            if (n.isEmpty() || a.isEmpty() || c.isEmpty() || add.isEmpty() || ser.isEmpty()) {
                // Show a customized alert for empty fields
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Empty Fields");
                alert.setHeaderText("Oops! Something is missing.");
                alert.setContentText("Please fill in all the fields before proceeding.");

                // Customize the alert style
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.initStyle(StageStyle.UTILITY); 

                // Show the alert
                alert.showAndWait();
                return;  // Return to avoid executing the rest of the code
            }
            if (ser.equals("2"))
            {
            	ser="At Home";
            }
            else
            {
            	ser="At Center";
            }
            // Establish database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDA", "root", "waiz");

            // Use PreparedStatement to avoid SQL injection
            String sql = "INSERT INTO receivingForms (name, age, contact, address,service) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, n);
                preparedStatement.setString(2, a);
                preparedStatement.setString(3, c);
                preparedStatement.setString(4, add);
                preparedStatement.setString(5, ser);

                // Execute the update
                preparedStatement.executeUpdate();
            }

            // Close the connection
            con.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Practice13.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setTitle("Thank You");
            currentStage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	@FXML
	private void back(javafx.event.ActionEvent event) {
	    try {
	    	
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Practice9.fxml"));
	        Parent root = loader.load();
	        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setTitle("Receiving Scheduling");
	        currentStage.getScene().setRoot(root);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
