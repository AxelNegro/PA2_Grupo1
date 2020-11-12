<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST['Usuario'];
$IdOrden = $_POST['IdOrden'];


if(empty($Usuario)||empty($IdOrden))
{
exit("Complete los datos.");
}

$consulta = "SELECT Usuario, IdOrden FROM ordenxusuario WHERE Usuario = '$Usuario' AND IdOrden = '$IdOrden'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num==0){
	$consulta = "INSERT INTO ordenxusuario(Usuario, IdOrden) VALUES ('$Usuario', '$IdOrden')";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		echo "Ejercicio hecho correctamente.";
	}
	else
	{
		echo "Error al completar el ejercicio.";
	}
}
else{
	echo "El ejercicio ya se encuentra completado.";
}

mysqli_close($conexion);
?>