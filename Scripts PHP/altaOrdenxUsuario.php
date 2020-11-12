<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Usuario = $_POST['Usuario'];
$IdOrden = $_POST['IdOrden'];


if(empty($Usuario)||empty($IdOrden))
{
exit("Complete los datos.");
}

$consulta = "SELECT Usuario, IdOrden FROM ordenxusuario WHERE Usuario = '$Usuario' AND IdOrden = '$IdOrden'";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);

if($num==0){
	$consulta = "INSERT INTO ordenxusuario(Usuario, IdOrden) VALUES ('$Usuario', '$IdOrden')";
	mysqli_query($conexion,$consulta);

	$num = mysqli_affected_rows($conexion);
	if($num>0)
	{
		$consulta = "SELECT ord.IdNivel IdNivel,
					CASE WHEN (SELECT COUNT(1)
								FROM ordennivel ord2
								WHERE ord2.IdNivel = ord.IdNivel
								AND ord2.Estado = '1'
								AND (CASE WHEN ord2.IdConsigna IS NOT NULL THEN 
									 (SELECT con2.ESTADO
									 FROM consignas con2
									 WHERE con2.IdConsigna = ord2.IdConsigna)
									 ELSE '1'
									 END = '1')) = COUNT(1) 
								THEN '1' 
								ELSE '0' END Igual
					FROM ordenxusuario ordxus
					INNER JOIN ordennivel ord ON ord.IdOrden = ordxus.IdOrden
					WHERE Usuario = '$Usuario'
					AND ord.IdNivel = (SELECT IdNivel 
									   FROM ordennivel ord3
									   WHERE ord3.IdOrden = '$IdOrden')
					AND ord.Estado = '1'
					AND (CASE WHEN ord.IdConsigna IS NOT NULL THEN 
						 (SELECT con.ESTADO
						 FROM consignas con
						 WHERE con.IdConsigna = ord.IdConsigna)
						 ELSE '1'
						 END = '1')";
		$resultado = mysqli_query($conexion,$consulta);

		while($mostrar=mysqli_fetch_array($resultado)){
			$Igual = $mostrar['Igual'];
			$IdNivel = $mostrar['IdNivel'];
		}
		if($Igual == "1"){
			$consulta = "INSERT INTO nivelesxusuario(IdNivel, Usuario) VALUES ('$IdNivel', '$Usuario')";
			mysqli_query($conexion,$consulta);

			$num = mysqli_affected_rows($conexion);
			if($num>0)
			{
				echo "Nivel completado correctamente.";
			}
			else
			{
				echo "Nivel ya completado.";
			}
		}else{
			echo "Ejercicio hecho correctamente.";
		}
	}
	else
	{
		echo "Ejercicio ya completado.";
	}
}
else{
	echo "El ejercicio ya se encuentra completado.";
}

mysqli_close($conexion);
?>