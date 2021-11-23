/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 15/10/2021
* Ultima alteracao: 15/10/2021
* Nome: Gerador.java
* Funcao: gera componentes que serao usados no programa
*************************************************************** */

package modelo;

import java.util.Random;

public class Gerador {

  private String[] vogaisMa = {"A","E","I","O","U"};
  private String[] vogaisMi = {"a","e","i","o","u"};
  private String[] consMi = {"b","c","d","f","g","h","j","l","m","n","p","r","s","t","v"};
  private String[] consMa = {"B","C","D","F","G","H","J","L","M","N","P","R","S","T","V"};



  public Gerador(){
    
  }

  /* ***************************************************************
  * Metodo: geraTempo
  * Funcao: gera o tempo de espera de cada escritor e leitor
  * Parametros: nao recebe nada
  * Retorno: um inteiro com a quantidade de segundos
  *************************************************************** */
  public int geraTempo(){

    int tempo = 0;
    Random gerador = new Random();
    tempo = gerador.nextInt(2500) + 2500;
    return tempo;
    
  }

  /* ***************************************************************
  * Metodo: geraNome
  * Funcao: gera um nome totalmente aleatorio
  * Parametros: nao recebe nada
  * Retorno: uma string com o nome gerado
  *************************************************************** */
  public String geraNome(){
    Random gerador = new Random();
    String nome = consMa[gerador.nextInt(15)];
    int tam = gerador.nextInt(7) + 4;
    for(int i=0;i<tam;i++){
      if(i % 2 == 1){
        nome = nome + consMi[gerador.nextInt(15)];
      }
      else{
        nome = nome + vogaisMi[gerador.nextInt(5)];
      }
    }

    return nome;
  }

  /* ***************************************************************
  * Metodo: geraSobrenome
  * Funcao: gera um sobrenome totalmente aleatorio
  * Parametros: nao recebe nada
  * Retorno: uma string com o sobrenome gerado
  *************************************************************** */
  public String geraSobrenome(){
    Random gerador = new Random();
    String sobrenome = consMa[gerador.nextInt(15)];
    int tam = gerador.nextInt(7) + 4;
    for(int i=0;i<tam;i++){
      if(i % 2 == 1){
        sobrenome = sobrenome + consMi[gerador.nextInt(15)];
      }
      else{
        sobrenome = sobrenome + vogaisMi[gerador.nextInt(5)];
      }
    }

    return sobrenome;
  }

  /* ***************************************************************
  * Metodo: geraIdade
  * Funcao: gera uma idade entre 18 e 80 anos
  * Parametros: nao recebe nada
  * Retorno: um inteiro que representa a idade
  *************************************************************** */
  public int geraIdade(){
    int idade = 0;
    Random gerador = new Random();
    idade = gerador.nextInt(63) + 18;
    return idade;
  }

  /* ***************************************************************
  * Metodo: geraSexo
  * Funcao: gera um sexo entre feminino e masculino
  * Parametros: nao recebe nada
  * Retorno: uma string com o sexo gerado
  *************************************************************** */
  public String geraSexo(){
    Random gerador = new Random();
    String sexo;
    int i = gerador.nextInt(2);
    if(i == 0){
      sexo = "Masculino";
    }
    else{
      sexo = "Feminino";
    }

    return sexo;
  }
  
}
