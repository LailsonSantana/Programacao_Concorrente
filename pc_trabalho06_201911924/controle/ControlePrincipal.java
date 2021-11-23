/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 06/10/2021
* Ultima alteracao: 16/10/2021
* Nome: ControlePrincipal.java
* Funcao: faz o controle geral do programa
*************************************************************** */
package controle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import modelo.Cliente;
import modelo.ContadorLeitores;
import threads.ThreadEscritor;
import threads.ThreadLeitor;

public class ControlePrincipal implements Initializable{

  @FXML
  private Label lblNome,lblSobrenome,lblIdade,lblSexo,lblUsuario;

  @FXML
  private Label lblNome1,lblSobrenome1,lblIdade1,lblSexo1;

  @FXML
  private Label lblNome2,lblSobrenome2,lblIdade2,lblSexo2;

  @FXML
  private Label lblNome3,lblSobrenome3,lblIdade3,lblSexo3;

  @FXML
  private Label lblNome4,lblSobrenome4,lblIdade4,lblSexo4;

  @FXML
  private Label lblNome5,lblSobrenome5,lblIdade5,lblSexo5;

  @FXML 
  private Slider slider1,slider2,slider3,slider4,slider5;

  @FXML 
  private Slider sliderE1,sliderE2,sliderE3,sliderE4,sliderE5;

  @FXML
  private Pane pane1,pane2,pane3,pane4,pane5;

  @FXML
  private Button btnCadastro;

  @FXML
  private Button btnPesquisar1,btnPesquisar2,btnPesquisar3,btnPesquisar4,btnPesquisar5;

  private int quantLeiEsc = 5;
  private ThreadEscritor[] threadEscritores = new ThreadEscritor[quantLeiEsc];
  private ThreadLeitor[] threadLeitores = new ThreadLeitor[quantLeiEsc];
  private Label[] labelsNome = new Label[quantLeiEsc];
  private Label[] labelsIdade = new Label[quantLeiEsc];
  private Label[] labelsSexo = new Label[quantLeiEsc];
  private Label[] labelsSobrenome = new Label[quantLeiEsc];
  private Slider[] slidersLeitor = new Slider[quantLeiEsc];
  private Slider[] slidersEscritor = new Slider[quantLeiEsc];
  private Button[] buttonsPesq = new Button[quantLeiEsc];
  private Pane[] panes = new Pane[quantLeiEsc];
  private Semaphore mutex,db;
  private ContadorLeitores contador;
  private ControleCliente controleCliente;


  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    atribui();
    this.controleCliente = new ControleCliente();
    controleCliente.inserirCliente(new Cliente("Lailson","Santana",21,"Masculino"));
    this.mutex = new Semaphore(1);
    this.db = new Semaphore(1);
    this.contador = new ContadorLeitores();
    this.instanciaThreads();
    this.iniciaThreads();
  }

  /* ***************************************************************
  * Metodo: instanciaThreads
  * Funcao: cria uma instancia das threads que serao usadas
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void instanciaThreads(){

    for(int i=0;i<quantLeiEsc;i++){
      threadEscritores[i] = new ThreadEscritor(i,this,controleCliente,lblNome,lblSobrenome,
      lblIdade,lblSexo,btnCadastro,lblUsuario,slidersEscritor[i]);
      threadLeitores[i] = new ThreadLeitor(i,this,contador,controleCliente,
      labelsNome[i],labelsSobrenome[i],labelsIdade[i],labelsSexo[i],panes[i],slidersLeitor[i],buttonsPesq[i]);
    }

  }

  /* ***************************************************************
  * Metodo: iniciaThreads
  * Funcao: inicia as threads usadas no programa
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaThreads(){

    for(int i=0;i<quantLeiEsc;i++){
      threadEscritores[i].start();
      threadLeitores[i].start();
    }

  }

  /* ***************************************************************
  * Metodo: atribui
  * Funcao: coloca componentes do layout em vetores
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void atribui(){

    labelsIdade[0] = lblIdade1;
    labelsIdade[1] = lblIdade2;
    labelsIdade[2] = lblIdade3;
    labelsIdade[3] = lblIdade4;
    labelsIdade[4] = lblIdade5;

    labelsSobrenome[0] = lblSobrenome1;
    labelsSobrenome[1] = lblSobrenome2;
    labelsSobrenome[2] = lblSobrenome3;
    labelsSobrenome[3] = lblSobrenome4;
    labelsSobrenome[4] = lblSobrenome5;

    labelsSexo[0] = lblSexo1;
    labelsSexo[1] = lblSexo2;
    labelsSexo[2] = lblSexo3;
    labelsSexo[3] = lblSexo4;
    labelsSexo[4] = lblSexo5;

    labelsNome[0] = lblNome1;
    labelsNome[1] = lblNome2;
    labelsNome[2] = lblNome3;
    labelsNome[3] = lblNome4;
    labelsNome[4] = lblNome5;

    panes[0] = pane1;
    panes[1] = pane2;
    panes[2] = pane3;
    panes[3] = pane4;
    panes[4] = pane5;

    slidersLeitor[0] = slider1;
    slidersLeitor[1] = slider2;
    slidersLeitor[2] = slider3;
    slidersLeitor[3] = slider4;
    slidersLeitor[4] = slider5;

    slidersEscritor[0] = sliderE1;
    slidersEscritor[1] = sliderE2;
    slidersEscritor[2] = sliderE3;
    slidersEscritor[3] = sliderE4;
    slidersEscritor[4] = sliderE5;

    buttonsPesq[0] = btnPesquisar1;
    buttonsPesq[1] = btnPesquisar2;
    buttonsPesq[2] = btnPesquisar3;
    buttonsPesq[3] = btnPesquisar4;
    buttonsPesq[4] = btnPesquisar5;
  }

  public Semaphore getMutex(){
    return this.mutex;
  }

  public Semaphore getDb(){
    return this.db;
  }
  
}
