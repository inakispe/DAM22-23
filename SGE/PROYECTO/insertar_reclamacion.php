<?php
  require('db.php');

  if (isset($_POST['submit'])) {
    $idCliente = $_POST['IdCliente'];
    $Titulo = $_POST['Titulo'];
    $fechaReclamacion = $_POST['FechaReclamacion'];
    $fechaSolucion = $_POST['FechaSolucion'];
    $idDept= $_POST['idDept']
    $descripcion=$_POST['Descripcion']
    $solucion=$_POST['Solucion']
    $sql = "INSERT INTO Reclamaciones (IdCliente, Titulo, FechaReclamacion, FechaSolucion, idDept, Descripcion, Solucion) VALUES (:IdCliente, :Titulo, :FechaReclamacion, :FechaSolucion, :idDept, :Descripcion,:Solucion)";
    $stmt= $conn->prepare($sql);

    $stmt->bindParam(':IdCliente', $idCliente);
    $stmt->bindParam(':Titulo', $Titulo);
    $stmt->bindParam(':FechaReclamacion', $fechaReclamacion);
    $stmt->bindParam(':FechaSolucion', $fechaSolucion);
    $stmt->bindParam(':idDept', $idDept);
    $stmt->bindParam(':Descripcion', $descripcion);
    $stmt->bindParam(':Solucion', $solucion);

    if ($stmt->execute()) {
      header("Location: reclamaciones.php");
      exit();
    } else {
      echo "Error: " . $sql . "<br>" . $conn->error;
    }
  }
?>
