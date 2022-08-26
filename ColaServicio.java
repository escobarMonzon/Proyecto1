import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Formatter;
/**      Muestra la cola de los servicios*/
public class ColaServicio extends Ticket{
    /** Variable que guarda el tipo de cola donde esta el ticket*/
    public static TipoCola tipo;
    /** Variable que almacena los ticket que se agregaran*/
    static ArrayList<Ticket> cola= new ArrayList<Ticket>();
    /** Variable almacena en la bitacora la fecha que se agrego el ticket*/
    static LocalDate fecha = LocalDate.now();
    /** Variable almacena en la bitacora la hora que se agrego el ticket*/
    static LocalTime hora = LocalTime.now();
    /** Variable almacena el correlativo que tendran los ticket*/
    public static int correlativo=0;

    /** Metodo constructor que inicializa la clase Colaservicios y hereda de Ticket
     * @param nitUsuario el nit del usuario que genero el ticket
     * @param descripcionProblema detalles del porque genero el ticket
     * @param estado estado del que se encuentra el ticket (iniciado)
     * @param Id el numero de ticket que le corresponde
     */
    public ColaServicio(String nitUsuario, String descripcionProblema, String estado, int Id) {
        super(nitUsuario, descripcionProblema, estado, Id);
    }

    /** Metodo que Agrega un nuevo tocket al sistema y guarda un registro del evento en la bitacora
     * @param nitUsuario el nit del usuario que genero el ticket
     * @param descripcionProblema detalles del porque genero el ticket
     * @param estado estado del que se encuentra el ticket (iniciado)
     */
    public static void Agregar(String nitUsuario, String descripcionProblema, String estado){
        correlativo++;
        cola.add(new Ticket(nitUsuario,descripcionProblema,estado, correlativo));
        Ticket.Agregar("000001", "Ingresa por carga masiva",TipoEvento.CrearTicket, fecha, hora,correlativo);
    }

    /** Metodo que genera un reporte general de los ticket incluyendo su bitacora
     */
    public static void verReporteGeneral(){
        //System.out.println(cola.size());
        System.out.printf("%5s %9s ", "No. Ticket", "Estado");
        System.out.println("\n-------------------------------");
        int id;
        for (Ticket colas: cola) {
            id=colas.getId();
            Formatter mesaMascara = new Formatter();
            mesaMascara.format("%06d",colas.getId());
            System.out.format("%3s %18s "
                    ,mesaMascara
                    ,colas.getEstado());
            System.out.println(" ");
            System.out.println("------------------------------------------");
            Ticket.ver(id);
            System.out.println(" ");
        }
    }
    /** metodo que muestra los ticket separado segun la cola donde se encuentra*/
    public static void verReporteCola(){
        //System.out.println(cola.size());
        System.out.println("COLA DE MESA");
        System.out.printf("%5s %13s %10s", "No. Ticket", "Nit Usuario" ,"Cola");
        System.out.println("\n-------------------------------------");
        int id, total = 0;
        for (Ticket colas: cola) {
            id=colas.getId();
            Formatter mesaMascara = new Formatter();
            mesaMascara.format("%06d",colas.getId());
            if(colas.getEstado().equals("mesa")){
                total++;
                System.out.format("%5s %15s %12s"
                        ,mesaMascara
                        ,colas.getNitUsuario()
                        ,colas.getEstado());
                System.out.println(" ");
            }
        }
        System.out.println("Total de Ticket en la mesa: "+total);
        total=0;
        System.out.println("\n\nCOLA DE SOPORTE");
        System.out.printf("%5s %13s %10s", "No. Ticket", "Nit Usuario" ,"Cola");
        System.out.println("\n-------------------------------------");
        for (Ticket colas: cola) {
            id=colas.getId();
            Formatter mesaMascara = new Formatter();
            mesaMascara.format("%06d",colas.getId());
            if(colas.getEstado().equals("soporte")){
                total++;
                System.out.format("%5s %15s %12s"
                        ,mesaMascara
                        ,colas.getNitUsuario()
                        ,colas.getEstado());
                System.out.println(" ");
            }
        }
        System.out.println("Total de Ticket en la mesa: "+total);
        total=0;
        System.out.println("\n\nCOLA DE DESARROLLO");
        System.out.printf("%5s %13s %10s", "No. Ticket", "Nit Usuario" ,"Cola");
        System.out.println("\n-------------------------------------");
        for (Ticket colas: cola) {
            id=colas.getId();
            Formatter mesaMascara = new Formatter();
            mesaMascara.format("%06d",colas.getId());
            if(colas.getEstado().equals("desarrollo")){
                total++;
                System.out.format("%5s %15s %12s"
                        ,mesaMascara
                        ,colas.getNitUsuario()
                        ,colas.getEstado());
                System.out.println(" ");
            }
        }
        System.out.println("Total de Ticket en la mesa: "+total);
        total=0;
        System.out.println("\n\nCOLA DE ATENDIDOS");
        System.out.printf("%5s %13s %10s", "No. Ticket", "Nit Usuario" ,"Cola");
        System.out.println("\n-------------------------------------");
        for (Ticket colas: cola) {
            id=colas.getId();
            Formatter mesaMascara = new Formatter();
            mesaMascara.format("%06d",colas.getId());
            if(colas.getEstado().equals("Resuelto")){
                total++;
                System.out.format("%5s %15s %12s"
                        ,mesaMascara
                        ,colas.getNitUsuario()
                        ,colas.getEstado());
                System.out.println(" ");
            }
        }
        System.out.println("Total de Ticket en la mesa: "+total);
        total=0;
    }
    /** Metodo que muestra los ticket en orden segun el usuario que lo ingreso*/
    public static void verReporteUsurios(){
        System.out.printf("%5s %13s %10s", "No. Ticket", "Nit Usuario" ,"Cola");
        System.out.println("\n-------------------------------");
        cola.sort((Ticket a , Ticket b) -> a.getNitUsuario().compareTo(b.getNitUsuario()));
        for (Ticket colas: cola) {
            Formatter mesaMascara = new Formatter();
            mesaMascara.format("%06d",colas.getId());
            System.out.format("%5s %15s %12s"
                    ,mesaMascara
                    ,colas.getNitUsuario()
                    ,colas.getEstado());
            System.out.println(" ");
        }
    }
}
