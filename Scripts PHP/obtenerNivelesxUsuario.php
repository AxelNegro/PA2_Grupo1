<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST["Usuario"];

$consulta = "SELECT niv.IdNivel IdNivel, niv.Nivel Nivel,
			CASE WHEN (SELECT COUNT(1) 
					   FROM nivelesxusuario nivxus
					   WHERE nivxus.IdNivel = niv.IdNivel
					   AND nivxus.Usuario = '$Usuario') > 0 
					   THEN '1' 
					   ELSE '0' 
					   END  Estado
			FROM niveles niv";
			
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdNivel'] . ";";
	echo $mostrar['Nivel'] . ";";
	echo $mostrar['Estado'] . "|";
}

mysqli_close($conexion);
?>