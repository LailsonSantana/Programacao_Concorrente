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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Utils;


public class ControleRegistro implements Initializable,Utils {

public static String NOME_JOGADOR;

@FXML
private Button btnSalvar,btnCancelar;

@FXML
private Label lblNome,lblIdade;

@FXML
private TextField txtNome,txtIdade;


private Stage stagePrincipal = new Stage();
private Stage stageRegistro = new Stage();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    
  }

  /* ***************************************************************
  * Metodo: setStage
  * Funcao: seta o objeto do tipo Stage da classe Principal e dessa classe (ControleRegistro)
  * Parametros: stage da classe principal e da classe registro
  * Retorno: nao retorna nada
  *************************************************************** */
  public void setStage(Stage stage,Stage stage1){
    this.stageRegistro = stage;
    this.stagePrincipal = stage1;
  }

  /* ***************************************************************
  * Metodo: salvaRegistro
  * Funcao: salva os dados do usuario e executa o metodo abreTelaJogo
  * Parametros: ActionEvent , evento de clique
  * Retorno: nao retorna nada
  *************************************************************** */
  public void salvaRegistro(ActionEvent event) throws IOException{
    String nome = txtNome.getText();
    String idade = txtIdade.getText();
    NOME_JOGADOR = nome;

    if(!(nome.isEmpty())){ // verifica se os campos estao vazios
      this.abreTelaJogo();
    }
    else{
      mensagemInformativa("Dados invalidos !","Voce precisa preencher todos os campos !");
    }
    
  }

  /* ***************************************************************
  * Metodo: abreTelaJogo
  * Funcao: abre a tela na qual sera feita a execucao do jogo
  * Parametros: sem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void abreTelaJogo() throws IOException{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/tela_jogo.fxml"));
    ControleJogo controlador = new ControleJogo();
    loader.setController(controlador);
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Your Bet");
    stage.resizableProperty().setValue(Boolean.FALSE); // desativa o maximizar e o minimizar
    stage.show();
    stageRegistro.close(); //fecha a tela principal 
    stagePrincipal.close(); //fecha a tela secundaria
  }

  /* ***************************************************************
  * Metodo: cancelaRegistro
  * Funcao: cancela a tentativa de resgistro do usuario
  * Parametros: ActionEvent , evento de clique
  * Retorno: nao retorna nada
  *************************************************************** */
  public void cancelaRegistro(ActionEvent event) {
         
    stageRegistro.close(); // fecha a tela
    
  }

  /* ***************************************************************
  * Metodo: mensagemInformativa
  * Funcao: exibe uma mensagem para o usuario
  * Parametros: duas strings que sao titulo e conteudo da mensagem
  * Retorno: nao retorna nada
  *************************************************************** */
  @Override
  public void mensagemInformativa(String titulo,String mensagem) {
    Alert alert = new Alert(Alert.AlertType.WARNING); 
    alert.setTitle("INFO");
    alert.setHeaderText(titulo);
    alert.setContentText(mensagem);
    alert.show();
  }
  
}
