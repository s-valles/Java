import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Paciente {

    private String nombre;
    private Connection conexion;
    private final Scanner scanner = new Scanner(System.in);


    public void pedirDatos() {
        System.out.println("Nombre: ");
        this.nombre = scanner.nextLine();
    }

    public void altaPaciente() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db");

            if (conexion != null) {
                System.out.println("Conectado.");
            }

            Statement enunciado;
            assert conexion != null;
            enunciado = conexion.createStatement();

            enunciado.execute("INSERT INTO Pacientes (nombre) VALUES('" + this.nombre + "');'");

            System.out.println("Registro Exitoso");

            enunciado.close();
            conexion.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void mostrarPacientes() {
        ResultSet resultado;

        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db");

            if(conexion != null) {
                System.out.println("Conectado.");
            }

            assert conexion != null;
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM Pacientes");

            resultado = query.executeQuery();

            while(resultado.next()) {
                System.out.print("ID: ");
                System.out.println(resultado.getInt("id"));

                System.out.print("Nombre: ");
                System.out.println(resultado.getString("nombre"));

                System.out.println("________________");
            }

            query.close();
            conexion.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
