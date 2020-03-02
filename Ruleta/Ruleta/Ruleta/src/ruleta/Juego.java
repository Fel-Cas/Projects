/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleta;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Usuario
 */
public class Juego {
    private LDLC tablero; //Esta lista es con la cual representamos el tablero
    private Object verificado[][]; //Esta matriz es donde reunimos la informacion del numro ganador
    private ArrayList<Jugador> jugadores;//Este arrayList es donde guardamos todos los jugadores que estan participando en el juego
    private int monedas[],canti[],dinero;// Monedas es donde esta guardado la denominacion de cada moneda, canti la es donde se guarda la cantidad de cada moneda, dinero es la cantidad de dinero del juego
    private String mensaje;// Es donde se va reunniendo la informacion del juego, quien gana y osas asi, para mostrarlo en cada ronda

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    /**
     * Metodo contructor.
     **/
    public Juego( ArrayList<Jugador> jugadores) {
        this.tablero = new LDLC();
        this.verificado = new Object[6][2];
        this.jugadores = jugadores;
        this.monedas = new int[]{50,25,10,5,1};
        this.canti = new int[]{20000,20000,20000,20000,20000};
        this.dinero = 1802000;
    }

    public LDLC getTablero() {
        return tablero;
    }

    public void setTablero(LDLC tablero) {
        this.tablero = tablero;
    }

    public Object[][] getVerificado() {
        return verificado;
    }

