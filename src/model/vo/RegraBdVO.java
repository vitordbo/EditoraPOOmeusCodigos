package model.vo;

import java.sql.Date;

public class RegraBdVO {

	private String id;
	private String status;
	private Date data;
	private String statusNovo;

	public RegraBdVO(String id, String status, Date data, String statusNovo) {
		super();
		this.id = id;
		this.status = status;
		this.data = data;
		this.statusNovo = statusNovo;
		
	}
	
	public String getStatusNovo() {
		return statusNovo;
	}
	public void setStatusNovo(String statusNovo) {
		if(statusNovo==null) {
			 System.out.println("Por favor, reveja essa informação");
		}
		else {
			if(status.equals("")) {
				System.out.println("Por favor, reveja essa informação, está vazia");
			}
			else 
				this.statusNovo = statusNovo;	
		}
	}
	
	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
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
}
