<?php

  $json = file_get_contents('../json/variables.json');
  $json_data = json_decode($json, true);

  //print_r($json_data);
  $servername = $json_data["servername"];
  $username = $json_data["username"];
  $password = $json_data["password"];
  $database = $json_data["database"];

  //transformar la cadena de texto en otra cadena, CODIFICA PEDRO EN UNA CONTRASEÑA
  //print_r(hash_algos());
  //EJEMPLO:
  /*
    $p1 = "PEDROOO";
    $salt = random_int(1, 10);
    echo $salt;
    echo "<br>";
    echo hash("sha256", $p1);
  */
  /* 
  $p1 = "PEDROOO";
    $pAntes = $salt.$p1;
    echo hash("sha256", $pAntes);
**/

  try {
    $conn = new PDO("mysql:host=$servername;dbname=$database;charset=utf8", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connected successfully";
  } catch(PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
  }
?>