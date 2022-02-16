package model.vo;

public class AutorVO extends UsuarioVO {
	//atributos
	private String nome;
	private String cpf;
	private String endereco;
	//UsuarioVO usuario;
	private int id;
	//public String id;
	// id usuario?
	
	
	public AutorVO(String nome, String endereco, String cpf, int id) {
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
	            System.out.println("Por favor, reveja essa informação");
			}
			else {
				if(nome.equals("")) { // perceber diferença de null para vazio 
					System.out.println("Por favor, reveja essa informação, está vazia");	
				}
				else
					this.nome = nome;
			}	
		}
	
	
	
	//métodos getter e setter
	public String getCpf() {
		return cpf;
	}
	//se o espaço do cpf estiver em branco ou nulo ele vai pedir pra informar o cpf, caso tenha sido informado o cpf ele vai guardar o cpf
	public void setCpf(String cpf) {
		if(cpf.equals("") || cpf == null) {
			System.out.println("Informe o cpf: ");
		} else {
			this.cpf = cpf;
		}
		
	}

	public String getEndereco() {
		return endereco;
	}
	//se o espaço do endereço estiver em branco ou nulo irá pedir para iformar o endereço, caso tenha sido informado ele irá guardar o endereço
	public void setEndereco(String endereco) {
		if(endereco.equals("") || endereco == null) {
			System.out.println("Informe o endereço: ");
		} else {
			this.endereco = endereco;
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
					 System.out.println("Por favor, reveja essa informação");
			}  
	
}
