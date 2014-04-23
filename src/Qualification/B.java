package Qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class B
{

	private static PrintWriter pw;
	private static boolean test;
	private static BufferedReader br;

	public static void main( String[] args ) throws Exception
	{
		// Imprimir a la consola (true) o al archivo (false)
		test = false;
		String baseName = "B"; // Nombre del archivo

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
			String[] datos = br.readLine().split(" ");
			double c = Double.parseDouble(datos[0]);
			double f = Double.parseDouble(datos[1]);
			double x = Double.parseDouble(datos[2]);
			String respuesta = "Case #"+(i+1)+": "+solve(c, f, x);
			if(test) System.out.println(respuesta);
			else pw.println(respuesta);
		}
	}
	
	private static String solve(double c, double f, double x)
	{
		boolean termino = false;
		double produccionSeg = 2;
		double segundos = 0;
		while(!termino)
		{
			double tiempoParaComprarGranja = c/(produccionSeg);
			double tiempoConGranja = x/(produccionSeg+f)+tiempoParaComprarGranja;
			double tiempoSinGranja = (x)/(produccionSeg);
			
			if(tiempoConGranja<tiempoSinGranja)
			{
				segundos += tiempoParaComprarGranja;
				produccionSeg+=f;
			}
			else
			{
				termino = true;
				segundos += tiempoSinGranja;
			}
		}

		return segundos+"";
	}

}