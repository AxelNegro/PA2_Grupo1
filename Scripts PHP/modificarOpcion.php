<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdConsigna = $_POST['IdConsigna'];
$IdOpcion = $_POST['IdOpcion'];
$Descripcion = $_POST['Descripcion'];
$Resultado = $_POST['Resultado'];

if(empty($IdConsigna)||empty($IdOpcion)||empty($Descripcion)||empty($Resultado))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdOpcion FROM opciones WHERE IdOpcion = '$IdOpcion'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	$consulta = "UPDATE opciones SET Descripcion='$Descripcion',Resultado='$Resultado' WHERE IdOpcion = '$IdOpcion'; UPDATE opciones_x_consigna SET IdConsigna = '$IdConsigna' WHERE IdOpcion = '$IdOpcion'";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		echo "Opción modificada correctamente.";
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