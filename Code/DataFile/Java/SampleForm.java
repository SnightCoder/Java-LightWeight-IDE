import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SampleForm extends Application{
 
    public static void main(String[] args) {
        launch(args);
    }
	
    @Override
    public void start(Stage primaryStage) {
       LoadSampleForm(primaryStage);
    }
	
	//#Sample Form
	Button button;
	int i=0;
	public void LoadSampleForm(Stage primaryStage){	
		primaryStage.setTitle("Sample Form");	
		button = new Button();
        button.setText("Click Me");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				i++;
                System.out.println("You Clicked The Button "+i+" Times");
				button.setText(i+"");
            }
        });
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	//#End Sample Form

}