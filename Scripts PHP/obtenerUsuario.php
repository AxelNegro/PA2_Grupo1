<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST["Usuario"];

if(empty(Usuario))
{
exit("Complete los datos.");
}

$consulta = "SELECT Contrasena, Nombre, Apellido, Email, Estado, Tipo FROM usuarios WHERE Usuario = '$Usuario'";
$resultado = mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	while($mostrar=mysqli_fetch_array($resultado)){
		echo $mostrar['Contrasena'] . ";";
		echo $mostrar['Nombre'] . ";";
		echo $mostrar['Apellido'] . ";";
		echo $mostrar['Email'] . ";";
		echo $mostrar['Estado'] . ";";
		echo $mostrar['Tipo'] . ";";
	}
}else{
	echo "El usuario especificado no existe.";
}

mysqli_close($conexion);
?>