<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST['Usuario'];
$IdConsigna = $_POST['IdConsigna'];


if(empty($Usuario)||empty($IdConsigna))
{
exit("Complete los datos.");
}

$consulta = "SELECT Usuario, IdConsigna FROM consignas_x_usuario WHERE Usuario = '$Usuario' AND IdConsigna = '$IdConsigna'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num==0){
	$consulta = "INSERT INTO consignas_x_usuario(Usuario, IdConsigna) VALUES ('$Usuario', '$IdConsigna')";
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