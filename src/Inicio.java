import java.util.Scanner;


public class Inicio {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String opcion;
        String res;
        String user, password;
        Usuario admin = new Usuario();
        admin.buscarDatosAdmin();

        do {

            System.out.println("Acceso al sistema...");
            System.out.println("Usuario: ");
            user = scanner.next();
            System.out.println("Contraseña: ");
            password = scanner.next();

            if (user.equals(admin.getUser()) && password.equals(admin.getPassword())) {
                System.out.println("Sistema de Administracion");
                System.out.println("1 - Alta de doctores.");
                System.out.println("2 - Alta pacientes.");
                System.out.println("3 - Crear una cita.");
                System.out.println("4 - Seleccionar un doctor.");
                System.out.println("5 - Lista de doctores.");
                System.out.println("6 - Lista de pacientes.");
                System.out.println("7 - Lista de citas.");
                System.out.println("8 - Informacion");
                System.out.println("0 - Salir.");
                System.out.println("Elija una opción: ");
                opcion = scanner.next();

                switch (opcion) {
                    case "1":
                        Doctor doctor = new Doctor();
                        doctor.pedirDatos();
                        doctor.altaDoctor();
                        break;

                    case "2":
                        Paciente paciente = new Paciente();
                        paciente.pedirDatos();
                        paciente.altaPaciente();
                        break;

                    case "3":
                        Cita cita = new Cita();
                        cita.pedirDatos();
                        cita.crearCita();
                        break;

                    case "4":
                        Relacion relacion = new Relacion();
                        relacion.pedirDatos();
                        relacion.relacionarInfo();
                        break;

                    case "5":
                        doctor = new Doctor();
                        doctor.mostrarDoctores();
                        break;

                    case "6":
                        paciente = new Paciente();
                        paciente.mostrarPacientes();
                        break;

                    case "7":
                        cita = new Cita();
                        cita.mostrarCitas();
                        break;

                    case "8":
                        relacion = new Relacion();
                        relacion.mostrarRelacion();
                        break;

                    case "0":
                        System.out.println("Sesion Terminada.");
                        System.exit(0);
                        break;

                }
            } else {
                System.out.println("Datos incorrectos, intentelo de nuevo.");
            }

            do {
                System.out.println("¿Ejecutar de nuevo?");
                System.out.println("Si / No: ");
                scanner.nextLine();
                res = scanner.next();
                res = res.toLowerCase();

                if (!res.equals("si") && !res.equals("no")) {
                    System.out.println("La respuesta es incorrecta");
                    System.out.println("Si / No: ");
                }

            } while (!res.equals("si") && !res.equals("no"));

            System.out.println();

        } while (res.equals("si"));

        System.out.println("Sesion Terminada.");

    }
}
