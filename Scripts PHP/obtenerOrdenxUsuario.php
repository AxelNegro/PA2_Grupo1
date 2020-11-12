<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST["Usuario"];
$IdNivel = $_POST["IdNivel"];

if(empty($Usuario)||empty($IdNivel)){
	exit ("Complete los datos.");
}

$consulta = "SELECT ord.IdOrden, ord.IdConsigna IdConsigna, ord.IdSena IdSena, 
        (SELECT sen.Sena   
         FROM senas sen   
         WHERE sen.IdSena = ord.IdSena) Sena,
		CASE WHEN (SELECT COUNT(1) 
				   FROM ordenxusuario ordxus
				   WHERE ordxus.IdOrden = ord.IdOrden
				   AND ordxus.Usuario = 'asd') > 0 
				   THEN '1' 
				   ELSE '0' 
				   END  Estado
		FROM ordennivel ord
		WHERE ord.IdNivel = 1
		AND ord.Estado = '1'
		AND (CASE WHEN ord.IdConsigna IS NOT NULL THEN 
			 (SELECT con.ESTADO
			 FROM consignas con
			 WHERE con.IdConsigna = ord.IdConsigna)
			 ELSE '1'
			 END = '1')";
			
$resultado = mysqli_query($conexion,$consulta);

while($mostrar=mysqli_fetch_array($resultado)){
	echo $mostrar['IdOrden'] . ";";
	echo $mostrar['IdConsigna'] . ";";
	echo $mostrar['IdSena'] . ";";
	echo $mostrar['Sena'] . ";";
	echo $mostrar['Estado'] . "|";
}

mysqli_close($conexion);
?>