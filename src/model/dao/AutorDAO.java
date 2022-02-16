package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.InsertException;
import model.vo.AutorVO;
import model.vo.GerenteVO;
import model.vo.UsuarioVO;

public class AutorDAO<VO extends AutorVO> extends BaseDAO<VO> {
	
	public void inserir(VO vo) throws SQLException {
			String sql = "insert into autor (nome,endereco,cpf,id,nickname,senha) values (?,?,?,?,?,?,?)";
			PreparedStatement ptst;
			
			try {
				ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ptst.setString(1, vo.getNome());
				ptst.setString(2, vo.getEndereco());
				ptst.setString(3, vo.getCpf());
				ptst.setInt(3, vo.getId());
				ptst.setString(4, vo.getNickname());
				ptst.setString(5, vo.getSenha());

				int affectedRows = ptst.executeUpdate();
			
				if(affectedRows == 0) {
			throw new SQLException("A inserção falhou!");
			}
				
			ResultSet generatedKeys = ptst.getGeneratedKeys();
			if(generatedKeys.next()){
				vo.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("A inserção falhou!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
			
				
	@Override
	public void deletar(VO vo) throws SQLException {
		String sql = "delete from autor where id = ?";
		PreparedStatement ptst;
      	 try {
				ptst = getConnection().prepareStatement(sql);
				ptst.setInt(1,vo.getId());
				ptst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    }    
	
	
	@Override
	public ResultSet listar(VO vo) throws SQLException {
		conn = getConnection();
		String sql = "select * from Autor";
		PreparedStatement st;
		ResultSet rs = null;
			
		try {
			st = conn.createStatement(sql);
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;	
	}

	@Override
	public void editar(VO vo) throws SQLException {
			conn = getConnection();
			String sql = "update from Autor set cpf = ?, endereco = ?, idUsuario = ? where id = ?";
			PreparedStatement ptst;
			try {
				ptst = conn.prepareStatement(sql);
				ptst.setString(1, vo.getCpf());
				ptst.setString(2, vo.getEndereco());
				ptst.setLong(3, vo.getUsuario().getId());
				ptst.setLong(5, vo.getId());
				ptst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	@Override
	public ResultSet listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResultSet findbyNickname(VO vo) throws SQLException {
		  String sql = "select * from autor where nickname = ?";  //"select nickname from usuario";
    	  PreparedStatement ptst;
    	  ResultSet rs = null;
    	  
    	  try {
    		  ptst = getConnection().prepareStatement(sql);
    		  ptst.setString(1, vo.getNickname());
    		  System.out.println(ptst);
    		  rs = ptst.executeQuery();
    		  
    	  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    	  return rs;
      }
	
	
	@Override
	public void atualizar(VO vo) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ResultSet listarPorId(VO vo) throws SQLException {
		 String sql = "select * from autor where id = ?";
      	  PreparedStatement ptst;
      	  ResultSet rs = null;
      	  
      	  try {
      		  ptst = getConnection().prepareStatement(sql);
      		  ptst.setInt(1, vo.getId());
      		  System.out.println(ptst);
      		  rs = ptst.executeQuery();
      		  
      	  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
      	  return rs;
        }
	
	@Override
	public ResultSet listarPorNome(VO entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarPorEmail(VO entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public ResultSet buscarporIdAutor(VO aut) {
		 String sql = "select * from autor where id_usuario = ?";  //ver se tem a chave estrangeira
       	  PreparedStatement ptst;
       	  ResultSet rs = null;
       	  System.out.println(aut.getId()); // id pessoa?
       	  
       	  try {
       		  ptst = getConnection().prepareStatement(sql);
       		  ptst.setInt(1, aut.getId());
       		  System.out.println(ptst);
       		  rs = ptst.executeQuery();
       		  
       	  } catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}  
       	  return rs;
}
	@Override
	public ResultSet buscarporIdGerente(VO ger) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarPorSenha(VO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarVisao() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarSeteDias() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarHoje() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarDuasSemanas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarTresSemanas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarMes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet listarTudo() {
		// TODO Auto-generated method stub
		return null;
	}

}