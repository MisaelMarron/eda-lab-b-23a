
import java.util.*;
public class Persona {
   private String nombre ="";
   private double peso;
   private double IMC = 0;	 
   private double altura;
   private int edad;
 
   public Persona( String n , double p , double a , int edad ){
	this.nombre = n ;
	this.peso = p;
	this.altura = a;
	this.edad = edad;
 
   }	
   
   public Persona(){
	   this("John",0.0,0.0,1);
   }
   
   public double getIMC(){
	return IMC;

   }
   public double setIMC() {
	   return peso/altura*altura;
   }
   public String toString(){
	return nombre + "\t" + edad + "\t" + peso + "\t" + altura + "\t" + setIMC();

   }

}