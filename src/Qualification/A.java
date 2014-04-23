package Qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class A
{

	private static PrintWriter pw;
	private static boolean test;
	private static BufferedReader br;

	public static void main( String[] args ) throws Exception
	{
		// Imprimir a la consola (true) o al archivo (false)
		test = false;
		String baseName = "A"; // Nombre del archivo

		pw = null;
		if( !test )
		{
			pw = new PrintWriter( new File( "./data/" + baseName + ".out" ) );
		}

		// Abrir el archivo con los datos de entrada
		br = new BufferedReader( new FileReader( new File( "./data/" + baseName + ".in" ) ) );

		solucionarProblema( );

		// Si no era una prueba, cerrar el archivo de salida
		if( !test )
		{
			pw.close( );
		}

	}

	/**
	 * Este es el mÈtodo que realmente soluciona el problema
	 * @throws IOException
	 */
	private static void solucionarProblema( ) throws IOException
	{
		String cantString = br.readLine();
		int cantidadPruebas = Integer.parseInt(cantString);
		for (int i = 0; i < cantidadPruebas; i++) {
			int row1 = Integer.parseInt(br.readLine());
			byte[] linea1 = buscarLinea1(row1);
			int row2 = Integer.parseInt(br.readLine());
			String respuesta = "Case #"+(i+1)+": "+darRespuesta(row2, linea1);
			if(test) System.out.println(respuesta);
			else pw.println(respuesta);
		}
	}

	public static byte[] buscarLinea1(int row) throws IOException
	{
		byte[] respuesta = new byte[16];
		for(int i = 0; i<16; i++) respuesta[i] = 0;
		int cont = 1;
		String linea = br.readLine();
		while(cont != row)
		{
			linea = br.readLine();
			cont ++;
		}
		
		String[] rowStrings = linea.split(" ");
		for (String rowString : rowStrings) {
			respuesta[Integer.parseInt(rowString)-1] = 1;
		}

		while(cont != 4)
		{
			linea = br.readLine();
			cont ++;
		}
		
		return respuesta;
	}
	
	public static String darRespuesta(int row, byte[]linea1) throws IOException
	{
		int cont = 1;
		String linea = br.readLine();
		while(cont != row)
		{
			linea = br.readLine();
			cont ++;
		}
		
		int filaEncontrada = 0;
		int cantidadEncontrados = 0;
		
		String[] rowStrings = linea.split(" ");
		for (String rowString : rowStrings) {
			int filaARevisar = Integer.parseInt(rowString)-1;
			if(linea1[filaARevisar] == 1)
			{
				filaEncontrada = filaARevisar;
				cantidadEncontrados ++;
			}
		}
		
		while(cont != 4)
		{
			linea = br.readLine();
			cont ++;
		}
		
		if(cantidadEncontrados == 0) return "Volunteer cheated!";
		if(cantidadEncontrados == 1) return ""+(filaEncontrada+1);
		return "Bad magician!";
	}

}