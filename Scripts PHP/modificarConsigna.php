<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdConsigna = $_POST['IdConsigna'];
$URL_Imagen = $_POST['URL_Imagen'];
$Descripcion = $_POST['Descripcion'];

if(empty($IdConsigna)||empty($URL_Imagen)||empty($Descripcion))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdConsigna FROM consignas WHERE IdConsigna = '$IdConsigna'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	$consulta = "UPDATE consignas SET URLImagen='$URL_Imagen',Descripcion='$Descripcion' WHERE IdConsigna = '$IdConsigna'";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		echo "Consigna modificada correctamente.";
	}
	else
	{
		echo "Error al modificar la consigna.";
	}
}
else{
	echo "La consigna ingresada no existe.";
}

mysqli_close($conexion);
?>