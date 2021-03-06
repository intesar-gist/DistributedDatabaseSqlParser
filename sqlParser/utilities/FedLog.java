package sqlParser.utilities;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/** Class to log the processed statements in a file
 *  Created by: X_Team_Member
 * */
public class FedLog {
    public static void logLine (String line) throws IOException {
        Date date = new Date();
        int hh = date.getHours();
        int mm = date.getMinutes();
        int ss = date.getSeconds();

        byte data[] = ("<" + hh + ":" + mm + ":" + ss + "> " + line + "\n").getBytes();
        Path p = Paths.get("./src/sqlParser/fedprot.txt");

        OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND));
        out.write(data, 0, data.length);
        out.close();
    }
}
