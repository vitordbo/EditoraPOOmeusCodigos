package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import helpers.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.vo.AvaliadorVO;
import model.vo.LivroVO;
import view.Telas;

public class DefinirAvaliadoresController implements Initializable {
	
	    @FXML
	    private TextField idFidLivro;

	    @FXML
	    private TextField tituloFidLivro;

	    @FXML
	    private TableColumn<AvaliadorVO, String> nomeColAvaliador;

	    @FXML
	    private TextField idFidAva;

	    @FXML
	    private TableColumn<LivroVO, String> tituloColLivros;

	    @FXML 
	    private TableColumn<AvaliadorVO, Integer> idColAvaliador;   // colocando integer aqui para testar

	    @FXML
	    private TableColumn<LivroVO, String> idColLivros;

	    @FXML
	    private TableView<LivroVO> tabelaLivros;

	    @FXML
	    private TextField nomeFidAva;

	    @FXML
	    private TableView<AvaliadorVO> tabelaAvaliadores;

    
	    int index = -1;
	    
	    String query = null;
	    String query2 = null;
	    Connection connection = null;
	    AvaliadorVO avaliador = null;
	    ResultSet rs = null;
	    PreparedStatement preparedStatement = null;
	    
	    @FXML
	    void clean() {
	    	nomeFidAva.setText(null);
	    	idFidAva.setText(null);
	    	tituloFidLivro.setText(null);
	    	idFidLivro.setText(null);
	    }
	    
	    
	   //Começando pela tabela Avaliador
	    ObservableList<AvaliadorVO> AvaliadorList = FXCollections.observableArrayList();
	    
	    private void loadDateAva() {
	    	connection = DbConnect.getConnection();
	        refreshTableAva();
	        
	    	nomeColAvaliador.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    	idColAvaliador.setCellValueFactory(new PropertyValueFactory<>("id"));
	    }
	    
	    @FXML
	    private void refreshTableAva() {
	    	try {
	    		AvaliadorList.clear();
		
	    		query = "select * from avaliador";	
	    		preparedStatement = connection.prepareStatement(query);
	    		System.out.println("tentando a querry select *");
	    		rs = preparedStatement.executeQuery();
	    	
	    		while (rs.next()) {   // ver aqui => construtor menos completo que o do livro
	    			AvaliadorList.add(new AvaliadorVO(
	    					rs.getString("nome"),
	    					rs.getString("endereco"),
	    					rs.getString("cpf"), 
	    					rs.getInt("id")));
	    			        // ver pq não ta parecendo id
	    					// status
	    			tabelaAvaliadores.setItems(AvaliadorList);   				
	    			}   			
	    	} catch (SQLException ex) {
	    		Logger.getLogger(TableAdmAvaliadoresController.class.getName()).log(Level.SEVERE,null, ex);
	    	}
	    }
	    
	    @FXML
	    void getSelectedAva (MouseEvent event){
	    	
	    	index = tabelaAvaliadores.getSelectionModel().getSelectedIndex();
	    if (index <= -1){
	  
	        return;
	    }
	   
	    // ver se vai dar erro ao selecionar id aqui 
	    idFidAva.setText(idColAvaliador.getCellData(index).toString()); 
	    nomeFidAva.setText(nomeColAvaliador.getCellData(index).toString());
	    }
	    
	    
	    
	    
	    // tabela Livros
	    ObservableList<LivroVO> LivroList = FXCollections.observableArrayList();
	    
	    private void loadDateLivros() {
	    	connection = DbConnect.getConnection();
	        refreshTableLivros();
	        
	    	idColLivros.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	tituloColLivros.setCellValueFactory(new PropertyValueFactory<>("titulo"));
	    }
	    
	    @FXML
	    private void refreshTableLivros() {
	    	try {
	    		LivroList.clear();
		
	    		query = "select * from livros";	
	    		preparedStatement = connection.prepareStatement(query);
	    		System.out.println("tentando a querry select livros");
	    		rs = preparedStatement.executeQuery();
	    	
	    		while (rs.next()) {  // troquei pelo novo construtor => se der ruim => colocar null nos dois ultimos
	    			LivroList.add(new LivroVO(
	    					rs.getString("titulo"),
	    					rs.getString("genero"),
	    					rs.getString("ano"),
	    					rs.getString("autor"),
	    					rs.getString("id"), 
	    					rs.getString("status"),
	    					rs.getInt("id_avaliador"),
	    					rs.getString("nome_avaliador"),
	    					rs.getInt("id_autor")));
	    			        // ver pq não ta parecendo id
	    					// status
	    				tabelaLivros.setItems(LivroList);
	    				
	    			}
	    			
	    	} catch (SQLException ex) {
	    		Logger.getLogger(TableLivrosAdmController.class.getName()).log(Level.SEVERE,null, ex);
	    	}
	    }
	    
