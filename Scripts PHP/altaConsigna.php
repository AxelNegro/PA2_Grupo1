<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$URL_Imagen = $_POST['URL_Imagen'];
$Descripcion = $_POST['Descripcion'];


if(empty($URL_Imagen)||empty($Descripcion))
{
exit("Complete los datos.");
}


$consulta = "INSERT INTO consignas(URL_Imagen, Descripcion, Estado) VALUES ('$URL_Imagen', '$Descripcion', 1)";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);
if($num>0)
{
	echo "Consigna dada de alta correctamente.";
}
else
{
	echo "Error al dar de alta la consigna.";
}

mysqli_close($conexion);
?>