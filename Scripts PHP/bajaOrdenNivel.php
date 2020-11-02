<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdOrden = $_POST['IdOrden'];
$Estado = $_POST['Estado'];

if(empty($IdOrden)||(empty($Estado))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdOrden FROM ordennivel WHERE IdOrden = '$IdOrden'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	$consulta = "UPDATE ordennivel SET Estado = '$Estado' WHERE IdOrden = '$IdOrden'";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		if($Estado==1){
			echo "Orden dada de alta correctamente.";
		}
		else{
			echo "Orden dada de baja correctamente.";
		}
	}
	else
	{
		echo "Error al modificar el estado de la Orden.";
	}
}
else{
	echo "La seña/consigna ingresada no existe.";
}

mysqli_close($conexion);
?>