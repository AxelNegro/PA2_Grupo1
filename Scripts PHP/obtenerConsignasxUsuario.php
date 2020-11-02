<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST["Usuario"];
$IdNivel = $_POST["IdNivel"];

if(empty(id))
{
exit("Complete los datos.");
}

$consulta = "SELECT cxu.IdConsigna AS IdCon FROM consignasxusuario cxu 
			 INNER JOIN ordennivel ord 
			 ON ord.IdConsigna = cxu.IdConsigna 
			 AND ord.IdNivel = '$IdNivel'
			 WHERE cxu.Usuario = '$Usuario'";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdCon'] . "|";
}

mysqli_close($conexion);
?>