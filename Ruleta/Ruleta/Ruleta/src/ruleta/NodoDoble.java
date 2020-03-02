/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleta;

/**
 *
 * @author Usuario
 */
public class NodoDoble {
    private int dato, valorMultiplicar, color; // Datos que representan cada espacio del tablero
    private NodoDoble lI, lD;//Nodo anteriro y siguiente de cada nodo

    /**
     * Metodo constructor 
     **/
    public NodoDoble(int dato, int valorMultiplicar, int color) {
        this.dato = dato;
        this.valorMultiplicar = valorMultiplicar;
        this.color = color;
       
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getValorMultiplicar() {
        return valorMultiplicar;
    }

    public void setValorMultiplicar(int valorMultiplicar) {
        this.valorMultiplicar = valorMultiplicar;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public NodoDoble getlI() {
        return lI;
    }

    public void setlI(NodoDoble lI) {
        this.lI = lI;
    }

    public NodoDoble getlD() {
        return lD;
    }

    public void setlD(NodoDoble lD) {
        this.lD = lD;
    }
    
}
