/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/10/2021
* Ultima alteracao: 06/10/2021
* Nome: Principal.java
* Funcao: inicializa a tela principal do programa
*************************************************************** */
import controle.ControlePrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/visao/tela_principal.fxml"));
    ControlePrincipal controlador = new ControlePrincipal();
    fxmlLoader.setController(controlador);
    Parent root = fxmlLoader.load();
    Scene tela = new Scene(root);
    primaryStage.setScene(tela);
    primaryStage.show();
  }

  public static void main(String[] args){
    launch(args);
  }
  
}
