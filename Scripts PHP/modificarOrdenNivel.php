<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdOrden = $_POST['IdOrden'];
$IdNivel = $_POST['IdNivel'];
$IdSena = $_POST['IdSena'];
$IdConsigna = $_POST['IdConsigna'];
$Orden = $_POST['Orden'];

if(empty($IdOrden)||empty($IdNivel)||(empty($IdSena)&&empty($IdConsigna))||empty($Orden))
{
exit("Complete los datos.");
}

$consulta = "SELECT IdOrden FROM ordennivel WHERE IdOrden = '$IdOrden'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num>0){
	$consulta = "SELECT IdOrden FROM ordennivel WHERE IdNivel = '$IdNivel' AND IdSena='$IdSena' AND IdConsigna='$IdConsigna'";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	
	if($num==0){
	    if(empty($IdSena)){
		$consulta = "UPDATE ordennivel SET IdNivel='$IdNivel',IdSena=null,IdConsigna='$IdConsigna',Orden='$Orden' WHERE IdOrden = '$IdOrden'";
	    }
	    else{
	        $consulta = "UPDATE ordennivel SET IdNivel='$IdNivel',IdSena='$IdSena',IdConsigna=null,Orden='$Orden' WHERE IdOrden = '$IdOrden'";
	    }
		mysqli_query($conexion,$consulta);

		$num = mysqli_affected_rows($conexion);
		if($num>0)
		{
			if(empty($IdSena)){
				echo 1;
			}
			else{
				echo 2;
			}
		}
		else
		{
			echo "Error al modificar la orden.";
		}
	}
	else{
		echo "Ya existe un orden igual al ingresado.";
	}
}
else{
	echo "La orden ingresada no existe.";
}

mysqli_close($conexion);
?>