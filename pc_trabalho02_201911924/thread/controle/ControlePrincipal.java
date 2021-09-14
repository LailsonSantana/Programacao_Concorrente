/* ***************************************************************
* Autor: nome(s) do(s) autor(es) do codigo
* Matricula: 201911924
* Inicio: data de inicio da codificacao
* Ultima alteracao: 18/08/2021
* Nome: ControlePrincipal.java
* Funcao: faz o controle geral do programa
*************************************************************** */

package controle;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import threds.ThreadBisneto;
import threds.ThreadFilhoDois;
import threds.ThreadFilhoTres;
import threds.ThreadFilhoUm;
import threds.ThreadNetoDois;
import threds.ThreadNetoUm;
import threds.ThreadPai;

public class ControlePrincipal implements Initializable{

  @FXML
  private Button btnIniciar;

  @FXML
  public ImageView imageArvore,imagePai,imageFilho3,imageNeto1,imageFilho2,imageNeto2,imageBisneto,imageFilho1;

  @FXML
  public Label lblIdadePai,lblIdadeFilho1,lblIdadeFilho2,lblIdadeFilho3,lblIdadeNeto1,lblIdadeNeto2,lblIdadeBisneto;

  @FXML
  public Label lblPai,lblFilho1,lblFilho2,lblFilho3,lblNeto1,lblNeto2,lblBisneto;


  private ThreadBisneto threadBisneto;
  private ThreadNetoDois threadNetoDois;
  private ThreadNetoUm threadNetoUm;
  private ThreadFilhoTres threadFilhoTres;
  private ThreadFilhoDois threadFilhoDois;
  private ThreadFilhoUm threadFilhoUm;
  private ThreadPai threadPai;

