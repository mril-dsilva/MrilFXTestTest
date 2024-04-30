import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CalcModel;
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
		    CalcModel model =new CalcModel(); 
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
	
	@Test
	public void TestButton(FxRobot robot) {
		try
		{
			Thread.sleep(1000);
			robot.clickOn("#addButton");
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}