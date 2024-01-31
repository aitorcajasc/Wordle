package wordle;

import java.util.Scanner;

public class Wordle {
	public static void main(String[] args) {
		String []palabras= {"PLATO","PISAR","PLANO","MAREO","LISTA","LISTO",
				"SUCIO","PERRO","MIXTO","BULTO","CASTO","PRADO","MOSCA","PISTO",
				"TURCO","BRAVO","VISTO","QUESO","GUISO","USADO"};
		
		String usuario="";
		Scanner teclado=new Scanner(System.in);
			
		//Genero un número aleatorio entre 0 y 20 para seleccionar la palabra
		int posicion=(int)(Math.random()*20);
		int intentos=6;
		String adivina="";
		String informacion="";
		System.out.println(palabras[posicion]);
		int i=0;
		do {
			//Bucle para solo comprobar la palabra cuando es de 
			//longitud 5
			do {
				System.out.println("Dame una palabra de 5 letras");
				usuario=teclado.next();
			}while(!usuario.matches("^[A-Z]{5}$"));
			
			adivina=comparar(palabras[posicion],usuario);
			System.out.println(adivina);
			i++;
			intentos--;
		}while(intentos>=0 && !palabras[posicion].equals(usuario));
		
		if(palabras[posicion].equals(usuario))
			System.out.println("Espléndido!!");
		else
			System.out.println("La palabra era: "+palabras[posicion]);
	}

	private static boolean repetida(String contiene, char letra) {
		for(int i=0;i<contiene.length(); i++)
			if(contiene.charAt(i)==letra)
				return true;
		
		return false;
	}

	private static String comparar(String secreta, String usuario) {
		// TODO Auto-generated method stub
		String adivina="";
		//Comprueba si ha adivinado la posición de alguna letra
		for(int i=0; i<secreta.length(); i++) {
			if(secreta.charAt(i)==usuario.charAt(i)) {
				adivina=adivina+secreta.charAt(i);
			}else {
				adivina=adivina+" _ ";
			}
		}
		
		return adivina;
	}
}