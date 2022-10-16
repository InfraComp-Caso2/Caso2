import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ProcesoPrin extends Thread {

    static LinkedList<Integer> TLB;
    static int capacidadTLB;
    TablaPag tablaPag;
    String archivo;

    public void run(String nombreArchivo){
        
        // sacar una referencia
        /* 
        try {
            File archivo = new File(nombreArchivo);
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] partes = linea.split(" ");
                int referencia = Integer.parseInt(partes[1]);
                if (TLB.contains(referencia)) {
                    System.out.println("encontrado en la TLB");
                } else {
                    if (TLB.size() < capacidadTLB) {
                        TLB.add(referencia);
                    } else {
                        TLB.removeFirst();
                        TLB.add(referencia);
                    }
                    System.out.println("NO encontrado en la TLB");
                    if (TablaPag.paginaEnTabla(referencia)) {
                        System.out.println("encontrado en la TP");
                    } else {
                        System.out.println("No encontrado en la TP");
                        TablaPag.agregarPagina(referencia);
                    }
                }
                TablaPag.correrUn0();
                // llamar envejecimiento
                Envejecimiento.sleep(1);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        */


        // proceso
            // TLB
            // tabla
            // envejecimiento

        // sacar el resultado

        //wait 2ms


    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el número de entradas de la TLB: ");
        int entradasTLB = in.nextInt();
        System.out.println("Ingrese el numero de marcos de memoria RAM: ");
        int marcosRAM = in.nextInt();
        System.out.println("Ingrese el número de entradas de la tabla de páginas: ");
        int entradasTabla = in.nextInt();
        System.out.println("Ingrese el nombre del archivo (.txt): ");
        String nombreArchivo = in.next();

            //archivo = nombreArchivo;

        // Inicializar la tabla de páginas
         //tablaPag = new TablaPag(marcosRAM);

        // Inicializar la TLB
        TLB = new LinkedList<Integer>();
        capacidadTLB = entradasTLB;
        
        // Instanciar y crea los Threads
            //new ProcesoPrin().start();


    }

}
