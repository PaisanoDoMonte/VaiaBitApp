<?php

function connectDB() {
    //Datos
    $server = "localhost";
    $user = "root";
    $pass = "4880.contrasenha";
    $bd = "VaiaBitBD";


    $conexion = mysqli_connect($server, $user, $pass, $bd);

    //if (!$conexion) {
        //echo 'La conexion de la base de datos se ha hecho satisfactoriamente';
    //} else {
        //echo 'Ha sucedido un error inexperado en la conexion de la base de datos';
    //}

    return $conexion;
}

function disconnectDB($conexion) {

    $close = mysqli_close($conexion);

    //if ($close) {
        //echo 'La desconexion de la base de datos se ha hecho satisfactoriamente';
    //} else {
        //echo 'Ha sucedido un error inexperado en la desconexion de la base de datos';
    //}

    return $close;
}

function getArraySQL($sql) {
    //Creamos la conexión 
    $conexion = connectDB();

    mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

    if (!$result = mysqli_query($conexion, $sql))
        die(); //si la conexión cancelar programa

    $rawdata = array(); //creamos un array
    //guardamos en un array multidimensional todos los datos de la consulta
    $i = 0;

    while ($row = mysqli_fetch_assoc($result)) {
        $rawdata[$i] = $row;
        $i++;
    }

    disconnectDB($conexion); //desconectamos la base de datos

    return $rawdata; //devolvemos el array
}

function executeSQL($sql) {
    //Creamos la conexión 
    $conexion = connectDB();

    mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

  
    if (mysqli_query($conexion, $sql)) {
        echo "Operacion realizada Correctamente";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
    }

    disconnectDB($conexion); //desconectamos la base de datos
}

?>