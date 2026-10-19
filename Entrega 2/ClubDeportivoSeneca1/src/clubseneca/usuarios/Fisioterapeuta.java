package clubseneca.usuarios;

public class Fisioterapeuta extends Empleado {
    public Fisioterapeuta(String login, String password, String codigoDescuento) {
        super(login, password, codigoDescuento);
    }

    public boolean esFisioterapeuta() {
        return true;
    }
}
