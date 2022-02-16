package model.vo;


public class LivroVO  {
	
	public LivroVO(String titulo, String genero, String ano, String autor, String id, String status, Integer id_avaliador, 
			String nome_avaliador, Integer id_autor) {
		super();
		this.titulo = titulo;
		this.genero = genero;
		this.ano = ano;
		this.autor = autor;
		this.id = id;
		this.status = status;
		this.id_avaliador = id_avaliador;
		this.nome_avaliador = nome_avaliador;
		this.id_autor = id_autor;
	}

	private String titulo;
	private String genero;
	private String ano;    
	private String status;  // troquei boolean por string 
	private String autor;
	//public int id; 
	private String id;
	private Integer id_avaliador;
    private String nome_avaliador;
    private Integer id_autor;
    
    
    
	// garantindo a integridade dos dados para o titulo
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		if(titulo==null) {
            System.out.println("Por favor, reveja essa informação");
		}
		else {
			if(titulo.equals("")) { // perceber diferença de null para vazio 
				System.out.println("Por favor, reveja essa informação, está vazia");	
			}
			else
				this.titulo = titulo;
		}	
	}
		
	
	// garantindo a integridade dos dados para o genero
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		if(genero==null) {
			 System.out.println("Por favor, reveja essa informação");
		}
		else {
			if(genero.equals("")) {
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			else 
				this.genero = genero;	
		}
	}
	
	// garantindo a integridade dos dados para o ano
	
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		if(ano != null)
			this.ano = ano;
		else 
			 System.out.println("Por favor, reveja essa informação");
	}
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if(status==null) {
			 System.out.println("Por favor, reveja essa informação");
		}
		else {
			if(status.equals("")) {
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			else 
				this.status = status;	
		}
	}
	
	// garantindo a integridade dos dados para o autor 
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		if(autor==null) {
			 System.out.println("Por favor, reveja essa informação");
		}
		else {
		  if(autor.equals("")) {  // apenas o nome interessa aqui
			  System.out.println("Por favor, reveja essa informação, está vazia");
		  }
		  else
			  this.autor = autor;
		  }
	}

	
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

	
	public String getNome_avaliador() {
		return nome_avaliador;
	}

	public void setNome_avaliador(String nome_avaliador) {
		if(nome_avaliador==null) {
			 System.out.println("Por favor, reveja essa informação");
		}
		else {
		  if(nome_avaliador.equals("")) {  // apenas o nome interessa aqui
			  System.out.println("Por favor, reveja essa informação, está vazia");
		  }
		  else
			  this.nome_avaliador = nome_avaliador;
		  }
	}

	
	public Integer getId_avaliador() {
		return id_avaliador;
	}

	public void setId_avaliador(Integer id_avaliador) {
		if(id_avaliador>0)
			this.id_avaliador = id_avaliador;
		else 
			 System.out.println("Por favor, reveja essa informação");
		
			}
	
	public Integer getId_autor() {
		return id_autor;
	}

	public void setId_autor(Integer id_autor) {
		if(id_autor>0)
			this.id_autor = id_autor;
		else 
			 System.out.println("Por favor, reveja essa informação");
		
			}
}
