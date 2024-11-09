import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class sistemaMensajes {
    private ArrayList<Mensaje> misMensajes;

    public sistemaMensajes() {
        misMensajes = new ArrayList<>();
    }

    public void insertarMensaje(Mensaje mensaje) {
        misMensajes.add(mensaje);
    }

    public void reproducirTodosMisMensajes() {
        for (Mensaje miMensaje : misMensajes) {
            System.out.println(miMensaje.toString());
            miMensaje.reproducir();
        }
    }

    public void borrarMensajesPorRemitente(String remitente) {
        misMensajes.removeIf(m -> m.getEmisor().equals(remitente));
        System.out.println("Mensajes de " + remitente + " han sido eliminados.");
    }

    public HashMap<String, String> buscarMensajesPorRemitente(String remitente) {
        HashMap<String, String> mensajesEncontrados = new HashMap<>();
        for (Mensaje m : misMensajes) {
            if (m.getEmisor().equals(remitente)) {
                if (m instanceof MensajeTexto) {
                    mensajesEncontrados.put(m.getEmisor(), ((MensajeTexto) m).getTexto());
                } else if (m instanceof MensajeFax) {
                    mensajesEncontrados.put(m.getEmisor(), ((MensajeFax) m).getNombreArchivo());
                }
            }
        }
        return mensajesEncontrados;
    }

    public ArrayList<Mensaje> buscarMensajesPorTipo(Class<?> tipo) {
        ArrayList<Mensaje> mensajesTipo = (ArrayList<Mensaje>) misMensajes.stream()
                .filter(m -> m.getClass().equals(tipo))
                .collect(Collectors.toList());
        return mensajesTipo;
    }

    public void ordenarMensajesPorRemitente() {
        misMensajes.sort((m1, m2) -> m1.getEmisor().compareTo(m2.getEmisor()));
        System.out.println("Mensajes ordenados por remitente.");
    }

    public void reproducirMensajePorIndice(int indice) {
        if (indice >= 0 && indice < misMensajes.size()) {
            System.out.println("Reproduciendo el mensaje: ");
            misMensajes.get(indice).reproducir();
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    public static void main(String[] args) {
        int op = 0;
        Scanner input = new Scanner(System.in);
        sistemaMensajes sistema = new sistemaMensajes();

        System.out.println("Bienvenido al super sistema de mensajes.");

        do {
            System.out.println("1. Insertar mensajes.");
            System.out.println("2. Leer todos los mensajes.");
            System.out.println("3. Borrar los mensajes de un remitente.");
            System.out.println("4. Ver todos los mensajes de un remitente.");
            System.out.println("5. Ver todos los mensajes de un tipo (Texto, Fax).");
            System.out.println("6. Ordenar los mensajes por remitente.");
            System.out.println("7. Reproducir un mensaje en particular con el índice.");
            System.out.println("8. Salir.");
            op = input.nextInt();
            input.nextLine(); // Limpiar buffer

            switch (op) {
                case 1:
                    System.out.println("Ingrese el tipo de mensaje (Texto o Fax): ");
                    String tipo = input.nextLine();
                    System.out.println("Ingrese el destinatario: ");
                    String destinatario = input.nextLine();
                    System.out.println("Ingrese el emisor: ");
                    String emisor = input.nextLine();

                    if ("Texto".equalsIgnoreCase(tipo)) {
                        System.out.println("Ingrese el texto del mensaje: ");
                        String texto = input.nextLine();
                        sistema.insertarMensaje(new MensajeTexto(destinatario, emisor, texto));
                    } else if ("Fax".equalsIgnoreCase(tipo)) {
                        System.out.println("Ingrese el nombre del archivo para el fax: ");
                        String archivo = input.nextLine();
                        sistema.insertarMensaje(new MensajeFax(destinatario, emisor, archivo));
                    }
                    break;
                case 2:
                    sistema.reproducirTodosMisMensajes();
                    break;
                case 3:
                    System.out.println("Ingrese el remitente cuyo mensaje desea borrar: ");
                    String remitente = input.nextLine();
                    sistema.borrarMensajesPorRemitente(remitente);
                    break;
                case 4:
                    System.out.println("Ingrese el remitente cuyo mensaje desea ver: ");
                    String remitenteBusqueda = input.nextLine();
                    HashMap<String, String> mensajes = sistema.buscarMensajesPorRemitente(remitenteBusqueda);
                    mensajes.forEach((k, v) -> System.out.println("Remitente: " + k + ", Mensaje: " + v));
                    break;
                case 5:
                    System.out.println("Ingrese el tipo de mensaje (Texto, Fax): ");
                    String tipoBusqueda = input.nextLine();
                    Class<?> tipoClase = tipoBusqueda.equalsIgnoreCase("Texto") ? MensajeTexto.class : MensajeFax.class;
                    ArrayList<Mensaje> mensajesTipo = sistema.buscarMensajesPorTipo(tipoClase);
                    for (Mensaje m : mensajesTipo) {
                        System.out.println(m.toString());
                        m.reproducir();
                    }
                    break;
                case 6:
                    sistema.ordenarMensajesPorRemitente();
                    break;
                case 7:
                    System.out.println("Ingrese el índice del mensaje que desea reproducir: ");
                    int indice = input.nextInt();
                    sistema.reproducirMensajePorIndice(indice);
                    break;
                case 8:
                    System.out.println("¡Gracias por usar el sistema de mensajes!");
                    break;
            }
        } while (op != 8);

        input.close();
    }
}