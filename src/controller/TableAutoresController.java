package controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import helpers.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.bo.AutorBO;
import model.bo.UsuarioBO;
import model.vo.AutorVO;
import model.vo.LivroVO;
import model.vo.UsuarioVO;
import view.Telas;

public class TableAutoresController implements Initializable {

	    @FXML
	    private TableColumn<AutorVO, String> nomeCol;

	    @FXML
	    private TableColumn<AutorVO, String> enderecoCol;

	    @FXML
	    private TableColumn<AutorVO, String> cpfCol;
	    
	    @FXML
	    private TableColumn<AutorVO, Integer> idCol;  //trocar por integer 

	    @FXML
	    private TextField enderecoFid;

	    @FXML
	    private TextField cpfFid;
	    
	    @FXML
	    private TextField idFid;

	    @FXML
	    private TextField filterField;

	    @FXML
	    private TextField nomeFid;
	    
	    @FXML
	    private TextField loginFid;
	    
	    @FXML
	    private TextField senhaFid;

	    @FXML
	    private TableView<AutorVO> tabelaAutores;

	    
	    ObservableList<AutorVO> listM;
	    ObservableList<AutorVO> dataList;
	    
	    int index = -1;
	    
	    String query = null;
	    String query2 = null;
	    Connection connection = null;
	    AutorVO autor = null;
	    ResultSet rs = null;
	    PreparedStatement preparedStatement = null;
	    ObservableList<AutorVO> AutorList = FXCollections.observableArrayList();
	    
	    UsuarioBO usuBo = new UsuarioBO();
	    AutorBO autorBo = new AutorBO();
	    
	    @FXML
	    void save(MouseEvent event) {
	    	connection = DbConnect.getConnection();
	    	
	    	String nome = nomeFid.getText();
	    	String endereco = enderecoFid.getText();
	    	String cpf = cpfFid.getText();
	    	String id = idFid.getText();
	    	String login = loginFid.getText();
	    	String senha = senhaFid.getText();
	    	
	    	
	    	if(nome.isEmpty() || endereco.isEmpty() ||cpf.isEmpty() || id.isEmpty()) { // esse if era pra ficar no bo 
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Por favor, preencha tudo");
	    		alert.showAndWait();
	  
	    	} else {
	    		// inserir na tabela usuario
	    	    getQuerryUsu(); 
	    		insertUsu();
	    		
	    		// inserir na tabela autor
	    		getQuerryAut();    
	    		System.out.println("inserindo");
	    		insertAut();
	    		clean();
	    		refreshTable();
	    	}
	    }
	    
	    @FXML
	    void clean() {
	    	nomeFid.setText(null);
	    	enderecoFid.setText(null);
	    	cpfFid.setText(null);
	    	idFid.setText(null);
	    	loginFid.setText(null);
	    	senhaFid.setText(null);
	    }
	    
	    private void getQuerryUsu() {   // faz um if => ver se é necessario
			query2 = "insert into usuario (nome,id_usu,nickname, senha) values (?,?,?,?)";
	    }
		
	    private void getQuerryAut() {   // faz um if => ver se é necessario
			query = "insert into autor (nome,endereco,cpf,id,id_usuario,nickname,senha) values (?,?,?,?,?,?,?)";
			
		}
	    
	    private void insertUsu() {
	    	try {
 				preparedStatement = connection.prepareStatement(query2);
 				preparedStatement.setString(1, nomeFid.getText());
 				preparedStatement.setInt(2,Integer.parseInt(idFid.getText()));
 				preparedStatement.setString(3, loginFid.getText());
 				preparedStatement.setString(4, senhaFid.getText());
 				preparedStatement.execute();

 			} catch (SQLException ex) {
 				Logger.getLogger(TableAutoresController.class.getName()).log(Level.SEVERE, null, ex);
 			}
 		}
	    	
	    
	    private void insertAut() {   
 	    	try {
 				preparedStatement = connection.prepareStatement(query);
 				preparedStatement.setString(1, nomeFid.getText());
 				preparedStatement.setString(2, enderecoFid.getText());
 				preparedStatement.setString(3, cpfFid.getText());
 				preparedStatement.setInt(4,Integer.parseInt(idFid.getText()));
 				preparedStatement.setInt(5,Integer.parseInt(idFid.getText()));
 				preparedStatement.setString(6, loginFid.getText());
 				preparedStatement.setString(7, senhaFid.getText());
 				preparedStatement.execute();

 			} catch (SQLException ex) {
 				Logger.getLogger(TableAutoresController.class.getName()).log(Level.SEVERE, null, ex);
 			}
 		}
    
