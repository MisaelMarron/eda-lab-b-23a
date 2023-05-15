import java.util.*;
public class Ejer01{

	public static void main(String[] args){
		int [] array = {2,4,5,6};
        invertirArray(array);
        
		for (int p : array)
        	System.out.println(" "+p);
	}
    public static void invertirArray(int[] array){
    	for (int i = 0; i < array.length/2; i++) {
            int j = array.length-i-1;
            int a = array[i];
            array[i] = array[j];
            array[j] = a;
		}
        
	}
}
