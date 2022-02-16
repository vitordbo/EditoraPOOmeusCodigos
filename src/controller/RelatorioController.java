package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import helpers.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bo.LivroBO;
import model.bo.RelatorioBO;
import model.dao.BaseInterDAO;
import model.dao.RelatorioDAO;
import model.vo.LivroVO;
import model.vo.RelatorioVO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import view.Telas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.DocumentException;
import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;

public class RelatorioController implements Initializable {

	//@FXML private ChoiceBox<String> choiceBox;
	
	//@FXML private Label choiceBoxLabel;
	
	@FXML
    private TableView<RelatorioVO> tabelaRelatorios;

    @FXML
    private TableColumn<RelatorioVO, String> NomeAvaliadorCol;

    @FXML
    private TableColumn<RelatorioVO, String> StatusCol;

    @FXML
    private TableColumn<RelatorioVO, Date> dataCol;
    
    @FXML
    private TableColumn<RelatorioVO, String> tituloCol;
    
    @FXML
    private Button hoje;
    
    @FXML
    private Button umaSemana;
    
    @FXML
    private Button duasSemanas;
    
    @FXML
    private Button tresSemanas;

    @FXML
    private Button umMes;
    
    @FXML 
    private Label erroRelatorio;
    
    @FXML
    private Button pdfButton;

    @FXML
    private Button statusAntigos;   // usando regra para mostrar toda a alteração

    @FXML
    void hojeRelatorio(MouseEvent event) {
    	// corrigir isso no bo 
    	try{
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(false);
    		tabelaRelatorios.setItems(bo.buscarRelatorioHoje());
    	}catch (Exception e) {
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(true);
			e.printStackTrace();
    	}
  }
    @FXML
    void umaSemanaRelatorio(MouseEvent event) {
    	try{
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(false);
    	tabelaRelatorios.setItems(bo.buscarRelatorioUmaSemana());
    	}catch (Exception e) {
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(true);
			e.printStackTrace();
    	}
    }

    @FXML 
    void duasSemanasRelatorio(MouseEvent event) {
    	try {
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
		erroRelatorio.setVisible(false);
    	tabelaRelatorios.setItems(bo.buscarRelatorioDuasSemanas());
    	}catch (Exception e) {
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(true);
			e.printStackTrace();
    	}
    }
    
    @FXML 
    void tresSemanasRelatorio(MouseEvent event) {
    	try {
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(false);
    	tabelaRelatorios.setItems(bo.buscarRelatorioTresSemanas());
    	}catch (Exception e) {
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(true);
			e.printStackTrace();
    	}
    }
    
    @FXML
    void mesRelatorio(MouseEvent event) {
    	try {
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(false);
    	tabelaRelatorios.setItems(bo.buscarRelatorioMes());
    	}catch (Exception e) {
    		erroRelatorio.setText("Relatório indisponível, por favor , selecione outro período");
    		erroRelatorio.setVisible(true);
			e.printStackTrace();
    	}
    }
    
