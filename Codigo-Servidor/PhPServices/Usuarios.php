<?php

//Importar los métodos de Utils
include 'Utils.php';

//Ejecutamos la funcion según los parámetros enviados por POST
switch ($_POST["Funcion"]) {
    case "SetUsuario":
        SetUsuario($_POST["Login"],$_POST["PassWord"],$_POST["Nombre"],$_POST["Apellidos"],$_POST["Correo"], $_POST["FechaNacimiento"]
                ,$_POST["Telefono1"],$_POST["Telefono2"]);
        break;
    case "GetUsuario":
        GetUsuario($_POST["Login"],$_POST["PassWord"]);
        break;
    case "UpdateUsuario":
        UpdateUsuario($_POST["Id"],$_POST["Login"],$_POST["PassWord"],$_POST["Nombre"],$_POST["Apellidos"],$_POST["Correo"], $_POST["FechaNacimiento"]
                ,$_POST["Telefono1"],$_POST["Telefono2"]);
        break;
}

function SetUsuario($login, $password, $nombre, $apellidos,$correo, $fechaNacimiento, $telefono1, $telefono2) {
    $respuesta = executeSQL("INSERT INTO `Usuario`(`RolId`, `Login`, `PassWord`, `Correo`, `Nombre`, `Apellidos`, `FechaNacimiento`, 
	`Telefono1`, `Telefono2`, `FechaRegistro`) VALUES (
	(select id from Rol where nombre = 'Registrado'), 
        '".$login."', '".$password."', '".$correo."', '".$nombre."','".$apellidos."',
	'".$fechaNacimiento."', '".$telefono1."','".$telefono2."', '".date('Y-m-d H:i:s')."'
    );");
    echo $respuesta;
}

function GetUsuario($login, $password) {
    $respuesta = getArraySQL("SELECT U.id, U.RolId, U.Login, U.PassWord, U.Correo, U.Nombre, U.Apellidos, U.FechaNacimiento, U.Telefono1, U.Telefono2, U.FechaRegistro,
        D.UsuarioId, D.Direccion1, D.Direccion2, D.Ciudad, D.Provincia, D.Pais, IFNULL(D.CodigoPostal,0) As CodigoPostal FROM `Usuario` U
        Left JOIN Domicilio D ON D.UsuarioId = U.id 
        Where Login = '".$login."' and PassWord = '".$password."' ");
    echo json_encode($respuesta);
}

function UpdateUsuario($id, $login, $password, $nombre, $apellidos,$correo, $fechaNacimiento, $telefono1, $telefono2) {
    $respuesta = executeSQL("UPDATE `Usuario` SET `Login`='".$login."',`PassWord`='".$password."',`Correo`='".$correo."',`Nombre`='".$nombre."',
    `Apellidos`='".$apellidos."',`FechaNacimiento`='".$fechaNacimiento."',`Telefono1`='".$telefono1."',`Telefono2`='".$telefono2."'
    WHERE id = ".$id);
    echo $respuesta;
}



?>
