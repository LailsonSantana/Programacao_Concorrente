/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 22/08/2021
* Ultima alteracao: 01/09/2021
* Nome: ThreadTremVerde
* Funcao: Controla o Trem Verde
*************************************************************** */

package threads;

import controle.ControlePrincipal;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ThreadCarroRoxo extends Thread{

  private ControlePrincipal controlador;
  private ImageView carroRoxo;
  public double velocidadeCurva = 0.5;

  public void setControlador(ControlePrincipal controlador){
    this.controlador = controlador;
    this.carroRoxo = controlador.getCarroRoxo();
  }

  public void run(){
    
    try{
    while(true){
      switch(this.verificaCurva()){ // eu fiz toda estrutura do programa baseado nas curvas do carro
        case 0:
          while(this.verificaCurva() == 0){ // 0 significa que nao tem curva, entao eh um movimento horizontal normal
            carroRoxo.setRotate(0); // como a curva deixa o carro inclinado, entao toda vez que voltar para esse case
            // eu deixo ele reto novamente
            this.movimentaCarro();
            this.sleep();
            this.voltaInicio(); // aqui eu verifico se ja chegou no final , caso sim ele volta
          }
          break;
        case 1:
          if(controlador.getAcessoTunel1() == true){ // verifica se o tunel esta vazio
            controlador.setAcessoTunel1(false); // marca o tunel como ocupado
            while(this.verificaCurva() == 1){
              this.fazCurva(); // faz a curva
              this.sleep();
            }
            regiaoCritica1(); // inicia o processo de entrada na regiao critica 1 , alem de manipular tambem 
            // o processo de saida dessa regiao
            controlador.setAcessoTunel1(true); // assim que finalizar , marca o tunel como vazio
          }
          else{ // caso o tunel esteja ocupado entao vira para esse else
            Platform.runLater(()->{controlador.sinalizadorFalse(1);}); // sinalizador levanta a mao
            while(controlador.getAcessoTunel1() == false){ // espera ate que o tunel se torne desocupado 
              this.sleep();
            }
            Platform.runLater(()->{controlador.sinalizadorTrue(1);}); // sinalizador abaixa a mao
          }
          break;
        case 2:
          while(this.verificaCurva() == 2){ // regiao normal , sem tuneos
            this.fazCurva();
            this.sleep();
          }
          break;
        case 3:
          if(controlador.getAcessoTunel2() == true){ // regiao critica 2
            controlador.setAcessoTunel2(false); // marca tunel como ocupado
            while(this.verificaCurva() == 3){ // faz curva
              this.fazCurva();
              this.sleep();
            }
            regiaoCritica2(); // passa pelo tunel
            controlador.setAcessoTunel2(true); // marca o tunel como desocupado
          }
        else{
          Platform.runLater(()->{controlador.sinalizadorFalse(3);});
          while(controlador.getAcessoTunel2() == false){ // aguarda o outro carro sair do tunel
            this.sleep();
          }
          Platform.runLater(()->{controlador.sinalizadorTrue(3);});
        }
        break;
        case 4:
          while(this.verificaCurva() == 4){ // regiao normal sem tuneos
            this.fazCurva();    
        }
        break;
      
      }  //fim do switch
    } //fim do while
    }catch(Exception e){
      System.out.println("O ERRO ESTA NO CARRO ROXO");
    }
  } //fim do metodo run

  /* ***************************************************************
  * Metodo: regiaoCritica1
  * Funcao: faz a passagem do carro pelo tunel 1 e a curva pos passagem
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void regiaoCritica1(){
    while(this.verificaCurva() == 0){
      carroRoxo.setRotate(0); // deixa o carro na sua forma horizontal
      this.movimentaCarro();
      this.sleep();
    }
    while(this.verificaCurva() == 2){ // quando sai do tunel eh feito a curva
      this.fazCurva();
      this.sleep();
    }
  }

  /* ***************************************************************
  * Metodo: regiaoCritica2
  * Funcao: faz a passagem do carro pelo tunel 2 e a curva pos passagem
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void regiaoCritica2(){
    while(this.verificaCurva() == 0){
      carroRoxo.setRotate(0); // deixa o carro na sua forma horizontal
      this.movimentaCarro(); 
      this.sleep();
    }
    while(this.verificaCurva() == 4){ // quando sai do tunel eh feito a curva
      this.fazCurva();
      this.sleep();
    }
  }

  /* ***************************************************************
  * Metodo: sleep
  * Funcao: define qual sera o sleep usado
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void sleep(){
    // para nao precisar colocar em todos os lugares que usei esse parametro abaixo
    // entao criei um metodo especifico
    try {
      sleep(40);
    }catch (InterruptedException e){
      System.out.println("VIM AQUI");
      e.getMessage();
    }
  }

  /* ***************************************************************
  * Metodo: verificaCurva
  * Funcao: verifica se eh hora de fazer a curva na atual posicao do layout
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public int verificaCurva(){

    int resultado = 0;
    double posCarro = carroRoxo.getLayoutX();
    if(posCarro > 140 && posCarro < 160){ // curva 1
      resultado = 1;
    }else if(posCarro > 255 && posCarro < 275){ // curva 2
      resultado = 2;
    }else if(posCarro > 420 && posCarro < 440){ // curva 3
      resultado = 3;
    }else if(posCarro > 535 && posCarro < 555){ // curva 4
      resultado = 4;
    }
    return resultado;
  
  }

  /* ***************************************************************
  * Metodo: movimentaCarro
  * Funcao: faz o movimento horizontal do carro
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void movimentaCarro(){

    Image image = new Image("/recursos/imagens/carro_roxo.png");
    carroRoxo.setImage(image);
    carroRoxo.setLayoutX(carroRoxo.getLayoutX() + controlador.aumentaVelocidadeRoxo());
    carroRoxo.setLayoutY(carroRoxo.getLayoutY());
    
  }

  /* ***************************************************************
  * Metodo: fazCurva
  * Funcao: realiza as curvas que estao ao longo do percurso
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void fazCurva(){
    double posCarro = carroRoxo.getLayoutX();
    carroRoxo.setImage((new Image("/recursos/imagens/carro_roxo.png")));

    if(posCarro > 140 && posCarro < 160 || posCarro > 420 && posCarro < 440){

      carroRoxo.setRotate(22);
      carroRoxo.setLayoutX(carroRoxo.getLayoutX()+velocidadeCurva);
      carroRoxo.setLayoutY(carroRoxo.getLayoutY()+velocidadeCurva);

    }
    else{
      
      carroRoxo.setRotate(-22);
      carroRoxo.setLayoutX(carroRoxo.getLayoutX()+velocidadeCurva);
      carroRoxo.setLayoutY(carroRoxo.getLayoutY()-velocidadeCurva);

    }
  }

  /* ***************************************************************
  * Metodo: voltaInicio
  * Funcao: traz o carro para o ponto inicial quando o percurso eh terminado
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void voltaInicio(){
    Image image = new Image("/recursos/imagens/carro_roxo.png");
    if(carroRoxo.getLayoutX() > 775){
      carroRoxo.setImage(image);
      carroRoxo.setLayoutX(-52);
      carroRoxo.setLayoutY(259);
    }
  }
}
