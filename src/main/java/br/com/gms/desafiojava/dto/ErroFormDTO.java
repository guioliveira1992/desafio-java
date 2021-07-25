package br.com.gms.desafiojava.dto;

public class ErroFormDTO {

	private int status_code;
	private String mesage;
	
	public ErroFormDTO(int status_code, String mesage) {
		this.status_code = status_code;
		this.mesage = mesage;
	}

	public int getStatus_code() {
		return status_code;
	}

	public String getMesage() {
		return mesage;
	}

	
	
}
