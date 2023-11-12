/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital;
import java.util.*;
/**
 *
 * @author Nayeli
 */
public class Hospital {

    private static final ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private static final ArrayList<Medico> listaMedicos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
 
        do {
            System.out.println("\n| Menu |");  
            System.out.println("1 -> Registrar datos del medico");
            System.out.println("2 -> Registrar datos de paciente.");
            System.out.println("3 -> Eliminar datos de un paciente");
            System.out.println("4 -> Modificar datos de un paciente");
            System.out.println("5 -> Mostrar peso que mas se repite");
            System.out.println("6 -> Mostrar cantidad de pacientes con el peso que mas se repite.");
            System.out.println("7 -> Mostrar peso mayor y menor");
            System.out.println("8 -> Mostrar cantidad de personas que corresponde a cada rango de pesos");
            System.out.println("9 -> Mostrar lista de pacientes ordenados por apellidos");
            System.out.println("10 -> Mostrar doctor del paciente.");
            System.out.println("11 -> Buscar doctores por especialidad");
            System.out.println("12 -> Salir.");

            System.out.print("\nSeleccione una operacion: ");
            opcion = scanner.nextInt(); 

            System.out.print("\n"); 

            switch (opcion) { 
                case 1:
                    registrarMedico();
                    break;
                case 2:
                    registrarPaciente();
                    break;
                case 3:
                    eliminarPaciente();
                    break;
                case 4:
                    modificarPaciente();
                    break;
                case 5:
                    mostrarPesoMasRepite();
                    break;
                case 6:
                    mostrarCantidadPacientesPesoRepite();
                    break;
                case 7:
                    mostrarPesoMayorYMenor();
                    break;
                case 8:
                    mostrarCantidadPersonasPorRangoPeso();
                    break;
                case 9:
                    mostrarPacientesOrdenadosPorApellido();
                    break;
                case 10:
                    mostrarDoctorDePaciente();
                    break;
                case 11:
                    buscarDoctoresPorEspecialidad();
                    break;
                case 12:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo");
            }

        } while (opcion != 12);
    }
 
    private static void registrarMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos del medico");
        System.out.print("Numero de colegiatura: ");
        int numeroColegiatura = scanner.nextInt();
        scanner.nextLine(); //limpiar el buffer 
        System.out.print("Nombre: ");
        String nombreMedico = scanner.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        // Creamos la instancia de Medico y lo agregamos a la lista
        Medico medico = new Medico(numeroColegiatura, nombreMedico, especialidad);
        listaMedicos.add(medico);

        System.out.println("\nMedico registrado exitosamente");
    }

    private static void registrarPaciente() {
        Scanner scanner = new Scanner(System.in);

        //verificamos si hay mÃ©dicos registrados
        if (listaMedicos.isEmpty()) {
            System.out.println("Debe registrar al menos un medico antes de registrar a un paciente");
            return;
        }

        System.out.println("Ingrese los datos del paciente:");
        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Nombre: ");
        String nombrePaciente = scanner.nextLine();
        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();
        System.out.print("Peso: ");
        double peso = scanner.nextDouble();
        System.out.print("Temperatura: ");
        double temperatura = scanner.nextDouble();

        //listamos los medics
        mostrarListaMedicos();

        //bucle para medico valido
        int numeroMedico;
        Medico medicoAsignado = null;
        while (medicoAsignado == null) {
            System.out.print("Seleccione el numero del medico a asignar: ");
            numeroMedico = scanner.nextInt();

            if (numeroMedico >= 1 && numeroMedico <= listaMedicos.size()) {
                medicoAsignado = listaMedicos.get(numeroMedico - 1);
            } else {
                System.out.println("\nNumero de medico no valido. Intente de nuevo.\n");
            }
        }

        //creamos la instancia de paciente y asignamos medico
        Paciente paciente = new Paciente(dni, nombrePaciente, direccion, peso, temperatura);
        paciente.setMedicoAsignado(medicoAsignado);

        //agregamos listas
        listaPacientes.add(paciente);

        System.out.println("\nPaciente registrado exitosamente");
    }

    private static void eliminarPaciente() {
        Scanner scanner = new Scanner(System.in);

        if (listaPacientes.isEmpty()) {
            System.out.println("La lista de pacientes esta vaci­a, no hay pacientes para eliminar");
            return;
        }

        mostrarListaPacientes();

        System.out.print("Ingrese el numero del paciente a eliminar: ");
        int posicion = scanner.nextInt();

        if (posicion >= 1 && posicion <= listaPacientes.size()) {
            Paciente pacienteEliminado = listaPacientes.remove(posicion - 1);
            System.out.println("\nPaciente eliminado correctamente: " + pacienteEliminado.getNombre());
        } else {
            System.out.println("\nPosicion de paciente no valida");
        }
    }

    private static void modificarPaciente() {
        Scanner scanner = new Scanner(System.in);

        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para modificar");
            return;
        }

        mostrarListaPacientes();

        System.out.print("Seleccione el numero del paciente a modificar: ");
        int numeroPaciente = scanner.nextInt();
        scanner.nextLine();

        if (numeroPaciente >= 1 && numeroPaciente <= listaPacientes.size()) {
            Paciente pacienteAModificar = listaPacientes.get(numeroPaciente - 1);

            System.out.println("\nIngrese los nuevos datos del paciente " + pacienteAModificar.getNombre() + ":");
            System.out.print("Nombre: ");
            String nuevoNombre = scanner.nextLine();
            System.out.print("DNI: ");
            int nuevoDNI = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Direccion: ");
            String nuevaDireccion = scanner.nextLine();
            System.out.print("Peso: ");
            double nuevoPeso = scanner.nextDouble();
            System.out.print("Temperatura: ");
            double nuevaTemperatura = scanner.nextDouble();

            //modificamos los datos del paciente
            pacienteAModificar.setNombre(nuevoNombre);
            pacienteAModificar.setDNI(nuevoDNI);
            pacienteAModificar.setDireccion(nuevaDireccion);
            pacienteAModificar.setPeso(nuevoPeso);
            pacienteAModificar.setTemperatura(nuevaTemperatura);

            System.out.println("\nPaciente modificado exitosamente");
        } else {
            System.out.println("\nNumero de paciente no valido");
        }
    }

    private static void mostrarPesoMasRepite() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados");
            return;
        }

        //mapa para encontrar el peso
        Map<Double, Integer> frecuenciaPesos = new HashMap<>();

        //frecuencia de cada peso.
        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            frecuenciaPesos.put(peso, frecuenciaPesos.getOrDefault(peso, 0) + 1);
        }

        //encontrar peso que mas se repite
        double pesoMasRepite = Collections.max(frecuenciaPesos.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("El peso que mas se repite es: " + pesoMasRepite);

        //mostrar el peso de los pacientes que mas se repiten
        System.out.println("\nPacientes con ese peso:");
        for (Paciente paciente : listaPacientes) {
            if (paciente.getPeso() == pesoMasRepite) {
                System.out.println("Nombre: " + paciente.getNombre());
            }
        }
    }

    private static void mostrarCantidadPacientesPesoRepite() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados");
            return;
        }

        Map<Double, Integer> frecuenciaPesos = new HashMap<>();

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            frecuenciaPesos.put(peso, frecuenciaPesos.getOrDefault(peso, 0) + 1);
        }

        int frecuenciaMaxima = Collections.max(frecuenciaPesos.values());

        System.out.println("Cantidad de pacientes con el peso que mas se repite es: " + frecuenciaMaxima);
    }

    private static void mostrarPesoMayorYMenor() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados");
            return;
        }

        //variables para el peso mayor y menor
        double pesoMayor = Double.MIN_VALUE;
        double pesoMenor = Double.MAX_VALUE;

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            pesoMayor = Math.max(pesoMayor, peso); // Mayor
            pesoMenor = Math.min(pesoMenor, peso); // Menor
        }
        System.out.println("El peso mayor es: " + pesoMayor);
        System.out.println("El peso menor es: " + pesoMenor);
    }

    private static void mostrarCantidadPersonasPorRangoPeso() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        double pesoMinimo = 40.0; 
        double pesoMaximo = 120.0; 
        int cantidadRangos = 4; 

        //tamaño de cada rango
        double rangoSize = (pesoMaximo - pesoMinimo) / cantidadRangos;

        int[] conteoRangos = new int[cantidadRangos];

        //bucle para contar la cantidad de personas en cada rango 
        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            int indiceRango = (int) ((peso - pesoMinimo) / rangoSize);

            //para ver que este dentro de los limites
            indiceRango = Math.min(Math.max(indiceRango, 0), cantidadRangos - 1);

            conteoRangos[indiceRango]++;
        }

        System.out.println("Cantidad de personas por rango de peso:\n");

        for (int i = 0; i < cantidadRangos; i++) {
            double rangoMinimo = pesoMinimo + i * rangoSize;
            double rangoMaximo = pesoMinimo + (i + 1) * rangoSize;

            System.out.println("Rango " + (i + 1) + ": De " + rangoMinimo + " a " + rangoMaximo + " kg - Cantidad: " + conteoRangos[i]);
        }
    }

    private static void mostrarPacientesOrdenadosPorApellido() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados");
            return;
        }

        //ordenar la lista por nombre
        Collections.sort(listaPacientes, Comparator.comparing(Paciente::getNombre));

        //lista ordenada
        System.out.println("Lista de pacientes por nombre:\n");
        for (int i = 0; i < listaPacientes.size(); i++) {
            Paciente paciente = listaPacientes.get(i);
            Medico medicoAsignado = paciente.getMedicoAsignado();

            System.out.println("Numero de paciente: " + (i + 1));
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("DNI: " + paciente.getDNI());
            System.out.println("Medico asignado: " + medicoAsignado.getNombre() + " (Especialidad: " + medicoAsignado.getEspecialidad() + ")");
            System.out.println("-----------------------------");
        }
    }

    private static void mostrarDoctorDePaciente() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados");
            return;
        }

        //lista de pacientes para que el usuario elija uno
        System.out.println("Lista de pacientes:\n");
        for (int i = 0; i < listaPacientes.size(); i++) {
            Paciente paciente = listaPacientes.get(i);
            System.out.println("Numero de paciente: " + (i + 1) + ", Nombre: " + paciente.getNombre() + ", DNI: " + paciente.getDNI());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el numero de paciente para ver su doctor: ");
        int numeroPaciente = scanner.nextInt();

        System.out.print("\n");

        //verificar
        if (numeroPaciente >= 1 && numeroPaciente <= listaPacientes.size()) {
            Paciente pacienteSeleccionado = listaPacientes.get(numeroPaciente - 1);
            Medico medicoAsignado = pacienteSeleccionado.getMedicoAsignado();

            //mostrar el nombre, DNI y el doctor asignado del paciente elegido
            System.out.println("El doctor asignado del paciente " + pacienteSeleccionado.getNombre() + "es: " + medicoAsignado.getNombre() + " (Especialidad: " + medicoAsignado.getEspecialidad() + ")");
        } else {
            System.out.println("Numero de paciente no valido.");
        }
    }

    private static void buscarDoctoresPorEspecialidad() {
        if (listaMedicos.isEmpty()) {
            System.out.println("No hay medicos registrados");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la especialidad a buscar: ");
        String especialidadBuscada = scanner.nextLine();

        System.out.print("\n");
        
        //medicos por especialidad
        boolean encontrado = false;
        for (Medico medico : listaMedicos) {
            if (medico.getEspecialidad().equalsIgnoreCase(especialidadBuscada)) {
                // Mostrar el nombre y nÃºmero de colegiatura del mÃ©dico
                System.out.println("Nombre: " + medico.getNombre());
                System.out.println("Numero de colegiatura: " + medico.getNumeroColegiatura());
                System.out.println("-----------------------------");
                encontrado = true;
            }
        }

        //mensaje para medicos no encontrados
        if (!encontrado) {
            System.out.println("\nNo se encontraron medicos de esa especialidad: " + especialidadBuscada);
        }
    }

    private static void mostrarListaMedicos() {
        System.out.println("\nLista de medicos:\n");
        for (int i = 0; i < listaMedicos.size(); i++) {
            Medico medico = listaMedicos.get(i);

            System.out.println("Numero de medico: " + (i + 1));
            System.out.println("Nombre: " + medico.getNombre());
            System.out.println("Numero de colegiatura: " + medico.getNumeroColegiatura());
            System.out.println("Especialidad: " + medico.getEspecialidad());
            System.out.println("-----------------------------");
        }
    }

    private static void mostrarListaPacientes() {
        System.out.println("\nLista de pacientes:\n");
        for (int i = 0; i < listaPacientes.size(); i++) {
            Paciente paciente = listaPacientes.get(i);
            Medico medicoAsignado = paciente.getMedicoAsignado();

            System.out.println("Numero de paciente: " + (i + 1));
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("DNI: " + paciente.getDNI());
            System.out.println("Direccion: " + paciente.getDireccion());
            System.out.println("Peso: " + paciente.getPeso());
            System.out.println("Temperatura: " + paciente.getTemperatura());
            System.out.println("Medico Asignado: " + medicoAsignado.getNombre() + " (Especialidad: " + medicoAsignado.getEspecialidad() + ")");
            System.out.println("-----------------------------"); 
        }
    }
}

