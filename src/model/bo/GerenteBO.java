package model.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.InsertException;
import model.dao.BaseInterDAO;
import model.dao.GerenteDAO;
import model.dao.UsuarioDAO;
import model.vo.AutorVO;
import model.vo.GerenteVO;
import model.vo.LivroVO;
import model.vo.UsuarioVO;

public class GerenteBO implements BaseInterBO<GerenteVO> {
	BaseInterDAO<GerenteVO> dao = new GerenteDAO();
	
	public void cadastrar(GerenteVO vo) throws InsertException {
		try {
			ResultSet rs = dao.listarPorNome(vo); // testar se já tem => não pode email repetido 
			if(rs.next()) {
				throw new InsertException("Impossivel cadastrar, pois já existe gerente cadastrado com esse email");
			}
			else {
				dao.inserir(vo);
				System.out.println("inserido com sucesso");
			}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
	}

	// transformar resultset em list 
	// terminar de consertar 
	public List<GerenteVO> buscarPorNome(GerenteVO vo) throws InsertException {
		List<GerenteVO> lista = new ArrayList<GerenteVO>();
		try {
			ResultSet rs = dao.listarPorNome(vo);
			if(!rs.next()) {
				throw new InsertException("Impossivel listar por nome, nome não existe");
			}
			else {
				rs = dao.listarPorNome(vo);
				while(rs.next()) {
					GerenteVO vo2 = new GerenteVO();
					vo2.setId(rs.getInt("id"));
					vo2.setEmail(rs.getString("email"));
					vo2.setNome(rs.getString("nome")); 
					vo2.setSenha(rs.getString("senha")); 
					lista.add(vo2);
				}
			}
		} catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}	

	public List<GerenteVO> buscarPorId(GerenteVO vo) throws InsertException {
		List<GerenteVO> lista = new ArrayList<GerenteVO>();
		try {
			ResultSet rs = dao.listarPorId(vo);
			if(!rs.next()) {
				throw new InsertException("Impossivel listar por id, id não cadastrado");
			}
			else {
				rs = dao.listarPorId(vo);
				while(rs.next()) {
					GerenteVO vo2 = new GerenteVO();
					vo2.setId(rs.getInt("id"));
					vo2.setEmail(rs.getString("email"));
					vo2.setNome(rs.getString("nome")); 
					vo2.setSenha(rs.getString("senha")); 
					lista.add(vo2);
				}
			}
		} catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
	}	

	
	// listar geral
	public  List<UsuarioVO> buscar() throws InsertException {  
		 List<UsuarioVO> lista = new ArrayList<UsuarioVO>();
		try {
			ResultSet rs = dao.listar();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar");
			}
			else {
				rs = dao.listar();
				while(rs.next()) { // listar completo 
					GerenteVO vo2 = new GerenteVO();
					vo2.setId(rs.getInt("id"));
					vo2.setNome(rs.getString("nome"));
					vo2.setEmail(rs.getString("email"));
					vo2.setNickname(rs.getString("nickname"));
					vo2.setSenha(rs.getString("senha"));  
					lista.add(vo2);
				}
			}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
		return lista;
		
	}

	// alterar 
	public void alterar(GerenteVO vo) throws InsertException {
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

	// remover
	public void remover(GerenteVO vo) throws InsertException {
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
	public void buscarPorTitulo(GerenteVO vo) throws InsertException {
		// TODO Auto-generated method stub	
	}

	@Override
	public void alterar(LivroVO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LivroVO> buscarLivro() throws InsertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buscarPorEmail(GerenteVO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioVO autenticar(UsuarioVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LivroVO cadastrarLivro(LivroVO vo) throws InsertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AutorVO> buscarAutor() throws InsertException {
		// TODO Auto-generated method stub
		return null;
	}

}
