package model;

import java.util.Objects;

public class Operation
{
	double num1;
	double num2;
	
	String op;
	double result;
	public Operation(double num1, String op, double num2,double result)
	{
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.op = op;
		this.result = result;
	}
	
	@Override
	public String toString()
	{
		return num1 + op + num2 + " = " + result;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(num1, num2, op, result);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		return Double.doubleToLongBits(num1) == Double.doubleToLongBits(other.num1)
				&& Double.doubleToLongBits(num2) == Double.doubleToLongBits(other.num2) && Objects.equals(op, other.op)
				&& Double.doubleToLongBits(result) == Double.doubleToLongBits(other.result);
	}
	
	
}
