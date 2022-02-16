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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.bo.LivroBO;
import model.vo.LivroVO;
import view.Telas;

public class PrincipalAutorController implements Initializable {

	// tabelas
	@FXML
    private TableColumn<LivroVO, String> autorCol;

    @FXML
    private TableColumn<LivroVO, String> statusCol; 
    
    @FXML
    private TableColumn<LivroVO, String> anoCol;

    @FXML
    private TableColumn<LivroVO, String> tituloCol;

    @FXML
    private TableColumn<LivroVO, String> generoCol;
    
    @FXML
    private TableColumn<LivroVO, String> idCol;
    
    @FXML
    private TableColumn<LivroVO, Integer> idAutorCol;  // nova coluna pra poder atualizar 

    @FXML
    private TableView<LivroVO> tabelaLivros;

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
    private TextField idAutorFid;
	
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
	    	
	    	LivroVO vo = new LivroVO(titulo,genero,ano,autor,id,null, null, null,null);
	    
	    	
	    	// tentei tirar o id para usar o serial automatico do bd
	    	if(titulo.isEmpty() || genero.isEmpty()  || ano.isEmpty()  
	    			|| autor.isEmpty()) { 
	    		
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
	    		filtrarPorAutor();
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
			query = "insert into livros (titulo,genero,ano,autor,id_autor) values (?,?,?,?,?)";
			
		}
	    
	    	 private void insert() {   // cadastrar bo => subistituir 
	 	    	//int intconvertido;
	 	    	try {
	 				preparedStatement = connection.prepareStatement(query);
	 				preparedStatement.setString(1, tituloFid.getText());
	 				preparedStatement.setString(2, generoFid.getText());
	 				preparedStatement.setInt(3, Integer.parseInt(anoFid.getText()));
	 				preparedStatement.setString(4, autorFid.getText());
	 				preparedStatement.setInt(5, Integer.parseInt(idAutorFid.getText()));
	 				
	 				preparedStatement.execute();
	 			
	 			} catch (SQLException ex) {
	 				Logger.getLogger(TableLivrosAdmController.class.getName()).log(Level.SEVERE, null, ex);
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
	            String value5 = autorFid.getText();  //livros no lugar de user no sql 
	            String value6 = idAutorFid.getText();
	            String sql = "update livros set id= '"+value1+"',titulo= '"+value2+"',genero= '"+
	                    value3+"',ano= '"+value4+"',autor= '"+value5+"', id_autor = '"+value6+"' where id='"+value1+"' ";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.execute();
	            
	            loadDate();
	            filtrarPorAutor();
	            clean();
	            
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	        
	    }
	    

	    @FXML
	    void filtrarPorAutor() {   // será que ele atualiza quando deleta ou adiciona?
	    	try {
	    		String teste = idAutorFid.getText();
	    		LivroList.clear(); // importante => se não repete mesmas coisas na tabela
	    		
	    		query = "select * from livros where id_autor = '"+teste+"' ";	// como fica esse sql?
	    		preparedStatement = connection.prepareStatement(query);
	    		System.out.println("tentando selecionar com id do autor ");
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
	  
	    public void Delete(){  // ver metodo bo 
	    String sql = "delete from livros where id = ?";
	        try {	
	        	preparedStatement = connection.prepareStatement(sql);
	        	preparedStatement.setInt(1,Integer.parseInt(idFid.getText()));
	        	preparedStatement.executeUpdate();
	            
	            loadDate();
	            filtrarPorAutor();
	            clean();
	        
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    }

	  
	    ObservableList<LivroVO> LivroList = FXCollections.observableArrayList();
	    
	    // aqui que fiz o voltar => volta pra tela do gerente  => visão do gerente
	    @FXML
	    private void voltar() {
	    	try {
	    		System.out.println("voltando de livros pra tela de login");
				Telas.telaLogin();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadDate();
	}
	
	 
    
    private void loadDate() {
    	connection = DbConnect.getConnection();
       // filtrarPorAutor();
        
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	generoCol.setCellValueFactory(new PropertyValueFactory<>("genero"));
    	anoCol.setCellValueFactory(new PropertyValueFactory<>("ano"));
    	autorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    	idAutorCol.setCellValueFactory(new PropertyValueFactory<>("id_autor"));
    }
}
