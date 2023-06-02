<?php
  require('db.php');

  if (isset($_POST['submit'])) {
    $nombreProveedor = $_POST['nombre'];
    $sede = $_POST['sede'];
    $telefono = $_POST['telefono'];
    $email = $_POST['email'];

    $sql = "INSERT INTO Proveedores (NombreProveedor, Sede, Telefono, Email) VALUES (:nombreProveedor, :sede, :telefono, :email)";
    $stmt= $conn->prepare($sql);

    $stmt->bindParam(':nombreProveedor', $nombreProveedor);
    $stmt->bindParam(':sede', $sede);
    $stmt->bindParam(':telefono', $telefono);
    $stmt->bindParam(':email', $email);

    if ($stmt->execute()) {
      header("Location: proveedores.php");
      exit();
    } else {
      echo "Error: " . $sql . "<br>" . $conn->error;
    }
  }
?>
