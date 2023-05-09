
import java.util.*;

public class Main{
  public static Scanner scan = new Scanner(System.in);
  public static void main(String[] args) {
	  
	  String pregunta= "si";
	  ArrayList<Persona> Lista_Personas = new ArrayList<Persona>();
	  
	  while (pregunta.toLowerCase().equals("si")) {
		  
		  Lista_Personas.add(IngresarDatos());
		  
		  System.out.println("Quiere ingresar otros datos? si/no");
		  pregunta= scan.next();
		  
	  }
	  
	  System.out.println("Usted a finalizado el llenado de datos");
	  
	  
  }
  public static Persona IngresarDatos() {
	  

  	System.out.println("Buenos Dias amig@ \nIngrese su nombre: ");
	String nombre = scan.next();

	System.out.println("Ingrese su edad: ");
	int edad = scan.nextInt();

	System.out.println("Ingrese su peso: ");
	double peso = scan.nextDouble();

	System.out.println("Ingrese su altura : ");		
	double altura = scan.nextDouble();

	Persona p = new Persona(nombre,peso,altura,edad);
	System.out.println(p);
	return (p);
	  
  }
  
}