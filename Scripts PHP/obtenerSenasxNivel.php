<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdOpcion = $_POST["IdNivel"];

if(empty($IdNivel))
{
exit("Complete los datos.");
}


$consulta = "SELECT sen.IdSena AS IdSen, sen.Sena AS Sen FROM senas sen
			INNER JOIN ordennivel ord ON ord.IdNivel = '$IdNivel'";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdSen'] . ";";
	echo $mostrar['Sen'] . "|";
}

mysqli_close($conexion);
?>