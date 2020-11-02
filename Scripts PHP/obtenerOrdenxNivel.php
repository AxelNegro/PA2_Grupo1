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

$consulta = "SELECT con.IdConsigna AS IdCon, ord.IdSena AS IdSen, ord.Sena AS Sen
			FROM ordennivel ord 
			INNER JOIN senas sen ON sen.IdSena = ord.IdSena
			INNER JOIN consignas con ON con.IdConsigna = ord.IdConsigna
			WHERE ord.IdNivel = '$IdNivel'
			AND ord.Estado = 1
			ORDER BY ord.Orden ASC";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdCon'] . ";";
	echo $mostrar['IdSen'] . ";";
	echo $mostrar['Sen'] . "|";
}

mysqli_close($conexion);
?>