	    @FXML
	    void getSelected (MouseEvent event){
	    	
	    	index = tabelaAutores.getSelectionModel().getSelectedIndex();
	    if (index <= -1){
	  
	        return;
	    }  
	    idFid.setText(idCol.getCellData(index).toString()); 
	    nomeFid.setText(nomeCol.getCellData(index).toString());
	    enderecoFid.setText(enderecoCol.getCellData(index).toString());
	    cpfFid.setText(cpfCol.getCellData(index).toString());
	    }
	       
	    // alterei o nome e só mudou na tabela autor => não mudou na tabela usuario => ver algo no bd em si pra mudar
	    public void Edit (){    
	    	try {
	    		PreparedStatement preparedStatementUsu; 
	    		String value1 = idFid.getText();
	    		String value2 = nomeFid.getText();
	            String value3 = enderecoFid.getText();
	            String value4 = cpfFid.getText();   // não vai alterar id nesse sql
	            //String value5 = loginFid.getText();
	           // String value6 = senhaFid.getText();
	            String sql = "update autor set id= '"+value1+"',nome= '"+value2+"',endereco= '"+
	                 value3+"',cpf= '"+value4+"' where id='"+value1+"' ";
	            
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.execute();
	            
	            String sqlUsu = "update usuario set id_usu= '"+value1+"',nome= '"+value2+"' where id_usu='"+value1+"' ";
	            
	         // atualizar usuario
	        	preparedStatementUsu = connection.prepareStatement(sqlUsu);
	        	preparedStatementUsu.executeUpdate();
	        	
	            loadDate();
	            searchAutor();
	            refreshTable();
	            
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }   
	    }
	    
	  
	    public void Delete(){  // ver metodo bo 
	    	connection = DbConnect.getConnection();
	    	PreparedStatement preparedStatementUsu; 
	    	String sql = "delete from autor where id = ?";// não usando id aqui => usando cpf para deletar
	    	String sqlUsu = "delete from usuario where id_usu = ?";
	        try {	
	        	preparedStatement = connection.prepareStatement(sql);
	        	preparedStatement.setInt(1,Integer.parseInt(idFid.getText()));
	        	preparedStatement.executeUpdate();
	            
	        	// deletar usuario
	        	preparedStatementUsu = connection.prepareStatement(sqlUsu);
	        	preparedStatementUsu.setInt(1,Integer.parseInt(idFid.getText()));
	        	preparedStatementUsu.executeUpdate();
	        	
	            loadDate();
	            searchAutor();
	            refreshTable();
	            clean();
	         
	            //search_user();  vai servir pra que???
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    }
	    
	    
	    
	    // aqui que fiz o voltar => volta pra tela do gerente 
	    @FXML
	    private void voltar() {
	    	try {
	    		System.out.println("voltando de autores pra tela principal do gerente");
				Telas.telaPrincipalGerente();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    @FXML
	    private void refreshTable() {
	    	try {
	    		AutorList.clear();
		
	    		query = "select * from autor";	
	    		preparedStatement = connection.prepareStatement(query);
	    		System.out.println("tentando a querry");
	    		rs = preparedStatement.executeQuery();
	    	
	    		while (rs.next()) {   // ver aqui => construtor menos completo que o do livro
	    			AutorList.add(new AutorVO(
	    					rs.getString("nome"),
	    					rs.getString("endereco"),
	    					rs.getString("cpf"), 
	    					rs.getInt("id")));
	    			
	    			tabelaAutores.setItems(AutorList);   				
	    			}   			
	    	} catch (SQLException ex) {
	    		Logger.getLogger(TableAutoresController.class.getName()).log(Level.SEVERE,null, ex);
	    	}
	    }
	

	    @FXML
	    void searchAutor() {          
		loadDate();
		
	    FilteredList<AutorVO> filteredData = new FilteredList<>(AutorList, b -> true);  
	    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(person -> {
	    
	    	if (newValue == null || newValue.isEmpty()) {
	     return true;
	    }    
	    
	    	String lowerCaseFilter = newValue.toLowerCase();
	    
	    if (person.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	     return true; // Filter matches username
	    } else if (person.getEndereco().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	     return true; // Filter matches password
	    }else if (person.getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	     return true; // Filter matches password
	    } 
		     else  
	        return false; // Does not match.
	   });
	  });  
	 
	  SortedList<AutorVO> sortedData = new SortedList<>(filteredData);  
	  sortedData.comparatorProperty().bind(tabelaAutores.comparatorProperty());  
	  tabelaAutores.setItems(sortedData);      
	   
	}
	    private void loadDate() {
	    	connection = DbConnect.getConnection();
	        refreshTable();
	        
	    	nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    	enderecoCol.setCellValueFactory(new PropertyValueFactory<>("endereco"));
	    	cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
	    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
	    }
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			loadDate();
			searchAutor();
		}

	}

