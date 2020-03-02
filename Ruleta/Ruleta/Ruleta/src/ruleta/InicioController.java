/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleta;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ruleta.Ruleta;

/**
 *
 * @author andres.castro3
 */
public class InicioController implements Initializable {
    @FXML
    public  Stage stage1 = new Stage();
    @FXML
    private Label mensaje;
    
    @FXML
    private Button juego;

    @FXML
    private Button instruccion;
    
    @FXML
    private javafx.scene.control.Button btnIniciarJuego;
    
   /*
    * cuando se muestra la pantalla principal y el jugador elige iniciar juego, este método lo lleva a la ventana
    * para registrar los jugadores que van a participar
    */
   @FXML
    void iniciar(ActionEvent event) throws IOException, Exception {
       try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registro.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage =(Stage) btnIniciarJuego.getScene().getWindow();
         stage.hide();
         stage1.setScene(new Scene(root1)); 
         stage1.setResizable(false);
         stage1.show();
         }catch(IOException e){
             System.out.println("No se puede cargar la puta pagina");
         }
    }
    
    @FXML
    void instrucciones(ActionEvent event){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Manual de Usuario");
         /*alert.setHeaderText("El objetivo principal es predecir que número caerá \nsus opciones son: \n"
                 + "Números: pagará 36 veces lo apostado \nDocenas o filas: pagará 3 veces lo apostado \n"
                 + "Color: Pagará 2 veces lo apostado \nPar o impar: pagará 2 veces lo apostado \n"
                 + "1-18 o 19-36: Pagará 2 veces lo apostado");*/
         alert.setHeaderText("Registro:\n" +
"Para iniciar el juego debe registrar como mínimo 1 jugador, ingresa su nombre en el campo de texto y presiona en el botón REGISTRAR, cuando desee iniciar el juego presiona el botón \nJUGAR, en cambio si no desea jugar en esta ocasión presiona CANCELAR.\n" +
"\n" +
"Juego:\n" +
"En la pantalla del juego debe presionar el botón EMPEZAR para que se habilite el juego, tiene la posibilidad "
+ "de escoger un número del 0 al 36, 1ra. docena, 2da. docena, 3ra. docena, \n1-18, 19-36, rojo, negro, par, impar.\n" +
"Al escoger un botón se abrirá una pantalla para colocar la cantidad \nde dinero que quiere apostar a esta opción, "
+ "para apostar debe darle en el botón apostar y si no quiere puede cancelar la apuesta presionando en el botón "
+ "cancelar, \ncuando no desee apostar más presiona el botón SIGUIENTE para darle turno al siguiente jugador, "
+ "cuando hayan apostado todos los jugadores se habilitará el botón \nGANADOR para saber que número fue el ganador, \n"
+ "se informa que jugadores perdieron y ganaron con su respectivo dinero.");
         alert.showAndWait();         
    }
    @Override
    public void initialize(URL url,ResourceBundle rb){
     //Todo
    }

}
