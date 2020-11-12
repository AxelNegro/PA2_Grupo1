<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion) exit("Error al intentar conectarse a la base de datos.");

$Usuario = $_POST['Usuario'];
$Contrasena = $_POST['Contrasena'];

	$consulta = "UPDATE usuarios SET Contrasena='$Contrasena' WHERE Usuario = '$Usuario'";
	mysqli_query($conexion,$consulta);
	$num = mysqli_affected_rows($conexion);

	if($num > 0) echo "Contraseña modificada exitosamente.";
	else echo "Error al modificar la contraseña.";

mysqli_close($conexion);
?>