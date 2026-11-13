package rica_api.compartido;

public class LimiteAnualExcedidoException extends RuntimeException {

    public LimiteAnualExcedidoException(String mensaje) {
        super(mensaje);
    }
}