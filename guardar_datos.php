<?php
include("conexionDB.php");

if (isset($_POST['enviar'])) {
    if (strlen($_POST['id']) >= 1 && 
	     strlen($_POST['name']) >= 1 &&
	     strlen($_POST['email']) >= 1 &&
		 strlen($_POST['address']) >= 1 &&
		 strlen($_POST['phone']) >= 1 ) {

	    $id = trim($_POST['id']);
		$nombre = trim($_POST['name']);
	    $email = trim($_POST['email']);
		$direccion = trim($_POST['address']);
	    $telefono = date($_POST["phone"]);
	   
		$consulta = "INSERT INTO datosClientes ( id, nombre, correo, direccion, telefono) VALUES ('$id','$nombre','$email','$direccion','$telefono')";
	   
		$resultado = mysqli_query($conex,$consulta);
	    if ($resultado) {
	    	?> 
	    	<h3 class="ok">¡Te has inscrito correctamente!</h3>
           <?php
	    } else {
	    	?> 
	    	<h3 class="bad">¡Ups ha ocurrido un error!</h3>
           <?php
	    }
    }   else {
	    	?> 
	    	<h3 class="bad">¡Por favor complete los campos!</h3>
           <?php
    }
}

?>
