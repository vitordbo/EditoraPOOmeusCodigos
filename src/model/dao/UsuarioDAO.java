package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.UsuarioVO;

public class UsuarioDAO<VO extends UsuarioVO> extends BaseDAO<VO> { 
        
	     // inserir novo usuario // atualizado
         public void inserir(VO vo) throws SQLException  {
        	 String sql = "insert into usuario (nome,id_usu,nickname, senha) values (?,?,?,?)";
        	 PreparedStatement ptst;
        	 try {
				ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ptst.setString(1,vo.getNome());
				ptst.setInt(2, vo.getId());
				ptst.setString(3, vo.getNickname());
				ptst.setString(4, vo.getSenha());
				
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
        	  String sql = "delete from usuario where id_usu = ?"; 
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
          
       
          public void atualizar(VO vo) {
        	// não é mais insert => agora é update 
        	  String sql = "update usuario set nome = ? where id_usu = ?";  
            	 PreparedStatement ptst;
            	 try {
            		ptst = getConnection().prepareStatement(sql);
    				ptst.setString(1,vo.getNome()); // vai receber um nome primerio para editar 
    				ptst.setInt(2,vo.getId());
    				ptst.executeUpdate();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}  
          }
          

            // atualizado
          public ResultSet listar() {
        	  String sql = "select * from usuario";
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
        	  String sql = "select * from usuario";
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
        	  String sql = "select * from usuario where nome = ?";
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
          
          // teste novo 
          public ResultSet findbyNickname(VO vo) {
        	  String sql = "select * from usuario where nickname = ?";  //"select nickname from usuario";
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
          
          // testar esse metodo 
          public ResultSet buscaPorLogin(VO vo) {
        	  String sql = "select p.id_usu as UsuarioId,p.nome,p.email,p.nickname,p.senha, u.id_usuario, u.senha,u.nickname"
        	  		+ "from usuario p, gerente u where u.nickanme = ? and p.id_usu = u.id_usuario";
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
        	  
          // atualizado
          public ResultSet listarPorId(VO vo) {
        	  String sql = "select id_usu from usuario where nickname = ?"; // select * where id_usu
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
          
          
          // atualizado
          public ResultSet listarPorEmail(VO vo) {
        	  String sql = "select * from usuario where email = ?";
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
          
          // atualizado
          public ResultSet listarPorNickname(VO vo) {
        	  String sql = "select * from usuario where nickname = ?";
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
          
          // atualizado
          public ResultSet listarPorSenha(VO vo) {
        	  String sql = "select * from usuario where senha = ?";
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
		public ResultSet buscarporIdGerente(VO ger) {
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