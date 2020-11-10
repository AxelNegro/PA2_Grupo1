<?php
error_reporting(0);

$conexion = mysqli_connect("localhost","id15283371_pagrupo1","Prog-Avanza2","id15283371_ensenartelsa");

if(!$conexion)
{
exit("Error al intentar conectarse a la base de datos.");
}

$IdConsigna = $_POST['IdConsigna'];
$IdNivel = $_POST['IdNivel'];
$IdSena = $_POST['IdSena'];
$URL_Imagen = $_POST['URL_Imagen'];
$Descripcion = $_POST['Descripcion'];


if(empty($URL_Imagen)||empty($Descripcion)||empty($IdSena)||empty($IdConsigna)||empty($IdNivel))
{
exit("Complete los datos.");
}


$consulta = "INSERT INTO consignas(URLImagen, Descripcion, Estado) VALUES ('$URL_Imagen', '$Descripcion', 1)";
mysqli_query($conexion,$consulta);

$num = mysqli_affected_rows($conexion);
if($num>0)
{
    $consulta = "INSERT INTO ordennivel(IdNivel,IdSena,IdConsigna,Orden,Estado) VALUES ('$IdNivel','$IdSena','$IdConsigna',1,1)";
    mysqli_query($conexion,$consulta);
    
    $num = mysqli_affected_rows($conexion);
    if($num>0)
    {
	    echo "Consigna dada de alta correctamente.";
    }
}
else
{
	echo "Error al dar de alta la consigna.";
}

mysqli_close($conexion);
?>
