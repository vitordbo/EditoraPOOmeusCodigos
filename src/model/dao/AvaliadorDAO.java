package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.AvaliadorVO;
import model.vo.UsuarioVO;

public class AvaliadorDAO<VO extends UsuarioVO> extends BaseDAO<VO> {
	
	@Override
	public void inserir(VO vo) throws SQLException {
			conn= getConnection();
			String sql = "insert into Avaliador (cpf, endereco, idUsuario) values (?,?,?)";
			PreparedStatement ptst;
			try {
				ptst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ptst.setString(1, vo.getCpf());
				ptst.setString(2, vo.getEndereco());
				ptst.setLong(3, vo.getUsuario().getId());
				ptst.execute();
				int affectedRows = ptst.executeUpdate();
				if(affectedRows == 0) {
					throws new SQLException('A inserção falhou!');
				}
				ResultSet generatedKeys = ptst.getGeneratedKeys();
				if(generatedKeys.next()){
					vo.setIDFunc(generatedKeys.getLong(1));
				} else {
					throws new SQLException('A inserção falhou!');
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		@Override
		public void remover(VO vo) throws SQLException {
			conn = getConnection();
			String sql = "delete from Avaliador where id = ?";
			PreparedStatement ptst;
			try {
				ptst = conn.prepareStatement(sql);
				ptst.setLong(1, vo.getId());
				ptst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		@Override
		public ResultSet listar(VO vo) throws SQLException {
			conn = getConnection();
			String sql = "select * from Avaliador";
			Statement st;
			ResultSet rs;
			
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
			
		}

		@Override
		public void editar(VO vo) throws SQLException {
			conn = getConnection();
			String sql = "update from Avaliador set cpf = ?, endereco = ?, idUsuario = ? where id = ?";
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
		public void atualizar(VO vo) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void deletar(VO vo) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public ResultSet listarPorId(VO vo) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ResultSet listarPorNome(VO entity) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ResultSet listar() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ResultSet listarPorEmail(VO entity) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void inserir(AvaliadorVO vo) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void atualizar(AvaliadorVO vo) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void deletar(AvaliadorVO vo) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public ResultSet listarPorId(AvaliadorVO vo) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ResultSet listarPorNome(AvaliadorVO entity) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ResultSet listarPorEmail(AvaliadorVO entity) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		@Override
		public ResultSet buscarporIdAvaliador(VO ava) {
			 String sql = "select * from avaliador where id_usuario = ?";  //ver se tem a chave estrangeira
	       	  PreparedStatement ptst;
	       	  ResultSet rs = null;
	       	  System.out.println(ava.getId()); // id pessoa?
	       	  
	       	  try {
	       		  ptst = getConnection().prepareStatement(sql);
	       		  ptst.setInt(1, ava.getId());
	       		  System.out.println(ptst);
	       		  rs = ptst.executeQuery();
	       		  
	       	  } catch (SQLException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}  
	       	  return rs;
	}
		@Override
		public ResultSet findbyNickname(AvaliadorVO entity) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ResultSet buscarporIdGerente(AvaliadorVO ger) {
			// TODO Auto-generated method stub
			return null;
		}


}