import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ProcesoPrin extends Thread {

    private LinkedList<Integer> TLB;
    private int capacidadTLB;
    private TablaPag tablaPag;
    private File archivo;
    public static Envejecimiento envejecimiento;
    
    public ProcesoPrin(File archivo, int cantidadPaginas, int capacidadTLB, 
                       TablaPag tablaPag) {
        this.archivo = archivo;
        this.capacidadTLB = capacidadTLB;
        this.tablaPag = tablaPag;
        this.TLB = new LinkedList<Integer>();
    }
    public void run(){
        
        try {
            Scanner sc = new Scanner(archivo);
            
            while (sc.hasNextLine()) {
                // sacar una referencia
                String linea = sc.nextLine();
                String[] partes = linea.split(" ");
                int referencia = Integer.parseInt(partes[1]);

                envejecimiento.despertar();
                Thread.sleep(2);
                if (TLB.contains(referencia)) {
                    System.out.println("Consultado en la TLB: 2ns");
                } else {
                    if (TLB.size() < capacidadTLB) {
                        TLB.add(referencia);
                    } else {
                        TLB.removeFirst();
                        TLB.add(referencia);
                    }
                    System.out.println("NO encontrado en la TLB");
                    if (tablaPag.paginaEnTabla(referencia)) {
                        
                        System.out.println("encontrado en la TP: 30ns");
                    } else {
                        System.out.println("Error en página: 60ns");
                        tablaPag.agregarPagina(referencia);
                    }
                }
                
                // llamar envejecimiento

            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Ingrese el número de entradas de la TLB: ");
            int entradasTLB = in.nextInt();
            System.out.println("Ingrese el numero de marcos de memoria RAM: ");
            int marcosRAM = in.nextInt();
            System.out.println("Ingrese el número de entradas de la tabla de páginas: ");
            int entradasTabla = in.nextInt();
            System.out.println("Ingrese el nombre del archivo (.txt): ");
            String nombreArchivo = in.next();
            nombreArchivo = "./data/" + nombreArchivo;
            
            File archivo = new File(nombreArchivo);

            // Inicializar la tabla de páginas
            TablaPag tablaPag = new TablaPag(marcosRAM);
            envejecimiento = new Envejecimiento(tablaPag);
            ProcesoPrin proceso = new ProcesoPrin(archivo, entradasTabla, entradasTLB, tablaPag);
   
            // Instanciar y crea los Threads
            proceso.start();
        }
        envejecimiento.start();
    }

}
