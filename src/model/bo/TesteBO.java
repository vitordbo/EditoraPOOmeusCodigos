package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.GerenteDAO;
import model.dao.UsuarioDAO;
import model.vo.GerenteVO;
import model.vo.UsuarioVO;

public class TesteBO {

	public static void main(String[] args) {
		UsuarioBO bo = new UsuarioBO();
		GerenteBO bog = new GerenteBO();
		UsuarioVO vo = new UsuarioVO();
		GerenteVO vog = new GerenteVO();
		UsuarioDAO dao = new UsuarioDAO();
		GerenteDAO daog = new GerenteDAO();
		System.out.println(dao.getConnection());
		
		
		List<UsuarioVO> listTeste = new ArrayList<UsuarioVO>();
		//List<UsuarioVO> listteste2 = bo.buscarPorNome(vo);
		
		vog.setId(18);
		vog.setNome("GabrielGerete");
		vog.setEmail("email@teste.com");
		vog.setNickname("nick teste");
        vog.setSenha("senha");
        
        /*
         usuario:
         cadastrar ok
         bo.cadastrar(vo);
        
         buscar por nome ok => só mostra o nome que foi passado se estiver no bd 
         => recebe as outras caracteristicas => mostra se quiser
         // listTeste = bo.buscarPorNome(vo);
        
        
         buscar por id ok => só mostra o id que foi passado se estiver no bd 
         // listTeste = bo.buscarPorId(vo);
         => recebe as outras caracteristicas => mostra se quiser
         
         buscar geral ok => mostra tudo que tem => escolhe o que mostra no for 
          listTeste = bo.buscar();
          
          alterar ok => passa id e altera nome 
           bo.alterar(vo);

         remover ok
         bo.remover(vo);        
        */
        
        /*
        gerente:   
         
        
         */
	
         bog.cadastrar(vog);
		
		
		
       // listTeste = bo.buscarPorNome(vo);
       // listTeste = bo.buscarPorId(vo);
       //listTeste = bo.buscar();
       // bo.alterar(vo);
     
       // for (UsuarioVO u: listTeste) {
        	//System.out.println(u.getNome());
        	//System.out.println(u.getId());
        	//System.out.println(u.getEmail());
        	//System.out.println(u.getSenha());
      //  }

	}

}
