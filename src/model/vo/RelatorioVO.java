package model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class RelatorioVO {
	//atributos
	private ArrayList<AutorVO> escritores = new ArrayList<>();
	private ArrayList<LivroVO> obras = new ArrayList<>();
	private ArrayList<AvaliadorVO> avaliadores = new ArrayList<>();

	private Date data;
	
	// testes depois => melhor get e set => por encapsulamento 
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNome_ava() {
		return nome_ava;
	}

	public void setNome_ava(String nome_ava) {
		this.nome_ava = nome_ava;
	}


	//private String periodo; // vai usar assim?	
	private String status;
	private String titulo;
    private String nome_ava;
	//private LivroVO livro;
	//String status = livro.getStatus();
	//String titulo = livro.getTitulo();
	//String nome_ava = livro.getNome_avaliador();
	
	/*
	//construtores
	public RelatorioVO(ArrayList<AutorVO> escritores, ArrayList<LivroVO> obras, ArrayList<AvaliadorVO> avaliadores, Date data) {
		this.escritores = escritores;
		this.obras = obras;
		this.avaliadores = avaliadores;
		this.data = data;
	}
	 */
	
	public RelatorioVO(Date data, String status, String titulo, String nome_ava) {
		this.data = data;
		this.status = status;
		this.titulo = titulo;
		this.nome_ava = nome_ava;
	}
	
	//métodos getter e setter
	public ArrayList<AutorVO> getEscritores() {
		return escritores;
	}
	//se for nulo ele irá pedir para informar o escritor, caso já tenha sido informado os escritores, irá guardar a informação dada.
	public void setEscritores(ArrayList<AutorVO> escritores) {
		if (escritores == null) {
			System.out.println("Informe o(s) escritor(es): ");
		} else {
			for (AutorVO autorVO : escritores) {
				if(!autorVO.getCpf().equals("") && !autorVO.getEndereco().equals("") && autorVO.getNome() != null) {
					this.escritores = escritores;
				}
			}
			
		}
		
	}
	public ArrayList<LivroVO> getObras() {
		return obras;
	}
	//se for nulo ele irá pedir para informar a obra, caso já tenha sido informado as obras, irá guardar a informação dada.
	public void setObras(ArrayList<LivroVO> obras) {
		if (obras == null) {
			System.out.println("Informe a(s) obra(s): ");
		} else {
			for (LivroVO livroVO : obras) {
				if(livroVO.getAutor() != null && !livroVO.getGenero().equals("") 
						&& !livroVO.getTitulo().equals("")) {
					this.obras = obras;
				}
			}
			
		}
		
	}
	public ArrayList<AvaliadorVO> getAvaliadores() {
		return avaliadores;
	}
	//se for nulo ele irá pedir para informar o avaliador, caso já tenha sido informado os avaliadores, irá guardar a informação dada.
	public void setAvaliadores(ArrayList<AvaliadorVO> avaliadores) {
		if (avaliadores == null) {
			System.out.println("Informe o(s) avaliador(es): ");
		} else {
			for (AvaliadorVO avaliadorVO : avaliadores) {
				if(!avaliadorVO.getCpf().equals("") && !avaliadorVO.getEndereco().equals("") && avaliadorVO.getUsuario() != null) {
					this.avaliadores = avaliadores;
				}
			}
			
		}
		
	}

	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}
	
}