// Author: Josiah Swanner
// Purpose: Create a class to store and manipulate complex numbers
// Date created: 3/28/2024
package ComplexDemo;

import java.lang.Math;
import java.util.Objects;

public class Complex {	
	//ATTRIBUTES
	private double real;
	private double imaginary;
	private double radius;
	private double angle;
	
	//CONSTRUCTORS
	//Default Constructor
	public Complex() {
		super();
		real = 0.0;
		imaginary = 0.0;
		radius = 0.0;
		angle = 0.0;
	}

	//Constructor with 2 parameters
	public Complex(double real, double imaginary) {
		super();
		this.real = real;
		this.imaginary = imaginary;
		calcPolar();
	}

	//Copy Constructor
	public Complex(Complex complex) {
		super();
		this.real = complex.getReal();
		this.imaginary = complex.getImaginary();
		this.radius = complex.getRadius();
		this.angle = complex.getAngle();
	}

	//GETTERS AND SETTERS
	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
		calcPolar();
	}

	public double getImaginary() {
		return imaginary;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
		calcPolar();
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		if(radius < 0.0) radius *= -1.0;
		calcCart();
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
		calcCart();
	}
	
	public void setCartesian(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
		calcPolar();
	}
	
	public void setPolar(double radius, double angle) {
		if(radius < 0.0) radius *= -1.0;
		this.radius = radius;
		this.angle = angle;
		calcCart();
	}
	
	//METHODS
	//Override the toString method to output the Cartesian coordinates
	@Override
	public String toString() {
		if(imaginary == 0) return "" + (float) getReal();
		else if(real == 0) return (float) getImaginary() + "i";
		else if(imaginary > 0) return (float) getReal() + "+" + (float) getImaginary() + "i";
		else return (float) getReal() + "" + (float) getImaginary() + "i";
	}
	
	//Override the equals method
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		else if((o == null) || (this.getClass() != o.getClass())) return false;
		Complex complex = (Complex) o;
		return ((this.getReal() == complex.getReal()) && (this.getImaginary() == complex.getImaginary()));
	}
	
	//Override the hashCode method
	@Override
	public int hashCode() {
		return Objects.hash(real, imaginary, radius, angle);
	}
	
	//Method to print the Cartesian coordinates
	public String printCartesian() {
		if(imaginary == 0) return "" + (float) getReal();
		else if(real == 0) return (float) getImaginary() + "i";
		else if(imaginary > 0) return (float) getReal() + "+" + (float) getImaginary() + "i";
		else return (float) getReal() + "" + (float) getImaginary() + "i";
	}
	
	//Method to print the polar coordinates
	public String printPolar() {
		if(angle == 0) return "" + (float) getRadius();
		else if(radius == 0) return "e^i(" + (float) getAngle() + ")";
		else return (float) getRadius() + "*e^i(" + (float) getAngle() + ")";
	}
	
	//Method to calculate the polar coordinates of a complex number
	private void calcPolar() {
		double tReal;
		
		radius = Math.sqrt(Math.pow(getReal(), 2) + Math.pow(getImaginary(), 2));
		
		if((real > 0.0) && (imaginary == 0.0)) angle = 0.0;
		else if((real < 0.0) && (imaginary == 0.0)) angle = Math.PI;
		else if(radius > 0.0) {
			tReal = getReal() / radius;
			if(imaginary >= 0.0) angle = Math.acos(tReal);
			else if(imaginary < 0.0) angle = -Math.acos(tReal);
		}
		else angle = 0.0;
	}
	
	//Method to calculate the Cartesian coordinates of a complex number
	private void calcCart() {
		real = (getRadius() * Math.cos(getAngle()));
		imaginary = (getRadius() * Math.sin(getAngle()));
	}
	
	//Methods to add complex numbers
	public void add(Complex input) {
		this.real += input.getReal();
		this.imaginary += input.getImaginary();
		calcPolar();
	}
	
	public void add(double real, double imaginary) {
		this.real += real;
		this.imaginary += imaginary;
		calcPolar();
	}
	
	public static Complex add(Complex inOne, Complex inTwo) {
		double real;
		double imaginary;
		real = inOne.getReal() + inTwo.getReal();
		imaginary = inOne.getImaginary() + inTwo.getImaginary();
		return new Complex(real, imaginary);
	}
	
	//Methods to subtract complex numbers
	public void subtract(Complex input) {
		this.real -= input.getReal();
		this.imaginary -= input.getImaginary();
		calcPolar();
	}
	
	public void subtract(double real, double imaginary) {
		this.real -= real;
		this.imaginary -= imaginary;
		calcPolar();
	}
	
	public static Complex subtract(Complex inOne, Complex inTwo) {
		double real;
		double imaginary;
		real = inOne.getReal() - inTwo.getReal();
		imaginary = inOne.getImaginary() - inTwo.getImaginary();
		return new Complex(real, imaginary);
	}
	
	//Method to multiply this complex number by another one
	public void multiply(Complex input) {
		this.radius *= input.radius;
		this.angle += input.angle;
		calcCart();
	}
	
	public void multiply(double real, double imaginary) {
		Complex temp = new Complex(real, imaginary);
		this.radius *= temp.getRadius();
		this.angle += temp.getAngle();
		calcCart();
	}
	
	public static Complex multiply(Complex inOne, Complex inTwo) {
		double radius;
		double angle;
		Complex complex = new Complex();
		radius = inOne.getRadius() * inTwo.getRadius();
		angle = inOne.getAngle() + inTwo.getAngle();
		complex.setPolar(radius, angle);
		return complex;
	}
	
	//Methods to divide complex numbers
	public void divide(Complex input) {
		this.radius /= input.radius;
		this.angle -= input.angle;
		calcCart();
	}
	
	public void divide(double real, double imaginary) {
		Complex temp = new Complex(real, imaginary);
		this.radius /= temp.getRadius();
		this.angle -= temp.getAngle();
		calcCart();
	}
	
	public static Complex divide(Complex inOne, Complex inTwo) {
		double radius;
		double angle;
		Complex temp = new Complex();
		radius = inOne.getRadius() / inTwo.getRadius();
		angle = inOne.getAngle() - inTwo.getAngle();
		temp.setPolar(radius, angle);
		return temp;
	}
	
	//Methods to raise complex numbers to powers
	public void power(double pow) {
		radius = Math.pow(getRadius(), pow);
		angle *= pow;
		calcCart();
	}
	
	public static Complex power(Complex input, double pow) {
		double radius;
		double angle;
		Complex temp = new Complex();
		radius = Math.pow(input.getRadius(), pow);
		angle = input.getAngle() * pow;
		temp.setPolar(radius, angle);
		return temp;
	}
	
	//Methods to take the sine of a complex number
	public void sin() {
		double tempR = ((Math.pow(Math.E, -getImaginary()) + Math.pow(Math.E, getImaginary())) * Math.sin(getReal())) / 2.0;
		double tempI = ((Math.pow(Math.E, getImaginary()) - Math.pow(Math.E, -getImaginary())) * Math.cos(getReal())) / 2.0;
		real = tempR;
		imaginary = tempI;
		calcPolar();
	}
	
	public static Complex sin(Complex input) {
		double tempR = ((Math.pow(Math.E, -input.getImaginary()) + Math.pow(Math.E, input.getImaginary())) * Math.sin(input.getReal())) / 2.0;
		double tempI = ((Math.pow(Math.E, input.getImaginary()) - Math.pow(Math.E, -input.getImaginary())) * Math.cos(input.getReal())) / 2.0;
		return new Complex(tempR, tempI);
	}
	
	public static Complex sin(double real, double imaginary) {
		double tempR = ((Math.pow(Math.E, -imaginary) + Math.pow(Math.E, imaginary)) * Math.sin(real)) / 2.0;
		double tempI = ((Math.pow(Math.E, imaginary) - Math.pow(Math.E, -imaginary)) * Math.cos(real)) / 2.0;
		return new Complex(tempR, tempI);
	}
	
	//Methods to take the cosine of a complex number
	public void cos() {
		double tempR = ((Math.pow(Math.E, -getImaginary()) + Math.pow(Math.E, getImaginary())) * Math.cos(getReal())) / 2.0;
		double tempI = ((Math.pow(Math.E, -getImaginary()) - Math.pow(Math.E, getImaginary())) * Math.sin(getReal())) / 2.0;
		real = tempR;
		imaginary = tempI;
		calcPolar();
	}
	
	public static Complex cos(Complex input) {
		double tempR = ((Math.pow(Math.E, -input.getImaginary()) + Math.pow(Math.E, input.getImaginary())) * Math.cos(input.getReal())) / 2.0;
		double tempI = ((Math.pow(Math.E, -input.getImaginary()) - Math.pow(Math.E, input.getImaginary())) * Math.sin(input.getReal())) / 2.0;
		return new Complex(tempR, tempI);
	}
	
	public static Complex cos(double real, double imaginary) {
		double tempR = ((Math.pow(Math.E, -imaginary) + Math.pow(Math.E, imaginary)) * Math.cos(real)) / 2.0;
		double tempI = ((Math.pow(Math.E, -imaginary) - Math.pow(Math.E, imaginary)) * Math.sin(real)) / 2.0;
		return new Complex(tempR, tempI);
	}
	
	//Methods to take the natural log of a complex number
	public void ln() {
		real = Math.log(getRadius());
		imaginary = getAngle();
		calcPolar();
	}
	
	public static Complex ln(Complex input) {
		double tempR = Math.log(input.getRadius());
		double tempI = input.getAngle();
		return new Complex(tempR, tempI);
	}
	
	public static Complex ln(double real, double imaginary) {
		Complex output = new Complex(real, imaginary);
		output.ln();
		return output;
	}
	
	//Methods to take the inverse sine of a complex number
	public void arcsin() {
		Complex temp = new Complex((1 - Math.pow(getReal(), 2.0) + Math.pow(getImaginary(), 2.0)), (-2.0 * getReal() * getImaginary()));
		temp.power(0.5);
		temp.add(-getImaginary(), getReal());
		temp.ln();
		temp.multiply(0.0, -1.0);
		setCartesian(temp.getReal(), temp.getImaginary());
	}
	
	public void arcsin(Complex outOne, Complex outTwo) {
		Complex temp = new Complex((1 - Math.pow(getReal(), 2.0) + Math.pow(getImaginary(), 2.0)), (-2.0 * getReal() * getImaginary()));
		temp.power(0.5);
		
		outOne = new Complex(-getImaginary(), getReal());
		outOne.add(temp);
		outOne.ln();
		outOne.multiply(0.0, -1.0);
		
		outTwo = new Complex(-getImaginary(), getReal());
		outTwo.subtract(temp);
		outTwo.ln();
		outTwo.multiply(0.0, -1.0);
	}
	
	public static Complex arcsin(Complex input) {
		Complex output = new Complex((1 - Math.pow(input.getReal(), 2.0) + Math.pow(input.getImaginary(), 2.0)), (-2.0 * input.getReal() * input.getImaginary()));
		output.power(0.5);
		output.add(-input.getImaginary(), input.getReal());
		output.ln();
		output.multiply(0.0, -1.0);
		return output;
	}
	
	public static void arcsin(Complex input, Complex outOne, Complex outTwo) {
		Complex temp = new Complex((1 - Math.pow(input.getReal(), 2.0) + Math.pow(input.getImaginary(), 2.0)), (-2.0 * input.getReal() * input.getImaginary()));
		temp.power(0.5);
		
		outOne = new Complex(-input.getImaginary(), input.getReal());
		outOne.add(temp);
		outOne.ln();
		outOne.multiply(0.0, -1.0);
		
		outTwo = new Complex(-input.getImaginary(), input.getReal());
		outTwo.subtract(temp);
		outTwo.ln();
		outTwo.multiply(0.0, -1.0);
	}
	
	//Methods to take the inverse cosine of a complex number
	public void arccos() {
		Complex temp = new Complex((Math.pow(getReal(), 2) - Math.pow(getImaginary(), 2) - 1), (2.0 * getReal() * getImaginary()));
		temp.power(0.5);
		temp.add(getReal(), getImaginary());
		temp.ln();
		temp.multiply(0.0, -1.0);
		setCartesian(temp.getReal(), temp.getImaginary());
	}
	
	public void arccos(Complex outOne, Complex outTwo) {
		Complex temp = new Complex((Math.pow(getReal(), 2) - Math.pow(getImaginary(), 2) - 1), (2.0 * getReal() * getImaginary()));
		
		outOne = new Complex(getReal(), getImaginary());
		outOne.add(temp);
		outOne.ln();
		outOne.multiply(0.0, -1.0);
		
		outTwo = new Complex(getReal(), getImaginary());
		outTwo.subtract(temp);
		outTwo.ln();
		outTwo.multiply(0.0, -1.0);
	}
	
	public static Complex arccos(Complex input) {
		Complex output = new Complex((Math.pow(input.getReal(), 2) - Math.pow(input.getImaginary(), 2) - 1), (2.0 * input.getReal() * input.getImaginary()));
		output.power(0.5);
		output.add(input.getReal(), input.getImaginary());
		output.ln();
		output.multiply(0.0, -1.0);
		return output;
	}
	
	public static void arccos(Complex input, Complex outOne, Complex outTwo) {
		Complex temp = new Complex((Math.pow(input.getReal(), 2) - Math.pow(input.getImaginary(), 2) - 1), (2.0 * input.getReal() * input.getImaginary()));
		
		outOne = new Complex(input.getReal(), input.getImaginary());
		outOne.add(temp);
		outOne.ln();
		outOne.multiply(0.0, -1.0);
		
		outTwo = new Complex(input.getReal(), input.getImaginary());
		outTwo.subtract(temp);
		outTwo.ln();
		outTwo.multiply(0.0, -1.0);
	}

}