  public ControlePrincipal(){
    // instanciando os threads
    threadPai = new ThreadPai();
    threadFilhoUm = new ThreadFilhoUm();
    threadFilhoDois = new ThreadFilhoDois();
    threadFilhoTres = new ThreadFilhoTres();
    threadNetoUm = new ThreadNetoUm();
    threadNetoDois = new ThreadNetoDois();
    threadBisneto = new ThreadBisneto();
  }


  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    //setando os controladores
    threadPai.setControlador(this);
    threadFilhoUm.setControlador(this);
    threadFilhoDois.setControlador(this);
    threadFilhoTres.setControlador(this);
    threadNetoUm.setControlador(this);
    threadNetoDois.setControlador(this);
    threadBisneto.setControlador(this);
    Image image = new Image("/recursos/imagens/arvore2.png");
    imageArvore.setImage(image);
    
  }

  /* ***************************************************************
  * Metodo: iniciaThreadPai
  * Funcao: da inicio na execucao do thread pai
  * Parametros: ActionEvent event , evento de clique
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreadPai(ActionEvent event) throws InterruptedException{
    Thread.sleep(1000); // espera 1 segundo antes de iniciar
    threadPai.start();
    Image image = new Image("/recursos/imagens/vivo.png");
    imagePai.setImage(image);
    imagePai.setVisible(true);
    lblPai.setVisible(true); // torna visivel a label com as informacoees
    lblIdadePai.setVisible(true);
    btnIniciar.setDisable(true);
    tocaSom(1); // inicia o metodo tocar som
  }

  /* ***************************************************************
  * Metodo: iniciaThreadFilhoUm
  * Funcao: da inicio na execucao do thread filho um
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreadFilhoUm(){
    threadFilhoUm.start();
    Image image = new Image("/recursos/imagens/vivo.png");
    imageFilho1.setImage(image);
    imageFilho1.setVisible(true);
    lblFilho1.setVisible(true);
    lblIdadeFilho1.setVisible(true);
  }

  /* ***************************************************************
  * Metodo: iniciaThreadFilhoDois
  * Funcao: da inicio na execucao do thread filho dois
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreadFilhoDois(){
    threadFilhoDois.start();
    Image image = new Image("/recursos/imagens/vivo.png");
    imageFilho2.setImage(image);
    imageFilho2.setVisible(true);
    lblFilho2.setVisible(true);
    lblIdadeFilho2.setVisible(true);
  }

  /* ***************************************************************
  * Metodo: iniciaThreadFilhoTres
  * Funcao: da inicio na execucao do thread filho tres
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreadFilhoTres(){
    threadFilhoTres.start();
    Image image = new Image("/recursos/imagens/vivo.png");
    imageFilho3.setImage(image);
    imageFilho3.setVisible(true);
    lblFilho3.setVisible(true);
    lblIdadeFilho3.setVisible(true);
    
  }

  /* ***************************************************************
  * Metodo: iniciaThreadNetoUm
  * Funcao: da inicio na execucao do thread neto um
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreadNetoUm(){
    threadNetoUm.start();
    Image image = new Image("/recursos/imagens/vivo.png");
    imageNeto1.setImage(image);
    imageNeto1.setVisible(true);
    lblNeto1.setVisible(true);
    lblIdadeNeto1.setVisible(true);
  }

  /* ***************************************************************
  * Metodo: iniciaThreadNetoDois
  * Funcao: da inicio na execucao do thread neto dois
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreadNetoDois(){
    threadNetoDois.start();
    Image image = new Image("/recursos/imagens/vivo.png");
    imageNeto2.setImage(image);
    imageNeto2.setVisible(true);
    lblNeto2.setVisible(true);
    lblIdadeNeto2.setVisible(true);
  }

  /* ***************************************************************
  * Metodo: iniciaThreadBisneto
  * Funcao: da inicio na execucao do thread bisneto
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreadBisneto(){
    threadBisneto.start();
    Image image = new Image("/recursos/imagens/vivo.png");
    imageBisneto.setImage(image);
    imageBisneto.setVisible(true);
    lblBisneto.setVisible(true);
    lblIdadeBisneto.setVisible(true);
  }

  /* ***************************************************************
  * Metodo: alteraImagem
  * Funcao: altera a imagem que representa se um membro esta vivo ou morto
  * Parametros: string com o nome do membro que sera alterado a imagem
  * Retorno: nao retorna nada
  *************************************************************** */
  public void alteraImagem(String pessoa){
    Image image = new Image("/recursos/imagens/morto.png");
    if(pessoa == "Pai"){
      imagePai.setImage(image);
      lblPai.setStyle("-fx-background-color:#f73112"); // faz a label ficar vermelha
      lblIdadePai.setStyle("-fx-background-color:#f73112");
    }
    else if(pessoa == "Filho1"){
      imageFilho1.setImage(image);
      lblFilho1.setStyle("-fx-background-color:#f73112");
      lblIdadeFilho1.setStyle("-fx-background-color:#f73112");
    }
    else if(pessoa == "Filho2"){
      imageFilho2.setImage(image);
      lblFilho2.setStyle("-fx-background-color:#f73112");
      lblIdadeFilho2.setStyle("-fx-background-color:#f73112");
    }
    else if(pessoa == "Filho3"){
      imageFilho3.setImage(image);
      lblFilho3.setStyle("-fx-background-color:#f73112");
      lblIdadeFilho3.setStyle("-fx-background-color:#f73112");
    }
    else if(pessoa == "Neto1"){
      imageNeto1.setImage(image);
      lblNeto1.setStyle("-fx-background-color:#f73112");
      lblIdadeNeto1.setStyle("-fx-background-color:#f73112");
    }
    else if(pessoa == "Neto2"){
      imageNeto2.setImage(image);
      lblNeto2.setStyle("-fx-background-color:#f73112");
      lblIdadeNeto2.setStyle("-fx-background-color:#f73112");
    }
    else{
      imageBisneto.setImage(image);
      lblBisneto.setStyle("-fx-background-color:#f73112"); 
      lblIdadeBisneto.setStyle("-fx-background-color:#f73112");
    }
    
  }

  /* ***************************************************************
  * Metodo: tocaSom
  * Funcao: toca um som durante a execucao
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void tocaSom(int valor){
    
    String uri = Paths.get("recursos/sons/som.mp3").toUri().toString();
    Media media = new Media(uri);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    if(valor == 1){
      mediaPlayer.play();
    }
    else{
      mediaPlayer.stop();
    } 
  } 
}
