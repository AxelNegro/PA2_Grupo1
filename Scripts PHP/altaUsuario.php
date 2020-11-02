<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST['Usuario'];
$Contrasena = $_POST['Contrasena'];
$Nombre = $_POST['Nombre'];
$Apellido = $_POST['Apellido'];
$Email = $_POST['Email'];


if(empty($Usuario)||empty($Contrasena)||empty($Nombre)||empty($Apellido)||empty($Email))
{
exit("Complete los datos.");
}

$consulta = "select Usuario from usuarios where Usuario = '$Usuario'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num==0){
	$consulta = "INSERT INTO usuarios(Usuario, Contrasena, Nombre, Apellido, Email, Estado, Tipo) VALUES ('$Usuario', '$Contrasena', '$Nombre', '$Apellido', '$Email', 1, 0)";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		echo "Usuario registrado correctamente.";
	}
	else
	{
		echo "Error al registrar el usuario.";
	}
}
else{
	echo "El usuario ingresado ya se encuentra registrado.";
}

mysqli_close($conexion);
?>