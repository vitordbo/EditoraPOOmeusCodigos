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
	 quando cria um objeto => valor null => n�o tem nada dentro 
	 se for nulo e tentar fazer => login.equals("") => vai dar erro 
	 � como se tivesse acessando um metodo de null, de um lugar que n�o existe
	 => n�o tem como acessar => por isso verificar se � nulo 
	 se for null => n�o tem como invocar nenhum metodo
	 come�a a garantir a integridade? => fazendo as verifica��es com ifs (nos gets n�o precisa)
	*/
	
	// garantindo a integridade dos dados para o nome
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if(nome==null) //valor null => n�o tem nada dentro 
		{
			//exce��o seria melhor => vai ser usada mais pra frente 
            System.out.println("Por favor, reveja essa informa��o");
		}
		else {
			if(nome.equals("")) { // vazio 
				System.out.println("Por favor, reveja essa informa��o, est� vazia");
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
				  System.out.println("Por favor, reveja essa informa��o");
			}
			else {
				if(senha.equals("")) {
					System.out.println("Por favor, reveja essa informa��o, est� vazia");
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
			 System.out.println("Por favor, reveja essa informa��o");
		}
		else {
			if(email.equals("")) { 
				System.out.println("Por favor, reveja essa informa��o, est� vazia");
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
            System.out.println("Por favor, reveja essa informa��o");
		}
		else {
			if(nickname.equals("")) { //perceber diferen�a de null para vazio 
				System.out.println("Por favor, reveja essa informa��o, est� vazia");	
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
			 System.out.println("Por favor, reveja essa informa��o");
	}
	
	
	/*
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		if(id==null) {
			 System.out.println("Por favor, reveja essa informa��o");
		}
		else {
		  if(id.equals("")) {  // apenas o nome interessa aqui
			  System.out.println("Por favor, reveja essa informa��o, est� vazia");
		  }
		  else
			  this.id = id;
		  }
		
	}
*/
}

