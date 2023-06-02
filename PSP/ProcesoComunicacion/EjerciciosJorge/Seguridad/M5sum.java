package Seguridad;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
//Este ejercicio thay que añadirle el apache commons al mavens

public class M5sum {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Debes proporcionar la ruta de un archivo");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.err.println("El archivo no existe");
            System.exit(1);
        }

        FileInputStream fis = new FileInputStream(file);
        String md5 = DigestUtils.md5Hex(fis);
        fis.close();

        System.out.println(md5);
    }
}