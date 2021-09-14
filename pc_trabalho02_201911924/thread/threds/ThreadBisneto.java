/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/08/2021
* Ultima alteracao: 18/08/2021
* Nome: ThreadBisneto.java
* Funcao: executa as acoes da thread Bisneto
*************************************************************** */

package threds;

import controle.ControlePrincipal;
import javafx.application.Platform;

public class ThreadBisneto extends Thread{

  private int idade = -1;
  private ControlePrincipal controlador;

  public void setControlador(ControlePrincipal controlador){
    this.controlador = controlador;
  }

  public void run(){

    while(true){
      idade++;
      Platform.runLater(()->{
        controlador.lblIdadeBisneto.setText(idade + "");
      });
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        
        e.printStackTrace();
      }
      if(idade == 12){
        Platform.runLater(()->{
          controlador.alteraImagem("Bisneto");
        });
        Platform.runLater(()->{
        });
        this.interrupt();
        break;
      }
    }
    
  } // fim do metodo run
  
}
