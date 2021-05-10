import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Usuario {

    String user;
    String password;


    public void buscarDatosAdmin() {
        ResultSet resultado;

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db");

            if (conexion != null) {
                System.out.println("Conectado.");
            }

            assert conexion != null;
            PreparedStatement query = conexion.prepareStatement("SELECT user, password FROM Usuarios WHERE id = 1");

            resultado = query.executeQuery();
            this.user = resultado.getString("user");
            this.password = resultado.getString("password");

            query.close();
            conexion.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }
}
