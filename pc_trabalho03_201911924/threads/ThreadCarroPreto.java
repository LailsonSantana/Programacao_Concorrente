/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 22/08/2021
* Ultima alteracao: 01/09/2021
* Nome: ThreadTremAzul
* Funcao: Controla o Trem Azul
*************************************************************** */

package threads;

import controle.ControlePrincipal;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ThreadCarroPreto extends Thread{

  private ControlePrincipal controlador;
  private ImageView carroPreto;
  public double velocidadeCurva = 0.5;

  public void setControlador(ControlePrincipal controlador){
    this.controlador = controlador;
    this.carroPreto = controlador.getCarroPreto();
  }
  
  public void run(){
    try{
    while(true){
    
      switch(this.verificaCurva()){
        case 0:
          while(this.verificaCurva() == 0){
            carroPreto.setRotate(0);
            this.movimentaCarro();
            this.sleep();
            voltaInicio();
          }
          break;
        case 1:
          if(controlador.getAcessoTunel2()){
            controlador.setAcessoTunel2(false);
            while(this.verificaCurva() == 1){
              this.fazCurva();
              this.sleep();
            }
            regiaoCritica1();
            controlador.setAcessoTunel2(true);
          }
          else{
            Platform.runLater(()->{controlador.sinalizadorFalse(4);});
            while(controlador.getAcessoTunel2() == false){
              this.sleep();
            }
            Platform.runLater(()->{controlador.sinalizadorTrue(4);});
          }
          break;
        case 2:
          while(this.verificaCurva() == 2){
            this.fazCurva();
            this.sleep();
          }
          break;
        case 3:
          if(controlador.getAcessoTunel1() == true){
            controlador.setAcessoTunel1(false);
            while(this.verificaCurva() == 3){
              this.fazCurva();
              this.sleep();
            }
            regiaoCritica2();
            controlador.setAcessoTunel1(true);
          }
        else{
          Platform.runLater(()->{controlador.sinalizadorFalse(2);});
          while(controlador.getAcessoTunel1() == false){
            this.sleep();
          }
          Platform.runLater(()->{controlador.sinalizadorTrue(2);});
        }
        break;
        case 4:
          while(this.verificaCurva() == 4){
            this.fazCurva();    
        }
        break;
      }  //fim do switch
    } //fim do while 
  }catch (Exception e) {
    System.out.println("O ERRO ESTA NO CARRO PRETO");
  }
  } // fim do metodo run 
  

  /* ***************************************************************
  * Metodo: regiaoCritica2
  * Funcao: faz a passagem do carro pelo tunel 2 e a curva pos passagem
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void regiaoCritica1(){
    while(this.verificaCurva() == 0){
      carroPreto.setRotate(0);
      this.movimentaCarro();
      this.sleep();
    }
    while(this.verificaCurva() == 2){
      this.fazCurva();
      this.sleep();
    }
  }

  /* ***************************************************************
  * Metodo: regiaoCritica2
  * Funcao: faz a passagem do carro pelo tunel 1 e a curva pos passagem
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void regiaoCritica2(){
    while(this.verificaCurva() == 0){
      carroPreto.setRotate(0);
      this.movimentaCarro();
      this.sleep();
    }
    while(this.verificaCurva() == 4){
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
    try {
      sleep(40);
    }catch (InterruptedException e){
      System.out.println("VIM AQUI");
      e.getMessage();}
  }

  /* ***************************************************************
  * Metodo: verificaCurva
  * Funcao: verifica eh hora de fazer a curva na atual posicao do layout
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public int  verificaCurva(){

    int resultado = 0; // se nao entrar em nenhum if isso significa que vai seguir horizontalmente
    double posCarro = carroPreto.getLayoutX();
  
    if(posCarro < 570 && posCarro > 550){ // curva 1
      resultado = 1;
    }else if(posCarro < 460 && posCarro > 440){ // curva 2
      resultado = 2;
    }else if(posCarro < 295 && posCarro > 275){ // curva 3
        resultado = 3;
    }else if(posCarro < 180 && posCarro > 160){ // curva 4
      resultado = 4;
    }
    // alem de verificar a curva , esse metodo tambem vai auxiliar para que o carro de uma pausa se caso
    // o tunel estiver ocupado por outro carro , se isso acontecer o carro ira parar justamente no ponto
    // na qual ele iria fazer a curva
    return resultado;
  }

  /* ***************************************************************
  * Metodo: movimentaCarro
  * Funcao: faz o movimento horizontal do carro
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void movimentaCarro(){
    // para fazer o movimento do carro , eu pego a posicao atual da imagem e vou diminuindo valores
    // fazendo com que essa imagem va mudando de posicao com auxilio da thread que vai sendo executada 
    Image image = new Image("/recursos/imagens/carro_preto.png");
    carroPreto.setImage(image);
    carroPreto.setLayoutX(carroPreto.getLayoutX() - controlador.aumentaVelocidadePreto());
    carroPreto.setLayoutY(carroPreto.getLayoutY());
    // o metodo aumentaVelocidadePreto() , retorna valores que variam , assim eh possivel dar
    // o efeito de velocidade

  }

  /* ***************************************************************
  * Metodo: fazCurva
  * Funcao: realiza as curvas que estao ao longo do percurso
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void fazCurva(){
    double posCarro = carroPreto.getLayoutX();
    carroPreto.setImage((new Image("/recursos/imagens/carro_preto.png")));

    // para fazer a curva eu mapeei todos os pontos na qual tinha curva , como
    //sao 2 tipos de curva diferentes (para cima e para baixo) foi necessario usar um else
    if(posCarro < 570 && posCarro > 550 || posCarro < 295 && posCarro > 275){

      carroPreto.setRotate(22); // inclina o carro para baixo
      carroPreto.setLayoutX(carroPreto.getLayoutX()-velocidadeCurva); // possibilita uma especie de movimento
      carroPreto.setLayoutY(carroPreto.getLayoutY()-velocidadeCurva); // vertical

    }
    else{
      
      carroPreto.setRotate(-22); // inclina o carro para cima
      carroPreto.setLayoutX(carroPreto.getLayoutX()-velocidadeCurva);
      carroPreto.setLayoutY(carroPreto.getLayoutY()+velocidadeCurva);

    }
  } // fim do metodo fazCurva

  /* ***************************************************************
  * Metodo: voltaInicio
  * Funcao: traz o carro para o ponto inicial quando o percurso eh terminado
  * Parametros: nao tem parametros
  * Retorno: nao retorna nada
  *************************************************************** */
  public void voltaInicio(){

    Image image = new Image("/recursos/imagens/carro_preto.png");
    if(carroPreto.getLayoutX() < -45){
      carroPreto.setImage(image);
      carroPreto.setLayoutX(775); // setando a posicao na qual o carro estava no inicio
      carroPreto.setLayoutY(300);
    }
  }
}
