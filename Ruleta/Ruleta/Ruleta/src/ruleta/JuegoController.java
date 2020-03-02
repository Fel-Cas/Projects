/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleta;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 *
 * @author andres.castro3
 */
public class JuegoController {
    
    static Jugador cliente;//Representa el jugador que está jugando en ese momento
    static int monedasJuego[]=null,monedasJugador[]=new int[]{0,0,0,0,0}, dineroTem;
    static String nombreJugador,dineroJugador;
    Stage abreVentanaFichas = new Stage();
    static int apuesta = 0,i=0,j=0, dato=0,dineroCliente, a, dineroApuesta, canMonedas1,canMonedas5,canMonedas10,canMonedas25,canMonedas50;
    Stage stage2 = new Stage();
    String nombre;
    Stage abreApuesta = new Stage();
    static Juego juego;
    ArrayList<Jugador>jugadores=new ArrayList<>();
    @FXML
    Label dinero;
    @FXML
    Label nombre1,monedas50,monedas25,monedas10,monedas5,monedas1;
    @FXML
    private Button btnSiguiente, btnMiDinero;
    @FXML
    private Label cantidad;
    @FXML
    private TextField nombres; 
    @FXML
    private Button juegoBtn0, juegoBtn1, juegoBtn2, juegoBtn3, juegoBtn4, juegoBtn5, juegoBtn6, juegoBtn7,
            juegoBtn8, juegoBtn9, juegoBtn10, juegoBtn11, juegoBtn12, juegoBtn13, juegoBtn14, juegoBtn15
            , juegoBtn16, juegoBtn17, juegoBtn18, juegoBtn19, juegoBtn20, juegoBtn21, juegoBtn22, juegoBtn23
            , juegoBtn24, juegoBtn25, juegoBtn26, juegoBtn27, juegoBtn28, juegoBtn29, juegoBtn30, juegoBtn31
            , juegoBtn32, juegoBtn33, juegoBtn34, juegoBtn35, juegoBtn36, juegoBtn37, juegoBtn38, juegoBtn39
            , juegoBtn40, juegoBtn41, juegoBtn42, juegoBtn43, juegoBtn44, juegoBtn45, juegoBtn46, juegoBtn47
            , juegoBtn48;
    @FXML
    private Label aleatorio;
    
    @FXML
    private javafx.scene.control.Button btnApostar;
    
    @FXML
    private javafx.scene.control.Button btnCancelar;
    
    @FXML
    private javafx.scene.control.Button btnJugar;
    
    @FXML
    private javafx.scene.control.Button btnCerrar;
    
    @FXML
    private Button btnGanador, btnEmpezar;
    
    @FXML
    private Label nombreJuego;      
    
     /**
      * Acá controlamos los eventos de registrar jugador, donde se  crean los jugadores, y se agregan a un ArrayList,
      * si el numero de jugadores registrados es igual a 4 de inmediato se abre la venta de juego y no se deja registrar
      * más jugadores
      **/    
    @FXML
    void registrar(ActionEvent event){
        nombre=nombres.getText(); //obtiene el nombre del jugador 
        Jugador jugador= new Jugador(nombre); //crea el jugador con su respectivo nombre y dinero
        jugador.iniciarApuesta();
        jugadores.add(jugador);
        System.out.println("Registro Jugador "+(i+1)+" "+nombre);
        i=i+1;
        if(i==4){ //cuando i==4 significa que se registraron 4 jugadores así que carga la pantalla para que empiece el juego
           juego=new Juego(jugadores);
           juego.iniciarJuego();
           cliente=juego.getJugadores().get(j);
           dineroCliente=cliente.getDinero();
           dineroTem=dineroCliente;
           j=j+1;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
           System.out.println("primer Jugador"+" "+cliente.getNombre());
           try{
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Juego.fxml"));
             Parent root1 = (Parent) fxmlLoader.load();         
             stage2.setScene(new Scene(root1)); 
             stage2.setResizable(false);
             stage2.show();
             Stage cerrarRegistro = (Stage) btnCerrar.getScene().getWindow();
             cerrarRegistro.close();
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }            
        }
        btnJugar.setDisable(false);
        nombres.setText("");
    }
   
