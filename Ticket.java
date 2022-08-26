import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Clase Ticket donde se procesara todos los datos
 */
public class Ticket  {
    /**variable que recogera el nit de la persona que genera el ticket*/
    private String nitUsuario;
    /**variable que recogera la descripcion de la generacion del ticket*/
    private String  descripcionProblema;
    /**variable que recogera el estado del ticket ticket*/
    private String estado;
    /**variable que recogera el id del ticket*/
    private int Id;
    /**variable que inicializa la variable bitacora*/
    static ArrayList<Bitacora> bitacora= new ArrayList<Bitacora>();

    /**metod constructor que recibe los datos para el ticket
     *
     * @param nitUsuario recibe el nit del usuario
     * @param descripcionProblema recibe el detalle dle problema
     * @param estado estado que genera el sistema autoamticamente como iniciado
     * @param Id el sitema le asigna un numero
     */
    public Ticket(String nitUsuario, String descripcionProblema, String estado, int Id) {
        this.nitUsuario = nitUsuario;
        this.descripcionProblema = descripcionProblema;
        this.estado = estado;
        this.Id = Id;
    }

    /**
     * metodo que agrega un nuevo registro a la bitacora
     * @param personaGenera el nit de la persona que registra un evento
     * @param mensaje el mensaje que se genera
     * @param evento el tipo de vento que se genero
     * @param fechaHora la fecha que se genero el evento
     * @param hora la hora que se genero el evento
     * @param id el numero de ticket
     */
    public static void Agregar(String personaGenera, String mensaje, TipoEvento evento, LocalDate fechaHora, LocalTime hora, int id){
        bitacora.add(new Bitacora(personaGenera,mensaje,evento,fechaHora, hora, id ));
    }

    /**
     * metodo que muestra la bitacra del ticket procesado
     * @param id recibe el numero del ticket para mostrar los datos
     */
    public static void ver(int id){
        //System.out.println(bitacora.size());
        System.out.printf("%5s %9s %30s %40s", "Nit Soporte ", "evento", "Mensaje", "Fecha y hora");
        System.out.println("\n---------------------------------------------------------------------------------------------");
        for (Bitacora bitacoras: bitacora) {
            if (bitacoras.getIdTicket()==id){
                System.out.format("%5s %15s%45s %35s "
                        ,bitacoras.getPersonaGenera()
                        ,bitacoras.getEvento()
                        ,bitacoras.getMensaje()
                        ,bitacoras.getFecha()+" - "+bitacoras.getHora());
                System.out.println(" ");

            }
        }

    }

    /**
     * metodo get
     * @return retorna el nit del usuario
     */
    public String getNitUsuario() {
        return nitUsuario;
    }
    /**
     * metodo get
     * @return retorna ella descripcion del problema
     */
    public String getDescripcionProblema() {
        return descripcionProblema;
    }
    /**
     * metodo get
     * @return retorna el estado del problema
     */
    public String getEstado() {
        return estado;
    }
    /**
     * metodo get
     * @return retorna el id del ticket
     */
    public int getId() {
        return Id;
    }


    /**
     * metodo que recibe el estado del ticket
     * @param estado define el estado en el que se creara el ticket
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * metodo que muestra lod datos del arraylist bitacora
     * @return los datos del array list
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "nitUsuario='" + nitUsuario + '\'' +
                ", descripcionProblema='" + descripcionProblema + '\'' +
                ", estado='" + estado + '\'' +
                ", Id=" + Id +
                '}';
    }
}
