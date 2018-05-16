/**
 * Fraction - a class to handle fractions
 * 
 */

import java.lang.IllegalArgumentException;

public class Fraction{

    private int num, den;       // Numerator and denominator

    public Fraction(){
        this.num = 0;
        this.den = 1;
    }

    public Fraction(int num, int den) throws IllegalArgumentException {
        if (den==0){
            throw new IllegalArgumentException("Denominator cannot be zero (0).");
        }
        this.num = num;
        this.den = den;
    }

    public Fraction(Fraction other){
        this.num = other.getNumerator();
        this.den = other.getDenominator();
    }

    public int getNumerator(){
        return this.num;
    }

    public int getDenominator(){
        return this.den;
    }

    public void setNumerator(int num){
        this.num = num;
    }

    public void setDenominator(int den) throws IllegalArgumentException{
        if (den==0)
            throw new IllegalArgumentException("Denominator cannot be zero (0).");
        this.den = den;
    }

    public String toString(){
    	return "" + this.num + "/" + this.den;
    }

    public void add(Fraction other){
        this.num += other.getNumerator();
        this.den += other.getDenominator();
    }
    
    public void subtract(Fraction other){
        this.num -= other.getNumerator();
        this.den -= other.getDenominator();
    }
    
    public double value() {
    	return num/den;
    }

}