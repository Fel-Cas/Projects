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
public class Jugador {
     private String nombre;//Nombre del Jugador, por medio de este atributo se identiica a cada Jugador
    private  int dinero;// Cantidad de dinero que posee el Jugador
    private int canti[], monedas[];// canti representa la cantidad de Monedas de cada denominacion y monedas representa la denominacion de cada moneda
    private LDLC apuesta;// Es donde se representa una especie de tablero donde registra el dinero y a opcion aposto el jugador

    /**
     * Este el metodo contructor de la clase Jugador, donde solo recibe por parametro el nombre del jugador, el resto de items
     * se le dan predeterminados a cada jugador por igual.
     **/
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.dinero = 910;
        this.canti =new int []{10,10,10,10,10};
        this.apuesta=new LDLC();
        this.monedas= new int[]{50,25,10,5,1};
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int[] getCanti() {
        return canti;
    }

    public void setCanti(int[] canti) {
        this.canti = canti;
    }

    public int[] getMonedas() {
        return monedas;
    }

    public void setMonedas(int[] monedas) {
        this.monedas = monedas;
    }

    public LDLC getApuesta() {
        return apuesta;
    }

    public void setApuesta(LDLC apuesta) {
        this.apuesta = apuesta;
    }
    
    /***
     * Este metodo reibe por parametro el dinero y la opcion a la que le va apostar el jugador, y lo que hace es que 
     * se busca el dato al que le desee apostar el jugador y se le cambia el valor a multiplar que esta en cero por la 
     * cantidad que indique el jugador 
     ***/
    public void apostar (int dato, int dinero){
      NodoDoble p = apuesta.getPrimero();
       if(p.getDato()==dato){
         p.setValorMultiplicar(dinero);
       }       
        do{
            p = p.getlD();
          if(p.getDato()==dato){
         p.setValorMultiplicar(dinero);
       }       
            
        }while(p!=apuesta.getPrimero());
   }
    /***
     * Este metodo es ele encargado de iniciar o crear la lista doblemente ligada circular en donde el jugador apuesta,
     * con todoas las opciones a las que se puede apostr y su valor multiplicar en 0
    ***/
    public void iniciarApuesta(){
      
     for(int i=0; i<49; i++){
          
         apuesta.insertar(i,0, 0, apuesta.getUltimo());
     }    
    }
    /**
     * Este metodo es el encargado de si el jugador gana devolverle las monedas que le corresponden por la cantidad de dinero ganada
     * la cantidad de monedas esta especifiada en el vector que se le manda por parametro
     **/
    public void devolverMonedas(int cantidad[]){
         int i;
            for(i=0;i<5;i++){
               canti[i]=canti[i]+cantidad[i];
               }
    }
    
   
}
