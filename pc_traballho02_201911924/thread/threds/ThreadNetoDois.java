/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/08/2021
* Ultima alteracao: 18/08/2021
* Nome: ThreadNetoDois.java
* Funcao: executa as acoes da thread NetoDois
*************************************************************** */

package threds;

import controle.ControlePrincipal;
import javafx.application.Platform;

public class ThreadNetoDois extends Thread{

  private int idade = -1;
  private ControlePrincipal controlador;

  public void setControlador(ControlePrincipal controlador){
    this.controlador = controlador;
  }

  public void run(){

    while(true){
      idade++;
      Platform.runLater(()->{
        controlador.lblIdadeNeto2.setText(idade + "");
      });
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        
        e.printStackTrace();
      }
      if(idade == 33){ // morte do neto 2
        Platform.runLater(()->{
          controlador.alteraImagem("Neto2");
        });
        this.interrupt();
        break;
      }
    }
    
  } // fim do metodo run
  
}
