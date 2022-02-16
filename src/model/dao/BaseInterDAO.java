package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.AutorVO;
import model.vo.GerenteVO;
import model.vo.UsuarioVO;

public interface BaseInterDAO <VO> {
   
	 public static Connection getConnection() {
		// TODO Auto-generated method stub
		return getConnection();
	}
   public void inserir(VO entity) throws SQLException;
   public void atualizar(VO entity) throws SQLException;
   public void deletar(VO entity) throws SQLException;

   public ResultSet listarPorId(VO entity) throws SQLException;
   public ResultSet listarPorNome(VO entity) throws SQLException;
   public ResultSet listar() throws SQLException;
   public ResultSet listarPorEmail(VO entity) throws SQLException;
   public ResultSet findbyNickname(VO entity) throws SQLException;
   public ResultSet buscarporIdGerente(VO ger);
   public ResultSet listarPorSenha(VO vo);
	   
   public ResultSet buscarporIdAutor(VO aut);
   public ResultSet buscarporIdAvaliador(VO ava);
   public ResultSet listarVisao();
   public ResultSet listarSeteDias(); // relatorio
   public ResultSet listarHoje(); // relatorio
   public ResultSet listarDuasSemanas();
   public ResultSet listarTresSemanas();
   public ResultSet listarMes();
   public ResultSet listarTudo();
}