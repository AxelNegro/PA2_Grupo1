<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdOpcion = $_POST['IdOpcion'];
$Estado = $_POST['Estado'];

if(empty($IdOpcion)||empty($Estado))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdOpcion FROM opciones WHERE IdOpcion = '$IdOpcion'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	$consulta = "UPDATE opciones SET Estado='$Estado' WHERE IdOpcion = '$IdOpcion';";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		if($Estado == 1){
			echo "Opción dada de alta correctamente.";
		}
		else{
			echo "Opción dada de baja correctamente.";
		}
	}
	else
	{
		echo "Error al modificar la Opción.";
	}
}
else{
	echo "La opción ingresada no existe.";
}

mysqli_close($conexion);
?>