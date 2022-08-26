/** Clase que permite manipular los errores del sistema*/
public class errores extends Exception{
    /** Metodo constructor que inicializa la clase */
    public errores() {
    }

    /** Metodo constructor que recibe por parametro el errir la clase
     *
     * @param message recibe el mensaje del error
     */

    public errores(String message) {
        super(message);
    }
}
