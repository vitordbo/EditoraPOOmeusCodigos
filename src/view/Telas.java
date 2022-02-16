package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Telas extends Application  {
	 private static Stage primaryStage;
		 
		 
	 public static Stage getPrimaryStage() {
		 return primaryStage;
	 }
	 public static void setPrimaryStage(Stage primaryStage) {
		 Telas.primaryStage = primaryStage;
	 }
	 
	 public void start(Stage primaryStage) throws Exception{
		 setPrimaryStage(primaryStage);
		 primaryStage.setTitle("Editora poo");
		 //primaryStage.setMaximized(true);
		 primaryStage.show();
		 telaLogin();
	 }
	 
	 public static void telaLogin() throws Exception{
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaLogin2.fxml"));
		 
		 Scene cena = new Scene(root);
		 
		 primaryStage.setScene(cena);
	 }
	 
	 
	 public static void telaPrincipalGerente() throws Exception{ //vai receber usuario?
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaEntradaAdm.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaCadastroObra() throws Exception { //vai receber usuario?
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaCadastroObra.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaListaObras() throws Exception { //vai receber usuario?
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaListaObras.fxml"));
		// TesteTabelaLivros teste = new TesteTabelaLivros();
		// teste.initialize();
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaLoginAutor( ) throws Exception { 
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaCadastroObra.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 
	 public static void telaTabelaAdmLivros( ) throws Exception { 
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/tableAdmLivros.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaTabelaLivros( ) throws Exception { 
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/tableLivros.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaTabelaAutores() throws Exception { 
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/tableAutores.fxml"));   // autores cadastrar, alterar e deletar do adm
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaTabelaAvaliadores() throws Exception { 
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/tableAdmAvaliadores.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 // mostra a nova tela com a janela anterior aberta 
		 //Stage stage = new Stage();
		 //stage.setScene(cena);
		 //stage.initStyle(StageStyle.UTILITY);
		// stage.show();
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaDefinirAvaliadores() throws Exception { 
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/DefinirAvaliadoresParaLivros.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaApenasMostraAvaliadorDeCadaObra() throws Exception { 
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/tabelaApenasVisualizacaoObrasDosAvaliadores.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaAvaliadorAlteraStatusCadaObra() throws Exception {  // tela principal do avaliador
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/tableAvaliadorTrocaStatus.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaPrinciplaAutorCadastrarLivros() throws Exception {  // tela principal do avaliador
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaPrincipalAutor.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaRelatorios() throws Exception {  // tela principal do avaliador
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/Relatorio.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 public static void telaTabelaRegras() throws Exception {  // tela principal do avaliador
		 Parent root = FXMLLoader.load(Telas.class.getResource("VE/RegraBd.fxml"));
		 
		 Scene cena = new Scene(root); 
		 
		 primaryStage.setScene(cena);
	 }
	 
	 
	 
	 public static void main(String[] args) {
			launch();
		} 
	 
}
