/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/08/2021
* Ultima alteracao: 18/08/2021
* Nome: ThreadNetoUm.java
* Funcao: executa as acoes da thread NetoUm
*************************************************************** */

package threds;

import controle.ControlePrincipal;
import javafx.application.Platform;

public class ThreadNetoUm extends Thread{

  private int idade = -1;
  private ControlePrincipal controlador;

  public void setControlador(ControlePrincipal controlador){
    this.controlador = controlador;
  }

  public void run(){

    while(true){
      idade++;
      Platform.runLater(()->{
        controlador.lblIdadeNeto1.setText(idade + "");
      });

      if(idade == 30){ // nascimento do bisneto
        Platform.runLater(()->{
          controlador.iniciaThreadBisneto();
        });
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        
        e.printStackTrace();
      }
      if(idade == 35){ // morte do neto 1
        Platform.runLater(()->{
          controlador.alteraImagem("Neto1");
        });
        this.interrupt();
        break;
      }
    }
    
  } // fim do metodo run
  
}
