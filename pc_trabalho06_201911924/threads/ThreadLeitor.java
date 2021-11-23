/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/10/2021
* Ultima alteracao: 16/10/2021
* Nome: ThreadLeitor
* Funcao: faz a manipulação da thread leitor
*************************************************************** */
package threads;

import controle.ControleCliente;
import controle.ControlePrincipal;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import modelo.Cliente;
import modelo.ContadorLeitores;
import modelo.Gerador;

import java.util.Random;

public class ThreadLeitor extends Thread{

  private int id;
  private ControlePrincipal controlador;
  private ContadorLeitores contador;
  private ControleCliente controleCliente;
  private Label lNome,lSobrenome,lIdade,lSexo;
  private Cliente cliente;
  private Pane pane;
  private Slider velocidade;
  private Button btnPesquisar;
  private Gerador gerador = new Gerador();

  public ThreadLeitor(int id,ControlePrincipal contr,ContadorLeitores cont,ControleCliente cc,Label n,
    Label so,Label i,Label se,Pane pane,Slider vel,Button btnPesq){
    this.id = id;
    this.controlador = contr;
    this.contador = cont;
    this.controleCliente = cc;
    this.lNome = n;
    this.lSobrenome = so;
    this.lIdade = i;
    this.lSexo = se;
    this.pane = pane;
    this.velocidade = vel;
    this.btnPesquisar = btnPesq;
  }

  public void run(){

    while(true){
      try{
        
        pane.setVisible(false); // deixa invisivel a informacao que sera exibida depois da pesquisa
        controlador.getMutex().acquire(); // verifica se pode acessar a variavel de controle
        //Platform.runLater(()->{contador.adiciona();});
        this.contador.adiciona(); // aumenta o contador de leitores 

        if(contador.getContador() == 1){
          controlador.getDb().acquire(); // tenta acessar o banco de dados
        }

        controlador.getMutex().release(); // sai do semaforo que controla a variavel contadora

        System.out.println("SOU O LEITOR "+ (id+1) + " E ENTREI NO BANCO DE DADOS");
        leBaseDados(); // faz o processo de leitura do banco de dados

        controlador.getMutex().acquire();  // verifica se pode acessar a variavel de controle
        //Platform.runLater(()->{contador.subtrai();});
        this.contador.subtrai(); // diminui o contador de leitores

        if(contador.getContador() == 0){
          controlador.getDb().release(); // libera o acesso a regiao critica (bd)
        }
        System.out.println("SOU O LEITOR "+ (id+1) + " E SAI DO BANCO DE DADOS");
        controlador.getMutex().release(); // sai do semaforo que controla a variavel contadora

        utilizaDadoLido(); // faz a utilizacao do dado lido
        sleep(gerador.geraTempo()); // tempo de espera 
        
      }catch(Exception e){
        e.getMessage();
      }
    }
  }

  /* ***************************************************************
  * Metodo: leBaseDados
  * Funcao: faz a leitura do banco de dados
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void leBaseDados(){
    try{
      int tam = controleCliente.getClientes().size();
      Random r = new Random();
      int pos = r.nextInt(tam);
      cliente = controleCliente.getClientes().get(pos);
    // pega um cliente aleatorio entre todos que estao la 
    }catch(Exception e){
      e.getMessage();
    }

  }

  /* ***************************************************************
  * Metodo: utilizaDadoLido
  * Funcao: utiliza o dado extraido no banco de dados
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void utilizaDadoLido(){
    try{
      Platform.runLater(()->{lNome.setText(cliente.getNome());}); // simula a pesquisa
      simulaClick();
      sleep(1000);
      // Exibe as informacoes do cliente escolhido
      pane.setVisible(true);
      
      Platform.runLater(()->{lSobrenome.setText(cliente.getSobrenome());});
      sleep(100);
      Platform.runLater(()->{lIdade.setText(cliente.getIdade() + "");});
      sleep(100);
      Platform.runLater(()->{lSexo.setText(cliente.getSexo());});
      sleep(500);
      Platform.runLater(()->{lNome.setText("");});
    }catch(Exception e){
      e.getMessage();
    }
  }

  /* ***************************************************************
  * Metodo: simulaClick
  * Funcao: faz um click falso no botao 
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void simulaClick(){
    try{
      btnPesquisar.setStyle("-fx-background-color: #737271");
      sleep(200);
      btnPesquisar.setStyle("-fx-background-color: #d9ded2");
    }catch(Exception e){
      e.getMessage();
    }
  }

  /* ***************************************************************
  * Metodo: velocidade
  * Funcao: altera a velocidade e execução
  * Parametros: nao recebe nada
  * Retorno: retorna um inteiro
  *************************************************************** */
  public int velocidade(){
    int vel = (int) velocidade.getValue();
    System.out.println(vel);
    return vel;
  }
  
}
