package com.practica.quickchat.modelo;

import java.util.Date;

public class MensajeDeServidor {

	 	private String IdRemitente;
	    private String nombreRemitente;
	    private String mensaje;
	    private Date fechaEnvio;

	    public MensajeDeServidor() {
	    }

	    public MensajeDeServidor(String mensaje) {
	        this.mensaje = mensaje;
	    }

	    public String getIdRemitente() {
			return IdRemitente;
		}

		public void setIdRemitente(String IdRemitente) {
			this.IdRemitente = IdRemitente;
		}

		public String getmensaje() {
			return mensaje;
		}

		public void setmensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		public String getnombreRemitente() {
	        return nombreRemitente;
	    }

	    public void setnombreRemitente(String nombreRemitente) {
	        this.nombreRemitente = nombreRemitente;
	    }

		public Date getfechaEnvio() {
			return fechaEnvio;
		}

		public void setfechaEnvio(Date fechaEnvio) {
			this.fechaEnvio = fechaEnvio;
		}
	}

