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

public class Practice3Controller {

	
	@FXML 
	private TextField option10;
	
	@FXML 
	private TextField option2;
	
	String option;
	String op2;
	@FXML
	private void display(javafx.event.ActionEvent event) {
	    try {
	    	
	    	option=option10.getText();
	    	op2=option2.getText();
	    	
	    	 if (option.isEmpty()||op2.isEmpty()) {
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
	    	 int count = 0;
    		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDA", "root", "waiz");
	            
	            // Use PreparedStatement to avoid SQL injection
	            String countQuery = "SELECT COUNT(*) FROM BloodStorage WHERE bloodgroup = ?";
	            
	            try (PreparedStatement countStatement = con.prepareStatement(countQuery)) {
	                countStatement.setString(1, op2);

	                // Execute the query
	                ResultSet resultSet = countStatement.executeQuery();

	                // Retrieve the count value
	                
	                if (resultSet.next()) {
	                    count = resultSet.getInt(1);
	                }
	            }
	         
	    	if(option.equals("donor")||option.equals("Donor")||option.equals("DONOR")||option.equals("1"))
	    	{
	    		// Use PreparedStatement to avoid SQL injection
		        String sql = "INSERT INTO BloodStorage (bloodgroup) VALUES (?)";
		        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) 
		        {
		            preparedStatement.setString(1, op2);
		            preparedStatement.executeUpdate();
		        }
	         
                if (count >= 2) {
                	 FXMLLoader loader = new FXMLLoader(getClass().getResource("Practice5.fxml"));
     	 	        Parent root = loader.load();
     	 	        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     	             currentStage.setTitle("Donor Scheduling");
     	 	        currentStage.getScene().setRoot(root);
                	
                } else {
                	 FXMLLoader loader = new FXMLLoader(getClass().getResource("Practice7.fxml"));
     	 	        Parent root = loader.load();
     	 	        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     	             currentStage.setTitle("Donor Scheduling");
     	 	        currentStage.getScene().setRoot(root);
                }

            }
	    	else if(option.equals("receiver")||option.equals("Receiver")||option.equals("RECEIVER")||option.equals("2"))
	    	{
	    		// Use PreparedStatement to avoid SQL injection
	    		String deleteQuery = "DELETE FROM BloodStorage WHERE bloodgroup = ?";
	    		try (PreparedStatement deleteStatement = con.prepareStatement(deleteQuery)) {
	    		    deleteStatement.setString(1, op2);

	    		    // Execute the delete query
	    		    deleteStatement.executeUpdate();

	    		}

	    		if(count==0)
	    		{
		    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Practice6.fxml"));
		 	        Parent root = loader.load();
		 	        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		             currentStage.setTitle("Receiving Scheduling");
		 	        currentStage.getScene().setRoot(root);
	    		}
	    		else
	    		{
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("Practice9.fxml"));
		 	        Parent root = loader.load();
		 	        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		             currentStage.setTitle("Receiving Scheduling");
		 	        currentStage.getScene().setRoot(root);
	    		}
	    	}
	    		

            con.close();
            
            
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	@FXML
	private void back(javafx.event.ActionEvent event) {
	    try {
	    	
	    	
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Practice.fxml"));
	        Parent root = loader.load();
	        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setTitle("Welcome");
	        currentStage.getScene().setRoot(root);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
