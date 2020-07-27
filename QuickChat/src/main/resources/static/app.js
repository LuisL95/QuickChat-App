var stompClient = null;
var socket = null;
var nombreCorto = "";

function establecerConexion(conectado) {
    $("#conectar").prop("disabled", conectado);
    $("#desconectar").prop("disabled", !conectado);
    if (conectado) {
        $("#conversacion").show();
    }
    else {
        $("#conversacioon").hide();
    }
    $("#mensajesChat").html("");
}

function conectar() {
    // crea objecto tipo sockJS
	socket = new SockJS('/quick-chat');
	
	// especifica que se está usando el  STOMP protocol en el socket
    stompClient = Stomp.over(socket);
    
    //  Se crea una conexion
    stompClient.conectar({}, function (frame) {
        establecerConexion(true);
        console.log('conectado: ' + frame);
        
        // subscribe a un tema y crea una función callback que se encarga de las actualizaciones desde el servidor 
        stompClient.subscribe("/tema/nombres-invitados", function (saludo) {
        	mostrarNombreAgregado(JSON.parse(saludo.body).content);
        });
        
        stompClient.subscribe("/tema/chats-invitados", function (saludo) {
            mostrarMensaje(JSON.parse(saludo.body).content);
            
        });
        
        stompClient.subscribe('/tema/actualizaciones-invitados', function (saludo) {
            mostrarEscribiendo(JSON.parse(saludo.body).content);
        });
        
        stompClient.subscribe('/tema/errores', function (saludo) {
            mostrarErrores(JSON.parse(saludo.body).content);
        });
        
        	enviarNombre();
    });
    
}

function desconectar() {
    if (stompClient !== null) {
    	$("#miembros").append("<tr><td>" + nombreCorto + " ha abandonado el chat</td></tr>");
        stompClient.disconnect();
    }
    establecerConexion(false);
    console.log("desconectado");
}

function mostrarEscribiendo(mensaje) {
	$("#actualizacionEscritura").html("<tr><td>Alguien está escribiendo...</td></tr>");
}

function enviarMensaje() {
  stompClient.send("/app/chat-invitados", {}, JSON.stringify({'mensaje': $("#mensaje").val()}));
}

function mostrarMensaje(mensaje) {
    $("#mensajesChat").append("<tr><td>" + mensaje + "</td></tr>");
    $("#actualizacionEscritura").html("<tr><td>&nbsp;</td></tr>");
    $("#mensaje").val("");
}
	
function enviarNombre() {
    stompClient.send("/app/invitados-adentro", {}, JSON.stringify({'mensaje': $("#nombreCorto").val()}));
}

function mostrarNombreAgregado(mensaje) {
	nombreCorto = mensaje;
    $("#miembros").append("<tr><td>" + mensaje + " acaba de unirse</td></tr>");
}

function mostrarErrores(mensaje) {
	$("#mensajesError").html("<tr><td>" + mensaje + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    
    $( "#conectar" ).click(function() { conectar(); });
    
    $( "#desconectado" ).click(function() { desconectar(); });
    
    $( "#enviar" ).click(function() { enviarMensaje(); });
    
    $("#mensaje").keyup(function (e)  {
		//Envía "está escribiendo " al servidor después de que son detectadas las pulsaciones de teclas 
		stompClient.send("/app/actualizacion-invitados", {}, JSON.stringify({'mensaje': $("#mensaje").val()}));
	});
});

