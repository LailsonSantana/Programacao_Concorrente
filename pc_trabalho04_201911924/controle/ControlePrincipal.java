/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 05/09/2021
* Ultima alteracao: 14/09/2021
* Nome: ControlePrincipal
* Funcao: classe de controle do programa
*************************************************************** */

package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import threads.ThreadConsumidor;
import threads.ThreadProdutor;

public class ControlePrincipal implements Initializable{

  @FXML
  private Button btnIniciar;

  @FXML
  private Slider sliderProdutor;

  @FXML
  private Slider sliderConsumidor;

  @FXML
  private ImageView imageAbelha,imagePassaro,imageFlor,imageSol,imageColmeia,imageGalho,imageCasa;

  @FXML Label lblMelRoubado;

  @FXML
  private ImageView favo1,favo2,favo3,favo4,favo5,favo6,favo7,favo8,favo9,
  favo10,favo11,favo12,favo13,favo14,favo15,favo16,favo17,favo18,favo19,favo20;

  private ThreadProdutor threadProdutor;
  private ThreadConsumidor threadConsumidor;
  private ArrayList<ImageView> favos; // ArrayList com as imagens dos favos
  private ArrayList<Integer> favosMel; // ArrayList que vai simular local na qual eh guardado os favos
  private Semaphore mutex,vazio,cheio; // semaforos
  
  public ControlePrincipal (){
    threadProdutor = new ThreadProdutor();
    threadConsumidor = new ThreadConsumidor();
    favos = new ArrayList<>();
    favosMel = new ArrayList<>();
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    threadProdutor.setControlador(this);
    threadConsumidor.setControlador(this);
    this.adicionaFavos();
    this.carregaCenario();
    this.mutex = new Semaphore(1);
    this.vazio = new Semaphore(20);
    this.cheio = new Semaphore(0);
    
  }

  /* ***************************************************************
  * Metodo: inicia
  * Funcao: inicializa o programa
  * Parametros: ActionEvent event , evento de clique
  * Retorno: nao retorna nada
  *************************************************************** */
  public void inicia(ActionEvent event){
    threadProdutor.start();
    threadConsumidor.start();
    btnIniciar.setDisable(true);
  }

  /* ***************************************************************
  * Metodo: getImageProdutor
  * Funcao: retorna a imagem da abelha
  * Parametros: nao recebe nada
  * Retorno: ImageView
  *************************************************************** */
  public ImageView getImageProdutor(){
    return this.imageAbelha;
  }

  /* ***************************************************************
  * Metodo: getImageConsumidor
  * Funcao: retorna a imagem do passaro
  * Parametros: nao recebe nada
  * Retorno: ImageView
  *************************************************************** */
  public ImageView getImageConsumidor(){
    return this.imagePassaro;
  }

  /* ***************************************************************
  * Metodo: aumentaProducao
  * Funcao: altera a velocidade da abelha
  * Parametros: nao recebe nada
  * Retorno: um double com a quantidade a ser aumentada
  *************************************************************** */
  public double aumentaProducao(){
    double velocidade = 0;
    velocidade = sliderProdutor.getValue();
    return velocidade;
  }

  /* ***************************************************************
  * Metodo: aumentaConsumo
  * Funcao: altera a velocidade do passaro
  * Parametros: nao recebe nada
  * Retorno: um double com a quantidade a ser aumentada
  *************************************************************** */
  public double aumentaConsumo(){
    double velocidade = 0;
    velocidade = sliderConsumidor.getValue();
    return velocidade;
  }

  /* ***************************************************************
  * Metodo: adicionaFavos
  * Funcao: adiciona as 20 imagens em um array
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void adicionaFavos(){ // a ideia eh que para ilustrar melhor a producao e o consumo
    favos.add(favo1);  // toda vez que for que for produzido um novo favo eu adiciono
    favos.add(favo2);  // um hexagono na tela , para ilustar esse favo , e toda vez que for
    favos.add(favo3);  // consumido , eu desabilito a imagem
    favos.add(favo4);
    favos.add(favo5);
    favos.add(favo6);
    favos.add(favo7);
    favos.add(favo8);
    favos.add(favo9);
    favos.add(favo10);
    favos.add(favo11);
    favos.add(favo12);
    favos.add(favo13);
    favos.add(favo14);
    favos.add(favo15);
    favos.add(favo16);
    favos.add(favo17);
    favos.add(favo18);
    favos.add(favo19);
    favos.add(favo20);
  }

  /* ***************************************************************
  * Metodo: modificaFavo
  * Funcao: torna o favo visivel ou invisivel
  * Parametros: receber um int com a posicao do favo , e um booleano com um estado
  * Retorno: nao retorna nada
  *************************************************************** */
  public void modificaFavo(int favo,boolean a){
    favos.get(favo).setVisible(a);
  }

  /* ***************************************************************
  * Metodo: carregaCenario
  * Funcao: coloca na tela , alguns componentes que fazem parte do cenario
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void carregaCenario(){
    Image image = new Image("/recursos/imagens/flores.png");
    Image image1 = new Image("/recursos/imagens/abelhaDireita.png");
    Image image2 = new Image("/recursos/imagens/passaroEsquerda.png");
    Image image3 = new Image("/recursos/imagens/hex.png");
    Image image5 = new Image("/recursos/imagens/colmeia.png");
    Image image6 = new Image("/recursos/imagens/galho.png");
    Image image7 = new Image("/recursos/imagens/casa_pass.png");
    imageFlor.setImage(image);
    imageAbelha.setImage(image1);
    imagePassaro.setImage(image2);
    imageColmeia.setImage(image5);
    imageGalho.setImage(image6);
    imageCasa.setImage(image7);
    for(int i=0;i<20;i++){ // for setar a imagem dos favos
      favos.get(i).setImage(image3);
      favos.get(i).setVisible(false); // todos sao iniciados como invisiveis
    }
  }

  /* ***************************************************************
  * Metodo: getMutex
  * Funcao: retornar a instancia do Semaforo mutex
  * Parametros: nao recebe nada
  * Retorno: retorna a instancia do semaforo
  *************************************************************** */
  public Semaphore getMutex(){
    return this.mutex;
  }

  /* ***************************************************************
  * Metodo: getVazio
  * Funcao: retornar a instancia do Semaforo vazio
  * Parametros: nao recebe nada
  * Retorno: retorna a instancia do semaforo
  *************************************************************** */
  public Semaphore getVazio(){
    return this.vazio;
  }

  /* ***************************************************************
  * Metodo: getCheio
  * Funcao: retornar a instancia do Semaforo cheio
  * Parametros: nao recebe nada
  * Retorno: retorna a instancia do semaforo
  *************************************************************** */
  public Semaphore getCheio(){
    return this.cheio;
  }

  /* ***************************************************************
  * Metodo: getMelRoubado
  * Funcao: pega o valor que esta na label e converte para inteiro
  * Parametros: nao recebe nada
  * Retorno: retorna um inteiro com o valor convertido
  *************************************************************** */
  public int getMel(){
    int valor = Integer.parseInt(lblMelRoubado.getText().toString());
    return valor;
  }

  /* ***************************************************************
  * Metodo: setMelRoubado
  * Funcao: mostra na label a quantidade de favos disponiveis
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void setMel(int mel){
    lblMelRoubado.setText(mel + "");
  }

  /* ***************************************************************
  * Metodo: getFavos
  * Funcao: retornar um ArrayList que simula os favos
  * Parametros: nao recebe nada
  * Retorno: retorna um ArrayList
  *************************************************************** */
  public ArrayList<Integer> getFavos(){
    return this.favosMel;
  }

  
}
