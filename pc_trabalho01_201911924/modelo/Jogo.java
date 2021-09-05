/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 29/07/2021
* Ultima alteracao: 04/08/2021
* Nome: Your Bet
* Funcao: Jogo de apostas
*************************************************************** */

package modelo;

import java.util.Random;

public class Jogo {

  private double valorAposta;
  private int chancePorcentagem = 50;
  private double pontos = 20;
  private int numeroSorteado;


  public Jogo(){

  }

  // inicio dos metodos getters e setters
  
  public void setNumeroSorteado(int numero){
    this.numeroSorteado = numero;
  }

  public int getNumeroSorteado(){
    return numeroSorteado;
  }

  public void setValorAposta(double valorAposta){
    this.valorAposta = valorAposta;
  }

  public double getValorAposta(){
    return this.valorAposta;
  }

  public void setChancePorcentagem(int chancePorcentagem){

    this.chancePorcentagem = chancePorcentagem;
  }

  public int getChancePocentagem(){

    return this.chancePorcentagem;
  }

  public void setPontos(double pontos){

    this.pontos = pontos;
  }

  public double getPontos(){
    return this.pontos;
  }
  // fim dos metodos getters e setters

  /* ***************************************************************
  * Metodo: sortearNumero
  * Funcao: sorteia o valor aleatorio
  * Parametros: nao recebe nada
  * Retorno: retorna um inteiro com o valor sorteado
  *************************************************************** */
  public int sorteaNumero(){
    Random random = new Random();
    int numero = random.nextInt(1000) + 1; // somei 1 para que o 0 nao entrasse na lista
    return numero;
  }

  /* ***************************************************************
  * Metodo: verificaGanho
  * Funcao: verifica se o jogador ganhou ou perdou na aposta
  * Parametros: nao recebe nada
  * Retorno: retorna um booleano com o resultado
  *************************************************************** */
  public boolean verificaGanho(){

    boolean resultado = false; // resultado padrao 
    switch(this.getChancePocentagem()){
      case 10:
        if(this.getNumeroSorteado() >= 1 && this.getNumeroSorteado() <= 100){
          resultado = true; // os intervalos de cada um estao ajustados de acordo
          // a porcentagem escolhida
        }
        break;
      case 25:
        if(this.getNumeroSorteado() >= 1 && this.getNumeroSorteado() <= 250){
          resultado = true;
        }
        break;
      case 50:
        if(this.getNumeroSorteado() >= 1 && this.getNumeroSorteado()<= 500){
          resultado = true;
        }
        break;
      case 75:
        if(this.getNumeroSorteado() >= 1 && this.getNumeroSorteado() <= 750){
          resultado = true;
        }
        break;
      case 90:
        if(this.getNumeroSorteado() >= 1 && this.getNumeroSorteado()<= 900){
          resultado = true;
        }
        break;
    } // fim do switch
    return resultado;
  } // fim do metodo verificaGanho

  /* ***************************************************************
  * Metodo: pontosObtidos
  * Funcao: calcula a pontuacao obtida pelo jogador em cada jogada
  * Parametros: recebe o resultdo da rodada que diz se o jogador ganhou ou perdeu
  * Retorno: retorna uma variavel do tipo double
  *************************************************************** */
  public double pontosObtidos(boolean resultado){
    if(resultado == false){
      return this.getValorAposta() * -1; // Ao inves de ganhar o jogador perdera essa quantidade de pontos
      // por esse motivo a inversao de valor
    }
    else{ // os pontos obtidos se da pelo produto da proporcao pelo valor de aposta
      double pontos = this.verificaProporcao() * getValorAposta();
      return pontos;
    }
  } // fim do metodo pontosObtidos

  /* ***************************************************************
  * Metodo: verificaProporcao
  * Funcao: verifica qual sera a pontuacao com relacao a porcentagem escolhida
  * Parametros: nao recebe nada
  * Retorno: retorna uma variavel do tipo double
  *************************************************************** */
  public double verificaProporcao(){
    double x = 0;
    if(this.getChancePocentagem() == 90){
      x = 0.55;
    }
    else if(this.getChancePocentagem() == 75){
      x = 0.67;
    }
    else if(this.getChancePocentagem() == 50){
      x = 1;
    }
    else if(this.getChancePocentagem() == 25){
      x = 2;
    }
    else{
      x = 5;
    }
    return x;
  } // fim do metodo verificaProporcao

  
}
