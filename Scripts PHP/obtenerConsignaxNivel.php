<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}


$IdConsigna = $_POST["IdConsigna"];
$IdNivel = $_POST["IdNivel"];

if(empty($IdConsigna)||empty($IdNivel))
{
exit("Complete los datos.");
}

$consulta = "SELECT con.URLImagen AS URL, con.Descripcion AS Desc FROM consignas con
			INNER JOIN ordennivel ord 
			ON ord.IdConsigna = con.IdConsigna 
			AND ord.IdNivel = '$IdNivel'
			WHERE con.IdConsigna = '$IdConsigna'
			AND con.Estado = 1";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['URL'] . ";";
	echo $mostrar['Desc'] . "|";
}

mysqli_close($conexion);
?>