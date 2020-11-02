<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdConsigna = $_POST["IdConsigna"];

if(empty($IdConsigna))
{
exit("Complete los datos.");
}

$consulta = "SELECT opc.Descripcion AS Desc, opc.Resultado AS Res FROM opciones opc
			INNER JOIN opcionesxconsigna opcxcon 
			ON opcxcon.IdConsigna = '$IdConsigna'
			AND opcxcon.IdOpcion = opc.IdOpcion
			WHERE opc.Estado = 1";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['Desc'] . ";";
	echo $mostrar['Res'] . "|";
}

mysqli_close($conexion);
?>