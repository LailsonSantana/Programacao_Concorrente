/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/10/2021
* Ultima alteracao: 16/10/2021
* Nome: ThreadEscritor
* Funcao: faz a manipulação da ThreadEscritor
*************************************************************** */
package threads;


import controle.ControleCliente;
import controle.ControlePrincipal;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import modelo.Cliente;
import modelo.Gerador;

public class ThreadEscritor extends Thread{

  private int id;
  private ControlePrincipal controlador;
  private ControleCliente controleCliente;
  private Label nome,sobrenome,idade,sexo,usuario;
  private Gerador gerador = new Gerador();
  private Cliente cliente;
  private Slider velocidade;
  private Button btn;

  public ThreadEscritor(int id,ControlePrincipal contr,ControleCliente conC,Label n,Label so,
    Label i,Label se,Button b,Label u,Slider vel){
    this.id = id;
    this.controlador = contr;
    this.controleCliente = conC;
    this.nome = n;
    this.sobrenome = so;
    this.idade = i;
    this.sexo = se;
    this.btn = b;
    this.usuario = u;
    this.velocidade = vel;
  }

  public void run(){

    while(true){
      try{
        obTemDado();
        controlador.getDb().acquire(); // verifica se pode entrar na regiao critica

        System.out.println("SOU O ESCRITOR "+ (id+1) + " E ENTREI NO BANCO DE DADOS");
        escreveBaseDados(); 
        limpaCampos();

        System.out.println("SOU O ESCRITOR "+ (id+1) + " E SAI DO BANCO DE DADOS");
        controlador.getDb().release(); // sai da regiao critica
        sleep(gerador.geraTempo()/velocidade());
        
      }catch(Exception e){
        e.getMessage();
      }
    }
  }

  /* ***************************************************************
  * Metodo: obtemDado
  * Funcao: extrai os dados que serao usados
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void obTemDado(){
    String nome = gerador.geraNome();
    String sobrenome = gerador.geraSobrenome();
    int idade = gerador.geraIdade();
    String sexo = gerador.geraSexo();
    cliente = new Cliente(nome,sobrenome,idade,sexo);
  }

  /* ***************************************************************
  * Metodo: escreveBaseDados
  * Funcao: salva os dados gerados na base de dados
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void escreveBaseDados(){

    try{
      // simula o cadastro de um cliente
      Platform.runLater(()->{usuario.setText("Escritor " + (id+1));});
      Platform.runLater(()->{nome.setText(cliente.getNome());});
      sleep(1000);
      Platform.runLater(()->{sobrenome.setText(cliente.getSobrenome());});
      sleep(1000);
      Platform.runLater(()->{idade.setText(cliente.getIdade() + "");});
      sleep(1000);
      Platform.runLater(()->{sexo.setText(cliente.getSexo());});
      sleep(1000);
      controleCliente.inserirCliente(cliente); // insere no array
      simulaClick();

    }catch(Exception e){
      
      e.getMessage();
    }
  }

  /* ***************************************************************
  * Metodo: limpaCampos
  * Funcao: deixa os campos de pesquisa vazios
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void limpaCampos(){
    // quando o cadastro e finalizado todos os campos sao limpos para o proximo cadastro
    Platform.runLater(()->{usuario.setText("");});
    Platform.runLater(()->{nome.setText("");});
    Platform.runLater(()->{sobrenome.setText("");});
    Platform.runLater(()->{idade.setText("");});
    Platform.runLater(()->{sexo.setText("");});
  }

  public int velocidade(){
    int vel = (int) velocidade.getValue();

    return vel;
  }

  public void simulaClick(){
    try{
      btn.setStyle("-fx-background-color: #737271");
      sleep(200);
      btn.setStyle("-fx-background-color: #d9ded2");
    }catch(Exception e){
      e.getMessage();
    }
  }

}
