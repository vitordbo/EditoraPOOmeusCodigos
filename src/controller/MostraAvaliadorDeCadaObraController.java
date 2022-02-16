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
import model.bo.LivroBO;
import model.dao.LivroDAO;
import model.vo.LivroVO;
import view.Telas;

public class MostraAvaliadorDeCadaObraController implements Initializable  {
		
		@FXML
	    private TableColumn<LivroVO, String> statusCol;

	    @FXML
	    private TableColumn<LivroVO, String> tituloLivroCol;

	    @FXML
	    private TableColumn<LivroVO, String> nomeAvaCol;

	    @FXML
	    private TableView<LivroVO> tabelaLivros;

	    @FXML
	    private TableColumn<LivroVO, String> idLivroCol;

	    @FXML
	    private TableColumn<LivroVO, String> idAvaCol;

	    @FXML
	    private TableColumn<LivroVO, String> generoCol;

	    @FXML
	    private TextField filterField;
	   
 
	    @FXML
	    void voltar(MouseEvent event) {
	    	try {
	    		System.out.println("voltando da tela que só mostra as obras e avaliadores para "
	    				+ "a tela que seleciona os avaliadores das obras");
				Telas.telaDefinirAvaliadores();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    LivroBO bo = new LivroBO();
	    
	    ObservableList<LivroVO> listM;
	    ObservableList<LivroVO> dataList;
	    
	    int index = -1;
	    
	    String query = null;
	    Connection connection = null;
	   
	    ResultSet rs = null;
	    PreparedStatement preparedStatement = null;
	       
	    ObservableList<LivroVO> LivroList = FXCollections.observableArrayList();
	    
	    @FXML
	    private void refreshTable() {
	    		LivroList.clear();
	    		tabelaLivros.setItems(bo.buscarVisao());
	    }
	
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadDate();
	}
	
	 
    private void loadDate() {
    	connection = DbConnect.getConnection();
        refreshTable();
      
	    idAvaCol.setCellValueFactory(new PropertyValueFactory<>("id_avaliador"));
	    nomeAvaCol.setCellValueFactory(new PropertyValueFactory<>("nome_avaliador"));
	    idLivroCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tituloLivroCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}