	    @FXML
	    void getSelectedLivros (MouseEvent event){
	    	
	    	index = tabelaLivros.getSelectionModel().getSelectedIndex();
	    if (index <= -1){
	    
	        return;
	    }
	    idFidLivro.setText(idColLivros.getCellData(index).toString());
	    tituloFidLivro.setText(tituloColLivros.getCellData(index).toString());

	    }
	   
	    @FXML
	    private void refreshTable() {
	    	// refresh geral do icone
	    	refreshTableAva();
	    	refreshTableLivros();
	    }

	    @FXML
	    void voltar(MouseEvent event) {
	    	try {
	    		System.out.println("voltando de autores pra tela principal do cadastrar avaliador");
				Telas.telaTabelaAvaliadores();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void save(MouseEvent event) {
	    	connection = DbConnect.getConnection();
	    	
	    	String titulo = tituloFidLivro.getText();
	    	String id = idFidLivro.getText();
	    	//String id = idFid.getText();
	    	
	    	if(titulo.isEmpty() || id.isEmpty()) { // esse if era pra ficar no bo 
	    		
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Por favor, preencha tudo");
	    		alert.showAndWait();
	  
	    	} else {
	    		insert();
	    		// isso seria o cadastrar do bo => usar a query e inserir 
	    		System.out.println("chegando aqui => erro no dao ou bo");
	    		//bo.cadastrarLivro(vo);
	    		clean();
	    		refreshTable();
	    	}
	    }
	    
	
	    private void insert() {   
 	    	try {
 	    		// colocando na tabela livro => vai só fazer o update com base no id 
 	    		// colocando na tabela avalia => ver se é a melhor coisa 
 	    		// selecionar daqui copiar pro fidText e muda na tabela livro o status
 	    		// sem chave estrangeira em livros => para poder cadastar 
 	    		String value1 = nomeFidAva.getText();
	    		String value2 = idFidAva.getText();
	            //String value3 = tituloFidLivro.getText();
	            String value4 = idFidLivro.getText();
	     
	      //não tem necessidade de mudar o nome do livro e o id => só mudar a parte do avaliador com base no id do livro
	            String sql = "update livros set nome_avaliador= '"+value1+"',id_avaliador= '"+value2+"'"
	            		+ " where id='"+value4+"' ";
 	    		System.out.println("definindo avaliador para a obra");
 				preparedStatement = connection.prepareStatement(sql);
 				preparedStatement.execute();
 				  
 				  loadDateAva();
 				  loadDateLivros(); 
 		          refreshTable();
 		            
 				
 			} catch (SQLException ex) {
 				Logger.getLogger(TableAutoresController.class.getName()).log(Level.SEVERE, null, ex);
 			}
 		}
	    
	    @FXML
	    void removerAvaliador(MouseEvent event) {
	    	connection = DbConnect.getConnection();
	    	
	    	String titulo = tituloFidLivro.getText();
	    	String id = idFidLivro.getText();
	    	//String id = idFid.getText();
	    	
	    	if(titulo.isEmpty() || id.isEmpty()) { // esse if era pra ficar no bo 
	    		
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Por favor, preencha tudo");
	    		alert.showAndWait();
	  
	    	} else {
	    		removerAvaObra();
	    		// isso seria o cadastrar do bo => usar a query e inserir 
	    		System.out.println("chegando aqui => erro no dao ou bo");
	    		//bo.cadastrarLivro(vo);
	    		clean();
	    		refreshTable();
	    	}
	    }
	    	
	   private void removerAvaObra() {
	    	try {       
	            String value1 = idFidLivro.getText();       //types null => vai colocar 0 na tabela se quiser remover
	            String sql = "update livros set nome_avaliador= '"+Types.NULL+"',id_avaliador= '"+Types.NULL+"'"
	            		+ " where id='"+value1+"' ";
 	    		
	            System.out.println("removendo avaliador da obra");
 				preparedStatement = connection.prepareStatement(sql);
 				preparedStatement.execute();
 				  
 				  loadDateAva();
 				  loadDateLivros(); 
 		          refreshTable();
 		            
 				
 			} catch (SQLException ex) {
 				Logger.getLogger(TableAutoresController.class.getName()).log(Level.SEVERE, null, ex);
 			}
 		}
	    		
	    
	    @FXML
	    void verAvaliadoresEscolhidos(MouseEvent event) {
	    	// fazer outro table view de livros => so que com coulnas diferentes 
	    	// mostrar colunas do nome avaliador e id avaliador => se tiver vazio não tem avaliador 
	    	// vai alterar na anterior => nessa é só pra ver cada avalaidor de cada obra 
	    	try {
				Telas.telaApenasMostraAvaliadorDeCadaObra();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadDateAva();
		loadDateLivros();
	}

}
