<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST["Usuario"];

$consulta = "SELECT IdNivel FROM nivelesxusuario WHERE Usuario = '$Usuario'";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdNivel'] . "|";
}

mysqli_close($conexion);
?>