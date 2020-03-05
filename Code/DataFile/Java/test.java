import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.event.EventHandler;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.PrintWriter;
import javafx.scene.control.Alert;
import javafx.application.Platform;


public class test extends Application{
 
	int index=-1;
	String dataPath=System.getProperty("user.home")+"\\SnightCoder_Data\\Java_LightWeight_IDE";
	String BPath=dataPath+"\\"+"Build.bat";
	String RPath=dataPath+"\\"+"Run.bat";
	String JPath=dataPath+"\\"+"JarBuild.bat";
	String CreateDataFilePath=dataPath+"\\"+"CreateDataFile.vbs";
	String BARPath=dataPath+"\\"+"BuildAndRun.bat";
	String IDEprojectPath=dataPath+"\\"+"ProjectPath.txt";
	String projectPath;
	String projectPath_JAVA;
	String mainClassName;
 
    public static void main(String[] args) {
        launch(args);
    }
	
    @Override
    public void start(Stage primaryStage) {
		try{
			BeforeStart();
			LoadSampleForm(primaryStage);
		}
	   catch(Exception e){
			System.out.println(e);	
			showAlert("Alert","Can't find any projects","Please set project path in "+IDEprojectPath);			
	   }
	
    }
	
	public void RunFile(String path){
		File file = new File(path);
		Desktop desktop = Desktop.getDesktop();
		try {
		desktop.open(file);
		
		} catch (Exception e1) {
			
		}
	}
	
	//#Sample Form
	Button button,btnB,btnR,btnSIF,btnPathSave,btnMainSave,btnJarExport,btnReloadList;
	Label pathtext,maintext;
	TextField pathTF,mainTF;
	ListView<String> listView;

	public void LoadSampleForm(Stage primaryStage){	
		primaryStage.setTitle("Java LightWeight IDE - SnightCoder");
		
		pathTF=new TextField(projectPath);
		pathTF.setLayoutX(105);
		pathTF.setLayoutY(5);
		
		mainTF=new TextField(mainClassName);
		mainTF.setLayoutX(105);
		mainTF.setLayoutY(45);
		
		pathtext=new Label("Project Path:");
		pathtext.setLayoutX(10);
		pathtext.setLayoutY(10);
		
		maintext=new Label("Main Class:");
		maintext.setLayoutX(10);
		maintext.setLayoutY(50);
		
		button = new Button();
        button.setText("Build And Run");
		
		btnB = new Button();
        btnB.setText("Build");
		
		btnR=new Button();
		btnR.setText("Run");
		
		btnSIF=new Button();
		btnSIF.setText("Show Java Files In Folder");
		
		btnReloadList=new Button("Reload Java Files List");
		btnReloadList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				listView.getItems().clear();
				ReloadItemJavaList();
            }
        });
		
		btnReloadList.setLayoutX(10);
		btnReloadList.setLayoutY(460);
		btnReloadList.setPrefWidth(245);
		
		btnPathSave=new Button("Save Project Path");	
		
		btnPathSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				WriteFile(IDEprojectPath,pathTF.getText()+"");	
				RunFile(CreateDataFilePath);
				showAlert("Alert","Saved","This program will be terminated, You need to open the program again");		
				Platform.exit();
            }
        });
		
		btnPathSave.setLayoutX(300);
		btnPathSave.setLayoutY(5);
		
		btnMainSave=new Button("Save Main Class");
		
		btnMainSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				WriteFile(projectPath+"\\"+"MainClass.txt",mainTF.getText()+"");	
            }
        });
		
		btnMainSave.setLayoutX(300);
		btnMainSave.setLayoutY(45);
		
		btnJarExport=new Button("Export JAR File");
		
		btnJarExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				RunFile(JPath);
            }
        });
		
		btnJarExport.setLayoutX(10);
		btnJarExport.setLayoutY(500);
		
		btnJarExport.setPrefWidth(500);
		
		btnSIF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				
					RunFile(projectPath_JAVA);
            }
        });
		
		btnSIF.setLayoutX(265);
		btnSIF.setLayoutY(460);
		
		btnSIF.setPrefWidth(245);
		
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
					RunFile(BARPath);
            }
        });
		
		button.setLayoutX(350);
        button.setLayoutY(100);
		
		button.setPrefWidth(160);
		
		btnB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				RunFile(BPath);
            }
        });
		
		btnB.setLayoutX(10);
        btnB.setLayoutY(100);
		
		btnB.setPrefWidth(160);
		
		
		btnR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				RunFile(RPath);
            }
        });
		
		btnR.setLayoutX(180);
        btnR.setLayoutY(100);
		
		btnR.setPrefWidth(160);
		
		
	    listView = new ListView<>();
		
		ReloadItemJavaList();
		
		listView.setPrefWidth(500);
		listView.setPrefHeight(300);
		
		listView.setLayoutX(10);
		listView.setLayoutY(150);
		
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

		
        @Override
        public void handle(MouseEvent event) {
			try{
			if(listView.getSelectionModel().getSelectedIndex()==index){
            System.out.println(""+listView.getSelectionModel().getSelectedItem());
			
			File file = new File(listView.getSelectionModel().getSelectedItem().toString());
			Desktop desktop = Desktop.getDesktop();
			try {
			desktop.open(file);
			} catch (Exception e1) { }	
			listView.getSelectionModel().clearSelection(index);
			index=-1;
			}
			index=listView.getSelectionModel().getSelectedIndex();
			}catch(Exception e){
				System.out.println(e);
			} 	
        }
		});
		
		

        Pane layout = new Pane();
        layout.getChildren().addAll(btnReloadList,pathTF,btnPathSave,listView,button,btnB,btnR,btnSIF,pathtext,mainTF,maintext,btnMainSave,btnJarExport);
        Scene scene = new Scene(layout, 520, 600);


		
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	//#End Sample Form
	void BeforeStart(){
		RunFile(CreateDataFilePath);
		projectPath=ReadFirstLineOfFile(IDEprojectPath);
		projectPath_JAVA=projectPath+"\\DataFile\\Java";
		mainClassName=ReadFirstLineOfFile(projectPath+"\\"+"MainClass.txt");	
	}
	void ReloadItemJavaList(){
		File repo = new File (projectPath_JAVA);
        File[] fileList = repo.listFiles();
		int i=0;
		for (File f : fileList) {
			//System.out.println(f);
			listView.getItems().add(i,f+"");
			i++;
		}
	}
	String ReadFirstLineOfFile(String Path){
		String line="";
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(Path)))) {
		line = reader.readLine();
        //while ((line = reader.readLine()) != null)		
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e);
		}
		return line;
	}
	void WriteFile(String path,String text){
		try{
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.println(text);
		writer.close();
		}
		catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void showAlert(String title, String header, String text) { 
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();
	}
}