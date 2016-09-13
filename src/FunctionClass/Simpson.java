package FunctionClass;

import java.util.Random;
/**
 * Class Function Simpson for calculate integrate with a simmetric function
 * @author Juan UdeA
 *
 */
public class Simpson {
	private double x_Value;
	private double dof_Value;
	private double acceptable_Error;
	
	
	public Simpson(double x_value, double dof){
		this.x_Value = x_value;
		this.dof_Value = dof;
	}
	
	public void setAcceptError(double error){
		this.acceptable_Error = error;
	}
	public double getAcceptError(){
		return this.acceptable_Error;
	}
	
	/*
	 * Function gamma, this function is implemnt for "calculator function"
	 */
	private double calculator_Gamma(double x){
		double gamma = 1;
		if(x == 1)return 1;
		if(x == 0.5)return Math.pow(Math.PI, 0.5);
		gamma =  (x-1) * this.calculator_Gamma(x-1); 
		return gamma;
	}
	
	/*
	 * Normal function that use the dof and the value x for implement  the gamma function
	 */
	private double calculator_Function(double value){
		double function_value = 0;
		double num = (this.calculator_Gamma((double)(this.dof_Value+1)/2));
		double den = Math.pow((double)(this.dof_Value * Math.PI), 0.5)*this.calculator_Gamma((double)this.dof_Value/2);
		function_value =  Math.pow(((double)(1 + (Math.pow(value, 2))/this.dof_Value)), (double)(-(this.dof_Value+1)/2));
		return function_value* ((double)num/den);
	}
	
	public double calculator_Simpson(){
		double simpson = 0;
		double width;
		Random random = new Random();
		int num_seg = Math.abs(random.nextInt(10000) + 1) * 2;
		width = this.x_Value/num_seg;
		simpson =(double) width/3;
		double first_sum = 0;
		double second_sum = 0;
		
		for(int i = 1; i < num_seg - 1; i=i+2){
			first_sum += calculator_Function((double)(i * width));
		}
		first_sum*=4;
		for(int j = 2; j < num_seg- 2;j=j+2){
			second_sum += calculator_Function((double)(j * width));
		}
		second_sum *=2;
		simpson = (double)simpson*(calculator_Function(0) + first_sum + second_sum + calculator_Function(this.x_Value));
		return simpson;
		
	}
	
}
