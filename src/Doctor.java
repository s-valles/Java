import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Doctor {
    private String nombre;
    private String especialidad;
    private Connection conexion;
    private final Scanner scanner = new Scanner(System.in);


    public void pedirDatos() {
        System.out.println("Nombre: ");
        this.nombre = scanner.nextLine();
        System.out.println("Especialidad: ");
        this.especialidad = scanner.nextLine();
    }

    public void altaDoctor() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db");

            if(conexion != null) {
                System.out.println("Conectado.");
            }

            Statement entrada;
            assert conexion != null;
            entrada = conexion.createStatement();

            entrada.execute("INSERT INTO Doctores (nombre, especialidad) VALUES('"+this.nombre+"','"+this.especialidad+"');'");

            System.out.println("Registro Exitoso");

            entrada.close();
            conexion.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void mostrarDoctores() {
        ResultSet resultado;

        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db");

            if(conexion != null) {
                System.out.println("Conectado.");
            }

            assert conexion != null;
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM Doctores");

            resultado = query.executeQuery();

            while(resultado.next()) {
                System.out.print("ID: ");
                System.out.println(resultado.getInt("id"));

                System.out.print("Nombre: ");
                System.out.println(resultado.getString("nombre"));

                System.out.print("Especialidad: ");
                System.out.println(resultado.getString("especialidad"));

                System.out.println("___________________");
            }

            query.close();
            conexion.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
