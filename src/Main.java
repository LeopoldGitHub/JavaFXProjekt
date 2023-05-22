import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.HashMap;

public class Main extends Application {
	protected static HashMap<Integer, Vehicle> carDB = new HashMap<>();
	public static void main(String[] args) {
		testCars();
		launch(args);
	}
	protected static void testCars(){
		carDB.put(Vehicle.getCount().get(), new Vehicle("VW","Golf",320));
		carDB.put(Vehicle.getCount().get(), new Vehicle("Audi","A1",220));
		carDB.put(Vehicle.getCount().get(), new Vehicle("Toyota","Yari",320));
	}
	// anordnung von objekten mithilfe vom Grid
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Aufgabe Vehicle JavaFX");
		GridPane mainGrid = new GridPane();
		Label labelId = new Label("ID");
		Label labelBrand = new Label("Brand");
		Label labelModel= new Label("Model");
		Label labelHP = new Label("HP");
		TextField textFieldID = new TextField(Vehicle.getCount().toString());
		TextField textFieldBrand = new TextField();
		TextField textFieldModel = new TextField();
		TextField textFieldHP = new TextField();
		GridPane gridPaneTop = new GridPane();
		ColumnConstraints column1= new ColumnConstraints();
		column1.setPercentWidth(20);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(80);
		gridPaneTop.getColumnConstraints().addAll(column1,column2);
		gridPaneTop.add(labelId,0,0);
		gridPaneTop.add(labelBrand,0,1);
		gridPaneTop.add(labelModel,0,2);
		gridPaneTop.add(labelHP,0,3);
		gridPaneTop.add(textFieldID,1,0);
		gridPaneTop.add(textFieldBrand,1,1);
		gridPaneTop.add(textFieldModel,1,2);
		gridPaneTop.add(textFieldHP,1,3);
		GridPane gridPaneMid =new GridPane();
		ColumnConstraints bottomColumn = new ColumnConstraints();
		Button btnShowAll = new Button("Anzeigen");
		btnShowAll.setMaxWidth(Double.MAX_VALUE);
		Button btnAdd =new Button("Add");
		btnAdd.setMaxWidth(Double.MAX_VALUE);
		Button btnDelete = new Button("Delete");
		btnDelete.setMaxWidth(Double.MAX_VALUE);
		gridPaneMid.addColumn(0,btnShowAll);
		gridPaneMid.addColumn(1,btnAdd);
		gridPaneMid.addColumn(2,btnDelete);
		bottomColumn.setPercentWidth(33.33);
		gridPaneMid.getColumnConstraints().addAll(bottomColumn,bottomColumn,bottomColumn);
		GridPane gridPaneBottom = new GridPane();
		TextArea textAreaOutput = new TextArea();
		gridPaneBottom.add(textAreaOutput,0,0);
		mainGrid.addRow(0,gridPaneTop);
		mainGrid.addRow(1,gridPaneMid);
		mainGrid.addRow(2,gridPaneBottom);
		btnAdd.setOnAction(actionEvent -> addCar(textFieldBrand,textFieldModel,textFieldHP,textFieldID,textAreaOutput));
		btnShowAll.setOnAction(actionEvent -> showAll(textAreaOutput));
		btnDelete.setOnAction(actionEvent -> deleteCar(textFieldID,textAreaOutput));
		Scene scene = new Scene(mainGrid);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showAll(TextArea textArea){
		textArea.clear();
		carDB.forEach((id,vehicle)-> textArea.appendText(vehicle.toString()+"\n"));
		}
		
	
	public static void addCar(TextField brand,TextField model,TextField hp,TextField id,TextArea outputArea){
		String tempBrand = brand.getText().trim();
		String tempModel= model.getText().trim();
		String tempHP = hp.getText().trim();
		if (tempBrand.isEmpty()||tempModel.isEmpty()||!tempHP.matches("\\d+")){
			brand.clear();
			model.clear();
			hp.clear();
			outputArea.setText("Falsche Eingabe!");
			
		}else {
			carDB.put(Vehicle.getCount().get(), new Vehicle(tempBrand,tempModel,Integer.parseInt(tempHP)));
			id.setText(Vehicle.getCount().toString());
			showAll(outputArea);
		}
		
	
	}
	public static void deleteCar(TextField id,TextArea outputArea){
		Integer input = Integer.parseInt(id.getText().trim());
		if (!carDB.containsKey(input)){
			id.clear();
			outputArea.setText("Falsche Eingabe!");
		}
		else {
			carDB.remove(input);
			showAll(outputArea);
			
		}
	
	}
	
}