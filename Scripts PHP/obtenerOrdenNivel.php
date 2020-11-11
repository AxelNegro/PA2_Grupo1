<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdOrden = $_POST["IdOrden"];

if(empty($IdOrden))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdNivel, IdSena, IdConsigna, Orden, Estado FROM ordennivel WHERE IdOrden = '$IdOrden'";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdNivel'] . ";";
	echo $mostrar['IdSena'] . ";";
	echo $mostrar['IdConsigna'] . ";";
	echo $mostrar['Orden'] . ";";
	echo $mostrar['Estado'] . "|";
}

mysqli_close($conexion);
?>