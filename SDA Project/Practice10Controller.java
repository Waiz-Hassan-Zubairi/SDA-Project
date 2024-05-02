package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

import java.io.IOException;

public class Practice10Controller {

	
	@FXML
	private TextField option1;
	@FXML
	private void display(javafx.event.ActionEvent event) {
	    try {
	    	String option;
	    	int count=0;
	    	option=option1.getText();
	    	
	    	if (option.isEmpty()) {
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
	    	if(option.equals("5")||option.equals("6"))
	    	{
	    	// Establish database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDA", "root", "waiz");

	    	String countQuery = "SELECT COUNT(*) FROM ReceivingSlots WHERE slotNumber = ?";
            
            try (PreparedStatement countStatement = con.prepareStatement(countQuery)) {
                countStatement.setString(1, option);

                // Execute the query
                ResultSet resultSet = countStatement.executeQuery();

                // Retrieve the count value
                
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
	    	  if(count<=3)
	    	  {
		        // Use PreparedStatement to avoid SQL injection
		        String sql = "INSERT INTO ReceivingSlots (slotNumber) VALUES (?)";
		        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		            preparedStatement.setString(1, option);
		
		            // Execute the update
		            preparedStatement.executeUpdate();
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Practice12.fxml"));
			        Parent root = loader.load();
			        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		            currentStage.setTitle("Receiving Information Form");
			        currentStage.getScene().setRoot(root);
		        }
	    	  }
	    	  else
	    	  {
	    		     // Show a customized alert for empty fields
	                Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Slots Full");
	                alert.setHeaderText("Oops! No Space here.");
	                alert.setContentText("Please fill in an other slot.");

	                // Customize the alert style
	                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	                stage.initStyle(StageStyle.UTILITY); 

	                // Show the alert
	                alert.showAndWait();
	                return;  // Return to avoid executing the rest of the code
	    	  }
            // Close the connection
            con.close();
	    	
	    	}
	    	else
	    	{
	    		// Show a customized alert for empty fields
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Invalid Entry");
                alert.setHeaderText("Check the slot number first.");
                alert.setContentText("Please fill in the slot again.");

                // Customize the alert style
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.initStyle(StageStyle.UTILITY); 

                // Show the alert
                alert.showAndWait();
                return;  // Return to avoid executing the rest of the code
	    	}
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
