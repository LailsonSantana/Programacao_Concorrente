/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 10/09/2021
* Ultima alteracao: 14/09/2021
* Nome: Principal
* Funcao: executa as acoes da thread consumidor
*************************************************************** */
package threads;


import controle.ControlePrincipal;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ThreadConsumidor extends Thread{

  private ControlePrincipal controlador;
  private ImageView imageConsumidor;

  public void setControlador(ControlePrincipal controle){
    this.controlador = controle;
    this.imageConsumidor = controlador.getImageConsumidor();
  }

  public void run(){
    while(true){ // while principal
      try {sleep(500);}catch (InterruptedException e){e.printStackTrace();}
      // esse sleep pausa o passaro la em baixo 
      while(imageConsumidor.getLayoutX() > 420){
        this.sobePassaro();
        this.sleep();
      }
      try {controlador.getCheio().acquire();}catch (InterruptedException e) {e.printStackTrace();}
      try {controlador.getMutex().acquire();}catch (InterruptedException e) {e.printStackTrace();}
      try {sleep(500);}catch (InterruptedException e){e.printStackTrace();}

      int pos = controlador.getFavos().size() - 1; // se o produtor adicionou 0 entao o 
      // consumidor vai buscar pelo numero 0 e remover , isso serve para nao dar conflitos com relacao
      // a numeros repetidos 
      System.out.println("Elemento a ser removido :" + pos);
      controlador.getFavos().remove(pos);
      // remove o inteiro que esta dentro do vetor representando um favo
      Platform.runLater(()->{controlador.modificaFavo(pos,false);});
      // deixa o favo invisivel
      Platform.runLater(()->{controlador.setMel(controlador.getFavos().size());});
      // seta o numero na label 
      controlador.getMutex().release();
      // deixa regiao critica
      controlador.getVazio().release();
      // incrementa o contador de posicoes vazias
      while(imageConsumidor.getLayoutX() < 698){
        this.descePassaro();
        this.sleep();
      }
    } // fim do while principal
  }

  /* ***************************************************************
  * Metodo: sobePassaro
  * Funcao: faz o passado subir simulando o voo pra cima
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void sobePassaro(){
    Image image;
    image = new Image("/recursos/imagens/passaroEsquerda.png");
    this.imageConsumidor.setImage(image);
    this.imageConsumidor.setLayoutX(this.imageConsumidor.getLayoutX()-controlador.aumentaConsumo());
    this.imageConsumidor.setLayoutY(this.imageConsumidor.getLayoutY()-controlador.aumentaConsumo());
    // nessa parte eu faco o uso do aumentaConsumo , que vai aumenta a velocidade
  }

  /* ***************************************************************
  * Metodo: descePassaro
  * Funcao: faz o passado descer simulando o voo pra baixo
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void descePassaro(){
    Image image;
    image = new Image("/recursos/imagens/passaroDireita.png");
    this.imageConsumidor.setImage(image);
    this.imageConsumidor.setLayoutX(this.imageConsumidor.getLayoutX()+controlador.aumentaConsumo());
    this.imageConsumidor.setLayoutY(this.imageConsumidor.getLayoutY()+controlador.aumentaConsumo());
  }

  /* ***************************************************************
  * Metodo: sleep
  * Funcao: executa o comando sleep
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void sleep(){
    try {
      sleep(1);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}