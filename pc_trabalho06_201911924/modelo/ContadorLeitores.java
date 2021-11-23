/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 15/10/2021
* Ultima alteracao: 15/10/2021
* Nome: ContadorLeitores.java
* Funcao: faz a contagem de leitores 
*************************************************************** */

package modelo;

public class ContadorLeitores {

  private int contador;

  public ContadorLeitores(){
    this.contador = 0;
  }

  public void adiciona(){
    contador++;
  }

  public void subtrai(){
    contador--;
  }

  public int getContador(){
    return contador;
  }
  
}
