/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 15/10/2021
* Ultima alteracao: 15/10/2021
* Nome: ControleCliente.java
* Funcao: faz o controle dos clientes
*************************************************************** */

package controle;

import java.util.ArrayList;

import modelo.Cliente;

public class ControleCliente {

  private ArrayList<Cliente> listaClientes;

  public ControleCliente(){
    listaClientes = new ArrayList<>();
  }

  public void inserirCliente(Cliente c){
    listaClientes.add(c);
  }

  public Cliente pesquisaCliente(String nome){
    Cliente c = null;
    for(int i=0;i<listaClientes.size();i++){
      if(listaClientes.get(i).getNome().equals(nome)){
        c = listaClientes.get(i);
        break;
      }
    }
    return c;
  }

  public ArrayList<Cliente> getClientes(){
    return listaClientes;
  }
  
}
