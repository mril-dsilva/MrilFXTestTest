import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CalcModel;
import model.Operation;
import view.MainController;

@ExtendWith(ApplicationExtension.class)
public class CalcTestFX
{
	@Start // Before
	private void start(Stage stage) {
		
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainController.class.getResource("../view/main.fxml"));
	    
		try
		{
			BorderPane view = loader.load();
			MainController cont = loader.getController();
		    CalcModel model = new CalcModel(); 
		    cont.setModel(model);
		    Scene s = new Scene(view);
		    stage.setScene(s);
		    stage.show();
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//Addition helper
	private void add(String num1, String num2, FxRobot robot) {
		robot.clickOn("#num1Input");
		robot.write(num1);
		robot.clickOn("#num2Input");
		robot.write(num2);
		robot.clickOn("#addButton");
	}
	
	//Subtraction helper
	private void subtract(String num1, String num2, FxRobot robot) {
		robot.clickOn("#num1Input");
		robot.write(num1);
		robot.clickOn("#num2Input");
		robot.write(num2);
		robot.clickOn("#subtractButton");
	}
	
	//Multiply helper
	private void multiply(String num1, String num2, FxRobot robot) {
		robot.clickOn("#num1Input");
		robot.write(num1);
		robot.clickOn("#num2Input");
		robot.write(num2);
		robot.clickOn("#multiplyButton");
	}
	
	//Divide helper
	private void divide(String num1, String num2, FxRobot robot) {
		robot.clickOn("#num1Input");
		robot.write(num1);
		robot.clickOn("#num2Input");
		robot.write(num2);
		robot.clickOn("#divideButton");
	}
	
	//CheckResult
	private void check(String result, FxRobot robot) {
		Assertions.assertThat(robot.lookup("#resultText")
				.queryAs(Label.class)).hasText(result);
	}
	

	@SuppressWarnings("unchecked")
	private ListView<Operation> getHistory(FxRobot robot){
		return (ListView<Operation>) robot.lookup("#historyList")
				.queryAll().iterator().next();
	}

	@Test
	public void TestCalculator(FxRobot robot) {
		try
		{
			//Check if History is empty
			ListView<Operation> operations = getHistory(robot);
			System.out.println(operations);
			
			Assertions.assertThat(operations).isEmpty();
			
			//ADD
			String num1 = "7";
			String num2 = "10";
			add(num1,num2,robot); //checked addition
			//Thread.sleep(1000);
			String result = "17";
			check(result,robot); //checked result

			//SUBTRACT
			subtract(num1,num2,robot); //checked subtraction
			//Thread.sleep(1000);
			String result2 = "-3";
			check(result2,robot); //checked result

			//MULTIPLY
			multiply(num1,num2,robot); //checked multiplication
			//Thread.sleep(1000);
			String result3 = "70";
			check(result3,robot); //checked result
			
			//DIVIDE
			divide(num1,num2,robot); //checked division
			//Thread.sleep(1000);
			String result4 = "0.7";
			check(result4,robot); //checked result
			
			//DIVIDE EDGE CASE (DIVIDE BY ZERO)
			String num3 = "0";
			divide(num1,num3,robot); //checked division
			Thread.sleep(1000);
			String result5 = "NaN";
			check(result5,robot); //checked result
			
			ObservableList<Operation> items = FXCollections.observableArrayList(
					new Operation(7.0, " + ", 10.0, 17.0),
	                new Operation(7.0, " - ", 10.0, -3.0),
	                new Operation(7.0, " * ", 10.0, 70.0),
	                new Operation(7.0, " / ", 10.0, 0.7)
			);
			
			Assertions.assertThat(operations).isNotEmpty();
			System.out.println(operations);
			
			for(Operation i:items) {
				
				Assertions.assertThat(operations).hasListCell(i);
				
			}
			
			
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}