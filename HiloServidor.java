package wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
	BufferedReader fentrada;
	PrintWriter fsalida;
	Socket socket;
	String palabra;
	
	public HiloServidor(Socket socket, String palabra) {
		this.socket = socket;
		try {
			this.fsalida=new PrintWriter(socket.getOutputStream(), true);
			this.fentrada=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.palabra=palabra;
		} catch (IOException e) {
			System.err.println("Error de E/S");
		}
	}

	@Override
	public void run() {
		try {
			int intentos=6;
			String adivina="";
			String comprueba="";
			do {
				//2. Recibo la cadena del cliente
				adivina=fentrada.readLine();
				System.out.println(adivina);
				
				//3. Envia
				if(adivina.equals(palabra)) {
					fsalida.println("HAS GANADO");
				}else {
					comprueba=comparar(adivina, palabra);
					fsalida.println(comprueba);
				}
				
				intentos--;
			}while(intentos>0 && !adivina.equals(palabra));
			
			fsalida.close();
			fentrada.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String comparar(String secreta, String palabra) {
		// TODO Auto-generated method stub
		String adivina="";
		//Comprueba si ha adivinado la posición de alguna letra
		for(int i=0; i<secreta.length(); i++) {
			if(secreta.charAt(i)==palabra.charAt(i)) {
				adivina=adivina+secreta.charAt(i);
			}else {
				adivina=adivina+" _ ";
			}
		}
		return adivina;
	}
}
