
import java.util.*;
import java.io.*;
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
	  escribirDocumento(Lista_Personas);
	  
	  
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
	p.setIMC();
	p.setEstatus(p.getIMC());
	//ejemplo 
	System.out.println(p);
	
	return (p);
	  
  }
  public static void escribirDocumento(ArrayList <Persona> lista) {
	  //creamos el documento. 
	  Date date = new Date();
	  try {
	      File archivo = new File("Registro.txt");
	      FileWriter writer = new FileWriter("Registro.txt");
	      	  writer.write("\tREGISTRO DE DATOS DE IMC\n");
	    	  writer.write("NOMBRE\tEDAD\tPESO\tALTURA\tIMC\tESTADO\n");
	        for (Persona p : lista) {
	        	String texto = p.toString()+"\n";
	        	writer.write(texto);

	      }
	        writer.write("\n\nEste registro fue llenado el :  "+date);
	        writer.close();
	        leerDocumento(archivo);
	    } 
	  catch (IOException e) {	      
	      e.printStackTrace();
	    }
	  
	  
  }
  public static void leerDocumento(File doc)  {
	  try {
	  FileReader fr = new FileReader(doc);
	  BufferedReader br = new BufferedReader(fr);
	  
	  String linea;
      while((linea=br.readLine())!=null)
         System.out.println(linea);
	  
      br.close();
	  }
	  
	  
	  catch(Exception e){
	         e.printStackTrace();
	  
	  	}
	  
  }
  
}