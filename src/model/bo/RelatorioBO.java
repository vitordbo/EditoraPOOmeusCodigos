package model.bo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import exceptions.AutenticarExceptions;
import exceptions.InsertException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.vo.AutorVO;
import model.vo.AvaliadorVO;
import model.vo.LivroVO;
import model.vo.RelatorioVO;
import model.dao.AutorDAO;
import model.dao.AvaliadorDAO;
import model.dao.BaseInterDAO;
import model.dao.LivroDAO;
import model.dao.RelatorioDAO;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;


public class RelatorioBO implements BaseInterBO<RelatorioVO> {
	BaseInterDAO<RelatorioVO> dao = new RelatorioDAO<RelatorioVO>();


	public ObservableList<RelatorioVO> buscarRelatorioGeral() throws InsertException {
		ObservableList<RelatorioVO> lista = FXCollections.observableArrayList();
		
		lista.clear(); // limpar do anteiror
		try {
			ResultSet rs = dao.listarTudo();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar Relatorio");
			}
			else { 
				rs = dao.listarTudo(); //Date data, String status, String titulo, String nome_ava) 
				while(rs.next()) { // listar completo 
					System.out.println("Entrando aqui no bo para pegar Relatorio");
					lista.add(new RelatorioVO(
		    					rs.getDate("data"),    // aqui do bd ou no vo?
		    					rs.getString("status_novo"),
		    					rs.getString("titulo_livro"),
		    					rs.getString("nome_avaliador")));
					}
				}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());  // ver isso => corrigir não avisar 
		}
		return lista;
	}
	
	public ObservableList<RelatorioVO> buscarRelatorioHoje() throws InsertException {
		ObservableList<RelatorioVO> lista = FXCollections.observableArrayList();
		
		lista.clear(); // limpar do anteiror
		try {
			ResultSet rs = dao.listarHoje();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar Relatorio");
			}
			else { 
				rs = dao.listarHoje(); //Date data, String status, String titulo, String nome_ava) 
				while(rs.next()) { // listar completo 
					System.out.println("Entrando aqui no bo para pegar Relatorio");
					lista.add(new RelatorioVO(
		    					rs.getDate("data"),    // aqui do bd ou no vo?
		    					rs.getString("status_novo"),
		    					rs.getString("titulo_livro"),
		    					rs.getString("nome_avaliador")));
					}
				}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());  // ver isso => corrigir não avisar 
		}
		return lista;
	}

		
	public ObservableList<RelatorioVO> buscarRelatorioUmaSemana() throws InsertException {
			ObservableList<RelatorioVO> lista = FXCollections.observableArrayList();
			
			lista.clear();
			try {
				ResultSet rs = dao.listarSeteDias();
				if(!rs.next()) {
					throw new InsertException("Impossivel listar Relatorio");
				}
				else { 
					rs = dao.listarSeteDias(); //Date data, String status, String titulo, String nome_ava) 
					while(rs.next()) { // listar completo 
						System.out.println("Entrando aqui no bo para pegar Relatorio");
						lista.add(new RelatorioVO(
			    					rs.getDate("data"),    // aqui do bd ou no vo?
			    					rs.getString("status_novo"),
			    					rs.getString("titulo_livro"),
			    					rs.getString("nome_avaliador")));
						}
					}
			}
			catch(SQLException e) {
				throw new InsertException(e.getMessage());
			}
			return lista;
		}
	
	
	public ObservableList<RelatorioVO> buscarRelatorioDuasSemanas() throws InsertException {
		ObservableList<RelatorioVO> lista = FXCollections.observableArrayList();
		
		lista.clear();
		try {
			ResultSet rs = dao.listarDuasSemanas();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar Relatorio");
			}
			else { 
				rs = dao.listarDuasSemanas();  
				while(rs.next()) { 
					System.out.println("Entrando aqui no bo para pegar Relatorio");
					lista.add(new RelatorioVO(
		    					rs.getDate("data"),    // aqui do bd ou no vo?
		    					rs.getString("status_novo"),
		    					rs.getString("titulo_livro"),
		    					rs.getString("nome_avaliador")));
					}
				}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}
	
	public ObservableList<RelatorioVO> buscarRelatorioTresSemanas() throws InsertException {
		ObservableList<RelatorioVO> lista = FXCollections.observableArrayList();
		
		lista.clear();
		try {
			ResultSet rs = dao.listarTresSemanas();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar Relatorio");
			}
			else { 
				rs = dao.listarTresSemanas(); //Date data, String status, String titulo, String nome_ava) 
				while(rs.next()) { // listar completo 
					System.out.println("Entrando aqui no bo para pegar Relatorio");
					lista.add(new RelatorioVO(
		    					rs.getDate("data"),    // aqui do bd ou no vo?
		    					rs.getString("status_novo"),
		    					rs.getString("titulo_livro"),
		    					rs.getString("nome_avaliador")));
					}
				}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}
	
	public ObservableList<RelatorioVO> buscarRelatorioMes() throws InsertException {
		ObservableList<RelatorioVO> lista = FXCollections.observableArrayList();
		
		lista.clear();
		try {
			ResultSet rs = dao.listarMes();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar Relatorio");
			}
			else { 
				rs = dao.listarMes(); //Date data, String status, String titulo, String nome_ava) 
				while(rs.next()) { // listar completo 
					System.out.println("Entrando aqui no bo para pegar Relatorio");
					lista.add(new RelatorioVO(
		    					rs.getDate("data"),    // aqui do bd ou no vo?
		    					rs.getString("status_novo"),
		    					rs.getString("titulo_livro"),
		    					rs.getString("nome_avaliador")));
					}
				}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}
	
