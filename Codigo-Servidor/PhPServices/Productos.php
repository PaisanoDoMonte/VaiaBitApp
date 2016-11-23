<?php

//Cabecera para indicar que devolvemos un JSON
header('Content-type: application/json');
//Importar los métodos de Utils
include 'Utils.php';

//Ejecutamos la funcion según los parámetros enviados por POST
switch ($_POST["Funcion"]) {
    case "GetProductos":
        GetProductos();
        break;
    case "GetProductosGraficas":
        GetProductosGraficas();
        break;
    case "GetProductosOrdenadores":
        GetProductosOrdenadores();
        break;
    case "GetProductosTeclados":
        GetProductosTeclados();
        break;
    case "GetProductosNovedad":
        GetProductosNovedad();
        break;
    case "GetProductosOferta":
        GetProductosOferta();
        break;
    case "GetProducto":
        GetProducto($_POST["Id"]);
        break;
    case "GetProductosReservaUsuario":
        GetProductosReservaUsuario($_POST["UsuarioId"]);
        break;
    case "SetProducto":       
        break;
}

function GetProductos() {
    $Productos = getArraySQL("SELECT P.id,P.FabricanteId,Codigo,P.PartNumber,P.Nombre,
P.Descripcion,P.DescripcionCorta,P.Unidades,P.Categoria,P.Novedad, P.Imagen,
PP.Descuento, PP.FechaDescuentoInicio, PP.FechaDescuentoFin, F.Nombre As Fabricante,
PP.Precio, PP.Iva FROM Producto P 
Inner Join ProductoPrecio PP ON P.id = PP.ProductoId
Inner Join Fabricante F ON P.FabricanteId = F.id");
    echo json_encode($Productos);
}

function GetProductosGraficas() {
    $Productos = getArraySQL("SELECT P.id,P.FabricanteId,Codigo,P.PartNumber,P.Nombre,
P.Descripcion,P.DescripcionCorta,P.Unidades,P.Categoria,P.Novedad, P.Imagen,
PP.Descuento, PP.FechaDescuentoInicio, PP.FechaDescuentoFin, F.Nombre As Fabricante,
PP.Precio, PP.Iva FROM Producto P 
Inner Join ProductoPrecio PP ON P.id = PP.ProductoId
Inner Join Fabricante F ON P.FabricanteId = F.id Where P.Categoria = 'Graficas'");
    echo json_encode($Productos);
}

function GetProductosOrdenadores() {
    $Productos = getArraySQL("SELECT P.id,P.FabricanteId,Codigo,P.PartNumber,P.Nombre,
P.Descripcion,P.DescripcionCorta,P.Unidades,P.Categoria,P.Novedad, P.Imagen,
PP.Descuento, PP.FechaDescuentoInicio, PP.FechaDescuentoFin, F.Nombre As Fabricante,
PP.Precio, PP.Iva FROM Producto P 
Inner Join ProductoPrecio PP ON P.id = PP.ProductoId
Inner Join Fabricante F ON P.FabricanteId = F.id Where P.Categoria = 'Ordenadores'");
    echo json_encode($Productos);
}

function GetProductosTeclados() {
    $Productos = getArraySQL("SELECT P.id,P.FabricanteId,Codigo,P.PartNumber,P.Nombre,
P.Descripcion,P.DescripcionCorta,P.Unidades,P.Categoria,P.Novedad, P.Imagen,
PP.Descuento, PP.FechaDescuentoInicio, PP.FechaDescuentoFin, F.Nombre As Fabricante,
PP.Precio, PP.Iva FROM Producto P 
Inner Join ProductoPrecio PP ON P.id = PP.ProductoId
Inner Join Fabricante F ON P.FabricanteId = F.id Where P.Categoria = 'Teclados'");
    echo json_encode($Productos);
}

function GetProductosNovedad() {
    $Productos = getArraySQL("SELECT P.id,P.FabricanteId,Codigo,P.PartNumber,P.Nombre,
P.Descripcion,P.DescripcionCorta,P.Unidades,P.Categoria,P.Novedad, P.Imagen,
PP.Descuento, PP.FechaDescuentoInicio, PP.FechaDescuentoFin, F.Nombre As Fabricante,
PP.Precio, PP.Iva FROM Producto P 
Inner Join ProductoPrecio PP ON P.id = PP.ProductoId
Inner Join Fabricante F ON P.FabricanteId = F.id Where P.Novedad = true");
    echo json_encode($Productos);
}

function GetProductosOferta() {
    $Productos = getArraySQL("SELECT P.id,P.FabricanteId,Codigo,P.PartNumber,P.Nombre,
P.Descripcion,P.DescripcionCorta,P.Unidades,P.Categoria,P.Novedad, P.Imagen,
PP.Descuento, PP.FechaDescuentoInicio, PP.FechaDescuentoFin, F.Nombre As Fabricante,
PP.Precio, PP.Iva FROM Producto P 
Inner Join ProductoPrecio PP ON P.id = PP.ProductoId
Inner Join Fabricante F ON P.FabricanteId = F.id Where PP.Descuento > 0");
    echo json_encode($Productos);
}

function GetProductosReservaUsuario($UsuarioId) {
    //Concatenamos con '.'
    $Producto = getArraySQL("SELECT P.id,P.FabricanteId,Codigo,P.PartNumber,P.Nombre,
P.Descripcion,P.DescripcionCorta,P.Unidades,P.Categoria,P.Novedad, P.Imagen,
PP.Descuento, PP.FechaDescuentoInicio, PP.FechaDescuentoFin, F.Nombre As Fabricante,
PP.Precio, PP.Iva FROM Producto P 
Inner Join ProductoPrecio PP ON P.id = PP.ProductoId
Inner Join Fabricante F ON P.FabricanteId = F.id
Inner Join ProductoReservaUsuario PRU ON P.id=PRU.ProductoId
Inner Join Usuario U on U.id=PRU.UsuarioId where U.id=".$UsuarioId);
    echo json_encode($Producto);
}

function GetProducto($Id) {
    //Concatenamos con '.'
    $Producto = getArraySQL("SELECT P.id,P.FabricanteId,Codigo,P.PartNumber,P.Nombre,
P.Descripcion,P.DescripcionCorta,P.Unidades,P.Categoria,P.Novedad, P.Imagen,
PP.Descuento, PP.FechaDescuentoInicio, PP.FechaDescuentoFin, F.Nombre As Fabricante,
PP.Precio, PP.Iva FROM Producto P 
Inner Join ProductoPrecio PP ON P.id = PP.ProductoId
Inner Join Fabricante F ON P.FabricanteId = F.id Where P.Id=" . $Id);
    echo json_encode($Producto);
}

?>