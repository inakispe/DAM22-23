<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <br>Escribir la contraseña <br>
    <form action="" method="post">
    Contraseña para entrar en home <input type= "text" name="passw" id="passw">
    <input type="submit" value="Enviar">

    </form>

</body>
</html>
<?php
//El isset, es una variable que si existe funcione, en este caso
if (isset($_POST['passw'])){
$test= $_POST['passw'];
$cont=5;
while ($test!='miau'){
    $cont--;
    echo "La contraseña es imposible"
    if ($cont==0)
    header ("Location:desc.php")
    }   
    //Con esto el header location(que es lo de arriba se cambia)
    header ("Location:home.php")
?>
}