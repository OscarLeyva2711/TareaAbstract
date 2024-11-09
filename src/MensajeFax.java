public class MensajeFax extends Mensaje {
    String nombreArchivo;

    public MensajeFax(String destinatario, String emisor, String nombreArchivo) {
        super(destinatario, emisor);
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void reproducir() {
        // Simula la "impresión" de un archivo de fax.
        System.out.println("Imprimiendo el archivo de fax: " + nombreArchivo);
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}