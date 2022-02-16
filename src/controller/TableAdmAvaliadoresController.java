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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.vo.AvaliadorVO;
import view.Telas;

public class TableAdmAvaliadoresController implements Initializable {
	    
	    @FXML
	    private TableColumn<AvaliadorVO, String> nomeCol;

	    @FXML
	    private TableColumn<AvaliadorVO, String> enderecoCol;

	    @FXML
	    private TableColumn<AvaliadorVO, String> cpfCol;
	    
	    @FXML
	    private TableColumn<AvaliadorVO, Integer> idCol;  // testar trocar por integer para funcionar select

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
	    private TableView<AvaliadorVO> tabelaAvaliadores;

	    
	    // metodo que vai levar para a tela de definir avaliadores para obras
	    // botão novo que vai levar para outra tela
	    @FXML
	    void definirAvaliadoresTela(MouseEvent event) {
	    	try {
				Telas.telaDefinirAvaliadores();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    }
	    
	    
	    ObservableList<AvaliadorVO> listM;
	    ObservableList<AvaliadorVO> dataList;
	    
	    int index = -1;
	    
	    String query = null;
	    String query2 = null;
	    Connection connection = null;
	    AvaliadorVO avaliador = null;
	    ResultSet rs = null;
	    PreparedStatement preparedStatement = null;
	    
	  	    
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
	    		
	           // inserindo na tabela avaliador 
	    		getQuerryAva();
	    		insertAva();
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
		
	    private void getQuerryAva() {   // faz um if => ver se é necessario
			query = "insert into avaliador (nome,endereco,cpf,id,id_usuario,nickname,senha) values (?,?,?,?,?,?,?)";
			
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
				Logger.getLogger(TableAdmAvaliadoresController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	    	
	    
	    private void insertAva() {   
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
				Logger.getLogger(TableAdmAvaliadoresController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
   
	    @FXML
	    void getSelected (MouseEvent event){
	    	
	    	index = tabelaAvaliadores.getSelectionModel().getSelectedIndex();
	    if (index <= -1){
	  
	        return;
	    }

	    idFid.setText(idCol.getCellData(index).toString()); 
	    nomeFid.setText(nomeCol.getCellData(index).toString());
	    enderecoFid.setText(enderecoCol.getCellData(index).toString());
	    cpfFid.setText(cpfCol.getCellData(index).toString());
	    }
	       
	    // alterei o nome e só mudou na tabela autor => não mudou na tabela usuario => ver algo no bd em si pra mudar
	    public void Edit (){    // ajitar esse esit => não ta editando cpf 
	    	try {
	    		PreparedStatement preparedStatementUsu; 
	    		String value1 = idFid.getText();
	    		String value2 = nomeFid.getText();
	            String value3 = enderecoFid.getText();
	            String value4 = cpfFid.getText();   // não vai alterar id nesse sql
	            String sql = "update avaliador set id= '"+value1+"',nome= '"+value2+"',endereco= '"+
	                 value3+"',cpf= '"+value4+"' where id='"+value1+"' ";
	            
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.execute();
	           
	            String sqlUsu = "update usuario set id_usu= '"+value1+"',nome= '"+value2+"' where id_usu='"+value1+"' ";
		         // atualizar usuario
		        	preparedStatementUsu = connection.prepareStatement(sqlUsu);
		        	preparedStatementUsu.executeUpdate();
	            loadDate();
	            searchAvaliador();
	            refreshTable();
	            
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }   
	    }
	    
	    
	    public void Delete(){  // ver metodo bo 
	    	connection = DbConnect.getConnection();
	    	PreparedStatement preparedStatementUsu; 
	    	String sql = "delete from avaliador where id = ?";  // não usando id aqui => usando cpf para deletar
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
	            searchAvaliador();
	            refreshTable();
	            clean();
	         
	            //search_user();  vai servir pra que???
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    }
	    
	    
	    ObservableList<AvaliadorVO> AvaliadorList = FXCollections.observableArrayList();
	    
	    // aqui que fiz o voltar => volta pra tela do gerente 
	    @FXML
	    private void voltar() {
	    	try {
	    		System.out.println("voltando de avaliadores pra tela principal do gerente");
				Telas.telaPrincipalGerente();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    @FXML
	    private void refreshTable() {
	    	try {
	    		AvaliadorList.clear();
		
	    		query = "select * from avaliador";	
	    		preparedStatement = connection.prepareStatement(query);
	    		System.out.println("tentando a querry select *");
	    		rs = preparedStatement.executeQuery();
	    	
	    		while (rs.next()) {   
	    			AvaliadorList.add(new AvaliadorVO(
	    					rs.getString("nome"),
	    					rs.getString("endereco"),
	    					rs.getString("cpf"), 
	    					rs.getInt("id")));
	    			        
	    			tabelaAvaliadores.setItems(AvaliadorList);   				
	    			}   			
	    	} catch (SQLException ex) {
	    		Logger.getLogger(TableAdmAvaliadoresController.class.getName()).log(Level.SEVERE,null, ex);
	    	}
	    }
	

	    @FXML
	    void searchAvaliador() {          
		loadDate();
		
	    FilteredList<AvaliadorVO> filteredData = new FilteredList<>(AvaliadorList, b -> true);  
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
	 
	  SortedList<AvaliadorVO> sortedData = new SortedList<>(filteredData);  
	  sortedData.comparatorProperty().bind(tabelaAvaliadores.comparatorProperty());  
	  tabelaAvaliadores.setItems(sortedData);      
	   
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
			searchAvaliador();
		}

	}


