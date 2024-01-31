package wordle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	public static void main(String[] args) {
		String []palabras= {"PLATO","PISAR","PLANO","MAREO","LISTA","LISTO",
				"SUCIO","PERRO","MIXTO","BULTO","CASTO","PRADO","MOSCA","PISTO",
				"TURCO","BRAVO","VISTO","QUESO","GUISO","USADO"};
		Scanner teclado=new Scanner(System.in);
		int posicion=(int)(Math.random()*20);
		System.out.println(palabras[posicion]);
		try {
			ServerSocket servidor=new ServerSocket(6000);
			System.out.println("Servidor iniciado");
			
			while(true) {
				Socket cliente=servidor.accept();
				HiloServidor h=new HiloServidor(cliente, palabras[posicion]);
				h.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
