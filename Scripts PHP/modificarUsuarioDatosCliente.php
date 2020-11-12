<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion) exit("Error al intentar conectarse a la base de datos.");

$Usuario = $_POST['Usuario'];
$Nombre = $_POST['Nombre'];
$Apellido = $_POST['Apellido'];
$Email = $_POST['Email'];

	$consulta = "UPDATE usuarios SET Nombre='$Nombre',Apellido='$Apellido',Email='$Email' WHERE Usuario = '$Usuario'";
	mysqli_query($conexion,$consulta);
	$num = mysqli_affected_rows($conexion);

	if($num > 0) {
	    echo "Datos modificados exitosamente.";
	} 
	else {
		echo "Error al modificar datos.";
	}

mysqli_close($conexion);
?>