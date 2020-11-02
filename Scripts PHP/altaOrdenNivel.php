<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdNivel = $_POST['IdNivel'];
$IdSena = $_POST['IdSena'];
$IdConsigna = $_POST['IdConsigna'];
$Orden = $_POST['Orden'];

if(empty($IdNivel)||(empty($IdSena)&&empty($IdConsigna))||empty($Orden))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdNivel, IdSena, IdConsigna FROM ordennivel WHERE IdNivel = '$IdNivel' AND IdSena = '$IdSena' AND IdConsigna = '$IdConsigna'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num==0){
	$consulta = "INSERT INTO ordennivel(IdNivel, IdSena, IdConsigna, Orden, Estado) VALUES ('$IdNivel', '$IdSena', '$IdConsigna', '$Orden', 1)";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		if(empty($IdSena)){
			echo "Consigna agregada al nivel correctamente.";
		}
		else{
			echo "Seña agregada al nivel correctamente.";
		}
	}
	else
	{
		if(empty($IdSena)){
			echo "Error al agregar la consigna al nivel.";
		}
		else{
			echo "Error al agregar la seña al nivel.";
		}
	}
}
else{
	if(empty($IdSena)){
		echo "La seña/consigna ya se encuentra en este nivel.";
	}
	else{
		echo "La seña ya se encuentra en este nivel.";
	}
}

mysqli_close($conexion);
?>