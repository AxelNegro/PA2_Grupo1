<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST['Usuario'];
$Estado = $_POST['Estado'];

if(empty($Usuario)||empty($Estado))
{
exit("Complete los datos.");
}

$consulta = "select Usuario from usuarios where Usuario = '$Usuario'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	$consulta = "UPDATE usuarios SET Estado='$Estado',Tipo='$Tipo' WHERE Usuario='$Usuario'";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		if($Estado == 1){
			echo "Usuario dado de alta correctamente.";
		}
		else{
			echo "Usuario dado de baja correctamente.";
		}
	}
	else
	{
		echo "Error al modificar el estado del usuario.";
	}
}
else{
	echo "El usuario ingresado no existe.";
}

mysqli_close($conexion);
?>