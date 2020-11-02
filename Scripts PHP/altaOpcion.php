<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$Descripcion = $_POST['Descripcion'];
$Resultado = $_POST['Resultado'];
$IdConsigna = $_POST['IdConsigna'];

if(empty($Descripcion)||empty($Resultado)||empty($IdConsigna))
{
exit("Complete los datos.");
}

$consulta = "INSERT INTO opciones(Descripcion, Resultado, Estado) VALUES ('$Descripcion', '$Resultado', 1); SELECT LAST_INSERT_ID() as IdOpcion;";
$resultado = mysqli_query($conexion,$consulta);

if(!empty($resultado))
{
	$IdOpcion = "";
	while($mostrar=mysqli_fetch_array($resultado)){
		$IdOpcion = $mostrar['IdOpcion'];
	}
	if (!empty($id)){
		$consulta = "INSERT INTO opciones_x_consigna(IdConsigna, IdOpcion) VALUES ('$IdConsigna','$IdOpcion')";
		
		$num = mysqli_affected_rows($conexion);
		
		if($num>0)
		{
			echo "Opción dada de alta correctamente.";
		}
		else
		{
			echo "Error al dar de alta la opción.";
		}
		
	}
	else{
		echo "Error al dar de alta la opción.";
	}
}
else
{
	echo "Error al dar de alta la opción.";
}

mysqli_close($conexion);
?>