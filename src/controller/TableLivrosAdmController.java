package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.bo.LivroBO;
import model.dao.LivroDAO;
import model.vo.LivroVO;
import view.Telas;

import javax.swing.JOptionPane;

import helpers.DbConnect;


public class TableLivrosAdmController implements Initializable  {
	
	// ajietar com metodos do bo e vo 
	
	
	// tabelas
	@FXML
    private TableColumn<LivroVO, String> autorCol;

    @FXML
    private TableColumn<LivroVO, String> statusCol; //string? // como vai ficar => 
    //3 estados => em avaliação, aceito ou recusado => fazer algum teste para saber se usuario tá digitando certo 
    
    @FXML
    private TableColumn<LivroVO, String> editcCol;

    @FXML
    private TableColumn<LivroVO, String> anoCol;

    @FXML
    private TableColumn<LivroVO, String> tituloCol;

    @FXML
    private TableView<LivroVO> tabelaLivros;

    @FXML
    private TableColumn<LivroVO, String> generoCol;
    
    @FXML
    private TableColumn<LivroVO, String> idCol;


    // textFields
    @FXML
    private TextField autorFid;

    @FXML
    private TextField tituloFid;

    @FXML
    private TextField anoFid;

    @FXML
    private TextField generoFid;
    
    @FXML 
    private TextField idFid;
   
    @FXML
    private TextField filterField;
    
    @FXML
    private ChoiceBox<String> ChoiceStatus;
   
    private String[] alterarStatus = {"Em avaliação", "Aceita", "Rejeitada"};

	    LivroBO bo = new LivroBO();
	    
	    ObservableList<LivroVO> listM;
	    ObservableList<LivroVO> dataList;
	    
	    int index = -1;
	    
	    String query = null;
	    Connection connection = null;
	    LivroVO livro = null;
	    ResultSet rs = null;
	    PreparedStatement preparedStatement = null;
	    
	    @FXML
	    void save(MouseEvent event) {
	    
	    	connection = DbConnect.getConnection();
	    	String titulo = tituloFid.getText();
	    	String genero = generoFid.getText();
	    	String ano = anoFid.getText();
	    	String autor = autorFid.getText();
	    	String id = idFid.getText();
	    	String status = ChoiceStatus.getValue();
	    	
	    	LivroVO vo = new LivroVO(titulo,genero,ano,autor,id,status, null, null,null);
	    
	    	
	    	if(titulo.isEmpty() || genero.isEmpty()  || ano.isEmpty()  // tirei o status 
	    			|| id.isEmpty() || autor.isEmpty()) { // esse if era pra ficar no bo 
	    		
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Por favor, preencha tudo");
	    		alert.showAndWait();
	  
	    	} else {
	    		getQuerry();   // isso seria o cadastrar do bo => usar a query e inserir 
	    		System.out.println("chegando aqui => erro no dao ou bo");
	    		//bo.cadastrarLivro(vo);
	    		insert();
	    		clean();
	    		refreshTable();
	    	}
	    }
	    
	    @FXML
	    void clean() {
	    	tituloFid.setText(null);
	    	generoFid.setText(null);
	    	anoFid.setText(null);
	    	autorFid.setText(null);
	    	idFid.setText(null);
	    }
	   
	    
	    private void getQuerry() {   // faz um if => ver se é necessario
			query = "insert into livros (titulo,genero,ano,autor,status) values (?,?,?,?,?)";
			
		}
	    
	    	 private void insert() {   // cadastrar bo => subistituir 
	 	    	//int intconvertido;
	 	    	try {
	 				preparedStatement = connection.prepareStatement(query);
	 				preparedStatement.setString(1, tituloFid.getText());
	 				preparedStatement.setString(2, generoFid.getText());
	 				preparedStatement.setInt(3, Integer.parseInt(anoFid.getText()));
	 				preparedStatement.setString(4, autorFid.getText());
	 				preparedStatement.setString(5, ChoiceStatus.getValue());
	 				//preparedStatement.setInt(6, Integer.parseInt(idFid.getText()));
	 				
	 				preparedStatement.execute();
	 			
	 			} catch (SQLException ex) {
	 				Logger.getLogger(TableLivrosAdmController.class.getName()).log(Level.SEVERE, null, ex);
	 			}
	 			
	 		}
	    	
	   
	    //////// methode select users ///////
	    @FXML
	    void getSelected (MouseEvent event){
	    	
	    	index = tabelaLivros.getSelectionModel().getSelectedIndex();
	    if (index <= -1){
	    
	        return;
	    }
	    idFid.setText(idCol.getCellData(index).toString());
	    tituloFid.setText(tituloCol.getCellData(index).toString());
	    generoFid.setText(generoCol.getCellData(index).toString());
	    anoFid.setText(anoCol.getCellData(index).toString());
	    autorFid.setText(autorCol.getCellData(index).toString());
	    }

