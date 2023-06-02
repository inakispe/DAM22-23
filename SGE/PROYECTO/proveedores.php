<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Lista de Proveedores</title>
    <link rel="stylesheet" type="text/css" href="estilo.css">
  </head>
  <body>
    <h1>Lista de Proveedores</h1>
    <table>
      <thead>
        <tr>
          <th>Nombre del proveedor</th>
          <th>Sede</th>
          <th>Teléfono</th>
          <th>Email</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <?php
          require('db.php');

          $stmt = $conn->prepare("SELECT * FROM Proveedores");
          $stmt->execute();
          $result = $stmt->fetchAll();

          foreach($result as $row){
            echo "<tr>";
            echo "<td>" .$row["NombreProveedor"] . "</td>";
            echo "<td>" . $row["Sede"] . "</td>";
            echo "<td>" . $row["Telefono"]. "</td>";
            echo "<td>" . $row["Email"]. "</td>";
            echo "<td>
                  <a href='ver_inventario.php?NombreProveedor=".$row["NombreProveedor"]."'>Ver inventario</a>
                  <a href='borrar_proveedor.php?NombreProveedor=".$row["NombreProveedor"]."'>Borrar</a>
                  </td>";
            echo "</tr>";
            
          }
        ?>
      </tbody>
    </table>
  </body>
</html>




<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Añadir Proveedor</title>
    <style>
      #formulario-container {
        display: none;
      }
    </style>
    <script>
      function mostrarFormulario() {
        var formulario = document.getElementById("formulario-container");
        formulario.style.display = "block";
      }
    </script>
  </head>
  <body>
    <h1>Añadir Proveedor</h1>
    <button id="mostrar-formulario" onClick="mostrarFormulario()">Agregar proveedor</button>
    <div id="formulario-container">
      <form method="post" action="insertar_proveedor.php">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>
        <label for="sede">Sede:</label>
        <input type="text" id="sede" name="sede" required><br><br>
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <input type="submit" name="submit" value="Añadir Proveedor">
      </form>
    </div>
  </body>
</html>



<!--
<script>
     function myFunction(id) {
        let text;
        if (confirm("Press a button!") == true) {
            window.location.href = "borrarGolfista.php?id=" + id;
        } else {
            text = "You canceled!";
        }
        document.body.innerHTML = text;
    }
</script>
-->