import java.io.*;
import java.util.*;

public class RestauranteSimulador1 {
    // Define los precios de los platos
    private static final Map<String, Double> precios = new HashMap<>();
    static {
        precios.put("Pizza - Peperoni", 24000.0);
        precios.put("Pizza - Carnes", 26000.0);
        precios.put("Pizza - Tocineta y Ciruela", 23000.0);
        precios.put("Lasaña - Carnes", 19000.0);
        precios.put("Lasaña - Pollo", 20000.0);
        precios.put("Lasaña - Mixta", 24000.0);
        precios.put("Postre - Maltealda", 8000.0);
        precios.put("Postre - Brownie", 5000.0);
        precios.put("Postre - Helado", 3000.0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> pedido = new ArrayList<>();
        double totalPedido = 0.0; // Variable para llevar un seguimiento del costo total del pedido

        // Menú inicial
        while (true) {
            System.out.println("Menú disponible:");
            System.out.println("1. Pizza");
            System.out.println("2. Lasaña");
            System.out.println("3. Postre");
            System.out.println("4. Cuenta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    totalPedido = mostrarMenu("Pizza", pedido, scanner, totalPedido);
                    break;
                case 2:
                    totalPedido = mostrarMenu("Lasaña", pedido, scanner, totalPedido);
                    break;
                case 3:
                    totalPedido = mostrarMenu("Postre", pedido, scanner, totalPedido);
                    break;
                case 4:
                    mostrarCuenta(pedido, totalPedido);
                    break;
                case 5:
                    mostrarCuenta(pedido, totalPedido);
                    guardarPedido(pedido, totalPedido);
                    System.out.println("Gracias por visitar el restaurante. ¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private static double mostrarMenu(String tipo, List<String> pedido, Scanner scanner, double totalPedido) {
        List<String> opciones = new ArrayList<>();

        // Definir las opciones del menú
        if (tipo.equals("Pizza")) {
            opciones = Arrays.asList("Peperoni", "Carnes", "Tocineta y Ciruela", "Volver");
        } else if (tipo.equals("Lasaña")) {
            opciones = Arrays.asList("Carnes", "Pollo", "Mixta", "Volver");
        } else if (tipo.equals("Postre")) {
            opciones = Arrays.asList("Maltealda", "Brownie", "Helado", "Volver");
        }

        while (true) {
            System.out.println("Menú " + tipo + ":");
            for (int i = 0; i < opciones.size(); i++) {
                System.out.println((i + 1) + ". " + opciones.get(i) + " - $" + precios.get(tipo + " - " + opciones.get(i))); // Muestra el precio del plato
            }
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= opciones.size()) {
                if (opcion == opciones.size()) {
                    // Volver al menú anterior
                    break;
                } else {
                    String plato = tipo + " - " + opciones.get(opcion - 1);
                    double precioPlato = precios.get(plato); // Obtener el precio del plato
                    pedido.add(plato);
                    totalPedido += precioPlato; // Actualizar el costo total
                    System.out.println("¡Pedido agregado!");
                }
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
        return totalPedido;
    }

    private static void mostrarCuenta(List<String> pedido, double totalPedido) {
        System.out.println("Cuenta:");
        for (String item : pedido) {
            System.out.println(item + " - $" + precios.get(item)); // Muestra el precio del plato
        }
        System.out.println("Total: $" + totalPedido); // Muestra el costo total del pedido
    }

    private static void guardarPedido(List<String> pedido, double totalPedido) {
        try {
            FileWriter writer = new FileWriter("pedido.txt");
            for (String item : pedido) {
                writer.write(item + " - $" + precios.get(item) + "\n"); // Guarda el plato con su precio
            }
            writer.write("Total: $" + totalPedido); // Guarda el costo total del pedido
            writer.close();
        } catch (IOException e) {
            System.err.println("Error al guardar el pedido.");
        }
    }
}