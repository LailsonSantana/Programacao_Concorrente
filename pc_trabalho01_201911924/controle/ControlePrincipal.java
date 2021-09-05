/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 29/07/2021
* Ultima alteracao: 04/08/2021
* Nome: Your Bet
* Funcao: Jogo de apostas
*************************************************************** */

package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class ControlePrincipal implements Initializable {

  @FXML
  private Button btnNovoJogo,btnSair,btnInformacoes,btnSairInfo;

  @FXML
  private Text txtTitulo;

  @FXML
  private TextArea txtInformacoes;

  @FXML
  private AnchorPane anchorPane;

  private Stage stagePrincipal = new Stage();

  

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    txtInformacoes.setVisible(false);
    
  }

  /* ***************************************************************
  * Metodo: abreTelaRegistro
  * Funcao: abre a tela usada para registrar um usuario
  * Parametros: ActionEvent , evento de clique
  * Retorno: void
  *************************************************************** */

  public void abreTelaRegistro(ActionEvent event) throws IOException{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/tela_registro.fxml"));
    ControleRegistro controlador = new ControleRegistro();
    loader.setController(controlador);
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    controlador.setStage(stage,stagePrincipal);
    stage.setScene(scene);
    stage.setTitle("Registro");
    stage.resizableProperty().setValue(Boolean.FALSE);
    stage.show();

  }

  /* ***************************************************************
  * Metodo: setStagePrincipal
  * Funcao: Seta o objeto do tipo Stage da classe Principal
  * Parametros: Stage
  * Retorno: void
  *************************************************************** */
  public void setStagePrincipal(Stage stage){
    this.stagePrincipal = stage;
  }

  /* ***************************************************************
  * Metodo: fechaTela
  * Funcao: sai do programa
  * Parametros: ActionEvent , evento de clique
  * Retorno: void
  *************************************************************** */
  public void fechaTela(ActionEvent event){
    System.exit(0);
  }

  /* ***************************************************************
  * Metodo: veInformacoes
  * Funcao: mostra algumas informacoes sobre o jogo
  * Parametros: ActionEvent , evento de clique
  * Retorno: void
  *************************************************************** */
  public void veInformacoes(ActionEvent event){
    desabilitaComponentes();
    anchorPane.setStyle("-fx-background-color:#fffff4");
  }

  /* ***************************************************************
  * Metodo: desabilitaComponentes
  * Funcao: desabilita a vizualizacao de alguns componentes da tela
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void desabilitaComponentes(){

    btnNovoJogo.setVisible(false);
    btnSair.setVisible(false);
    btnInformacoes.setVisible(false);
    txtTitulo.setVisible(false);
    txtInformacoes.setVisible(true);
    btnSairInfo.setVisible(true);

  }

  /* ***************************************************************
  * Metodo: habilitaComponentes
  * Funcao: habilita alguns componentes da tela
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void habilitaComponentes(){

    anchorPane.setStyle("-fx-background-color:#156a94");
    txtTitulo.setVisible(true);
    btnNovoJogo.setVisible(true);
    btnSair.setVisible(true);
    btnInformacoes.setVisible(true);
    txtInformacoes.setVisible(false);
    btnSairInfo.setVisible(false);
  }

  
  


  

     
      
        
      

    
    
}
