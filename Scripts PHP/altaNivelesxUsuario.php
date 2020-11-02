<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST['Usuario'];
$IdNivel = $_POST['IdNivel'];

if(empty($Usuario)||empty($IdNivel))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdNivel, Usuario FROM niveles_x_usuario WHERE IdNivel = '$IdNivel' AND Usuario = '$Usuario'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num==0){
	$consulta = "INSERT INTO niveles_x_usuario(IdNivel, Usuario) VALUES ('$IdNivel', '$Usuario')";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		echo "Nivel terminado correctamente.";
	}
	else
	{
		echo "Hubo un error al terminar el nivel.";
	}
}
else{
	echo "Hubo un error al terminar el nivel.";
}

mysqli_close($conexion);
?>