    /**
    * El método jugar abre la pantalla del juego cuando el usuario no desee registrar el máximo de jugadores
    **/
    @FXML
    void jugar(ActionEvent event) throws IOException {
          juego=new Juego(jugadores);
          juego.iniciarJuego();
          cliente=juego.getJugadores().get(j);
          dineroCliente=cliente.getDinero();
          j=j+1;
           dineroTem=dineroCliente;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
          System.out.println(cliente.getNombre());
          try{
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Juego.fxml"));
             Parent root1 = (Parent) fxmlLoader.load();         
             stage2.setScene(new Scene(root1)); 
             stage2.setResizable(false);
             stage2.show();
             Stage cerrarRegistro = (Stage) btnJugar.getScene().getWindow();
             cerrarRegistro.close();
          }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    
    /**
    * Este método no permite ingresar números en el nombre del jugador
    **/    
    public void txtNombre_KeyTyped(javafx.scene.input.KeyEvent keyEvent) {
        char car = keyEvent.getCharacter().charAt(0);
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && car != 'á' //Minúsculas
                && car != 'é'
                && car != 'í'
                && car != 'ó'
                && car != 'ú'
                && car != 'Á' //Mayúsculas
                && car != 'É'
                && car != 'Í'
                && car != 'Ó'
                && car != 'Ú'
                && car != 'ñ'
                && car != 'Ñ'
                && (car != (char) KeyEvent.VK_SPACE)) {
            keyEvent.consume();
        }
    }
       
