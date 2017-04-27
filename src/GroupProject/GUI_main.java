package GroupProject;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;



public class GUI_main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*BorderPane root = new BorderPane();
			
			Scene scene = new Scene(root,700,700);
			Button btOK = new Button("OK");
			Scene buttonScene = new Scene(btOK,100,150);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Airline Reservation System");
			primaryStage.show();
			*/
			
			
			//create a flow pane and set its properties
			FlowPane flowPane = new FlowPane();
			flowPane.setPadding(new Insets(40,40,40,40));
			flowPane.setHgap(10);
			flowPane.setVgap(10);
			
			//place nodes in the pane
			flowPane.getChildren().addAll(new Label("Dear Customer, please enter your information here:  "));
			
			
			flowPane.getChildren().addAll(new Label("First Name: "), 
					new TextField());
			flowPane.getChildren().addAll(new Label("Last Name: "), 
					new TextField());
			flowPane.getChildren().addAll(new Label("Username: "), 
					new TextField());
			flowPane.getChildren().addAll(new Label("Password: "), 
					new TextField());
			flowPane.getChildren().addAll(new Label("SSN: "), 
					new TextField());
			flowPane.getChildren().addAll(new Label("Email: "), 
					new TextField());
			flowPane.getChildren().addAll(new Label("Security Question: "), 
					new TextField());
			flowPane.getChildren().addAll(new Label("Security Question Answer: "), 
					new TextField());
			
			TextField text = new TextField();
					text.setPrefColumnCount(50);
					flowPane.getChildren().addAll(new Label("Address: "),text);
					flowPane.getChildren().addAll(new Label("State: "), 
							new TextField());
					flowPane.getChildren().addAll(new Label("Zip Code: "), 
							new TextField());
					
					flowPane.getChildren().add(new Button("Save"));	
				
			
					flowPane.setStyle("-fx-background-color: #D8BFD8;");
					
					
			
					 
			       
			    Scene scene = new Scene(flowPane, 700, 500);
			    primaryStage.setTitle("Airline Reservation System"); // Set the stage title
			    primaryStage.setScene(scene); // Place the scene in the stage
			    primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
