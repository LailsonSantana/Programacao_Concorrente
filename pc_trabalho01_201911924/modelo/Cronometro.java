/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 01/08/2021
* Ultima alteracao: data da ultima alteracao realizada no codigo
* Nome: Your Bet
* Funcao: Jogo de apostas
*************************************************************** */

package modelo;


import controle.ControleJogo;
import javafx.application.Platform;


public class Cronometro extends Thread{

  private ControleJogo controlador;
  private int contador = 0; // variavel de controle do tempo

  /* ***************************************************************
  * Metodo: setControlador
  * Funcao: seta o controlador da classe ControleJogo
  * Parametros: controlador da classe ControleJogo
  * Retorno: nao retorna nada
  *************************************************************** */
  public void setControlador(ControleJogo controlador){ 
    this.controlador=controlador;
  }

  /* ***************************************************************
  * Metodo: run
  * Funcao: executa o codigo da thread
  * Parametros: nao recebe parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void run(){

    while(true){
      Platform.runLater(()->{
        controlador.lblTempo.setText(contador + "");
        // mostra o valor do contador na label tempo da classe ControleJogo
      });
      contador++; // incrementa o contador de tempo
      
      if(contador == 60){ // 60 eh o tempo de jogo 

        Platform.runLater(()->{
          controlador.encerraJogo(); // quando esse tempo for atinjido o jogo sera encerrado
        });
        this.interrupt(); // interrompe a thread
        break; // sai do laco
      }
      if(controlador.avisaCrono == 1){ // usei essa variavel para conseguir encerrar o jogo caso o jogador 
        // quisesse parar de jogar antes de completar os 60 segundos , ou caso a pontuacao do mesmo se 
        //tornasse negativa
        this.interrupt();
        break;
      }
      try {
        Thread.sleep(1000); // contagem dos segundos
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } // fim do laco while

  } // fim do metodo run

}
