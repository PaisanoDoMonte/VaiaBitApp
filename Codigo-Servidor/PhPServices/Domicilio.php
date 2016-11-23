<?php

//Importar los métodos de Utils
include 'Utils.php';

//Ejecutamos la funcion según los parámetros enviados por POST
switch ($_POST["Funcion"]) {
    case "SetDomicilio":
        SetDomicilio($_POST["UsuarioId"], $_POST["Direccion1"], $_POST["Direccion2"], $_POST["Ciudad"], $_POST["Provincia"], 
                $_POST["Pais"], $_POST["CodigoPostal"] );
        break;
    case "UpdateDomicilio":
        UpdateDomicilio($_POST["UsuarioId"], $_POST["Direccion1"], $_POST["Direccion2"], $_POST["Ciudad"], $_POST["Provincia"], 
                $_POST["Pais"], $_POST["CodigoPostal"] );
        break;
}

function SetDomicilio($UsuarioId, $Direccion1, $Direccion2, $Ciudad, $Provincia, $Pais, $CodigoPostal) {
    $respuesta = executeSQL("INSERT INTO `Domicilio`(`UsuarioId`, `Direccion1`, `Direccion2`, `Ciudad`, `Provincia`, `Pais`, `CodigoPostal`) 
    VALUES (".$UsuarioId.",'".$Direccion1."','".$Direccion2."','".$Ciudad."','".$Provincia."','".$Pais."',".$CodigoPostal.")");
    echo $respuesta;
}

function UpdateDomicilio($UsuarioId, $Direccion1, $Direccion2, $Ciudad, $Provincia, $Pais, $CodigoPostal) {
    $respuesta = executeSQL("UPDATE `Domicilio` SET `Direccion1`='".$Direccion1."',`Direccion2`='".$Direccion2."',`Ciudad`='".$Ciudad."',
    `Provincia`='".$Provincia."',`Pais`='".$Pais."',`CodigoPostal`=".$CodigoPostal."
    WHERE UsuarioId = ".$UsuarioId);
    echo $respuesta;
}

?>

