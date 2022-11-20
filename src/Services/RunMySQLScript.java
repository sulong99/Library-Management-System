package Services;

import Data.ConnectionFactory;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;

/**
 * Klasa odpowiadająca za czytanie poleceń MySQL z pliku
 */
public class RunMySQLScript {
    /**
     * Metoda uruchamiająca skrypt SQL z pliku.
     * @param sqlScriptFilePath ścieżka do pliku
     * @throws FileNotFoundException wyjątek zwracany w przypadku błędnej ścieżki pliku
     */
    public void runMysqlScript(String sqlScriptFilePath) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            ScriptRunner runner = new ScriptRunner(connection);
            Reader br = new BufferedReader(new FileReader(sqlScriptFilePath));
            runner.runScript(br);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
