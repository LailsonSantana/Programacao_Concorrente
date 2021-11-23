/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 16/09/2021
* Ultima alteracao: 29/09/2021
* Nome: ControlePrincpal.java
* Funcao: faz o controle dos componentes da tela principal
*************************************************************** */

package controle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import thread.ThreadFilosofo;

public class ControlePrincipal implements Initializable{

  @FXML
  private Button btnIniciar;

  @FXML
  private ImageView imageMesa;

  @FXML
  private ImageView filosofo1,filosofo2,filosofo3,filosofo4,filosofo5;

  @FXML
  private ImageView sopa1,sopa2,sopa3,sopa4,sopa5;

  @FXML
  private Slider comer1,comer2,comer3,comer4,comer5,pensar1,pensar2,pensar3,pensar4,pensar5;
  
  public final int quant_filosofos = 5;
  private int[] estado = new int[quant_filosofos]; // estado do filosofo i
  private Slider[] comer = new Slider[quant_filosofos];
  private Slider[] pensar = new Slider[quant_filosofos];
  private ImageView[] imageFilosofos = new ImageView[quant_filosofos];
  private ImageView[] pratos = new ImageView[quant_filosofos];
  private Semaphore[] semFilosofos = new Semaphore[quant_filosofos];
  private ThreadFilosofo[] threadsFilosofos = new ThreadFilosofo[quant_filosofos];
  private Semaphore mutex;
  
  //public final static int pensando = 0;
  //public final static int esfomeado = 1;
  //public final static int comendo = 2;

  

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    this.mutex = new Semaphore(1);
    this.atribuiComponentes();
    this.instanciaThreads();
    this.carregaCenario();

  }

  /* ***************************************************************
  * Metodo: instanciaThreads
  * Funcao: instancia as threads e semaforos
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void instanciaThreads(){
    for(int i=0;i<5;i++){
      threadsFilosofos[i] = new ThreadFilosofo(this, i,imageFilosofos[i],comer[i],pensar[i]);
      semFilosofos[i] = new Semaphore(0);
    }
  }

  /* ***************************************************************
  * Metodo: iniciaThreads
  * Funcao: inicia a execucao das threads
  * Parametros: ActionEvent event , evento de clique
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreads(ActionEvent event){
    for(int i=0;i<5;i++){
      threadsFilosofos[i].start();
    }
    btnIniciar.setVisible(false);
  }

  /* ***************************************************************
  * Metodo: getMutex
  * Funcao: retornar o semaforo mutex
  * Parametros: nao recebe nada
  * Retorno: retorna o semaforo mutex
  *************************************************************** */
  public Semaphore getMutex(){
    return this.mutex;
  }

  /* ***************************************************************
  * Metodo: getEstado
  * Funcao: retornar o vetor de estados
  * Parametros: nao recebe nada
  * Retorno: retorna um vetor
  *************************************************************** */
  public int[] getEstado(){
    return this.estado;
  }

  /* ***************************************************************
  * Metodo: getSemaphores
  * Funcao: retornar um vetor com os semaforos dos filosofos
  * Parametros: nao recebe nada
  * Retorno: retorna um vetor de semaforos
  *************************************************************** */
  public Semaphore[] getSemaphores(){
    return this.semFilosofos;
  }

  /* ***************************************************************
  * Metodo: carregaCenario
  * Funcao: carrega os componentes da tela
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void carregaCenario(){

    Image image = new Image("/recursos/imagens/mesa.jpeg");
    Image image1 = new Image("/recursos/imagens/pensando.png");
    Image image2 = new Image("/recursos/imagens/sopa.png");
    imageMesa.setImage(image);
    
    for(int i=0;i<quant_filosofos;i++){
      imageFilosofos[i].setImage(image1);
    }
    sopa1.setImage(image2);
    sopa2.setImage(image2);
    sopa3.setImage(image2);
    sopa4.setImage(image2);
    sopa5.setImage(image2);
    for(int i=0;i<quant_filosofos;i++){
      pratos[i].setVisible(false); // torna os pratos invisiveis no inicio
    }
  }

  /* ***************************************************************
  * Metodo: atribuiComponentes()
  * Funcao: coloca os componentes dentro de vetores
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void atribuiComponentes(){
    
    imageFilosofos[0] = filosofo1;
    imageFilosofos[1] = filosofo2;
    imageFilosofos[2] = filosofo3;
    imageFilosofos[3] = filosofo4;
    imageFilosofos[4] = filosofo5;
    pratos[0] = sopa1;
    pratos[1] = sopa2;
    pratos[2] = sopa3;
    pratos[3] = sopa4;
    pratos[4] = sopa5;
    comer[0] = comer1;
    comer[1] = comer2;
    comer[2] = comer3;
    comer[3] = comer4;
    comer[4] = comer5;
    pensar[0] = pensar1;
    pensar[1] = pensar2;
    pensar[2] = pensar3;
    pensar[3] = pensar4;
    pensar[4] = pensar5;
 
  }

  /* ***************************************************************
  * Metodo: getPratos
  * Funcao: retornar um vetor com as imagens dos pratos
  * Parametros: nao recebe nada
  * Retorno: retorna um vetor de imagens
  *************************************************************** */
  public ImageView[] getPratos(){
    return this.pratos;
  }

  /* ***************************************************************
  * Metodo: getImageFilosofo
  * Funcao: retornar a imagem de cada filosofo
  * Parametros: um inteiro com o id do filosofo
  * Retorno: retorna uma imagem
  *************************************************************** */
  public ImageView getImageFilosofo(int filosofo){
    return imageFilosofos[filosofo];
  }
}
