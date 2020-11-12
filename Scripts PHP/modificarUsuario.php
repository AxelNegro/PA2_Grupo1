<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion) exit("Error al intentar conectarse a la base de datos.");

$Usuario = $_POST['Usuario'];
$Contrasena = $_POST['Contrasena'];
$Nombre = $_POST['Nombre'];
$Apellido = $_POST['Apellido'];
$Email = $_POST['Email'];
$Tipo = $_POST['Tipo'];
$Estado = $_POST['Estado'];

$consulta = "select Usuario from usuarios where Usuario = '$Usuario'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num > 0){
	$consulta = "UPDATE usuarios SET Contrasena='$Contrasena',Nombre='$Nombre',Apellido='$Apellido',Email='$Email',Estado='$Estado',Tipo='$Tipo' WHERE Usuario = '$Usuario'";
	
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num > 0)
	{
	    echo "Usuario modificado exitosamente.";
	}
	else
	{
		echo "Error al modificar el usuario.";
	}
}
else{
	echo "El usuario ingresado no existe.";
}

mysqli_close($conexion);
?>