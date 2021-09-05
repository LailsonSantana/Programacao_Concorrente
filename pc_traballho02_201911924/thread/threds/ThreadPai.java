/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/08/2021
* Ultima alteracao: 18/08/2021
* Nome: ThreadPai.java
* Funcao: executa as acoes da thread Pai
*************************************************************** */

package threds;

import controle.ControlePrincipal;
import javafx.application.Platform;

public class ThreadPai extends Thread{

  private int idade=-1;
  private ControlePrincipal controlador;

  public void setControlador(ControlePrincipal controlador){
    this.controlador = controlador;
  }

  public void run(){ // inicio do metodo run
    while(true){
      idade++;
      Platform.runLater(()->{
        controlador.lblIdadePai.setText(idade + ""); // modifica a idade na label
      });
      
      if(idade == 22){ // nascimento do filho 1
        Platform.runLater(()->{
          controlador.iniciaThreadFilhoUm();
        });
      }
      if(idade == 25){ // nascimento do filho 2
        Platform.runLater(()->{
          controlador.iniciaThreadFilhoDois();
        });
      }
      if(idade == 32){ // nascimento do filho 3
        Platform.runLater(()->{
          controlador.iniciaThreadFilhoTres();
        });
      }
      
      try {
        Thread.sleep(1000);
      }catch (InterruptedException e) {
        
        e.printStackTrace();
      }

      if(idade == 90){ // morte do pai
        Platform.runLater(()->{
          controlador.alteraImagem("Pai"); // altera a figura na arvore
          controlador.tocaSom(0); // para o som
        });
        this.interrupt();
        break;
      }
    }
  } // fim do metodo run
}
