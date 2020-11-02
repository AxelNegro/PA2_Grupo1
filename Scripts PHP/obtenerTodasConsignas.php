<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$consulta = "SELECT IdConsigna, URLImagen, Descripcion, Estado FROM consignas";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdConsigna'] . ";";
	echo $mostrar['URLImagen'] . ";";
	echo $mostrar['Descripcion'] . ";";
	echo $mostrar['Estado'] . "|";
}

mysqli_close($conexion);
?>