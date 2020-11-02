<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdOpcion = $_POST["IdSena"];

if(empty($IdSena))
{
exit("Complete los datos.");
}


$consulta = "SELECT Sena, URLImagen, Descripcion FROM senas WHERE IdSena = '$IdSena'";
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['Sena'] . ";";
	echo $mostrar['URLImagen'] . ";";
	echo $mostrar['Descripcion'];
}

mysqli_close($conexion);
?>