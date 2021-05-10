import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Cita {

        private String fecha;
        private String hora;
        private String motivoCita;
        private Connection conexion;
        private final Scanner scanner = new Scanner(System.in);


        public void pedirDatos() {
            System.out.println("Fecha de la cita (dd/mm/yyyy): ");
            this.fecha = scanner.nextLine();
            System.out.println("Hora: ");
            this.hora = scanner.nextLine();
            System.out.println("Motivo: ");
            this.motivoCita = scanner.nextLine();
        }

        public void crearCita() {
            try {

                Class.forName("org.sqlite.JDBC");
                conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db");

                if (conexion != null) {
                    System.out.println("Conectado.");
                }

                Statement enunciado;
                assert conexion != null;
                enunciado = conexion.createStatement();

                enunciado.execute("INSERT INTO Citas (fecha, hora, motivo_cita) VALUES('" + this.fecha + "','" + this.hora + "','" + this.motivoCita + "');'");

                System.out.println("Registro Existoso");

                conexion.close();
                enunciado.close();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        public void mostrarCitas() {
            ResultSet resultado;

            try {
                Class.forName("org.sqlite.JDBC");
                conexion = DriverManager.getConnection("jdbc:sqlite:C:/Users/Serge/IdeaProjects/SistemaAdmon/db/SistemaAdmon.db");

                if(conexion != null) {
                    System.out.println("Conectado.");
                }

                assert conexion != null;
                PreparedStatement query = conexion.prepareStatement("SELECT * FROM Citas");

                resultado = query.executeQuery();

                while(resultado.next()) {
                    System.out.print("ID: ");
                    System.out.println(resultado.getInt("id"));

                    System.out.print("Fecha: ");
                    System.out.println(resultado.getString("fecha"));

                    System.out.print("Hora: ");
                    System.out.println(resultado.getString("hora"));

                    System.out.print("Motivo de la cita: ");
                    System.out.println(resultado.getString("motivo_cita"));

                    System.out.println("_________________");
                }

                conexion.close();
                query.close();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }
