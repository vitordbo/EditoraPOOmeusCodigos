package model.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.AutenticarExceptions;
import exceptions.InsertException;
import model.dao.AutorDAO;
import model.dao.BaseInterDAO;
import model.dao.GerenteDAO;
import model.dao.AvaliadorDAO;
import model.dao.UsuarioDAO;
import model.vo.AutorVO;
import model.vo.AvaliadorVO;
import model.vo.GerenteVO;
import model.vo.LivroVO;
import model.vo.UsuarioVO;

public class UsuarioBO<VO extends UsuarioVO> implements BaseInterBO<UsuarioVO> {
	
	static private BaseInterDAO<UsuarioVO> dao = new UsuarioDAO<UsuarioVO>(); // usuDAO não dao 
	static private BaseInterDAO<GerenteVO> gerDAO = new GerenteDAO<GerenteVO>();
	static private BaseInterDAO<AutorVO> autorDAO = new AutorDAO();
	static private BaseInterDAO<AvaliadorVO> avaliadorDAO = new AvaliadorDAO();
	
	public UsuarioVO autenticar (UsuarioVO vo) throws AutenticarExceptions {
		try {
			ResultSet usuRS = dao.listarPorSenha(vo);
			
			// existe e senha ok => descobrir se é responsavel ou grerente
			if(usuRS.next()) { 
				if(usuRS.getString("senha").equals(vo.getSenha())) { 
					System.out.println("dentro da tabela usuario");
					
					GerenteVO ger = new GerenteVO(); 
					ger.setId(usuRS.getInt("id_usu"));
					ResultSet gerRS = gerDAO.buscarporIdGerente(ger);  //ger
					
					if(gerRS.next()) {
						// é um gerente 
						ger.setNickname(vo.getNickname());  //vo ou ger?
						ger.setEmail(gerRS.getString("email"));  
						ger.setId(gerRS.getInt("id"));
						ger.setNome(gerRS.getString("nome"));
						ger.setSenha(gerRS.getString("senha"));
					    return ger;		
					}
					
					
					AutorVO aut = new AutorVO(null, null, null, 0);
					aut.setId(usuRS.getInt("id_usu"));
					ResultSet autorRS = autorDAO.buscarporIdAutor(aut);
					
					if(autorRS.next()) {
						aut.setNickname(vo.getNickname());
						aut.setEmail(autorRS.getString("email"));
						aut.setId(autorRS.getInt("id"));
						aut.setNome(autorRS.getString("nome"));
						aut.setSenha(autorRS.getString("senha"));
						return aut;
						}
					
					AvaliadorVO ava = new AvaliadorVO(null, null, null, 0);
					ava.setId(usuRS.getInt("id_usu"));
					ResultSet avaRS = avaliadorDAO.buscarporIdAvaliador(ava);
					
					if(avaRS.next()) {
						ava.setNickname(vo.getNickname());
						ava.setEmail(avaRS.getString("email"));
						ava.setId(avaRS.getInt("id"));
						ava.setNome(avaRS.getString("nome"));
						ava.setSenha(avaRS.getString("senha"));
						return ava;
					}
				}
			}
						else {
							throw new AutenticarExceptions();
							}
				}
				
			catch(SQLException e) {
				e.printStackTrace();
				throw new AutenticarExceptions();
			}
					
		return vo;
	}

	
	
	public void cadastrar(UsuarioVO vo) throws InsertException {
		try {
			ResultSet rs = dao.listarPorEmail(vo); // testar se já tem => não pode email repetido 
			if(rs.next()) {
				throw new InsertException("Impossivel cadastrar, pois já existe usuario cadastrado com esse email");
			}
			else {
				dao.inserir(vo);
			}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
	}

	// transformar resultset em list 
	// terminar de consertar 
	public List<UsuarioVO> buscarPorNome(UsuarioVO vo) throws InsertException {
		List<UsuarioVO> lista = new ArrayList<UsuarioVO>();
		try {
			ResultSet rs = dao.listarPorNome(vo);
			if(!rs.next()) {
				throw new InsertException("Impossivel listar por nome,nome não existe");
			}
			else {
				rs = dao.listarPorNome(vo); //listar geral
				while(rs.next()) {
					UsuarioVO vo2 = new UsuarioVO(); 
					vo2.setId(rs.getInt("id_usu"));
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

	public List<UsuarioVO> buscarPorId(UsuarioVO vo) throws InsertException {
		List<UsuarioVO> lista = new ArrayList<UsuarioVO>();
		try {
			ResultSet rs = dao.listarPorId(vo);
			if(!rs.next()) {
				throw new InsertException("Impossivel listar por id, id não cadastrado");
			}
			else {
				rs = dao.listarPorId(vo);
				while(rs.next()) {
					UsuarioVO vo2 = new UsuarioVO(); 
					vo2.setId(rs.getInt("id_usu"));
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

	
	public List<UsuarioVO> buscar() throws InsertException {  //listar tudo 
		List<UsuarioVO> lista = new ArrayList<UsuarioVO>();
		try {
			ResultSet rs = dao.listar();
			if(!rs.next()) {
				throw new InsertException("Impossivel listar, nada cadastrado");
			}
			else {
				rs = dao.listar();
				while(rs.next()) { // listar completo 
					UsuarioVO vo2 = new UsuarioVO(); 
					vo2.setId(rs.getInt("id_usu"));
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
	public void alterar(UsuarioVO vo) throws InsertException { //altera nome com base no id 
		try {
			ResultSet rs = dao.listar();
			if(rs.next()) {
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
	public void remover(UsuarioVO vo) throws InsertException {
		try {
			ResultSet rs = dao.listarPorId(vo);
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
	public void alterar(LivroVO vo) throws InsertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LivroVO> buscarLivro() throws InsertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buscarPorEmail(UsuarioVO vo) throws InsertException {
		// TODO Auto-generated method stub
		
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
