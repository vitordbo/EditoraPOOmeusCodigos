package model.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.AutenticarExceptions;
import exceptions.InsertException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dao.BaseInterDAO;
import model.dao.LivroDAO;
import model.dao.UsuarioDAO;
import model.vo.AutorVO;
import model.vo.LivroVO;
import model.vo.UsuarioVO;

public class LivroBO implements BaseInterBO<UsuarioVO> {
	BaseInterDAO<LivroVO> dao = new LivroDAO<LivroVO>();

	public LivroVO cadastrarLivro(LivroVO vo) throws InsertException { 
		try {
			ResultSet rs = dao.listarPorNome(vo); // testar se já tem => não pode repetir nome
			if(rs.next()) {
				throw new InsertException("Impossivel cadastrar, pois já existe um livro com esse nome");
			}
			else {
				dao.inserir(vo);
			}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		
		return vo; // colocado depois
	}

	public List<LivroVO> buscarPorNome(LivroVO vo) throws InsertException { //autor
		List<LivroVO> lista = new ArrayList<LivroVO>();
		LivroVO vo2 = new LivroVO(null, null, null, null, null, null, null, null, null);
		try {
			ResultSet rs = dao.listarPorNome(vo);
			if(!rs.next()) {
				throw new InsertException("Impossivel listar por titulo");
			}
			else {
				rs = dao.listar();
				while(rs.next()) {
					vo2.setTitulo(rs.getString("titulo")); 
					lista.add(vo2);
				}
			}
		} catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}	

	public List<LivroVO> buscarPorId(LivroVO vo) throws InsertException {
		List<LivroVO> lista = new ArrayList<LivroVO>();
		LivroVO vo2 = new LivroVO(null, null, null, null, null, null, null, null, null);
		try {
			ResultSet rs = dao.listarPorId(vo);
			if(!rs.next()) {
				throw new InsertException("Impossivel listar por id");
			}
			else {
				rs = dao.listar();
				while(rs.next()) {
					vo2.setId(rs.getString("id")); 
					lista.add(vo2);
				}
			}
		} catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}	
	
	// metodo ok para a tabela que só mostra os livros de cada avaliador
	//=> ver se fica ok na tabela que altera 
	public ObservableList<LivroVO> buscarLivro() throws InsertException {
		ObservableList<LivroVO> lista = FXCollections.observableArrayList();
		
		try {
			ResultSet rs = dao.listar();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar");
			}
			else { 
				rs = dao.listar();
				while(rs.next()) { // listar completo 
					System.out.println("Entrando aqui no bo para pegar livroVO");
					lista.add(new LivroVO(
		    					rs.getString("titulo"),
		    					rs.getString("genero"),
		    					rs.getString("ano"),
		    					rs.getString("autor"),
		    					rs.getString("id"), 
		    					rs.getString("status"),
		    					rs.getInt("id_avaliador"),
		    					rs.getString("nome_avaliador"),
		    					rs.getInt("id_autor")));
					}
				}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}
	
	public ObservableList<LivroVO> buscarVisao() throws InsertException {
		ObservableList<LivroVO> lista = FXCollections.observableArrayList();
		
		try {
			ResultSet rs = dao.listarVisao();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar");
			}
			else { 
				rs = dao.listarVisao();
				while(rs.next()) { // listar completo 
					System.out.println("Entrando aqui no bo para pegar livroVO");
					lista.add(new LivroVO(
		    					rs.getString("titulo"),
		    					null,
		    					null,
		    					null,
		    					rs.getString("id"), 
		    					rs.getString("status"),
		    					rs.getInt("id_avaliador"),
		    					rs.getString("nome_avaliador"), null));
					}
				}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}
	
	@Override
	public void alterar(LivroVO vo) throws InsertException {
		try {
			ResultSet rs = dao.listar();
			if(!rs.next()) {
				throw new InsertException("Impossivel alterar");
			}
			else {
				dao.atualizar(vo);
			}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		
	}
	
	public void remover(LivroVO vo) throws InsertException {
		try {
			ResultSet rs = dao.listar();
			if(!rs.next()) {
				throw new InsertException("Impossivel deletar");
			}
			else {
				dao.deletar(vo);
			}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		
	}

	
	@Override
	public void buscarPorTitulo(UsuarioVO vo) throws InsertException { 
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UsuarioVO> buscarPorNome(UsuarioVO vo) throws InsertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cadastrar(UsuarioVO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UsuarioVO> buscarPorId(UsuarioVO vo) throws InsertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioVO> buscar() throws InsertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(UsuarioVO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(UsuarioVO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscarPorEmail(UsuarioVO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioVO autenticar(UsuarioVO vo) throws AutenticarExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AutorVO> buscarAutor() throws InsertException {
		// TODO Auto-generated method stub
		return null;
	} 

}
