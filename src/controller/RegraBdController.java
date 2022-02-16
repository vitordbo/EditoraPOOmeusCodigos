package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import helpers.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.bo.LivroBO;
import model.vo.LivroVO;
import model.vo.RegraBdVO;
import view.Telas;

public class RegraBdController implements Initializable {

    @FXML
    private TableColumn<RegraBdVO, String> idLivroCol;
	 
	@FXML
    private TableColumn<RegraBdVO, String> statusCol;

    @FXML
    private TableColumn<RegraBdVO, String> dataTrocaCol;

    @FXML
    private TableColumn<RegraBdVO, String> statusNovo;

    @FXML
    private TableView<RegraBdVO> tabelaRegra;

    
    @FXML
    void voltar(MouseEvent event) {
    	try {
    		System.out.println("voltando da tela que só mostra a tabela da regra"
    				+ "a tela relatorio");
			Telas.telaRelatorios();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    ObservableList<LivroVO> listM;
    ObservableList<LivroVO> dataList;
    
    int index = -1;
    
    String query = null;
    Connection connection = null;
    LivroVO livro = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;
       
    ObservableList<RegraBdVO> RegraList = FXCollections.observableArrayList();
    
    // implementar voltar com livros da visão autor/avaliador
    
    
    @FXML
    private void refreshTable() {
    	try {
    		RegraList.clear(); 
    		
    		query = "select * from teste_status_relatorio";	
    		preparedStatement = connection.prepareStatement(query);
    		System.out.println("tentando a querry RegraBD");
    		rs = preparedStatement.executeQuery();
    	
    		System.out.println("carregando tabela da regra");
    		while (rs.next()) {
    			RegraList.add(new RegraBdVO(
    		    					rs.getString("id_livro"),
    		    					rs.getString("status"),
    		    					rs.getDate("data_troca_status"),
    		    					rs.getString("status_novo")));
    			tabelaRegra.setItems(RegraList);
    						}  
    	} catch (SQLException ex) {
    		Logger.getLogger(TableLivrosAdmController.class.getName()).log(Level.SEVERE,null, ex);
    		}
    	}

 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	loadDate();
    }

 

    private void loadDate() {
    	connection = DbConnect.getConnection();
    	refreshTable();
  
    	idLivroCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    	dataTrocaCol.setCellValueFactory(new PropertyValueFactory<>("data"));
    	statusNovo.setCellValueFactory(new PropertyValueFactory<>("statusNovo"));
    	
		}
	}