    public void setVerificado(Object[][] verificado) {
        this.verificado = verificado;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int[] getMonedas() {
        return monedas;
    }

    public void setMonedas(int[] monedas) {
        this.monedas = monedas;
    }

    public int[] getCanti() {
        return canti;
    }

    public void setCanti(int[] canti) {
        this.canti = canti;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    
      /**
     * Este método es el encargado de representar nuestro tablero en un lista Doblemente Ligada Circular
     * que contiene el valor por cual se va multiplicar el valor apostado y en caso de los numero el color
     * asociado a el
     * En nuestro codigo estan estas representaciones con los siguientes numeros:
     * 37 Representa que el numero es par.
     * 38 Representa que el numero es impar.
     * 39 Representa que el numero esta entre 1-18.
     * 40 Representa que el numero  esta entre 19-36
     * 41 Representa que el numero es de color Rojo
     * 42 Representa que el numero es de color Negro
     * 43 Representa que el numero pertenece a la 1ª docena
     * 44 Representa que el numero pertenece a la 2ª docena
     * 45 Representa que el numero pertenece a la 3ª docena
     * 46 Representa que el numero pertenece a la 1 fila
     * 47 Representa que el numero pertenece a la 2 fila
     * 48 Representa que el numero pertenece a la 3 fila.
     **/
    public void iniciarJuego(){
     for(int i=0; i<49; i++){
           if(i<37){
            tablero.insertar(i, 36, 0, tablero.getUltimo());
           }
            if(i>36 && i<43){
            tablero.insertar(i, 2, 0, tablero.getUltimo());
            }
            if(i>=43){
            tablero.insertar(i, 3, 0, tablero.getUltimo());
            }
        }
        int[] rojo = new int[] {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
        int[] negro = new int[]{2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
         
        for(int k=0;k<18;k++){
            int a =rojo[k];
            NodoDoble m= tablero.buscarDato(a);
            m.setColor(41);
        }
         for(int k=0;k<18;k++){
            int a =negro[k];
            NodoDoble m= tablero.buscarDato(a);
            m.setColor(42);
        }
    }
    
    /**
     * Este metodo es el encargado de generar el numero aleatorio, ademas de verificar, si este
     * es impar o par, si esta entre un rango de 1-18 o 19-36,si pertenece a la fila 1,2 o 3,
     * si pertenece a la 1°,2° o 3° docena y si es rojo o negro.
     * 
     **/
    public void ganador(int ganador){
        NodoDoble p;
        mensaje="El numero ganador es "+ ganador+"\n";
        p=tablero.buscarDato(ganador);
        verificado[0][0]=ganador;
        verificado[0][1]= (int) p.getValorMultiplicar();
        if(ganador!=0){
                
        if(p.getColor()==41){
           mensaje=mensaje+"Es de color rojo.\n ";
         }else{
             mensaje=mensaje+"Es de color negro.\n ";
         }
        verificado[1][0]=p.getColor();
        verificado[1][1]=2; 
 
         if(ganador%2==0){
            verificado[2][0]=38;
            p=tablero.buscarDato(38);
            verificado[2][1]=p.getValorMultiplicar();
            mensaje=mensaje+"Es numero par.\n ";
         }else{
            verificado[2][0]=37;
            p=tablero.buscarDato(37);
            verificado[2][1]=p.getValorMultiplicar();
            mensaje=mensaje+"Es numero impar.\n ";
         }
         if(ganador>=1 && ganador<=18){
           verificado[3][0]=39;
            p=tablero.buscarDato(39);
            verificado[3][1]=p.getValorMultiplicar();
            mensaje=mensaje+"El numero esta entre 1-18.\n ";
         }else{
            verificado[3][0]=40;
            p=tablero.buscarDato(40);
            verificado[3][1]=p.getValorMultiplicar();
            mensaje=mensaje+"El numero esta entre 19-36.\n ";
         }
         if(ganador>0 && ganador<=12){
            verificado[4][0]=43;
            p=tablero.buscarDato(43);
            verificado[4][1]=p.getValorMultiplicar();
            mensaje=mensaje+"El numero pertenece a la primera 12ª.\n ";
         }else{
             if(ganador>12 && ganador<=24){             
                verificado[4][0]=44;
                p=tablero.buscarDato(44);
                verificado[4][1]=p.getValorMultiplicar();
                mensaje=mensaje+"El numero pertenece a la segunda 12ª. \n";
                System.out.println(mensaje);
             }else{                 
              verificado[4][0]=45;
              p=tablero.buscarDato(45);
              verificado[4][1]=p.getValorMultiplicar();
              mensaje=mensaje+"El numero pertenece a la tercera 12ª. \n";
             }
         
         }
         if(esFila1(ganador)){          
            verificado[5][0]=46;
            p=tablero.buscarDato(46);
            verificado[5][1]=p.getValorMultiplicar();
            mensaje=mensaje+"El numero pertenece a la primera fila.\n ";
            System.out.println(mensaje);
         }else{
            if (esFila2(ganador)){              
            verificado[5][0]=47;
            p=tablero.buscarDato(47);
            verificado[5][1]=p.getValorMultiplicar();
            mensaje=mensaje+"El numero pertenece a la segunda fila.\n ";
            System.out.println(mensaje);
            }else{
                 
            verificado[5][0]=48;
            p=tablero.buscarDato(48);
            verificado[5][1]=p.getValorMultiplicar();
            mensaje=mensaje+"El numero pertenece a la tercera fila.\n";
            System.out.println(mensaje);
            }
         }
         for(int i=0;i<6;i++){
             System.out.println(verificado[i][0]+"-----"+verificado[i][1]);
         }
        }else{
             verificado[1][0]=0;
             verificado[1][1]=0;
             verificado[2][0]=0;
             verificado[2][1]=0;
             verificado[3][0]=0;
             verificado[3][1]=0;
             verificado[4][0]=0;
             verificado[4][1]=0;
        }
         
    }
    /**
     * Este metodo es ele encargado de verificar si el numero ganador pertenece a la fila numero 1.
     */
    public boolean esFila1(int numero){
       for(int i=1;i<=36; i=i+3){
           if(numero==i){
            return true;
           }      
       }
      return false;
    }
    /**
     * Este metodo es ele encargado de verificar si el numero ganador pertenece a la fila numero 2.
     */
    public boolean esFila2(int numero){
       for(int i=2;i<=36; i=i+3){
           if(numero==i){
            return true;
           }      
       }
      return false;
    }
    /**
     * Este metodo es el cargado de recorrer la lista de cada jugador buscado a cual opcion aposto(donde el valor a multiplicar sea diferente de cero)
     * y luego llama el metodo verificar
     * En el caso de que el ganador sea el cero, verifica si se le aposto al numero cero se llama a vereficar cero, 
     * si no se aposto al cero se le quita el dinero  que se haya apostado a otras opciones.
     **/
    public void revisar(){
            int i, valor;
            NodoDoble recorre;
            LDLC apostado;
            Jugador participante=null;
            int ganador=(int) verificado[0][0];
            if(ganador==0){
              for(i=0; i<jugadores.size();i++){
                participante=jugadores.get(i);
                apostado=participante.getApuesta();
                recorre=apostado.getPrimero();
                valor=(int)recorre.getValorMultiplicar();
                if(valor!=0){
                    verificarCero(recorre, participante);
                }
                recorre=recorre.getlD();
                while(recorre!=apostado.getPrimero()){
                    
                    valor=(int)recorre.getValorMultiplicar();
                    if(valor!=0){  
                         participante.setDinero(participante.getDinero()-valor);
                         dinero=dinero+valor;                        
                    }
                    recorre=recorre.getlD();
                }
            }
              
            mensaje=mensaje+"Como el número ganador fue el cero, las apuestas que fueron diferentes a que cero ganaba pierden el dinero ";
            }else{
            for(i=0; i<jugadores.size();i++){
                participante=jugadores.get(i);
                apostado=participante.getApuesta();
                recorre=apostado.getPrimero();
                valor=(int)recorre.getValorMultiplicar();
                if(valor!=0){
                    verificar(recorre, participante);
                }
                recorre=recorre.getlD();
                while(recorre!=apostado.getPrimero()){
                    valor=(int)recorre.getValorMultiplicar();
                    if(valor!=0){  
                          verificar(recorre, participante);
                    }
                   recorre=recorre.getlD();
                }
            }
        }
    }
    /**
     * Este metodo es llamado por el metodo verificar,con la informacion que esta guardada en la matriz verificado, se va buscado 
     * en la matriz si alguna de las apuestas corresponde al dato que el jugador le aposto, se calcula el valor ganadao multiplicado
     * el dinero apostado y el numero de vesces que paga esa opcion, se llama el metodo devolverMonedasP, se le da el dinero al jugador y 
     * si no ganda se le quita el dinero apostado.
     **/
    public void verificar(NodoDoble recorre, Jugador persona){
     int j,valor=0, total=0,dineroJ=0, ganador=0,dineroA=0;
     boolean bandera=false;
     int dato;
     dineroA=(int) recorre.getValorMultiplicar();
     ganador=(int)recorre.getDato(); 
     for(j=0;j<6;j++){
          dato=(int)verificado[j][0];
          if(dato==ganador && dineroA!=0 ){
            persona.setDinero(persona.getDinero()-dineroA);
            dinero=dinero+dineroA;
            total=(int)verificado[j][1];
            dineroJ=total*dineroA;
            dinero=dinero-dineroJ;
            devolverMonedasP(dineroJ,persona);
            valor=persona.getDinero();
            valor=valor+dineroJ;
            persona.setDinero(valor);
            if(ganador<=36){
                 mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos al número " + ganador +" y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==37){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era impar y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }    
            if(ganador==38){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era par y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==39){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número estaba entre 1-18 y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==40){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número estaba entre 19-36 y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==41){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era de color rojo y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==42){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era de clor negro y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==43){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 1ra. docena y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";                
            }
            if(ganador==44){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 2da. docena y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==45){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 3ra. docena y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==46)
            {
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 1ra. fila y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==47){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 2da. fila y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==48){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 3ra. fila y ganó "+dineroJ+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            System.out.println(dineroJ);
            bandera=true;
           }
        }
        if(!bandera && dineroA!=0 ){
            dinero=dinero+dineroA;
            persona.setDinero(persona.getDinero()-dineroA);
          
            if(ganador<=36){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era "+ ganador + " y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==37){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era impar y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==38){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era par y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==39){            
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número estaba entre 1-18 y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==40){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número estaba entre 19-36 y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==41){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era de color rojo y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==42){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número era de color negro y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==43){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 1ra. docena y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==44){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 2da. docena y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==45){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 3ra. docena y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==46){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 1ra. fila y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==47){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 2da. fila y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
            if(ganador==48){
                mensaje=mensaje+"El jugador "+ persona.getNombre()+" apostó "+dineroA+" pesos a que el número pertenecía a la 3ra. fila y perdió "+"\n"+"El dinero que le queda es "+persona.getDinero()+"\n";
            }
        }   
      }
    /**
     * Este metodo es el encargado de calcular el minimo numero de monedas conlos cuales se le puede devolver el dinero ganado al jugador
     * y se le resta a las monedas del jueo tambien llama el metodo con el cual se le entrgan la monedas al jugador.
     **/
     public void devolverMonedasP(int dineroA, Jugador persona){
         int i=0, cantidad[], suma=dineroA;         
        cantidad=new int []{0,0,0,0,0};
        while(i<5){
          if(monedas[i]<= suma && canti[i]>0){
              cantidad[i]=cantidad[i]+1;
              suma=suma-monedas[i];    
          }else{
              i++;
          }
        }
            for(i=0;i<5;i++){
               canti[i]=canti[i]-cantidad[i];
                
            }
            persona.devolverMonedas(cantidad);
    }
    /**
     * Este metodo e el encargado de poner la listas donde se representa las apuesta de cada jugador cero
     * donde va el dinero a multiplicar.
     **/
    public void reiniciarApuestas(){
       int k=0;
       for(k=0;k<jugadores.size();k++){
           jugadores.get(k).getApuesta().vaciarLista();
       }
    }
    /**
     * Este método es el encargado de calcular el dinero ganado por el jugador cuando le apuesta al cero y este es el numero
     * que ganó, quitarlas monedas al casino y darle las monedas al jugador que ganó.
     **/
    public void verificarCero(NodoDoble nodo, Jugador participante){
     int total=0, valorMulti=0;
     participante.setDinero(participante.getDinero()-nodo.getValorMultiplicar());
     valorMulti=(int)verificado[0][1];
     total= valorMulti*nodo.getValorMultiplicar();
     participante.setDinero(participante.getDinero()+total);
     devolverMonedasP(total,participante);
     mensaje=mensaje+"El jugador apostó "+nodo.getValorMultiplicar()+"a que el número ganador era el 0 y ganó"+total+"\n Y el dinero que le queda es "+participante.getDinero()+"\n";
    }
          
}
