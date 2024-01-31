package wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 6000;
		Scanner sc = new Scanner(System.in);
		try {
			Socket cliente = new Socket(host, puerto);
			PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			int intento = 6;
			String cadena = "";
			String eco = "";
			String usuario = "";
			System.out.println("\tWORDLE");
			do {
				do {
					System.out.println("Dame una palabra de 5 letras: ");
					cadena = sc.nextLine().toUpperCase();
				} while (!cadena.matches("^[A-Z]{5}$"));

				// 1. Envío la cadena al hilo
				fsalida.println(cadena);

				// 4. Recibo la cadena modificada
				eco = fentrada.readLine();
				System.out.println(eco);
				
				intento--;
			} while (intento > 0 && !eco.equals("HAS GANADO"));
			
			if(!eco.equals("HAS GANADO") && intento==0) {
				System.out.println("Te has quedado sin intentos");
			}

			fsalida.close();
			fentrada.close();
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
