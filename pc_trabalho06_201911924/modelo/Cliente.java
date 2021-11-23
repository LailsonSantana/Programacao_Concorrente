/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 15/10/2021
* Ultima alteracao: 15/10/2021
* Nome: Cliente.java
* Funcao: objeto que sera usado no banco de dados
*************************************************************** */

package modelo;

public class Cliente {

  private String nome;
  private String sobrenome;
  private int idade;
  private String sexo;

  public Cliente(String nome,String sobrenome,int idade,String sexo){
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.idade = idade;
    this.sexo = sexo;
  }

  public String getNome(){
    return nome;
  }

  public void setNome(String nome){
    this.nome = nome;
  }

  public String getSobrenome(){
    return sobrenome;
  }

  public void setSobrenome(String sobrenome){
    this.sobrenome = sobrenome;
  }

  public int getIdade(){
    return idade;
  }

  public void setIdade(int idade){
    this.idade = idade;
  }

  public String getSexo(){
    return sexo;
  }

  public void setSexo(String sexo){
    this.sexo = sexo;
  }
  
}
