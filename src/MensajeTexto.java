public class MensajeTexto extends Mensaje {
    String texto;

    public MensajeTexto(String destinatario, String emisor, String texto) {
        super(destinatario, emisor);
        this.texto = texto;
    }

    @Override
    public void reproducir() {
        System.out.println("El texto es: " + texto);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}