package model.vo;

public class UsuarioVO {
	private String nome;
	private String senha;
	private String email;
	private String nickname;
	private int id; 
   // private String id;
	/*
	 fazer esses esquemas de if prar os metodos set (garantir o encapsulamento)
	 quando cria um objeto => valor null => não tem nada dentro 
	 se for nulo e tentar fazer => login.equals("") => vai dar erro 
	 é como se tivesse acessando um metodo de null, de um lugar que não existe
	 => não tem como acessar => por isso verificar se é nulo 
	 se for null => não tem como invocar nenhum metodo
	 começa a garantir a integridade? => fazendo as verificações com ifs (nos gets não precisa)
	*/
	
	// garantindo a integridade dos dados para o nome
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if(nome==null) //valor null => não tem nada dentro 
		{
			//exceção seria melhor => vai ser usada mais pra frente 
            System.out.println("Por favor, reveja essa informação");
		}
		else {
			if(nome.equals("")) { // vazio 
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			else 
				this.nome = nome;
		   }
		}
	

	// garantindo a integridade dos dados para a senha 
		public String getSenha() {
			return senha;
		}
		
		public void setSenha(String senha) {
			if(senha==null)
			{
				  System.out.println("Por favor, reveja essa informação");
			}
			else {
				if(senha.equals("")) {
					System.out.println("Por favor, reveja essa informação, está vazia");
				}
				else 
					this.senha = senha;
			}
		}
		
		
	// garantindo a integridade dos dados para o email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		if(email==null) {
			 System.out.println("Por favor, reveja essa informação");
		}
		else {
			if(email.equals("")) { 
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			else
				this.email = email;
		}
	} 
	
	
	// garantindo a integridade dos dados para o nickname
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {  //garantir fazendo um if 
		if(nickname==null) {
            System.out.println("Por favor, reveja essa informação");
		}
		else {
			if(nickname.equals("")) { //perceber diferença de null para vazio 
				System.out.println("Por favor, reveja essa informação, está vazia");	
			}
			else
				this.nickname = nickname;
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
	
	
	/*
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		if(id==null) {
			 System.out.println("Por favor, reveja essa informação");
		}
		else {
		  if(id.equals("")) {  // apenas o nome interessa aqui
			  System.out.println("Por favor, reveja essa informação, está vazia");
		  }
		  else
			  this.id = id;
		  }
		
	}
*/
}

