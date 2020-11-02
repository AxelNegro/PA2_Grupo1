<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdOpcion = $_POST["IdOpcion"];

if(empty($IdOpcion))
{
exit("Complete los datos.");
}

$consulta = "SELECT Descripcion, Resultado, Estado FROM opciones WHERE IdOpcion = '$IdOpcion'";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['Descripcion'] . ";";
	echo $mostrar['Resultado'] . ";";
	echo $mostrar['Estado'];
}


mysqli_close($conexion);
?>