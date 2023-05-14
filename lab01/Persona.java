
import java.util.*;
public class Persona {
   private String nombre ="";
   private double peso;
   private double IMC = 0;	 
   private double altura;
   private int edad;
   private String estatus=""; 
 
   public Persona( String n , double p , double a , int edad ){
	this.nombre = n ;
	this.peso = p;
	this.altura = a/100;
	this.edad = edad;
 
   }	
   
   public Persona(){
	   this("John",0.0,0.0,1);
   }
   
   public void setIMC() {
	   IMC= peso/(altura*altura);

   }
   public double getIMC(){
		double redond =Math.round(IMC*10);
		return redond/10;
	   }
   public void setEstatus(double imc) {
	   if (imc <18.5)
		   estatus= "Bajo Peso";
	   else if (imc>18.5 && imc <25)
		   estatus = "Saludable";
	   else if (imc>=25 && imc <30)
		   estatus ="Sobrepeso";
	   else 
		   estatus="Obesidad";
   }
   
   public String toString(){
	return nombre + "\t" + edad + "\t" + peso + "\t" + altura + "\t" + getIMC()+"\t"+estatus;

   }

}