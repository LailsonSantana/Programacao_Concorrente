/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 10/09/2021
* Ultima alteracao: 14/09/2021
* Nome: Principal
* Funcao: executa as acoes da thread produtor
*************************************************************** */

package threads;

import controle.ControlePrincipal;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ThreadProdutor extends Thread{

  private ControlePrincipal controlador;
  private ImageView imageProdutor;

  public void setControlador(ControlePrincipal controle){
    this.controlador = controle;
    this.imageProdutor = controle.getImageProdutor();
  }

  public void run(){
    while(true){ // while principal

      try {sleep(500);}catch (InterruptedException e){e.printStackTrace();}
      // pausa a abelha em cima da flor 
      while(imageProdutor.getLayoutX() < 350){
        this.sobeAbelha(); // faz a abelha voar ate que ela atinja a posicao 350 do layout 
        this.sleep();
      }
      try {controlador.getVazio().acquire();}catch(InterruptedException e) {e.printStackTrace();}
      // decrementa a quantidade de posicoes vazias
      try{controlador.getMutex().acquire();}catch(InterruptedException e) {e.printStackTrace();}
      // verifica se o buffer nao esta sendo usado 
      try {sleep(500);}catch (InterruptedException e){e.printStackTrace();}
      // pequena pausa no momento em que a abelha esta na colmeia
      Platform.runLater(()->{controlador.modificaFavo(controlador.getFavos().size(),true);});
      // torna visivel um favo , simulando a producao 
      System.out.println("Elemento a ser adicionado :" +controlador.getFavos().size());
      controlador.getFavos().add(controlador.getFavos().size());
      // adiciona inteiros dentro do vetor , que simulam o favo de mel 
      // os inteiros adicionados eh um numero que representa a quantidade de elementos que
      // tem atualmente no vetor 
      Platform.runLater(()->{controlador.setMel(controlador.getFavos().size());});
      // mostra na label a quantidade de favos produzidos que estao disponiveis para consumo 
      controlador.getMutex().release();
      // deixa regiao critica
      controlador.getCheio().release();
      // incrementa o contador de posicoes ocupadas
      while(imageProdutor.getLayoutX() > 40){
        this.desceAbelha();
        this.sleep();
      }
    } // fim do while principal
  }

  /* ***************************************************************
  * Metodo: sobeAbelha
  * Funcao: faz a abelha subir simulando o voo pra cima
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void sobeAbelha(){
    Image image;

    image = new Image("/recursos/imagens/abelhaDireita.png");
    this.imageProdutor.setImage(image);
    this.imageProdutor.setLayoutX(this.imageProdutor.getLayoutX()+controlador.aumentaProducao());
    this.imageProdutor.setLayoutY(this.imageProdutor.getLayoutY()-controlador.aumentaProducao());
  }

  /* ***************************************************************
  * Metodo: desceAbelha
  * Funcao: faz a abelha descer simulando o voo pra baixo
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void desceAbelha(){
    Image image;

    image = new Image("/recursos/imagens/abelhaEsquerda.png");
    this.imageProdutor.setImage(image);
    this.imageProdutor.setLayoutX(this.imageProdutor.getLayoutX()-controlador.aumentaProducao());
    this.imageProdutor.setLayoutY(this.imageProdutor.getLayoutY()+controlador.aumentaProducao());
  }

  /* ***************************************************************
  * Metodo: sleep
  * Funcao: executa o comando sleep
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void sleep(){
    try {sleep(80);
    }catch (InterruptedException e) {e.printStackTrace();}
  }
  
}
