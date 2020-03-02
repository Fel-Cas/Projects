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
public class LDLC {
    private NodoDoble primero, ultimo;// parametros en los cuales puedo referenciar el primer y ultimo dato de la lista doblemente ligada circular
    
    /**
     * Constructor 
     **/
    public LDLC(){
        this.primero = null;
        this.ultimo = null;
    }
     
    public NodoDoble getPrimero() {
        return primero;
    }

    public void setPrimero(NodoDoble primero) {
        this.primero = primero;
    }

    public NodoDoble getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoDoble ultimo) {
        this.ultimo = ultimo;
    }
    /**
     * Este metodo es el encragado de crear un nuevo  nodo doble con los datos enviados por parametro
     * y llama el metodo conectar
     **/
    public  void insertar(int valor,int valorMulti,int color, NodoDoble y){
        NodoDoble x,p;
        x=new NodoDoble(valor,valorMulti,color);
        conectar(x,y);
    }
    /**
     * Este metodo es el encargado de conectar en la lista dblemente ligada circular un nodo al lado de otro,
     * el primer nodo x es el que se va a conectar y el nodo y es al lado del que se debe conectar
     **/
    public  void conectar(NodoDoble x, NodoDoble y){
         if(y == null){
           this.primero= x;
           ultimo=x;
           primero.setlD(x);
           primero.setlI(x);
           ultimo.setlD(x);
           ultimo.setlI(x);          
         }else{               
                x.setlD(y.getlD());
                y.getlD().setlI(x);
                x.setlI(y);
                y.setlD(x);
                if(y==ultimo ){
                    ultimo=x;
                    primero=x.getlD();
                }        
    }        
        
}
    /**
     * Este metodo es el encargado de recorrer la lista desde el primero hasta el ultimo, y va 
     * escribiendo el dato, el valor a multiplicar y el color
     **/
    public void recorreIzqDer(){
        NodoDoble p;
        p = getPrimero();
        do{
            System.out.println(p.getDato() + ", " + p.getValorMultiplicar()+ ", " + p.getColor());
            p = p.getlD();
        }while(p!=getPrimero());        
    }
    /**
     * Est metoo es el encarda de buscar el nodo en el cual se encuntra el dato que se le envia por parametro
     **/
    public NodoDoble buscarDato(int v){
        NodoDoble p = getPrimero();
        if(p==null){
          return null;
        }
        if(v==p.getDato()){
            return p;
        }
        do{
            p = p.getlD();
        }while(p!=getPrimero() && p.getDato() != v);
        return p;
    }
    /**
     * Este metodo es el encargado de verificar que el nodo a desconectar exista y no sea una variable nula
     **/
    public void borrar(NodoDoble x){
       if(x==null){
           System.out.println("No hay dato");
           return;
       }
       desconectar(x);
    }
    /**
     * Este metodo cumple la funcion de de desconectar un nodo en especifico el cual se le manda por parametro
     **/
   public void desconectar(NodoDoble x){
       if(x==primero){
        primero=primero.getlD();
        if(primero==null){
           ultimo=null;
           return;
        }
        primero.setlI(ultimo);
        ultimo.setlD(primero);
        return;
       }
       if(x==ultimo){
           ultimo=x.getlI();
           ultimo.setlD(primero);
           primero.setlI(ultimo);
          
       }
       x.getlD().setlI(x.getlI());
       x.getlI().setlD(x.getlD());
       x.setlD(null);
       x.setlI(null);
   }
   /**
    * Este metodo es el encargado de recorrer la lista despues de cada ronda y en el valor a multiplicar que es diferente de cero
    * lo pone en cero, para poder volver a registrar las apuestas 
    **/
   public void vaciarLista(){
      NodoDoble p = getPrimero();
       p.setValorMultiplicar(0);
        do{
            p = p.getlD();
            p.setValorMultiplicar(0);
            
        }while(p!=getPrimero());
   }
}
