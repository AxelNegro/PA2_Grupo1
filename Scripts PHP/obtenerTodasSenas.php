<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$consulta = "SELECT IdSena, Sena, URLImagen, Descripcion FROM senas";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdSena'] . ";";
	echo $mostrar['Sena'] . ";";
	echo $mostrar['URLImagen'] . ";";
	echo $mostrar['Descripcion'] . "|";
}

mysqli_close($conexion);
?>