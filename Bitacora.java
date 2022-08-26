import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Almacena Eventos del sistema
 * <p>
 *     esta clase nos permite almacenar cada evento que se realiza en el sistema.
 *     El cual quedara guardado en la memoria
 */
public class Bitacora {
    /**     *  Almacena el nit de la persona que genero un evento en el sistema     */
    private String personaGenera;
    /**     *  almacena el mensaje del porque se genero un evento     */
    private String mensaje;
    /**     *  almacena el tipo de evento que se genero     */
    private TipoEvento evento;
    /**     *  almacena la fecha en que se genero un evento     */
    private LocalDate fecha;
    /**     *  almacena la hora en que se genero el evento     */
    private LocalTime hora;
    /**     *  almacena el numero de ticket al que se le genero el ticket     */
    private int idTicket;

    /**     *  Constructor que inicializa la clase Bitacora
     * @param evento recibe el tipo de evento
     * @param fecha recibe la fecha del evento
     * @param hora  recibe la hora del evento
     * @param idTicket recibe el id del ticket
     * @param mensaje mensaje por el cual se genero el evento
     * @param personaGenera la persona que realiza el evento
     * */
    public Bitacora(String personaGenera, String mensaje, TipoEvento evento, LocalDate fecha, LocalTime hora, int idTicket) {
        this.personaGenera = personaGenera;
        this.mensaje = mensaje;
        this.evento = evento;
        this.fecha = fecha;
        this.hora = hora;
        this.idTicket = idTicket;
    }
    /**
     * Get de la variable ticket
     * @return idTicket
     * */
    public int getIdTicket() {
        return idTicket;
    }
    /**     *  Get de la variable PersonaGenera
     * @return personaGenera*/
    public String getPersonaGenera() {
        return personaGenera;
    }
    /**     *  Get de la variable hora
     * @return hora*/
    public LocalTime getHora() {
        return hora;
    }
    /**     *  Get de la variable mensaje
     *  @return mensaje*/
    public String getMensaje() {
        return mensaje;
    }

    /**     *  Get de la variable evento
     * @return evento
     */
    public TipoEvento getEvento() {
        return evento;
    }

    /**     *  Get de la variable fecha
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**     *  Metodo que muestra los datos del array list Bitacora     */
    @Override
    public String toString() {
        return "Bitacora{" +
                "personaGenera='" + personaGenera + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", evento=" + evento +
                ", fecha=" + fecha +
                ", hora=" + hora +
                '}';
    }
}
