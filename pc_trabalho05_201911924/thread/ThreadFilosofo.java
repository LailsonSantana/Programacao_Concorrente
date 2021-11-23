/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 16/09/2021
* Ultima alteracao: 29/09/2021
* Nome: ThreadFilosofo.java
* Funcao: executa as acoes da thread
*************************************************************** */

package thread;

import java.util.Random;

import controle.ControlePrincipal;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ThreadFilosofo extends Thread{

  private ControlePrincipal controlador;
  private int id;
  private int dir; // vizinho da direita
  private int esq; // vizinho da esquerda
  private ImageView imageFilosofo;
  private Slider sliderComer;
  private Slider sliderPensar;

  
  public ThreadFilosofo(ControlePrincipal controlador,int identificador,ImageView image,Slider comer,Slider pensar){
    this.controlador = controlador;
    this.id = identificador;
    this.dir = (id+1) % 5;
    this.esq = (id+4) % 5;
    this.imageFilosofo = image;
    this.sliderComer = comer;
    this.sliderPensar = pensar;
  }

  public void run(){

    while(true){

      // esta pensando
      this.pensa();

      // esta com fome
      this.alteraEstado(1);
      try {this.pegaGarfos(this.id);} catch (InterruptedException e) {e.printStackTrace();}

      // esta comendo
      this.come();
      
      // devolve os garfos
      this.alteraEstado(0);
      try {this.devolveGarfo(this.id);} catch (InterruptedException e) {e.printStackTrace();}

    }
  }

  /* ***************************************************************
  * Metodo: alteraEstado
  * Funcao: altera a imagem que simboliza o estado do filosofo
  * Parametros: recebe um inteiro que sinaliza o estado atual do filosofo
  * Retorno: descricao do valor retornado
  *************************************************************** */
  public void alteraEstado(int estado){
    Image image;
    if(estado == 0){
      image = new Image("/recursos/imagens/pensando.png");
      imageFilosofo.setImage(image);
    }
    else if(estado == 1){
      image = new Image("/recursos/imagens/com_fome.png");
      imageFilosofo.setImage(image);
      this.alteraPosicao(1);
      
    }
    else if(estado == 2){
      image = new Image("/recursos/imagens/pega_garfo.png");
      imageFilosofo.setImage(image);
      this.alteraPosicao(2);
    }
    else{
      image = new Image("/recursos/imagens/comendo.png");
      imageFilosofo.setImage(image);
      this.alteraPosicao(3);
    }
  }

  /* ***************************************************************
  * Metodo: geraTempo
  * Funcao: gera um tempo randomico que vai simbolizar o tempo que o filosofo ficara comendo ou pensando
  * Parametros: nao recebe nada
  * Retorno: um inteiro com o tempo gerado
  *************************************************************** */
  public int geraTempo(){
    int tempo = 0;
    Random gerador = new Random();
    tempo = gerador.nextInt(6500) + 3500;
    // o tempo gerado esta no intervalo de 3500 a 10000
    return tempo;
  }

  /* ***************************************************************
  * Metodo: pensa
  * Funcao: executa as acoes do filosofo no estado de pensando
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void pensa(){
    System.out.println("Filosofo "+ (id+1) +" esta pensando !");
    this.alteraEstado(0);
    controlador.getPratos()[id].setVisible(false);
    int tempo = (geraTempo() * tempoPensar());
    // o tempo so eh alterado em novas execucoes desse metodo
    // quando maior for a selecao do slider maior sera o tempo
    while(tempo > 0){
      try {sleep(500);} catch (InterruptedException e1) {e1.printStackTrace();}
      tempo = tempo - 500;
    }
  }

  /* ***************************************************************
  * Metodo: come
  * Funcao: executa as acoes do filosofo no estado de comendo
  * Parametros: nao recebe nada
  * Retorno: no retorna nada
  *************************************************************** */
  public void come(){
    System.out.println("Filosofo "+ (id+1) +" esta comendo !");
    int tempo = (geraTempo() * tempoComer());
    this.alteraEstado(2);
    controlador.getPratos()[id].setVisible(true); // torna o prato visivel
    while(tempo > 0){
      alteraEstado(2);
      try {sleep(500);} catch (InterruptedException e1) {e1.printStackTrace();}
      alteraEstado(3);
      tempo = tempo - 500;
    }
  }

  /* ***************************************************************
  * Metodo: pegaGarfos
  * Funcao: sinaliza que o filosofo esta com fome e tenta pegar os garfos
  * Parametros: um inteiro com o id do filosofo
  * Retorno: nao retorna nada
  *************************************************************** */
  public void pegaGarfos(int filosofo) throws InterruptedException{
    System.out.println("Filosofo "+ (id+1) +" esta com fome !");
    controlador.getMutex().acquire(); // entra na regiao critica
    controlador.getEstado()[filosofo] = 1; // filosofo quer comer
    this.testaGarfos(filosofo); // tenta pegar os 2 garfos
    controlador.getMutex().release();
    controlador.getSemaphores()[filosofo].acquire();
  }

  /* ***************************************************************
  * Metodo: testaGarfos
  * Funcao: verifica se um filosofo que esta com fome ja pode comer
  * Parametros: um inteiro com o id do filosofo
  * Retorno: nao retorna nada
  *************************************************************** */
  public void testaGarfos(int filosofo){

    int[] estado = controlador.getEstado();
    int esquerda = (filosofo + 4) % 5;
    int direita = (filosofo  + 1) % 5;
    if(estado[filosofo] == 1 && estado[esquerda] != 2 && estado[direita] != 2){
      controlador.getEstado()[filosofo] = 2; // filosofo passa para estado de comendo
      controlador.getSemaphores()[filosofo].release();
    }
  }

  /* ***************************************************************
  * Metodo: devolveGarfo
  * Funcao: encerra a acao de comendo do filosofo atual , e testa se os filosofos adjascentes estao com fome
  * Parametros: um inteiro com o id do filosofo
  * Retorno: descricao do valor retornado
  *************************************************************** */
  public void devolveGarfo(int filosofo) throws InterruptedException{
    controlador.getMutex().acquire(); // entra na RC
    controlador.getEstado()[filosofo] = 0; // devolve os garfos
    this.testaGarfos(esq);
    this.testaGarfos(dir);
    controlador.getMutex().release(); // sai da RC
  }

  /* ***************************************************************
  * Metodo: alteraPosicao
  * Funcao: ajusta a posicao da imagem no layout
  * Parametros: um inteiro que simboliza qual das imagens sera ajustada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void alteraPosicao(int pos){
    // para fazer a animacao de comer , foi necessário fazer um ajuse manual nas imagens
    // mudancas referentes as imagens na qual o filosofo esta com fome ou comendo
    if(pos != 3){
      if(this.id == 0){
        imageFilosofo.setRotate(-10);
        imageFilosofo.setLayoutX(50);
        imageFilosofo.setLayoutY(170);
      }
      else if(this.id == 1){
        imageFilosofo.setRotate(-70);
        imageFilosofo.setLayoutX(140);
        imageFilosofo.setLayoutY(352);
      }else if(this.id == 2){
        imageFilosofo.setRotate(215);
        imageFilosofo.setLayoutX(330);
        imageFilosofo.setLayoutY(315);
      }else if(this.id == 3){
        imageFilosofo.setRotate(150);
        imageFilosofo.setLayoutX(375);
        imageFilosofo.setLayoutY(135);
      }else{
        imageFilosofo.setRotate(55);
        imageFilosofo.setLayoutX(165);
        imageFilosofo.setLayoutY(21);
      }
    }
    else{
      // mudancas referente a imagem a qual o filosofo esta pensando
      if(this.id == 0){
        imageFilosofo.setRotate(-10);
        imageFilosofo.setLayoutX(25);
        imageFilosofo.setLayoutY(200);
      }
      else if(this.id == 1){
        imageFilosofo.setRotate(-70);
        imageFilosofo.setLayoutX(152);
        imageFilosofo.setLayoutY(370);
      }else if(this.id == 2){
        imageFilosofo.setRotate(215);
        imageFilosofo.setLayoutX(359);
        imageFilosofo.setLayoutY(311);
      }else if(this.id == 3){
        imageFilosofo.setRotate(150);
        imageFilosofo.setLayoutX(385);
        imageFilosofo.setLayoutY(100);
      }else{
        imageFilosofo.setRotate(55);
        imageFilosofo.setLayoutX(145);
        imageFilosofo.setLayoutY(21);
      }
    }
  }

  /* ***************************************************************
  * Metodo: tempo comer
  * Funcao: retorna o valor selecionado no slider comer
  * Parametros: nao recebe nada
  * Retorno: um inteiro com o valor selecionado
  *************************************************************** */
  public int tempoComer(){
    int velocidade = (int)(sliderComer.getValue());
    return velocidade;
  }

  /* ***************************************************************
  * Metodo: tempo comer
  * Funcao: retorna o valor selecionado no slider pensar
  * Parametros: nao recebe nada
  * Retorno: um inteiro com o valor selecionado
  *************************************************************** */
  public int tempoPensar(){
    int velocidade = (int)(sliderPensar.getValue());
    return velocidade;
  }
}
