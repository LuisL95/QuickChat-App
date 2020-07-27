package com.practica.quickchat.modelo;

import java.util.Date;

public class MensajeFueraServidor {

	private String contenido;
	private String nombreGrupo;
	private Date fechaEnvio;
	
	public MensajeFueraServidor() {
		
	}
	
	public MensajeFueraServidor(String contenido) {
        this.contenido = contenido;
    }
	 
	public String getcontenido() {
		return contenido;
	}
	public void setcontenido(String contenido) {
		this.contenido = contenido;
	}
	public String getnombreGrupo() {
		return nombreGrupo;
	}
	public void setnombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public Date getfechaEnvio() {
		return fechaEnvio;
	}

	public void setfechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
}