	    public void Edit (){   
	        
	    	try {
	        	//connection = DbConnect.getConnection();
	            String value1 = idFid.getText();
	            String value2 = tituloFid.getText();
	            String value3 = generoFid.getText();
	            String value4 = anoFid.getText();
	            String value5 = autorFid.getText();  //livros no lugar de user no sql 
	            String value6 = ChoiceStatus.getValue();
	            String sql = "update livros set id= '"+value1+"',titulo= '"+value2+"',genero= '"+
	                    value3+"',ano= '"+value4+"',autor= '"+value5+"', status= '"+value6+"' where id='"+value1+"' ";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.execute();
	           //JOptionPane.showMessageDialog(null, "Update");
	            //UpdateTable();
	            loadDate();
	            searchLivro();
	            //search_user(); vai usar aqui?
	            refreshTable();
	            
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	        
	    }
	    
	    public void Delete(){  // ver metodo bo 
	    	//connection = DbConnect.getConnection();
	    String sql = "delete from livros where id = ?";
	        try {	
	        				//	ptst.setInt(1,vo.getId());
	        					//ptst.executeUpdate();
	        	preparedStatement = connection.prepareStatement(sql);
	        	preparedStatement.setInt(1,Integer.parseInt(idFid.getText()));
	        	preparedStatement.executeUpdate();
	            
	        	//JOptionPane.showMessageDialog(null, "Delete"); tava fazendo sair da tela 
	            //UpdateTable();
	            loadDate();
	            searchLivro();
	            refreshTable();
	         
	            //search_user();  vai servir pra que???
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    }

	  
	    ObservableList<LivroVO> LivroList = FXCollections.observableArrayList();
	    
	    // aqui que fiz o voltar => volta pra tela do gerente  => visão do gerente
	    @FXML
	    private void voltar() {
	    	try {
	    		System.out.println("voltando de livros pra tela principal do gerente");
				Telas.telaPrincipalGerente();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    
	    @FXML
	    private void refreshTable() {
	    	try {
	    		LivroList.clear(); // importante => se não repete mesmas coisas na tabela
	    		query = "select * from livros";	
	    		preparedStatement = connection.prepareStatement(query);
	    		System.out.println("tentando a querry livroVO");
	    		rs = preparedStatement.executeQuery();
	    	
	    		System.out.println("carregando tabela de livros");
	    		while (rs.next()) {
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
	    				tabelaLivros.setItems(LivroList);
	    						}  
	    	} catch (SQLException ex) {
	    		Logger.getLogger(TableLivrosAdmController.class.getName()).log(Level.SEVERE,null, ex);
	    	}
	    }
	
	 

	@FXML
	    void searchLivro() {      // serch não prestando em telas      
		loadDate();
		
	    FilteredList<LivroVO> filteredData = new FilteredList<>(LivroList, b -> true);  
	    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(person -> {
	    
	    	if (newValue == null || newValue.isEmpty()) {
	     return true;
	    }    
	    
	    	String lowerCaseFilter = newValue.toLowerCase();
	    
	    if (person.getTitulo().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	     return true; // Filter matches username
	    } else if (person.getGenero().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	     return true; // Filter matches password
	    }else if (person.getAutor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	     return true; // Filter matches password
	    }
	    else if (String.valueOf(person.getAno()).indexOf(lowerCaseFilter)!=-1)
	         return true;// Filter matches email
	                                
	         else  
	          return false; // Does not match.
	   });
	  });  
	 
	  SortedList<LivroVO> sortedData = new SortedList<>(filteredData);  
	  sortedData.comparatorProperty().bind(tabelaLivros.comparatorProperty());  
	  tabelaLivros.setItems(sortedData);      
	   
	}
	    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ChoiceStatus.getItems().addAll(alterarStatus);
		loadDate();
		searchLivro();
	}
	
	 
    
    private void loadDate() {
    	connection = DbConnect.getConnection();
        refreshTable();
        
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	generoCol.setCellValueFactory(new PropertyValueFactory<>("genero"));
    	anoCol.setCellValueFactory(new PropertyValueFactory<>("ano"));
    	autorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}