    /**
    * el método cancelar, cancela los registros de los jugadores y vuelve a la pantalla de inicio
    **/
    public void cancelar(){
        try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Iniciar.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();    
         stage2.setScene(new Scene(root1));
         stage2.setResizable(false);
         stage2.show();
         Stage cerrarRegistro = (Stage) btnCerrar.getScene().getWindow();
         cerrarRegistro.close();
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
        }
    }
    /*           
     *           
     *          
     *         Apuesta.fxml
     *       
     *
     */  
    
    /**
     * apuesta1 se encarga de tomar las veces que el jugador realiza la apuesta con la ficha 1
    **/
    public void apuesta1(ActionEvent event){
        if(canMonedas1>0){        
        nombreJugador=cliente.getNombre();     
        apuesta=apuesta+1;
        dineroCliente=dineroCliente-1;
        canMonedas1=canMonedas1-1;
        monedasJugador[4]=monedasJugador[4]+1;
        }else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad de monedas insuficiente");
         alert.showAndWait();
        }
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);        
        nombre1.setText(nombreJugador);
        cantidad.setText(Integer.toString(apuesta));
        monedas1.setText(Integer.toString(canMonedas1));
    }
    /**
     * apuesta5 se encarga de tomar las veces que el jugador realiza la apuesta con la ficha 5
    **/
    public void apuesta5(ActionEvent event){
        if(canMonedas5>0){
        nombreJugador=cliente.getNombre();
        dineroCliente=dineroCliente-5;
        apuesta=apuesta+5;
        monedasJugador[3]=monedasJugador[3]+1;
        canMonedas5=canMonedas5-1;         
        }else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad de monedas insuficiente");
         alert.showAndWait();
        }
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);
        nombre1.setText(nombreJugador);
        cantidad.setText(Integer.toString(apuesta));
        monedas5.setText(Integer.toString(canMonedas5));
    }
    /**
     * apuesta10 se encarga de tomar las veces que el jugador realiza la apuesta con la ficha 10
    **/
    public void apuesta10(ActionEvent event){
          if(canMonedas10>0){
        nombreJugador=cliente.getNombre();
        dineroApuesta = dineroCliente;
        apuesta=apuesta+10;
        dineroCliente=dineroCliente-10;
        monedasJugador[2]=monedasJugador[2]+1; 
        canMonedas10=canMonedas10-1;
        }else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad de monedas insuficiente");
         alert.showAndWait();
        }
          dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);
        nombre1.setText(nombreJugador);
        cantidad.setText(Integer.toString(apuesta));
        monedas10.setText(Integer.toString(canMonedas10));
    }
    /**
     * apuesta25 se encarga de tomar las veces que el jugador realiza la apuesta con la ficha 25
    **/
    public void apuesta25(ActionEvent event){
          if(canMonedas25>0){
        nombreJugador=cliente.getNombre();        
        apuesta=apuesta+25;
        dineroCliente=dineroCliente-25;
        monedasJugador[1]=monedasJugador[1]+1;
        canMonedas25=canMonedas25-1;
        
        }else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad de monedas insuficiente");
         alert.showAndWait();
        }
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);
        nombre1.setText(nombreJugador);
        cantidad.setText(Integer.toString(apuesta));
        monedas25.setText(Integer.toString(canMonedas25));
    }
    /**
     * apuesta50 se encarga de tomar las veces que el jugador realiza la apuesta con la ficha 50
    **/
    public void apuesta50(ActionEvent event){
        if(canMonedas50>0){
        nombreJugador=cliente.getNombre();
        dineroApuesta = dineroCliente; 
        apuesta=apuesta+50;
        dineroCliente=dineroCliente-50;
        monedasJugador[0]=monedasJugador[0]+1;
        canMonedas50=canMonedas50-1;
        }else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad de monedas insuficiente");
         alert.showAndWait();
        }
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);
        nombre1.setText(nombreJugador);
        cantidad.setText(Integer.toString(apuesta));        
        monedas50.setText(Integer.toString(canMonedas50));
    }
    
    /**
    * resta1 se encarga de disminuir la apuesta del jugador con la cantidad de ficha 1 por si él lo desea.
    **/
    public void resta1(ActionEvent event){
        nombreJugador=cliente.getNombre();
        nombre1.setText(nombreJugador);        
        int a=apuesta;
        if( (apuesta>0) && (canMonedas1<cliente.getCanti()[4])){
        dineroCliente=dineroCliente+1;
        monedasJugador[4]=monedasJugador[4]-1;
        canMonedas1=canMonedas1+1;
        apuesta=apuesta-1;
        }else{ // se controla que el jugador no apueste con un valor negativo
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad inválida");
         alert.showAndWait();
        }
        if(apuesta<0){
         apuesta=a;
        }
        cantidad.setText(Integer.toString(apuesta));
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);
        monedas1.setText(Integer.toString(canMonedas1));
        
    }
    /**
    * resta5 se encarga de disminuir la apuesta del jugador con la cantidad de ficha 5 por si él lo desea.
    **/
    public void resta5(ActionEvent event){
        nombreJugador=cliente.getNombre();
        nombre1.setText(nombreJugador);        
        int a=apuesta;
         if( (apuesta>0) && (canMonedas5<cliente.getCanti()[3])){
        dineroCliente=dineroCliente+5;
        canMonedas5=canMonedas5+1;        
        apuesta=apuesta-5;
        monedasJugador[3]=monedasJugador[3]-1;
        }else{ // se controla que el jugador no apueste con un valor negativo
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad inválida");
         alert.showAndWait();
        }
        if(apuesta<0){
         apuesta=a;
        }
        cantidad.setText(Integer.toString(apuesta));
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);
        monedas5.setText(Integer.toString(canMonedas5));
    }
    /**
    * resta10 se encarga de disminuir la apuesta del jugador con la cantidad de ficha 10 por si él lo desea.
    **/
    public void resta10(ActionEvent event){
        nombreJugador=cliente.getNombre();
        nombre1.setText(nombreJugador);        
        int a=apuesta;
        if( (apuesta>0) && (canMonedas10<cliente.getCanti()[2])){
        dineroCliente=dineroCliente+10;
        monedasJugador[2]=monedasJugador[2]-1;
        apuesta=apuesta-10;
        canMonedas10=canMonedas10+1;
        }else{ // se controla que el jugador no apueste con un valor negativo
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad inválida");
         alert.showAndWait();
        }
        if(apuesta<0){
         apuesta=a;
        }
        cantidad.setText(Integer.toString(apuesta));
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);        
        monedas10.setText(Integer.toString(canMonedas10));
    }
    /**
    * resta25 se encarga de disminuir la apuesta del jugador con la cantidad de ficha 25 por si él lo desea.
    **/
    public void resta25(ActionEvent event){
        nombreJugador=cliente.getNombre();
        nombre1.setText(nombreJugador);        
        int a=apuesta;
         if( (apuesta>0) && (canMonedas25<cliente.getCanti()[1])){
        dineroCliente=dineroCliente+25;
        canMonedas25=canMonedas25+1;
        apuesta=apuesta-25;
        monedasJugador[1]=monedasJugador[1]-1;
        
        } else{ // se controla que el jugador no apueste con un valor negativo
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad inválida");
         alert.showAndWait();
        }
        if(apuesta<0){
         apuesta=a;
        }
        cantidad.setText(Integer.toString(apuesta));
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);        
        monedas25.setText(Integer.toString(canMonedas25));
    }
    /**
    * resta50 se encarga de disminuir la apuesta del jugador con la cantidad de ficha 50 por si él lo desea.
    **/
    public void resta50(ActionEvent event){
        nombreJugador=cliente.getNombre();
        nombre1.setText(nombreJugador);        
        int a=apuesta;
        if( (apuesta>0) && (canMonedas50<cliente.getCanti()[0])){
        dineroCliente=dineroCliente+50;
        apuesta=apuesta-50;
        canMonedas50=canMonedas50+1;
        monedasJugador[0]=monedasJugador[0]-1;
        }else{ // se controla que el jugador no apueste con un valor negativo
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error en Apuesta");
         alert.setHeaderText("Cantidad inválida");
         alert.showAndWait();;
        }
        if(apuesta<0){
         apuesta=a;
         
        }
        cantidad.setText(Integer.toString(apuesta));
        dineroJugador=Integer.toString(dineroCliente);
        dinero.setText(dineroJugador);
        monedas50.setText(Integer.toString(canMonedas50));        
    }
    
   /**
   *  este método es cuando el jugador haya elegido la cantidad que desee apostar y realiza la apuesta, tambien se le 
   * resta el numero de monedas apostadas y se el aumentan al casino.
   **/    
    public void apostar(ActionEvent event){ 
        Alert error = new Alert(Alert.AlertType.ERROR);
        if(apuesta<2){
           error.setTitle("Cantidad insuficiente");
           error.setHeaderText("La cantidad mínima debe ser 2");
           error.showAndWait();
        }else{
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("APOSTAR");
             alert.setHeaderText("¿Desea apostar? " + apuesta);
             alert.showAndWait().ifPresent(response -> {
                 if (response == ButtonType.OK) {


                     Stage stage =(Stage) btnApostar.getScene().getWindow();
                     stage.close();
                     dineroCliente=dineroCliente-apuesta;                 
                     cliente.apostar(dato, apuesta);
                     dineroTem=dineroTem-apuesta;
                     monedasJuego=cliente.getCanti();
                     monedasJuego[0]=monedasJuego[0]-monedasJugador[0];
                     monedasJuego[1]=monedasJuego[1]-monedasJugador[1];
                     monedasJuego[2]=monedasJuego[2]-monedasJugador[2];
                     monedasJuego[3]=monedasJuego[3]-monedasJugador[3];
                     monedasJuego[4]=monedasJuego[4]-monedasJugador[4];
                     cliente.setCanti(monedasJuego);
                     monedasJuego=juego.getCanti();
                     monedasJuego[0]=monedasJuego[0]+monedasJugador[0];
                     monedasJuego[1]=monedasJuego[1]+monedasJugador[1];
                     monedasJuego[2]=monedasJuego[2]+monedasJugador[2];
                     monedasJuego[3]=monedasJuego[3]+monedasJugador[3];
                     monedasJuego[4]=monedasJuego[4]+monedasJugador[4];
                     juego.setCanti(monedasJuego);
                     apuesta=0;
                    canMonedas1=cliente.getCanti()[4];
                    canMonedas5=cliente.getCanti()[3];
                    canMonedas10=cliente.getCanti()[2];
                    canMonedas25=cliente.getCanti()[1];
                    canMonedas50=cliente.getCanti()[0];
                    monedasJugador[0]=0;
                    monedasJugador[1]=0;
                    monedasJugador[2]=0;
                    monedasJugador[3]=0;
                    monedasJugador[4]=0;

                 }
                 if(response == ButtonType.CANCEL){
                     System.out.println("presionó el botón cancelar");
                 }
             });
        }
         abreVentanaFichas.close();
    }
    
    @FXML
    void cancelarApuesta(ActionEvent event){
        Stage stage =(Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
     /*
    *
    *
    *
    *       juego.fxml
    *
    *
    *
    */
    /**
     * Este metodo es el encargado de activar los botones cuando el jugador está en la pantalla de juego.
     **/
    public void empezar(){
        juegoBtn0.setDisable(false);
        juegoBtn1.setDisable(false);
        juegoBtn2.setDisable(false);
        juegoBtn3.setDisable(false);
        juegoBtn4.setDisable(false);
        juegoBtn5.setDisable(false);
        juegoBtn6.setDisable(false);
        juegoBtn7.setDisable(false);
        juegoBtn8.setDisable(false);
        juegoBtn9.setDisable(false);
        juegoBtn10.setDisable(false);
        juegoBtn11.setDisable(false);
        juegoBtn12.setDisable(false);
        juegoBtn13.setDisable(false);
        juegoBtn14.setDisable(false);
        juegoBtn15.setDisable(false);
        juegoBtn16.setDisable(false);
        juegoBtn17.setDisable(false);
        juegoBtn18.setDisable(false);
        juegoBtn19.setDisable(false);
        juegoBtn20.setDisable(false);
        juegoBtn21.setDisable(false);
        juegoBtn22.setDisable(false);
        juegoBtn23.setDisable(false);
        juegoBtn24.setDisable(false);
        juegoBtn25.setDisable(false);
        juegoBtn26.setDisable(false);
        juegoBtn27.setDisable(false);
        juegoBtn28.setDisable(false);
        juegoBtn29.setDisable(false);
        juegoBtn30.setDisable(false);
        juegoBtn31.setDisable(false);
        juegoBtn32.setDisable(false);
        juegoBtn33.setDisable(false);
        juegoBtn34.setDisable(false);
        juegoBtn35.setDisable(false);
        juegoBtn36.setDisable(false);
        juegoBtn37.setDisable(false);
        juegoBtn38.setDisable(false);
        juegoBtn39.setDisable(false);
        juegoBtn40.setDisable(false);
        juegoBtn41.setDisable(false);
        juegoBtn42.setDisable(false);
        juegoBtn43.setDisable(false);
        juegoBtn44.setDisable(false);
        juegoBtn45.setDisable(false);
        juegoBtn46.setDisable(false);
        juegoBtn47.setDisable(false);
        juegoBtn48.setDisable(false);
        btnSiguiente.setDisable(false);
        btnMiDinero.setDisable(false);
        nombreJugador=cliente.getNombre();  
        nombreJuego.setText(nombreJugador);
        btnEmpezar.setVisible(false);
    }
    
    /**
    *   Este método es el que lanza el número aleatorio, verificar quien es ganador y hacer todos los calculos.
    **/
    public void ganador(){
         Random a = new Random();
         int azar = 5;
         aleatorio.setText(Integer.toString(azar));
         juego.ganador(azar);         
         juego.revisar();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);         
         alert.setTitle("APOSTAR");
         alert.setHeaderText(juego.getMensaje());
          alert.showAndWait().ifPresent(response -> {
             if (response == ButtonType.OK) {
                 aleatorio.setText("--");
                 juegoBtn0.setDisable(false);
                juegoBtn1.setDisable(false);
                juegoBtn2.setDisable(false);
                juegoBtn3.setDisable(false);
                juegoBtn4.setDisable(false);
                juegoBtn5.setDisable(false);
                juegoBtn6.setDisable(false);
                juegoBtn7.setDisable(false);
                juegoBtn8.setDisable(false);
                juegoBtn9.setDisable(false);
                juegoBtn10.setDisable(false);
                juegoBtn11.setDisable(false);
                juegoBtn12.setDisable(false);
                juegoBtn13.setDisable(false);
                juegoBtn14.setDisable(false);
                juegoBtn15.setDisable(false);
                juegoBtn16.setDisable(false);
                juegoBtn17.setDisable(false);
                juegoBtn18.setDisable(false);
                juegoBtn19.setDisable(false);
                juegoBtn20.setDisable(false);
                juegoBtn21.setDisable(false);
                juegoBtn22.setDisable(false);
                juegoBtn23.setDisable(false);
                juegoBtn24.setDisable(false);
                juegoBtn25.setDisable(false);
                juegoBtn26.setDisable(false);
                juegoBtn27.setDisable(false);
                juegoBtn28.setDisable(false);
                juegoBtn29.setDisable(false);
                juegoBtn30.setDisable(false);
                juegoBtn31.setDisable(false);
                juegoBtn32.setDisable(false);
                juegoBtn33.setDisable(false);
                juegoBtn34.setDisable(false);
                juegoBtn35.setDisable(false);
                juegoBtn36.setDisable(false);
                juegoBtn37.setDisable(false);
                juegoBtn38.setDisable(false);
                juegoBtn39.setDisable(false);
                juegoBtn40.setDisable(false);
                juegoBtn41.setDisable(false);
                juegoBtn42.setDisable(false);
                juegoBtn43.setDisable(false);
                juegoBtn44.setDisable(false);
                juegoBtn45.setDisable(false);
                juegoBtn46.setDisable(false);
                juegoBtn47.setDisable(false);
                juegoBtn48.setDisable(false);                
                btnMiDinero.setDisable(false);
             }             
         });
         juego.setMensaje("");
         j=0;
         juego.reiniciarApuestas(); // se eliminan las apuesta hechas en el turno anterior
         cliente=juego.getJugadores().get(j);
         j=j+1;
         apuesta=0;
         dineroCliente=cliente.getDinero();
         dineroTem=dineroCliente;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];        
        monedasJugador[0]=0;
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;        
        nombreJugador=cliente.getNombre();  
        nombreJuego.setText(nombreJugador);
         btnGanador.setDisable(true);
         btnSiguiente.setDisable(false);                 
    }
    
    /**
     * pasa el turno de cada jugador hasta que todos hayan apostado, cuando todos finalizan la apuesta
     *muesta el botón ganador para saber quien ganó.
     **/
    public void siguiente(){
      try{
        cliente=juego.getJugadores().get(j);
        dineroCliente=cliente.getDinero();
        j=j+1;
        dineroTem=dineroCliente;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];       
        nombreJugador=cliente.getNombre();  
        nombreJuego.setText(nombreJugador);
        monedasJugador[0]=0;
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;        
      }catch(Exception e){
          btnGanador.setDisable(false);
          btnSiguiente.setDisable(true);
          juegoBtn0.setDisable(true);
        juegoBtn1.setDisable(true);
        juegoBtn2.setDisable(true);
        juegoBtn3.setDisable(true);
        juegoBtn4.setDisable(true);
        juegoBtn5.setDisable(true);
        juegoBtn6.setDisable(true);
        juegoBtn7.setDisable(true);
        juegoBtn8.setDisable(true);
        juegoBtn9.setDisable(true);
        juegoBtn10.setDisable(true);
        juegoBtn11.setDisable(true);
        juegoBtn12.setDisable(true);
        juegoBtn13.setDisable(true);
        juegoBtn14.setDisable(true);
        juegoBtn15.setDisable(true);
        juegoBtn16.setDisable(true);
        juegoBtn17.setDisable(true);
        juegoBtn18.setDisable(true);
        juegoBtn19.setDisable(true);
        juegoBtn20.setDisable(true);
        juegoBtn21.setDisable(true);
        juegoBtn22.setDisable(true);
        juegoBtn23.setDisable(true);
        juegoBtn24.setDisable(true);
        juegoBtn25.setDisable(true);
        juegoBtn26.setDisable(true);
        juegoBtn27.setDisable(true);
        juegoBtn28.setDisable(true);
        juegoBtn29.setDisable(true);
        juegoBtn30.setDisable(true);
        juegoBtn31.setDisable(true);
        juegoBtn32.setDisable(true);
        juegoBtn33.setDisable(true);
        juegoBtn34.setDisable(true);
        juegoBtn35.setDisable(true);
        juegoBtn36.setDisable(true);
        juegoBtn37.setDisable(true);
        juegoBtn38.setDisable(true);
        juegoBtn39.setDisable(true);
        juegoBtn40.setDisable(true);
        juegoBtn41.setDisable(true);
        juegoBtn42.setDisable(true);
        juegoBtn43.setDisable(true);
        juegoBtn44.setDisable(true);
        juegoBtn45.setDisable(true);
        juegoBtn46.setDisable(true);
        juegoBtn47.setDisable(true);
        juegoBtn48.setDisable(true);
        btnMiDinero.setDisable(true);
        nombreJuego.setText("--");
      }      
    }
    @FXML
     /**
      * Metodo encargado de mostrar en un alert la cantidad de dinero que posee cada jugador.
      **/
    void miDinero(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("dinero del jugador "+nombreJugador);
        alert.setHeaderText("Su dinero es:"+dineroTem);
        alert.showAndWait();
    }
 
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 0 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar0(ActionEvent event){
        try{
            
            apuesta=0;
        if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(0);
         dato=0;
         }catch(IOException e){
            System.out.println("No se puede cargar la puta pagina");
         }
    }    
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 1 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar1(ActionEvent event){
        
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(1);
         dato=1;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 2 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar2(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
             dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader abreApuesta = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) abreApuesta.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(2);
         dato=2;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 3 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar3(ActionEvent event){
      
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(3);
         dato=3;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
          }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 4 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar4(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(4);
         dato=4;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 5 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar5(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(5);
         dato=5;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 6 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar6(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(6);
         dato=6;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 7 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar7(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(7);
         dato=7;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 8 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar8(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(8);
         dato=8;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 9 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar9(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(9);
         dato=9;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 10 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar10(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(10);
         dato=10;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 11 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar11(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(11);
         dato=11;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 12 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar12(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(12);
         dato=12;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 13 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar13(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(13);
         dato=13;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 14 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar14(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(14);
         dato=14;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 15 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar15(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(15);
         dato=15;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 16 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar16(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(16);
         dato=16;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 17 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar17(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }         
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;  
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(17);
         dato=17;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 18 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar18(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(18);
         dato=18;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 19 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar19(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(19);
         dato=19;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 20 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar20(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(20);
         dato=20;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 21 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar21(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(21);
         dato=21;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 22 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar22(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(22);
         dato=22;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 23 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar23(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(23);
         dato=23;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 24 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar24(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(24);
         dato=24;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 25 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar25(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(25);
         dato=25;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 26 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar26(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(26);
         dato=26;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 27 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar27(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(27);
         dato=27;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 28 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar28(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(28);
         dato=28;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 29 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar29(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(29);
         dato=29;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 30 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar30(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(30);
         dato=30;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 31 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar31(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(31);
         dato=31;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 32 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar32(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(32);
         dato=32;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 33 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar33(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(33);
         dato=33;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 34 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar34(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(34);
         dato=34;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 35 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar35(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(35);
         dato=35;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle al numero 36 y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar36(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println(36);
         dato=36;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numero es par y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar37(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("Impar");
         dato=37;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numero es impar y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar38(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("par");
         dato=38;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numero esta entre 1 y 18, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar39(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("1-18");
         dato=39;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numero esta entre 19 y 36, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar40(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("19-36");
         dato=40;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numero es de color rojo, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar41(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("Rojo");
         dato=41;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numero es de color negro, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar42(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("Negro");
         dato=42;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
   /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numeropertenece a la primera 12ª, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar43(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("1° Docena");
         dato=43;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numeropertenece a la segunda 12ª, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar44(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("2° docena");
         dato=44;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numeropertenece a la tercera 12ª, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar45(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("3° docena");
         dato=45;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numeropertenece a la fila 1, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar46(ActionEvent event){
        try{
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("Fila 1");
         dato=46;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numeropertenece a la fila 2, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar47(ActionEvent event){
        try{apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("Fila 2");
         dato=47;
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    /**
    * Este metodo es  donde el jugador escoge su la opcion de apostarle a que el numeropertenece a la fila 3, y se 
    * abre la ventana donde se encuentra las fichas para hacer la apuesta con la cantidad de dinero que desee.
    **/
    public void apostar48(ActionEvent event){
        try{
            apuesta=0;
            if(dineroTem!=dineroCliente){
            dineroCliente=dineroTem;
        canMonedas1=cliente.getCanti()[4];
        canMonedas5=cliente.getCanti()[3];
        canMonedas10=cliente.getCanti()[2];
        canMonedas25=cliente.getCanti()[1];
        canMonedas50=cliente.getCanti()[0];
            
        }
        monedasJugador[0]=0;    
        monedasJugador[1]=0;
        monedasJugador[2]=0;
        monedasJugador[3]=0;
        monedasJugador[4]=0;
           dato=48;
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apuesta.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         abreVentanaFichas.setScene(new Scene(root1)); 
         abreVentanaFichas.setResizable(false);
         abreVentanaFichas.show();
         System.out.println("Fila 3");

         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    
}