    @FXML
    void voltar(MouseEvent event) {
    	try {
    		System.out.println("voltando da tela relatorio para a principal gerente");
			Telas.telaPrincipalGerente();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void mostraRegra(MouseEvent event) {
    	try {
			Telas.telaTabelaRegras();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("erro no botão");
			e.printStackTrace();
		}
    }
    @FXML
	public void pdf(MouseEvent event) {
    	BaseInterDAO<RelatorioVO> dao = new RelatorioDAO<RelatorioVO>();
    	ResultSet rs = dao.listarTudo();
   
    		try {
    			TableView<RelatorioVO> tableView = new TableView<RelatorioVO>();
    			
    			TableColumn<RelatorioVO, String> col1 = new TableColumn<>("Data");  //essas são a do pdf
    			col1.setCellValueFactory(new PropertyValueFactory<>("data"));    // essas são fxml
    			TableColumn<RelatorioVO, String> col2 = new TableColumn<>("Status");
    			col2.setCellValueFactory(new PropertyValueFactory<>("status"));
    			TableColumn<RelatorioVO, String> col3 = new TableColumn<>("Nome Avalaidor");
    			col3.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    			TableColumn<RelatorioVO, String> col4 = new TableColumn<>("Titulo");
    			col4.setCellValueFactory(new PropertyValueFactory<>("nome_ava"));
    			
    			
    			tableView.getColumns().add(col1);
    			tableView.getColumns().add(col2);
    			tableView.getColumns().add(col3);
    			tableView.getColumns().add(col4);
    			
    						// pegando os dados 
    						while(rs.next()) { 
    							System.out.println("Entrando aqui no bo para pegar Relatorio");
    							tableView.getItems().add(new RelatorioVO(
    				    					rs.getDate("data"),   
    				    					rs.getString("status_novo"),
    				    					rs.getString("titulo_livro"),
    				    					rs.getString("nome_avaliador")));
    							}
    		
    			
    				pdfButton.setOnAction(actionEvent -> {
    				//criando pagina e pdf
    				File out = new File("RelatorioCompleto.pdf");
    				FileOutputStream fos;
    				try {
    					fos = new FileOutputStream(out);
    					PDF pdf = new PDF(fos);
    					Page page = new Page(pdf, A4.PORTRAIT);
    					
    					// fonte para o nome das colunas
    					Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD);
    					
    					// fonte para os dados
    					Font f2 = new Font(pdf, CoreFont.HELVETICA);
    					
    					//tabela do pdf
    					Table table = new Table();
    					List<List<Cell>> tableData = new ArrayList<List<Cell>>();
    					
    					// row da tabela
    					List<Cell> tableRow = new ArrayList<Cell>();
    					
    					// criando header e colocando no row
    					Cell cell = new Cell(f1, col1.getText());
    					tableRow.add(cell);
    					
    					cell = new Cell(f1, col2.getText());
    					tableRow.add(cell);
    					
    					cell = new Cell(f1, col3.getText());
    					tableRow.add(cell);
    					
    					cell = new Cell(f1, col4.getText());
    					tableRow.add(cell);
    					
    					//adicionando row na tabela
    					tableData.add(tableRow);
    					
    					// pegando a table view e criando um row para cada e adicionando 
    					List<RelatorioVO> items = tableView.getItems();
    					
    					for (RelatorioVO comm : items) {
    						Cell country = new Cell(f2, comm.getData().toString());
    						Cell pop = new Cell(f2, comm.getStatus());
    						Cell prod = new Cell(f2, comm.getNome_ava());
    						Cell cons = new Cell(f2, comm.getTitulo());
    						
    						tableRow = new ArrayList<Cell>();
    						tableRow.add(country);
    						tableRow.add(pop);
    						tableRow.add(prod);
    						tableRow.add(cons);
    						
    						// adicionando row na tabela
    						tableData.add(tableRow);
    					}
    					
    					table.setData(tableData);
    					table.setPosition(70f, 60f);
    					table.setColumnWidth(0, 100f);
    					table.setColumnWidth(1, 100f);
    					table.setColumnWidth(2, 140f);
    					table.setColumnWidth(3, 140f);
    					
    					// nova pagina se necessario
    					while (true) {
    						table.drawOn(page);
    						if (!table.hasMoreData()) {
    							table.resetRenderedPagesCount();
    							break;
    						}
    						page = new Page(pdf, A4.PORTRAIT);
    					}
    					
    					pdf.close();
    					fos.flush();
    					fos.close();					
    				} catch (FileNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}

    				
    				System.out.println("Saved to " + out.getAbsolutePath());				
    			});

    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    RelatorioBO bo = new RelatorioBO();
  
    String query = null;
    Connection connection = null;
    LivroVO livro = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;
       
 
    private void loadDate() {
    	connection = DbConnect.getConnection();
    	// usando o gatilho
    	dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
    	StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    	tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	NomeAvaliadorCol.setCellValueFactory(new PropertyValueFactory<>("nome_ava"));
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//itemsDaChoiceBox();
		loadDate();
	}

}
