import java.util.ArrayList;

public class Envejecimiento
{

	private String[] contadores;
	private ArrayList<Integer> usos;
	private Integer cantidadPaginas;

	public Envejecimiento(int pCantidadPaginas)
	{
		cantidadPaginas = pCantidadPaginas;
		usos = new ArrayList<Integer>();
		contadores = new String[pCantidadPaginas];
		llenarTabla();		
	}

	public void llenarTabla()
	{
		for(int i = 0; i < cantidadPaginas; i++)
		{
			contadores[i] = "00000000";
		}
	}

	public void agregarUsos(int indice)
	{
		usos.add(indice);
	}

	public void actualizarCiclo()
	{
		for(int i = 0; i < cantidadPaginas; i++)
		{
			boolean flag = false;
			for(int j = 0; j < usos.size() && !flag; j++)
			{
				if(i == usos.get(j))
				{
					String edit = borrarUltimoChar(contadores[i]);
					edit = "1" + edit;
					contadores[i] = edit;
					flag = true;
				}
				else if(j == usos.size() - 1)
				{
					String edit = borrarUltimoChar(contadores[i]);
					edit = "0" + edit;
					contadores[i] = edit;					
				}
			}				
		}
		usos = new ArrayList<Integer>();
	}

	public int envejecer()
	{
		int lower = 128;
		int indice = 0;

		for(int i = 0; i < cantidadPaginas; i++)
		{
			int comparacion = Integer.parseInt(contadores[i], 2);
			if(comparacion < lower && comparacion != 0)
			{
				lower = comparacion;
				indice = i;
			}
		}
		contadores[indice] = "00000000";
		agregarUsos(indice);
		return indice;
	}

	public static String borrarUltimoChar(String str)
	{
		return str.substring(0, str.length() - 1);
	}

}