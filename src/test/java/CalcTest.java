import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CalcModel;
import model.Operation;

class CalcTest
{
	CalcModel calcModel;
	
	@BeforeEach
	void setUp() throws Exception
	{
		calcModel = new CalcModel();
	}
	
	//private Operation operation = new Operation(10.0, "+", 5.0, 15.0);
	
	
	@Test
	void test()
	{
		assertNotNull(calcModel);
		// Test addition
        calcModel.setNum1(new SimpleDoubleProperty(5.0));
        assertEquals(5.0, calcModel.getNum1().get());
        calcModel.setNum2(new SimpleDoubleProperty(3.0));
        assertEquals(3.0, calcModel.getNum2().get());
        calcModel.add();
        
        assertEquals(8.0, calcModel.getResult().get());
        calcModel.setResult(new SimpleDoubleProperty(7.7));
        assertEquals(7.7, calcModel.getResult().get());

        // Test subtraction
        calcModel.setNum1(new SimpleDoubleProperty(10.0));
        calcModel.setNum2(new SimpleDoubleProperty(4.0));
        calcModel.subtract();
        
        assertEquals(6.0, calcModel.getResult().get());

        // Test multiplication
        calcModel.setNum1(new SimpleDoubleProperty(2.0));
        calcModel.setNum2(new SimpleDoubleProperty(5.0));
        calcModel.multiply();
        
        assertEquals(10.0, calcModel.getResult().get());

        // Test division
        calcModel.setNum1(new SimpleDoubleProperty(15.0));
        calcModel.setNum2(new SimpleDoubleProperty(3.0));
        calcModel.divide();
        
        assertEquals(5.0, calcModel.getResult().get());
        
        // Test division by zero (undefined output)
        calcModel.setNum1(new SimpleDoubleProperty(10.0));
        calcModel.setNum2(new SimpleDoubleProperty(0.0));
        calcModel.divide();
        assertTrue(Double.isNaN(calcModel.getResult().get()));
        
	}
	
	@Test // Test getters and setters for operations
    void operations() {
        Operation operation = new Operation(10.0, "+", 5.0, 15.0);
        assertEquals("10.0+5.0 = 15.0", operation.toString());
        
        ObservableList<Operation> operations = FXCollections.observableArrayList(
                new Operation(10.0, "+", 5.0, 15.0),
                new Operation(20.0, "-", 7.0, 13.0)
        );
        
        calcModel.setOperations(operations);
        assertEquals(operations,calcModel.getOperations());
        assertEquals(2, calcModel.getOperations().size());
        
    }

}
