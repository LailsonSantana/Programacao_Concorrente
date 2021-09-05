/* ***************************************************************
* Autor: Lailson Santana Alves
* Matricula: 201911924
* Inicio: 29/07/2021
* Ultima alteracao: 03/08/2021
* Nome: Your Bet
* Funcao: jogo de apostas
*************************************************************** */
package controle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import modelo.Cronometro;
import modelo.Jogo;
import modelo.Utils;

public class ControleJogo implements Initializable,Utils {

  private double valorSpinner = 1.0;
  private int botaoSelecionado= 3;
  public int avisaCrono = 0;

  @FXML
  private Button btnApostar,btnIniciar,btnEncerrar;

  @FXML
  private Label lblGanha,lblPerde,lblPontuacao,lblNumSorteado,lblDescNumSorteado;

  @FXML
  public Label lblJogador,lblTempo;

  @FXML
  private Spinner<Double> spinner;

  @FXML
  private RadioButton radioBtn1,radioBtn2,radioBtn3,radioBtn4,radioBtn5;

  private ControleRegistro controle = new ControleRegistro();
  private Jogo jogo = new Jogo();
  private Cronometro cronometro;
  private Cronometro cronometro1;

  public ControleJogo(){
    
    this.cronometro = new Cronometro();
    
  }
  

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    lblJogador.setText(controle.NOME_JOGADOR); // insere o nome do jogador
    carregaSpinner(1.0); // Logo que a tela eh inicializada, o componente na qual sera possivel editar o valor
    // das apostas tambem eh
    cronometro.setControlador(this); // passando o controlador dessa classe , para a classe Cronometro
    desabilitaComponentes(); // desabilita alguns componentes da tela que nao podem ser clicados antes
    // do inicio do jogo

  }

  /* ***************************************************************
  * Metodo: iniciaJogo
  * Funcao: da inicio no jogo
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void iniciaJogo(){

    this.cronometro1 = new Cronometro(); //instancia o objeto cronometro
    this.jogo = new Jogo(); // instancia o objeto jogo
    cronometro1.setControlador(this); //passa o controlador para a classe cronometro
    habilitaComponentes(); 
    btnIniciar.setVisible(false); // enconde o botao iniciar
    carregaSpinner(1.0);
    this.botaoSelecionado = 3;
    avisaCrono = 0;
    cronometro1.start(); // inicia o cronometro
    
  } // fim do metodo iniciaJogo

  /* ***************************************************************
  * Metodo: aposta
  * Funcao: faz as manipulacoes para que a aposta seja feita
  * Parametros: ActionEvent , evento de clique
  * Retorno: nao retorna nada
  *************************************************************** */
  public void aposta(ActionEvent event){

    int num = jogo.sorteaNumero(); // faz o sorteio de um valor
    lblNumSorteado.setText(num + ""); //mostra na label o valor sorteado

    jogo.setNumeroSorteado(num); // seta o numero sorteado

    jogo.setValorAposta(valorSpinner); // A aposta sera o valor que esta no spinner , assim que o usuario
    //apertar o botao apostar esse valor sera passado para a variavel

    double pontos = jogo.pontosObtidos(jogo.verificaGanho()); //pega a pontuacao do usuario na jogada
    pontos = this.arredondaPontuacao(pontos); 
    jogo.setPontos(jogo.getPontos() + pontos); // soma os pontos que ja estao la com os pontos obtidos na rodada
    lblPontuacao.setText(this.arredondaPontuacao(jogo.getPontos()) + ""); //mostra na label pontuacao
    this.mudaCorLabel(jogo.verificaGanho());

    if(jogo.getPontos() < 0){ // verifica se a pontuacao ficou negativa , caso sim o usurio perde
      
      this.mensagemInformativa("Voce Perdeu : Pontuacao negativa","Deseja iniciar novamente ?");
      avisaCrono = 1;
      
    }
  } // fim do metodo aposta

  /* ***************************************************************
  * Metodo: desabilitaComponentes
  * Funcao: desabilita alguns componenentes da tela
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void desabilitaComponentes(){
    btnApostar.setDisable(true);
    radioBtn1.setDisable(true);
    radioBtn2.setDisable(true);
    radioBtn3.setDisable(true);
    radioBtn4.setDisable(true);
    radioBtn5.setDisable(true);
    spinner.setDisable(true);
    btnEncerrar.setVisible(false);;
  } 

  /* ***************************************************************
  * Metodo: habilitaComponentes
  * Funcao: habilita alguns componentes da tela
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void habilitaComponentes(){
    spinner.setDisable(false);
    btnApostar.setDisable(false);
    radioBtn1.setDisable(false);
    radioBtn2.setDisable(false);
    radioBtn3.setDisable(false);
    radioBtn4.setDisable(false);
    radioBtn5.setDisable(false);
    spinner.setDisable(false);
    btnEncerrar.setVisible(true);
  }

  /* ***************************************************************
  * Metodo: encerraJogo
  * Funcao: finaliza o jogo sem fecha-lo
  * Parametros: nao recebe nada
  * Retorno: nao retorna nada
  *************************************************************** */
  public void encerraJogo(){
    
    desabilitaComponentes();
    lblPontuacao.setText("20");
    btnIniciar.setVisible(true);
    //cronometro.interrupt();
    //cronometro1.interrupt();
    avisaCrono = 1;

  }

  /* ***************************************************************
  * Metodo: selecionaPorcentagem
  * Funcao: vai guardando a porcentagem que vai sendo escolhida pelo usuario
  * Parametros: ActionEvent , evento de clique
  * Retorno: nao retorna nada
  *************************************************************** */
  public void selecionaPorcentagem(ActionEvent event){
    if(radioBtn1.isSelected()){
      this.botaoSelecionado = 1;
      jogo.setChancePorcentagem(90);
      carregaSpinner(spinner.getValue()); // toda vez que os botoes com as porcentagens forem clicados os
      // valores vao ser atualizados 
      // estou passando getValue como parametro para que que o valor do spinner nao volte para 1
      // ao inves disso seja carregado exatamente no valor que estava
    }
    else if(radioBtn2.isSelected()){
      this.botaoSelecionado = 2;
      jogo.setChancePorcentagem(75);
      carregaSpinner(spinner.getValue());
    }
    else if(radioBtn3.isSelected()){
      this.botaoSelecionado = 3;
      jogo.setChancePorcentagem(50);
      carregaSpinner(spinner.getValue());
    }
    else if(radioBtn4.isSelected()){
      this.botaoSelecionado = 4;
      jogo.setChancePorcentagem(25);
      carregaSpinner(spinner.getValue());
    }
    else if(radioBtn5.isSelected()){
      this.botaoSelecionado = 5;
      jogo.setChancePorcentagem(10);
      carregaSpinner(spinner.getValue());
    }

  } // fim do metodo seleciona porcentagem

  
  /* ***************************************************************
  * Metodo: carregaSpinner
  * Funcao: carrega e faz manipulacoes no spinner
  * Parametros: uma variavel tipo double que eh o valor inicial do spinner
  * Retorno: nao retorna nada
  *************************************************************** */
  public void carregaSpinner(double valorInicial){
    SpinnerValueFactory<Double> value = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0, 50.0);
    //1.0 e 50.0 eh o intervalo maximo do spinner
    double vetor[] = {0.55,0.67,1.00,2.00,5.00}; // vetor com  os pontos que o usuario ganhar

    double pontos = this.arredondaPontuacao(vetor[botaoSelecionado-1] * valorSpinner);
    // esses pontos sao multiplicados pelo valor de aposta
    value.setValue(valorInicial);
    spinner.setValueFactory(value);
    valorSpinner = spinner.getValue();

    lblGanha.setText(pontos+""); // imprime esses valores na tela
    lblPerde.setText(valorSpinner + "");
    spinner.valueProperty().addListener(new ChangeListener<Double>(){ // evento de clique

      @Override
      public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
        BigDecimal dec = new BigDecimal(vetor[botaoSelecionado-1] * valorSpinner).
        setScale(3, RoundingMode.HALF_EVEN); // apesar de ter criado a funcao para conversao , tive que colocar o codigo 
        // de conversao outra vez aqui , pois a funcao nao podia ser usada nesse metodo
        valorSpinner = spinner.getValue();
        lblGanha.setText(dec.doubleValue()+""); // coloquei pra imprimir novamente porque esse metodo eh uma
        lblPerde.setText(valorSpinner + ""); // especie de observador , toda e qualquer mudanca no spinner
        // mudara tambem na tela
      }
      
    }); // fim do evento de clique

  } // fim do carrega spinner

  /* ***************************************************************
  * Metodo: arredondaPontuacao
  * Funcao: arredonda os valores com uma precisao de 3 casas decimais
  * Parametros: uma variavel tipo double que eh o valor a ser arredondado
  * Retorno: retorna o valor arredondado
  *************************************************************** */
  public double arredondaPontuacao(double valor){
    double resultado = 0;
    //classe utilizada para arredondar
    BigDecimal decimal = new BigDecimal(valor).setScale(3, RoundingMode.HALF_EVEN);
    resultado = decimal.doubleValue(); // conversao de decimal para double
    return resultado;
  }

  /* ***************************************************************
  * Metodo: mudaCorLabel
  * Funcao: modifica a cor da label
  * Parametros: recebe um resultado , verdadeiro ou falso
  * Retorno: nao retorna nada
  *************************************************************** */
  public void mudaCorLabel(boolean resultado){
    if(resultado){
      lblNumSorteado.setStyle("-fx-background-color:#20ff1c");
      // se for verdadeiro a label fica verde , indicando que o jogador pontuou
    }
    else{
      lblNumSorteado.setStyle("-fx-background-color:#ff0f07");
      // se for falso a label fica vermelha indicando que o jogador perdeu pontos
    }
  }

  /* ***************************************************************
  * Metodo: mensagemInformativa
  * Funcao: exibe uma mensagem para o usuario
  * Parametros: recebe duas strings , o titulo e o conteudo da mensagem
  * Retorno: nao retorna nada
  *************************************************************** */
  @Override
  public void mensagemInformativa(String titulo,String mensagem) {
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION); 
    alert.setTitle("INFO");
    alert.setHeaderText(titulo);
    alert.setContentText(mensagem);

    // botoes que serao exibidos
    ButtonType btnNao = new ButtonType("NAO");
    ButtonType btnSim = new ButtonType("SIM");
    
    alert.getButtonTypes().setAll(btnSim,btnNao);
    
    Optional<ButtonType> acao = alert.showAndWait();
    if(acao.get() == btnSim){
      this.encerraJogo(); // se o usuario quiser continuar a jogar eu chamo esse metodo para
      // que seja possivel voltar para tela jogo
    }
    else{
      // se nao eu somente fecho o programa
      System.exit(0);
    }
    
  } // fim do mensagem informativa
    
}
