package gym_fit;  // Declarar el paquete

import gym_fit.datos.ClienteDAO;
import gym_fit.dominio.Cliente;
import java.util.List;
import java.util.Scanner;

public class GymFitAPP {
    public static void main(String[] args) {
        gymFitApp(); 
    }

    private static void gymFitApp(){

        // Limpiar pantalla
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la pantalla: " + e.getMessage());
        }

        boolean salir = false;
        Scanner consola = new Scanner(System.in);
        // Creamos un objeto de la clase clienteDao
        ClienteDAO clienteDao = new ClienteDAO();
        while (!salir) {
            try {
                int opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao); // Usamos la variable `salir` ya declarada
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    // Cambiamos el tipo de retorno a `int`
    private static int mostrarMenu(Scanner consola) {
        System.out.print("*** Gym Fit (GYM)\n"
                + "1. Listar Clientes\n"
                + "2. Buscar Cliente\n"
                + "3. Agregar Cliente\n"
                + "4. Modificar Cliente\n"
                + "5. Eliminar Cliente\n"
                + "6. Salir\n"
                + "Elije una opción: ");

        return Integer.parseInt(consola.nextLine());  // Devolvemos un int
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, ClienteDAO clienteDao) {
        boolean salir = false;
        // Implementa las acciones de acuerdo a la opción seleccionada
        switch (opcion) {
            case 1:
                // Lógica para listar clientes
                System.out.println("--- Listado de Clientes ---");
                List<Cliente> clientes = clienteDao.listarClientes();
                clientes.forEach(System.out::println);
                break;
    
            case 2:
                // Buscar cliente por id
                System.out.print("Introduce el id del Cliente a buscar: ");
                int idCliente = Integer.parseInt(consola.nextLine());
                Cliente cliente = new Cliente(idCliente);
                boolean encontrado = clienteDao.buscarClientePorId(cliente);
                if (encontrado)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente NO encontrado: " + cliente);
                break;
    
            case 3:
                // Agregar cliente
                System.out.println("--- Agregar Cliente ---");
                System.out.print("Nombre: ");
                String nombre = consola.nextLine();
                System.out.print("Apellido: ");
                String apellido = consola.nextLine();
                System.out.print("Membresia: ");
                int membresia = Integer.parseInt(consola.nextLine());
                Cliente nuevoCliente = new Cliente(nombre, apellido, membresia);
                boolean agregado = clienteDao.agregarCliente(nuevoCliente);
                if (agregado)
                    System.out.println("Cliente agregado: " + nuevoCliente);
                else
                    System.out.println("Cliente NO agregado: " + nuevoCliente);
                break;
    
            case 4:
                // Modificar cliente
                System.out.println("--- Modificar Cliente ---");
                System.out.print("Id Cliente: ");
                idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                nombre = consola.nextLine();
                System.out.print("Apellido: ");
                apellido = consola.nextLine();
                System.out.print("Membresia: ");
                membresia = Integer.parseInt(consola.nextLine());
                Cliente clienteModificado = new Cliente(idCliente, nombre, apellido, membresia);
                boolean modificado = clienteDao.modificarCliente(clienteModificado);
                if (modificado)
                    System.out.println("Cliente modificado: " + clienteModificado);
                else
                    System.out.println("Cliente NO modificado: " + clienteModificado);
                break;
    
            case 5:
                // Eliminar Cliente
                System.out.println("--- Eliminar Cliente ---");
                System.out.print("Id Cliente: ");
                idCliente = Integer.parseInt(consola.nextLine());
                cliente = new Cliente(idCliente);
                boolean eliminado = clienteDao.eliminarCliente(cliente);
                if (eliminado)
                    System.out.println("Cliente Eliminado: " + cliente);
                else
                    System.out.println("Cliente NO eliminado: " + cliente);
                break;
    
            case 6:
                // Salir
                System.out.println("Hasta pronto!");
                salir = true;
                break;
    
            default:
                System.out.println("Opción no válida.");
        }
        return salir;  // Regresamos el valor de `salir`
    }
    
}
