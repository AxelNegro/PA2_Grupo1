<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST['Usuario'];
$Contrasena = $_POST['Contrasena'];

if(empty($Usuario)||empty($Contrasena))
{
exit("Complete los datos.");
}

$consulta = "select Usuario from usuarios where Usuario = '$Usuario' AND Contrasena = '$Contrasena'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	$consulta = "select Usuario from usuarios where Usuario = '$Usuario' AND Contrasena = '$Contrasena' AND Estado = 1";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	
	if($num>0){
		echo "1";
	}
	else{
		echo "Usuario dado de baja.";
	}
}
else{
	echo "Usuario y/o contraseña incorrectos.";
}

mysqli_close($conexion);
?>