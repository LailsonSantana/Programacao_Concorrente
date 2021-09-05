/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/08/2021
* Ultima alteracao: 18/08/2021
* Nome: ThreadFilhoUm.java
* Funcao: executa as acoes da thread FilhoUm
*************************************************************** */

package threds;

import controle.ControlePrincipal;
import javafx.application.Platform;

public class ThreadFilhoUm extends Thread{

  private int idade = -1;
  private ControlePrincipal controlador;

  public void setControlador(ControlePrincipal controlador){
    this.controlador = controlador;
  }

  public void run(){
    
    while(true){
      idade++;
      Platform.runLater(()->{
        controlador.lblIdadeFilho1.setText(idade + "");
      });

      if(idade == 16){ // nascimento do neto 1
        Platform.runLater(()->{
          controlador.iniciaThreadNetoUm();
        });
      }
      
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        
        e.printStackTrace();
      }
      if(idade == 61){ // morte do filho 1
        Platform.runLater(()->{
          controlador.alteraImagem("Filho1");
        });
        this.interrupt();
        break;
      }
    }
    
  } // fim do metodo run
  
}
