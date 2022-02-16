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

import javax.swing.JOptionPane;

import helpers.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.bo.LivroBO;
import model.vo.LivroVO;
import view.Telas;


public class TableAvaliadorTrocaStatusController implements Initializable  {
	
	// tabelas
	@FXML
    private TableColumn<LivroVO, String> autorCol;

    @FXML
    private TableColumn<LivroVO, String> statusCol; //string? // como vai ficar => 
    //3 estados => em avaliação, aceito ou recusado => fazer algum teste para saber se usuario tá digitando certo 
    
    @FXML
    private TableColumn<LivroVO, String> nomeAvaCol;

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
    private TextField filtroLivros;

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
	    void clean() {
	    	tituloFid.setText(null);
	    	generoFid.setText(null);
	    	anoFid.setText(null);
	    	autorFid.setText(null);
	    	idFid.setText(null);
	    }
	    
	    @FXML
	     void filtrarPorAvaliador() {
	    	try {
	    		filtroLivros.getText();
	    		LivroList.clear(); // importante => se não repete mesmas coisas na tabela
	    		System.out.println("tentando a querry livroVO status => erro no sql");
	    		query = "select * from livros where id_avaliador = '"+filtroLivros.getText()+"' ";	// como fica esse sql?
	    		preparedStatement = connection.prepareStatement(query);
	    		System.out.println("tentando a querry livroVO status");
	    		rs = preparedStatement.executeQuery();
	    	
	    		System.out.println("carregando tabela de muda status do livro");
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
	            String value5 = autorFid.getText();  
	            String value6 = ChoiceStatus.getValue();	// pegando da choice box
	           // pode modificar nome do livro??? => acho que pode por exemplo se quiser alterar o genero 
	            String sql = "update livros set status= '"+value6+"',titulo= '"+value2+"',genero= '"+      
	                    value3+"',ano= '"+value4+"',autor= '"+value5+"' where id='"+value1+"' ";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.execute();
	            
	            loadDate();
	            filtrarPorAvaliador();
	            clean();
	            
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	        
	    }
	    	  
	    ObservableList<LivroVO> LivroList = FXCollections.observableArrayList();
	    
	    // aqui que fiz o voltar => volta pra tela principal de login  => trocar por uma casa
	    @FXML
	    private void voltar() {
	    	try {
	    		System.out.println("voltando da visão avaliador troca status pra tela de login");
				Telas.telaLogin();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ChoiceStatus.getItems().addAll(alterarStatus);
		loadDate();
	}
	
	

    private void loadDate() {
    	connection = DbConnect.getConnection();
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	generoCol.setCellValueFactory(new PropertyValueFactory<>("genero"));
    	anoCol.setCellValueFactory(new PropertyValueFactory<>("ano"));
    	autorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    	nomeAvaCol.setCellValueFactory(new PropertyValueFactory<>("nome_avaliador"));
    }
}


