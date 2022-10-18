import java.util.ArrayList;
import java.util.Collections;

public class TablaPag {
    
    int cantidad;

    ArrayList<Integer> paginasCargadas;
    ArrayList<Integer> cantidadAccesos;

    public TablaPag(int cantidadPaginas) {

        cantidad = cantidadPaginas;
        paginasCargadas = new ArrayList<Integer>(cantidadPaginas);
        cantidadAccesos = new ArrayList<Integer>(cantidadPaginas);

        for (int i = 0; i < cantidadPaginas; i++) {
            paginasCargadas.add(-1);
            cantidadAccesos.add(0);
        }

    }

    public synchronized boolean paginaEnTabla(int numPagina) {
        boolean respuesta = paginasCargadas.contains(numPagina);

        if (respuesta) {
            int indice = paginasCargadas.indexOf(numPagina);
            int elemento = cantidadAccesos.get(indice);
            elemento += Math.pow(2, 31);
            cantidadAccesos.set(indice, elemento);
            return true;
        } else {
            return false;
        }   
    }

    public synchronized void correrUn0(){

        for (int i = 0; i < cantidad; i++) {
            int elemento = cantidadAccesos.get(i);
            elemento = elemento >> 1;
            cantidadAccesos.set(i, elemento);
        }

    }

    public synchronized void agregarPagina(int numPagina) {

        boolean respuesta = paginasCargadas.contains(-1);

        if (respuesta) {
            int indice = paginasCargadas.indexOf(-1);
            paginasCargadas.set(indice, numPagina);
            cantidadAccesos.set(indice, (int) Math.pow(2, 31));
        } else {
            int indice = cantidadAccesos.indexOf(Collections.min(cantidadAccesos));
            paginasCargadas.set(indice, numPagina);
            cantidadAccesos.set(indice, (int) Math.pow(2, 31));
        }
        
    }

}
