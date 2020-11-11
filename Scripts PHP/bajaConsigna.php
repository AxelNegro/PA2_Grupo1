<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdConsigna = $_POST['IdConsigna'];
$Estado = $_POST['Estado'];

if(empty($IdConsigna))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdConsigna FROM consignas WHERE IdConsigna = '$IdConsigna'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	$consulta = "UPDATE consignas SET Estado='$Estado' WHERE IdConsigna = '$IdConsigna'";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		if($Estado == 1){
			echo "Consigna dada de alta correctamente.";
		}
		else{
			echo "Consigna dada de baja correctamente.";
		}
	}
	else
	{
		echo "Error al modificar el estado de la consigna.";
	}
}
else{
	echo "La consigna ingresada no existe.";
}

mysqli_close($conexion);
?>