/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 22/08/2021
* Ultima alteracao: 01/09/2021
* Nome: ControlePrincipal
* Funcao: Controla a tela principal
*************************************************************** */

package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import threads.ThreadCarroPreto;
import threads.ThreadCarroRoxo;

public class ControlePrincipal implements Initializable {
 
  @FXML
  private ImageView arvore1,arvore2,arvore3,imageCarroRoxo,imageCarroPreto;

  @FXML
  private ImageView sinalizador1,sinalizador2,sinalizador3,sinalizador4;
  
  @FXML
  private ImageView tunel1,tunel2,imageLinha;

  @FXML
  private Slider sliderCarroRoxo,sliderCarroPreto;
  
  @FXML
  private Button btnIniciar,btnParar;


  private ThreadCarroRoxo carroRoxo;
  private ThreadCarroPreto carroPreto;
  private Boolean verificadorTunel1 = true; // variavel de controle do tunel 1
  private Boolean verificadorTunel2 = true; // variavel de controle dp tunel 2
  

  public ControlePrincipal(){
    
    carroRoxo = new ThreadCarroRoxo();
    carroPreto = new ThreadCarroPreto();
    
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    carroRoxo.setControlador(this);
    carroPreto.setControlador(this);
    carregaCenario(); 
    
  }

  /* ***************************************************************
  * Metodo: inicio
  * Funcao: inicia as threads dos dois carros
  * Parametros: ActionEvent event , evento de clique
  * Retorno: nao retorna nada
  *************************************************************** */
  public void inicio(ActionEvent event){

    carroPreto.start();
    carroRoxo.start();
    btnIniciar.setDisable(true);

  }

  /* ***************************************************************
  * Metodo: aumentaVelocidadeRoxo
  * Funcao: controla a velocidade do carro roxo
  * Parametros: nao tem parametros
  * Retorno: retorna um valor do tipo double
  *************************************************************** */
  public double  aumentaVelocidadeRoxo(){
    double velocidade = 0;

      velocidade = sliderCarroRoxo.getValue(); // pega o valor que esta marcado no slider e retorna
      carroRoxo.velocidadeCurva = velocidade / 2;

    return velocidade;
  }

  /* ***************************************************************
  * Metodo: aumentaVelocidadePreto
  * Funcao: controla a velocidade do carro preto
  * Parametros: nao tem parametros
  * Retorno: retorna um valor do tipo double
  *************************************************************** */
  public double  aumentaVelocidadePreto(){
    double velocidade = 0;

      velocidade = sliderCarroPreto.getValue();
      carroPreto.velocidadeCurva = velocidade / 2;

    return velocidade;
  }

  /* ***************************************************************
  * Metodo: sinalizadorFalse
  * Funcao: faz aparecer a imagem de um sinalizador na tela
  * Parametros: um inteiro que eh a posicao na qual o sinalizador esta
  * Retorno: nao retorna nada
  *************************************************************** */
  public void sinalizadorFalse(int num){
    Image image1 = new Image("/recursos/imagens/homem_pare1.png");
    Image image2 = new Image("/recursos/imagens/homem_pare2.png");
    if(num == 1){ // os valores 1,2,3,4 sao os lugares que os sinalizadores vao aparecer
      // esses lugares ja estao pre definidos
      sinalizador1.setImage(image1);
    }else if(num == 2){
      sinalizador2.setImage(image2);
    }else if(num == 3){
      sinalizador3.setImage(image1);
    }else if(num == 4){
      sinalizador4.setImage(image2);
    }
  }

  /* ***************************************************************
  * Metodo: sinalizadorTrue
  * Funcao: faz aparecer a imagem de um sinalizador na tela
  * Parametros: um inteiro que eh a posicao na qual o sinalizador esta
  * Retorno: nao retorna nada
  *************************************************************** */
  public void sinalizadorTrue(int num){
    Image image1 = new Image("/recursos/imagens/homem_siga1.png");
    Image image2 = new Image("/recursos/imagens/homem_siga2.png");
    if(num == 1){
      sinalizador1.setImage(image1);
    }else if(num == 2){
      sinalizador2.setImage(image2);
    }else if(num == 3){
      sinalizador3.setImage(image1);
    }else if(num == 4){
      sinalizador4.setImage(image2);
    }
  }

  /* ***************************************************************
  * Metodo: carregaCenario
  * Funcao: carrega algumas imagens que compoem o cenario
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void carregaCenario(){

    Image image = new Image("/recursos/imagens/linha.png");
    Image image1 = new Image("/recursos/imagens/tunel.png");
    Image image2 = new Image("/recursos/imagens/tunel.png");
    Image image3 = new Image("/recursos/imagens/homem_siga1.png");
    Image image4 = new Image("/recursos/imagens/homem_siga2.png");
    Image image5 = new Image("/recursos/imagens/arvore.png");

    imageLinha.setImage(image);
    tunel1.setImage(image1);
    tunel2.setImage(image2);
    sinalizador1.setImage(image3);
    sinalizador2.setImage(image4);
    arvore1.setImage(image5);
    arvore2.setImage(image5);
    arvore3.setImage(image5);

  }

  /* ***************************************************************
  * Metodo: getCarroRoxo
  * Funcao: retorna um ImageView do carro roxo
  * Parametros: nao tem parametros
  * Retorno: uma ImageView
  *************************************************************** */
  public ImageView getCarroRoxo(){
    return this.imageCarroRoxo;
  }

  /* ***************************************************************
  * Metodo: getCarroPreto
  * Funcao: retorna um ImageView do carro preto
  * Parametros: nao tem parametros
  * Retorno: uma ImageView
  *************************************************************** */
  public ImageView getCarroPreto(){
    return this.imageCarroPreto;
  }

  /* ***************************************************************
  * Metodo: setAcessoTunel1
  * Funcao: modificador da variavel verificador
  * Parametros: um booleano verificador
  * Retorno: nao retorna nada
  *************************************************************** */
  public void setAcessoTunel1(Boolean verificador){
    this.verificadorTunel1 = verificador;
  }

  /* ***************************************************************
  * Metodo: getAcessoTunel1
  * Funcao: retorna o valor que esta no modificador setAcessoTunel1
  * Parametros: nao tem parametros
  * Retorno: uma variavel do tipo booleano
  *************************************************************** */
  public Boolean getAcessoTunel1(){
    return this.verificadorTunel1;
  }

  /* ***************************************************************
  * Metodo: setAcessoTunel2
  * Funcao: modificador da variavel verificador
  * Parametros: um booleano verificador
  * Retorno: nao retorna nada
  *************************************************************** */
  public void setAcessoTunel2(Boolean verificador){
    this.verificadorTunel2 = verificador;
  }

  /* ***************************************************************
  * Metodo: getAcessoTunel2
  * Funcao: retorna o valor que esta no modificador setAcessoTunel2
  * Parametros: nao tem parametros
  * Retorno: uma variavel do tipo booleano
  *************************************************************** */
  public Boolean getAcessoTunel2(){
    return this.verificadorTunel2;
  }
  
  /* ***************************************************************
  * Metodo: encerra
  * Funcao: finaliza o programa
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void encerra(ActionEvent event){

    carroPreto.interrupt();
    carroRoxo.interrupt();
    System.exit(0);
  }

}
