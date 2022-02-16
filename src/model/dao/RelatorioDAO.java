package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.vo.RelatorioVO;

public class RelatorioDAO <VO extends RelatorioVO> extends BaseDAO<VO>{

	
	  // atualizado
    public ResultSet listarTudo() {  
  	  String sql = "select * from modificacao_status";  // 21 dias do dia atual 
  	  Statement st;
  	  ResultSet rs = null;
  	
  	  try {
  		  System.out.println("executando o select * from modificacao_status no dao do relatorio ");
  	  	  st =  getConnection().createStatement();
  	  	  rs = st.executeQuery(sql);
  	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  	  return rs;
    }
	 // atualizado
    public ResultSet listarHoje() {  
  	  String sql = "select * from modificacao_status where data = current_date"; 
  	  Statement st;
  	  ResultSet rs = null;
  	
  	  try {
  		  System.out.println("executando o select * from modificacao_status no dao do relatorio ");
  	  	  st =  getConnection().createStatement();
  	  	  rs = st.executeQuery(sql);
  	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  	  return rs;
    }
	
	 // atualizado
    public ResultSet listarSeteDias() {  //SELECT Table.date FROM Table WHERE date > current_date - 10;
  	  String sql = "select * from modificacao_status where data > current_date - 7";  // sete dias do dia atual 
  	  Statement st;
  	  ResultSet rs = null;
  	
  	  try {
  		  System.out.println("executando o select * from modificacao_status no dao do relatorio ");
  	  	  st =  getConnection().createStatement();
  	  	  rs = st.executeQuery(sql);
  	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  	  return rs;
    }
	
    // atualizado
    public ResultSet listarDuasSemanas() {  //SELECT Table.date FROM Table WHERE date > current_date - 10;
  	  String sql = "select * from modificacao_status where data > current_date - 14";  // 14 dias do dia atual 
  	  Statement st;
  	  ResultSet rs = null;
  	
  	  try {
  		  System.out.println("executando o select * from modificacao_status no dao do relatorio ");
  	  	  st =  getConnection().createStatement();
  	  	  rs = st.executeQuery(sql);
  	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  	  return rs;
    }
    
    // atualizado
    public ResultSet listarTresSemanas() {  
  	  String sql = "select * from modificacao_status where data > current_date - 21";  // 21 dias do dia atual 
  	  Statement st;
  	  ResultSet rs = null;
  	
  	  try {
  		  System.out.println("executando o select * from modificacao_status no dao do relatorio ");
  	  	  st =  getConnection().createStatement();
  	  	  rs = st.executeQuery(sql);
  	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  	  return rs;
    }
    
    // atualizado
    public ResultSet listarMes() {  
  	  String sql = "select * from modificacao_status where data > current_date - 30";  // 21 dias do dia atual 
  	  Statement st;
  	  ResultSet rs = null;
  	
  	  try {
  		  System.out.println("executando o select * from modificacao_status no dao do relatorio ");
  	  	  st =  getConnection().createStatement();
  	  	  rs = st.executeQuery(sql);
  	  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  	  return rs;
    }
    
    // metodo depois aqui
    public ResultSet buscarPorIntervaloDeDias(LocalDate dataMin, LocalDate dataMax) {
    	String sql = "select * from modificacao_status where data between '"+dataMin+"' and '"+dataMax+"' ";  //ver esse sql
    	  Statement st;
    	  ResultSet rs = null;
    	
    	  try {
    		  System.out.println("executando o select * from modificacao_status no dao do relatorio ");
    	  	  st =  getConnection().createStatement();
    	  	  rs = st.executeQuery(sql);
    	  } catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}  
    	  return rs;
      }
	
    
	@Override
	public ResultSet findbyNickname(VO entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
	public void inserir(VO vo) throws SQLException {
		// TODO Auto-generated method stub
		
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
	public ResultSet listarPorEmail(VO entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResultSet listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
