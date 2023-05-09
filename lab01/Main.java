
import java.util.*;

public class Ingresar_Datos {
  public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);

    	System.out.println("Buenos Dias amig@ \nIngrese su nombre ");
	String nombre = scan.nextLine();

	System.out.println("Ingrese su edad: ");
	int edad = scan.nextInt();

	System.out.println("Ingrese su peso: ");
	double peso = scan.nextDouble();

	System.out.println("Ingrese su altura : ");		
	double altura = scan.nextDouble();awdadw

	Persona p1 = new Persona(nombre,peso,altura,edad);
	System.out.println(p1);
  }
}