/*
	  public static String selecionarPath() {
	    JFileChooser chooser;

	    chooser = new JFileChooser();
	    chooser.setDialogTitle("Selecione o local para salvar o PDF");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	      return chooser.getCurrentDirectory().getPath();
	    } else {
	      return null;
	    }
	  }
	  
	public Document gerarPDF(String nomePdf) throws FileNotFoundException, DocumentException, IOException {
		String path = selecionarPath();
	    Document documento = new Document();

	    PdfWriter.getInstance(documento, new FileOutputStream(path + "/" + nomePdf + ".pdf"));

	    documento.open();

	    Paragraph titulo = new Paragraph("Relatório");
	    titulo.setAlignment(Element.ALIGN_CENTER);
	    documento.add(titulo);
	    documento.add(Chunk.NEWLINE);

	    return documento;
	  }
	
	
	public void gerarRelatorioGeral() throws DocumentException, SQLException, IOException, NullPointerException {		    
			List<RelatorioVO> relatorios;
			  
			    //if (dataInicial != null && dataFinal != null) {
			   //   alugueis = this.buscarPorIntervaloDeDias(dataInicial, dataFinal);
			   // } else {
			relatorios = this.buscarRelatorioGeral();
			   // }

			    LocalDateTime dataHoraAtual = LocalDateTime.now();
			    DateTimeFormatter formatadorNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
			    DateTimeFormatter formatadorDataAluguel = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    Document relatorio = gerarPDF("RelatorioGeral-Bibly" + dataHoraAtual.format(formatadorNomeArquivo));

			    Integer totalDeAlugueis = relatorios.size();

			    if (totalDeAlugueis <= 0) {
			      relatorio.close();
			      return;
			    }

			    for (RelatorioVO relatorioteste : relatorios) {
			      relatorio.add(Chunk.NEWLINE);
			      relatorio.add(new Paragraph("  -Data: " + relatorioteste.getData()));
			      relatorio.add(new Paragraph("  -Status Atual: " + relatorioteste.getStatus()));
			      relatorio.add(new Paragraph("  -Titulo: " + relatorioteste.getTitulo()));
			      relatorio.add(new Paragraph("  -Nome Avaliador: " + relatorioteste.getNome_ava()));
			    }

			    relatorio.close();
			  }
		*/
		
		@Override
		public void cadastrar(RelatorioVO vo) throws InsertException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public LivroVO cadastrarLivro(LivroVO vo) throws InsertException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<UsuarioVO> buscarPorNome(RelatorioVO vo) throws InsertException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<UsuarioVO> buscarPorId(RelatorioVO vo) throws InsertException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void buscarPorTitulo(RelatorioVO vo) throws InsertException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void buscarPorEmail(RelatorioVO vo) throws InsertException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<UsuarioVO> buscar() throws InsertException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void alterar(RelatorioVO vo) throws InsertException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void alterar(LivroVO vo) throws InsertException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void remover(RelatorioVO vo) throws InsertException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<LivroVO> buscarLivro() throws InsertException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public UsuarioVO autenticar(UsuarioVO vo) throws AutenticarExceptions {
			// TODO Auto-generated method stub
			return null;
		}	


}
