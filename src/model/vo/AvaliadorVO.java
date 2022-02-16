package model.vo;

public class AvaliadorVO extends UsuarioVO {
	//atributos
	private String cpf;
	private String endereco;
	private UsuarioVO usuario; //ver se tirar vai mudar?
	private int id;
	private String nome;

	public AvaliadorVO(String nome, String endereco, String cpf, int id) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.id = id;
	}
	
	// garantindo a integridade dos dados para o nome
			public String getNome() {
				return nome;
			}
			
			public void setNome(String nome) {
				if(nome==null) {
		            System.out.println("Por favor, reveja essa informa��o");
				}
				else {
					if(nome.equals("")) { // perceber diferen�a de null para vazio 
						System.out.println("Por favor, reveja essa informa��o, est� vazia");	
					}
					else
						this.nome = nome;
				}	
			}
	
	//m�todos getter e setter
	public String getCpf() {
		return cpf;
	}
	//se o espa�o do cpf estiver em branco ou nulo ele vai pedir pra informar o cpf, caso tenha sido informado o cpf ele vai guardar o cpf
	public void setCpf(String cpf) {
		if (cpf.equals("") || cpf == null) {
			System.out.println("Informe cpf: ");
		} else {
		this.cpf = cpf;
		}
	}

	public String getEndereco() {
		return endereco;
	}
	//se o espa�o do endere�o estiver em branco ou nulo ir� pedir para iformar o endere�o, caso tenha sido informado ele ir� guardar o endere�o
	public void setEndereco(String endereco) {
		if (endereco.equals("") || endereco == null) {
			System.out.println("Informe o endere�o: ");
		} else {
			this.endereco = endereco;
		}
		
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}
	//se o espa�o do usuario for nulo ele ir� pedir para informar o usu�rio, caso j� tenha sido informado o usu�rio ir� guardar a informa��o dada.
	public void setUsuario(UsuarioVO usuario) {
		if (usuario == null) {
			System.out.println("Informa o usu�rio: ");
		} else {
			if(!usuario.getEmail().equals("") && !usuario.getNickname().equals("") && 
					!usuario.getNome().equals("") && !usuario.getSenha().equals(""))
				this.usuario = usuario;
			else
				System.out.println("Por favor preenca os campos!");
		}
		
	}
	
	// garantindo a integridade dos dados para o id
				public int getId() {
					return id;
				}

				public void setId(int id) {
					if(id>0)
						this.id = id;
					else 
						 System.out.println("Por favor, reveja essa informa��o");
				}

}
