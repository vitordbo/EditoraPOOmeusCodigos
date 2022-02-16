package model.vo;

public class GerenteVO extends UsuarioVO {
      private UsuarioVO usuario;
      private int id;

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		if(usuario==null) {
			 System.out.println("Por favor, reveja essa informação");
		}
		else {  // testando se os 4 atributos da classe UsuarioVO estão vazios
			    // se não estiverem, vai para o último else 
			if(usuario.getNome().equals("")) {
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			if(usuario.getSenha().equals("")) {
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			if(usuario.getEmail().equals("")) {
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			if(usuario.getNickname().equals("")) {
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			else 
				this.usuario = usuario;
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