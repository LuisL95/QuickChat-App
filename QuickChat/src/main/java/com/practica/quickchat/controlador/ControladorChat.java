package com.practica.quickchat.controlador;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


import com.practica.quickchat.modelo.MensajeDeServidor;
import com.practica.quickchat.modelo.MensajeFueraServidor;



@Controller
public class ControladorChat {

	@MessageMapping("/chat-invitados")
	 @SendTo("/tema/chats-invitados")
	 public MensajeFueraServidor manejarMensajes(MensajeDeServidor mensaje) throws Exception {
       Thread.sleep(1000); 
       mensaje= null;
       mensaje.getmensaje();
       return new MensajeFueraServidor(mensaje.getmensaje());
   }

	@MessageMapping("/actualizacion-invitados")
	@SendTo("/tema/actualizaciones-invitados")
	public MensajeFueraServidor manejarAlguienEscribiendo(MensajeDeServidor mensaje)  throws Exception {
		return new MensajeFueraServidor("Alguien está escribiendo...");
	}
	
   @MessageMapping("/invitados-adentro")
   @SendTo("/tema/nombres-invitados")
   public MensajeFueraServidor hmanejarNuevosMiembros(MensajeDeServidor mensaje) throws Exception {
       return new MensajeFueraServidor(mensaje.getmensaje());
   }
   
	@MessageExceptionHandler
	@SendTo("/tema/errores")
	public  MensajeFueraServidor handleException(Throwable excepcion) {
		MensajeFueraServidor error = new MensajeFueraServidor("Ha ocurrido un error.");
		return error;
}
	
	
}
