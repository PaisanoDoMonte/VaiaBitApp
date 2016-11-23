<?php

//Importar los métodos de Utils
include 'Utils.php';

//Ejecutamos la funcion según los parámetros enviados por POST
switch ($_POST["Funcion"]) {
    case "SetReserva":
        SetReserva($_POST["ProductoId"],$_POST["UsuarioId"],$_POST["Unidades"]);
        break;
}

function SetReserva($ProductoId, $UsuarioId, $Unidades) {
    $respuesta = executeSQL("INSERT INTO `ProductoReservaUsuario`"
            . "(`ProductoId`, `UsuarioId`, `Unidades`, `Fecha`) "
            . "VALUES (".$ProductoId.",".$UsuarioId.",".$Unidades.",'".date('Y-m-d H:i:s')."')");
    echo $respuesta;
}

?>