<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$consulta = "SELECT Usuario, Contrasena, Nombre, Apellido, Email, Estado, Tipo FROM usuarios";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['Usuario'] . ";";
	echo $mostrar['Contrasena'] . ";";
	echo $mostrar['Nombre'] . ";";
	echo $mostrar['Apellido'] . ";";
	echo $mostrar['Email'] . ";";
	echo $mostrar['Estado'] . ";";
	echo $mostrar['Tipo'] . "|";
}

mysqli_close($conexion);
?>