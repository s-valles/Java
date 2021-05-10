import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Relacion {
    private int idDoctor;
    private int idPaciente;
    private int idCita;
    private Connection conexion;
    private final Scanner scanner = new Scanner(System.in);


    public void pedirDatos() {
        System.out.println("ID del Doctor: ");
        this.idDoctor = scanner.nextInt();
        System.out.println("ID del Paciente: ");
        this.idPaciente = scanner.nextInt();
        System.out.println("ID de la Cita: ");
        this.idCita = scanner.nextInt();
    }

    public void relacionarInfo() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection ignored = conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db")) {
            }

            if (conexion != null) {
                System.out.println("Conectado.");
            }

            Statement enunciado;
            assert conexion != null;
            enunciado = conexion.createStatement();

            enunciado.execute("INSERT INTO Relacion (idDoctor, idPaciente, idCita) VALUES('" + this.idDoctor + "','" + this.idPaciente + "','" + this.idCita + "');'");

            System.out.println("Ralacion de Datos Exitosa");

            enunciado.close();
            conexion.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void mostrarRelacion() {
        ResultSet resultado;

        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db");

            if(conexion != null) {
                System.out.println("Conectado.");
            }

            assert conexion != null;
            try (PreparedStatement query = conexion.prepareStatement("SELECT Doctores.*, Pacientes.*, Citas.* FROM Relacion INNER JOIN Doctores "
                    + "ON Relacion.idDoctor = Doctores.id "
                    + "INNER JOIN Pacientes ON Relacion.idPaciente = Pacientes.id "
                    + "INNER JOIN Citas ON Relacion.idCita = Citas.id")) {

                resultado = query.executeQuery();

                while (resultado.next()) {
                    System.out.println("-----Doctor-----");
                    System.out.print("ID: ");
                    System.out.println(resultado.getString(1));
                    System.out.print("Nombre: ");
                    System.out.println(resultado.getString(2));
                    System.out.print("Especialidad: ");
                    System.out.println(resultado.getString(3));
                    System.out.println();

                    System.out.println("-----Paciente-----");
                    System.out.print("ID: ");
                    System.out.println(resultado.getString(4));
                    System.out.print("Nombre: ");
                    System.out.println(resultado.getString(5));
                    System.out.println();

                    System.out.println("-----Cita-----");
                    System.out.print("ID: ");
                    System.out.println(resultado.getString(6));
                    System.out.print("Fecha: ");
                    System.out.println(resultado.getString(7));
                    System.out.print("Hora: ");
                    System.out.println(resultado.getString(8));
                    System.out.print("Motivo: ");
                    System.out.println(resultado.getString(9));

                    System.out.println("________________");
                    System.out.println();
                }

            }
            conexion.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
