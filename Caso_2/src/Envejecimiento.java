public class Envejecimiento extends Thread
{
	private boolean activo;
	private TablaPag tabla;

	public Envejecimiento(TablaPag pTabla)
	{
		activo = true;
		tabla = pTabla;
	}

	public void detenerEjecucion()
	{
		activo = false;
	}
	public void despertar()
	{
		notify();
	}
	public synchronized void  run()
	{
		while(activo)
		{
			try
			{
				wait();
				tabla.correrUn0();
				Thread.sleep(1);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}			
		}
	}	
}