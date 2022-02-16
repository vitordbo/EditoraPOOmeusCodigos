package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.GerenteVO;
import model.vo.UsuarioVO;

public class GerenteDAO<VO extends UsuarioVO> extends BaseDAO<VO> {
	     
    public void inserir(GerenteVO vo) throws SQLException  {
   	 try {
   	   	 String sql =  "insert into gerente (nome,email,senha,nickname) values (?,?,?,?)";
   	   	 PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setString(1,vo.getNome());
			ptst.setString(2,vo.getEmail());
			ptst.setString(3, vo.getSenha());
			ptst.setString(4, vo.getNickname());
			
			int affectedRows = ptst.executeUpdate();
			
			if(affectedRows == 0) {
				throw new SQLException("A inserção falhou, nenhuma linha foi modificada");
			}
	
			ResultSet generatedKeys = ptst.getGeneratedKeys();
			if(generatedKeys.next()) {
				vo.setId(generatedKeys.getInt(1)); 
			}
			else {
				throw new SQLException("A inserção falhou, nenhum id foi retornado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
   
 
    public void deletar(VO vo) {
  	  String sql = "delete from gerente where id = ?";
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
    
  
    //editando o nome com base no id  // atualizado
    public void atualizar(VO vo) {
  	// não é mais insert => agora é update 
  	  String sql = "update gerente set nome = ? where id = ?";  
      	 PreparedStatement ptst;
      	 try {
      		ptst = getConnection().prepareStatement(sql);
				ptst.setString(1,vo.getNome()); 
				ptst.setInt(2,vo.getId());
				ptst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    }
      	 
         // atualizado
         public ResultSet listar() {
       	  String sql = "select * from gerente";
       	  Statement st;
       	  ResultSet rs = null;
       	  
       	  try {
       		  st =  getConnection().createStatement();
       		  rs = st.executeQuery(sql);
       	  } catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}  
       	  return rs;
         }

         // atualizado
         public ResultSet listar2() {
       	  String sql = "select * from gerente";
       	  PreparedStatement ptst;
       	  ResultSet rs = null;
       	  
       	  try {
       		  ptst = getConnection().prepareStatement(sql);
       		  System.out.println(ptst);
       		  rs = ptst.executeQuery(sql);
       		  
       	  } catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}  
       	  return rs;
         }
         
         // atualizado
         public ResultSet listarPorNome(VO vo) {
       	  String sql = "select * from gerente where nome = ?";
       	  PreparedStatement ptst;
       	  ResultSet rs = null;
       	  
       	  try {
       		  ptst = getConnection().prepareStatement(sql);
       		  ptst.setString(1, vo.getNome());
       		  rs = ptst.executeQuery();
       		  
       	  } catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}  
       	  return rs;
         }
         
         public ResultSet listarPorId(VO vo) {
       	  String sql = "select * from gerente where id = ?";
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
         
   
         public ResultSet listarPorEmailGer(VO vo) {
       	  String sql = "select * from gerente where email = ?";
       	  PreparedStatement ptst;
       	ResultSet rs = null;
       	  try {
       		  ptst = getConnection().prepareStatement(sql);
       		  ptst.setString(1, vo.getEmail());
       		  rs = ptst.executeQuery();
       		  
       	  } catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}  
       	  return rs;
         }
         
      
         public ResultSet listarPorNickname(VO vo) {
       	  String sql = "select * from gerente where nickname = ?";
       	  PreparedStatement ptst;
       	  ResultSet rs = null;
       	  
       	  try {
       		  ptst = getConnection().prepareStatement(sql);
       		  ptst.setString(1, vo.getNickname());
       		  rs = ptst.executeQuery();
       		  
       	  } catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}  
       	  return rs;
         }
         
      
         public ResultSet listarPorSenha(VO vo) {
       	  String sql = "select * from gerente where senha = ?";
       	  PreparedStatement ptst;
       	  ResultSet rs = null;
       	  
       	  try {
       		  ptst = getConnection().prepareStatement(sql);
       		  ptst.setString(1, vo.getSenha());
       		  rs = ptst.executeQuery();
       		  
       	  } catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}  
       	  return rs;
         }


		@Override
		public ResultSet listarPorEmail(VO entity) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void inserir(VO vo) throws SQLException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public ResultSet findbyNickname(VO vo) throws SQLException {
			 String sql = "select * from gerente where nickname = ?";  //"select nickname from usuario";
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
		
		// só VO ou gerenteVO que vai receber? olhar de baixo 
		public ResultSet buscarporIdGerente(VO ger) {
			 String sql = "select * from gerente where id_usuario = ?";  //ver se tem a chave estrangeira
	       	  PreparedStatement ptst;
	       	  ResultSet rs = null;
	       	  System.out.println(ger.getId()); // id pessoa?
	       	  
	       	  try {
	       		  ptst = getConnection().prepareStatement(sql);
	       		  ptst.setInt(1, ger.getId());
	       		  System.out.println(ptst);
	       		  rs = ptst.executeQuery();
	       		  
	       	  } catch (SQLException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}  
	       	  return rs;
		
		}
}
