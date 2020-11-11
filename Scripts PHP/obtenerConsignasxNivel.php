<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}


$IdNivel = $_POST["IdNivel"];

if(empty($IdNivel))
{
exit("Complete los datos.");
}

$consulta = "SELECT con.IdConsigna AS IdConsigna, con.URLImagen AS URLImagen, con.Descripcion AS Descripcion, con.Estado AS 'Estado' FROM consignas con
			INNER JOIN ordennivel ord 
			ON ord.IdConsigna = con.IdConsigna 
			AND ord.IdNivel = '$IdNivel'
			AND con.Estado = 1";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdConsigna'] . ";";
	echo $mostrar['URLImagen'] . ";";
	echo $mostrar['Descripcion'] . ";";
	echo $mostrar['Estado'] . "|";
}

mysqli_close($conexion);
?>