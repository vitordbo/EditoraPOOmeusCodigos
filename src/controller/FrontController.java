package controller;

import exceptions.AutenticarExceptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import model.bo.BaseInterBO;
import model.bo.UsuarioBO;
import model.bo.LivroBO;
import model.vo.AutorVO;
import model.vo.GerenteVO;
import model.vo.UsuarioVO;
import model.vo.AvaliadorVO;

import view.Telas;

public class FrontController {
	
	// metodo autenticar 
	@FXML private Label erroAut;
	@FXML private TextField logusuario;
	@FXML private TextField senha;
	
	//cadastrar livro 
	@FXML private TextField titulo;
	@FXML private TextField genero;
	@FXML private TextField ano;
	@FXML private TextField autor;
	
	
	
	private static BaseInterBO<UsuarioVO> usuBO = new UsuarioBO<UsuarioVO>();
	private static BaseInterBO<UsuarioVO> livroBO = new LivroBO();
	private Integer idRetornadoDoAutenticar;
	
	// metodo autenticar ok 
	// ler login e ler senha e chamar classe BO para fazer a autenticação 
	public void autenticar(ActionEvent event) throws Exception{
		UsuarioVO vo = new UsuarioVO();
		vo.setNickname(logusuario.getText());
		vo.setSenha(senha.getText());
		 
		
		
		try {
			UsuarioVO autenticado = usuBO.autenticar(vo);

			////
			idRetornadoDoAutenticar = autenticado.getId();
			setIdRetornadoDoAutenticar(autenticado.getId());
			//System.out.println("" + teste + " teste tentativa guardar id ");
			
			if (autenticado instanceof GerenteVO) {
				Telas.telaPrincipalGerente(); 
			}
			if (autenticado instanceof AvaliadorVO)
			{
				Telas.telaAvaliadorAlteraStatusCadaObra();
				 
			}
			if (autenticado instanceof AutorVO) {
				Telas.telaPrinciplaAutorCadastrarLivros();
			}
		}
		
		catch(AutenticarExceptions e) { 
			erroAut.setText("Usuario ou senha invalidos");
			erroAut.setVisible(true);
			e.printStackTrace();
		}
	}
	
	  // aqui que fiz o voltar => volta pra tela principal de login  => trocar por uma casa
    @FXML
    private void voltar() {
    	try {
    		System.out.println("voltando da visão gerente principal pra tela de login");
			Telas.telaLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	// quando aperta o botão da tela do adm => vai mostrar a tebela com autores
	@FXML
    void telaTableAutores(MouseEvent event) {
           try {
        	System.out.println("indo para a tebela de autores");
			Telas.telaTabelaAutores();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
		// quando aperta o botão da tela do adm => vai mostrar a tebela com avaliadores
		@FXML
	    void telaTableAvaliadores(MouseEvent event) {
	           try {
	        	System.out.println("indo para a tebela de avaliadores");
				Telas.telaTabelaAvaliadores();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		// quando aperta o botão da tela do adm => vai mostrar a tebela com os livros
				@FXML
			    void telaTableLivros(MouseEvent event) {
			           try {
			        	System.out.println("indo para a tebela de livros");
						Telas.telaTabelaAdmLivros();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
				
		// quando aperta o botão do relatorio do adm => vai mostrar a tela de relatorios
				@FXML
			    void telaRelatorios(MouseEvent event) {
			           try {
			        	System.out.println("indo para a tela de relatorios");
						Telas.telaRelatorios();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
	
	// colocar nas telas botão de voltar para a tela de login => pesquisar como colocar imagem em um botão
	public void logout(ActionEvent event) throws Exception{
		Telas.telaLogin();
	}

	public Integer getIdRetornadoDoAutenticar() {
		return idRetornadoDoAutenticar;
	}

	public void setIdRetornadoDoAutenticar(Integer idRetornadoDoAutenticar) {
		this.idRetornadoDoAutenticar = idRetornadoDoAutenticar;
	}
}
