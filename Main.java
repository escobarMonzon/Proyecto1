import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.time.*;
import java.util.*;
/**
 * Muestra la funcionalidad del sistema
 * @author Natanael Eleazar Escobar Monzon --- 7690-16-16333
 * @version 1.0 16/08/2022
 *
*/
public class Main {
    /**
     * inicia el sistema
     * @param args inicializa el sistema
     * @throws Exception muestra excepcion si algo sale mal
     */
    public static void main(String[] args) throws Exception {
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        Scanner DatosIngresoSistema = new Scanner(System.in);
        int Opcion = 0;
        do {
            try {
                System.out.println("*********************************");
                System.out.println("*\t\tMENU DE OPCIONES\t\t*");
                System.out.println("*********************************");
                System.out.println("\t\t1. \tCarga de datos");
                System.out.println("\t\t2. \tCrear Ticket");
                System.out.println("\t\t3. \tTrabajar Ticket");
                System.out.println("\t\t4. \tListado general de Tickets con bitacora");
                System.out.println("\t\t5. \tListado de ticket por cola");
                System.out.println("\t\t6. \tListado de Tickets por usuario");
                System.out.println("\t\t0. \tSalir");
                System.out.println("\tPresione el numero de la opcion que desea procesar");
                Scanner digito = new Scanner(System.in);
                Opcion = digito.nextInt();
                switch (Opcion) {
                    case 1:
                        System.out.println("Subir datos predeterminados al Sistema");
                        CargarDocumento();
                        System.out.println("Los datos fueron cargados al sistema");
                        Continuar();
                        break;
                    case 2:
                        Scanner DatosCrearTicket = new Scanner(System.in);
                        String nitUsuario;
                        String descripcionProblema;
                        System.out.println("Ingrese su nit");
                        nitUsuario = DatosCrearTicket.nextLine();
                        System.out.println("Ingrese Detalles del Inconveniente");
                        descripcionProblema = DatosCrearTicket.nextLine();
                        ColaServicio.Agregar(nitUsuario, descripcionProblema, "mesa");
                        Ticket.Agregar(nitUsuario, "Creacion de Ticket", TipoEvento.CrearTicket, fecha, hora, ColaServicio.cola.size());
                        System.out.println("GRACIAS POR REPORTAR SU INCONVENIENTE, NUESTROS COLABORADORES LO ATENDERAN LO ANTES POSIBLE");
                        Continuar();
                        break;
                    case 3:

                        String nitIngresado;
                        int rol = 0;
                        do {
                            Scanner roles = new Scanner(System.in);
                            try {
                                System.out.println("*\t\tIngresar al Sistema Para trabajar ticket\t\t*");
                                System.out.println("*********************************");
                                System.out.println("Ingrese su nit");
                                nitIngresado = DatosIngresoSistema.nextLine();
                                System.out.println("INGRESE EL NUMERO DE ROL AL QUE PERTENECE");
                                System.out.println("\t\t1.Mesa de Ayuda");
                                System.out.println("\t\t2.Soporte Tecnico");
                                System.out.println("\t\t3.Desarrollador.");
                                System.out.println("\t\t0.Cancelar.");
                                rol = roles.nextInt();
                                switch (rol) {
                                    case 1:
                                        Collections.reverse(ColaServicio.cola);
                                        int seleccion = 0;
                                        do {
                                            Scanner seleccionTrabajar = new Scanner(System.in);
                                            try {
                                                System.out.println("Seleccione una opcion para continuar");
                                                System.out.println("\t1.\tSolicitar Asignacion de Ticket");
                                                System.out.println("\t0.\tSalir");
                                                seleccion = seleccionTrabajar.nextInt();
                                                switch (seleccion) {
                                                    case 1:
                                                        int mesa = 0;
                                                        String nitResultado = null, DescripcionResultado = null;
                                                        for (Ticket colas : ColaServicio.cola) {
                                                            if (colas.getEstado().equals("mesa")) {
                                                                mesa = colas.getId();
                                                                nitResultado = colas.getNitUsuario();
                                                                DescripcionResultado = colas.getDescripcionProblema();
                                                            }
                                                        }
                                                        Ticket.Agregar(nitIngresado, "Solicito Asignacion de Ticket", TipoEvento.Asignar, fecha, hora, mesa);
                                                        if (mesa == 0) {
                                                            System.out.println("Felicidades no tiene datos en cola");
                                                            Continuar();
                                                            seleccion = 0;
                                                        } else {
                                                            int seleccionProceso = 0;
                                                            do {
                                                                Scanner seleccionProcesar = new Scanner(System.in);
                                                                try {
                                                                    Formatter mesaMascara = new Formatter();
                                                                    mesaMascara.format("%06d", mesa);
                                                                    System.out.println("NUEVO TOCKET A TRABAJAR");
                                                                    System.out.println("No.\t" + mesaMascara);
                                                                    System.out.println("Nit\t" + nitResultado);
                                                                    System.out.println("Detalle\t" + DescripcionResultado);
                                                                    System.out.println("Opciones a elegir con el ticket");
                                                                    System.out.println("\t1.\tEscalar");
                                                                    System.out.println("\t2.\tSolucionar");
                                                                    System.out.println("\t0.\tSalir");
                                                                    seleccionProceso = seleccionProcesar.nextInt();
                                                                    switch (seleccionProceso) {
                                                                        case 1:
                                                                            Ticket.Agregar(nitIngresado, "Traslado Ticket a Mesa de soporte", TipoEvento.Mover, fecha, hora, mesa);
                                                                            for (Ticket colas : ColaServicio.cola) {
                                                                                if (colas.getId() == mesa) {
                                                                                    colas.setEstado("soporte");
                                                                                }
                                                                            }
                                                                            System.out.println("El Ticket fue trasladado a mesa de Soporte");
                                                                            Continuar();
                                                                            seleccionProceso = 0;
                                                                            break;
                                                                        case 2:
                                                                            System.out.println("Ingrese mensaje de solucion");
                                                                            String mensaje = DatosIngresoSistema.nextLine();
                                                                            Ticket.Agregar(nitIngresado, mensaje, TipoEvento.Solucion, fecha, hora, mesa);
                                                                            for (Ticket colas : ColaServicio.cola) {
                                                                                if (colas.getId() == mesa) {
                                                                                    colas.setEstado("Resuelto");
                                                                                }
                                                                            }
                                                                            System.out.println("El Ticket fue Solucionado");
                                                                            Continuar();
                                                                            seleccionProceso = 0;
                                                                            break;
                                                                        default:
                                                                            if (seleccionProceso == 0) {
                                                                            } else {
                                                                                System.out.println("Seleccion no valida");
                                                                                Continuar();
                                                                            }
                                                                    }
                                                                } catch (Exception w) {
                                                                    System.out.println("Debe de Ingresar solo el indice del menu");
                                                                    Continuar();
                                                                }
                                                            } while (seleccionProceso != 0);
                                                        }
                                                        break;
                                                    default:
                                                        if (seleccion == 0) {
                                                        } else {
                                                            System.out.println("Seleccion no valida");
                                                            Continuar();
                                                        }
                                                }
                                            } catch (Exception w) {
                                                System.out.println("Debe de Ingresar solo el indice del menu");
                                                Continuar();
                                            }
                                        } while (seleccion != 0);
                                        rol = 0;
                                        Collections.reverse(ColaServicio.cola);
                                        break;

                                    case 2:
                                        int seleccion2 = 0;
                                        do {
                                            Scanner seleccionTrabajar = new Scanner(System.in);
                                            try {
                                                System.out.println("Seleccione una opcion para continuar");
                                                System.out.println("\t1.\tSolicitar Asignacion de Ticket");
                                                System.out.println("\t0.\tSalir");
                                                seleccion2 = seleccionTrabajar.nextInt();
                                                switch (seleccion2) {
                                                    case 1:

                                                        int mesa = 0;
                                                        String nitResultado = null, DescripcionResultado = null;
                                                        for (Ticket colas : ColaServicio.cola) {
                                                            if (colas.getEstado().equals("soporte")) {
                                                                mesa = colas.getId();
                                                                nitResultado = colas.getNitUsuario();
                                                                DescripcionResultado = colas.getDescripcionProblema();
                                                            }
                                                        }
                                                        Ticket.Agregar(nitIngresado, "Solicito Asignacion de Ticket", TipoEvento.Asignar, fecha, hora, mesa);
                                                        if (mesa == 0) {
                                                            System.out.println("Felicidades no tiene datos en cola");
                                                            Continuar();
                                                            seleccion2 = 0;
                                                        } else {
                                                            int seleccionProceso = 0;
                                                            do {
                                                                Scanner seleccionProcesar = new Scanner(System.in);
                                                                try {
                                                                    Formatter mesaMascara = new Formatter();
                                                                    mesaMascara.format("%06d", mesa);
                                                                    System.out.println("NUEVO TOCKET A TRABAJAR");
                                                                    System.out.println("No.\t" + mesaMascara);
                                                                    System.out.println("Nit\t" + nitResultado);
                                                                    System.out.println("Detalle\t" + DescripcionResultado);
                                                                    System.out.println("Opciones a elegir con el ticket");
                                                                    System.out.println("\t1.\tEscalar");
                                                                    System.out.println("\t2.\tSolucionar");
                                                                    System.out.println("\t0.\tSalir");
                                                                    seleccionProceso = seleccionProcesar.nextInt();
                                                                    switch (seleccionProceso) {
                                                                        case 1:
                                                                            Ticket.Agregar(nitIngresado, "Traslado Ticket a Mesa de Desarrollo", TipoEvento.Mover, fecha, hora, mesa);
                                                                            for (Ticket colas : ColaServicio.cola) {
                                                                                if (colas.getId() == mesa) {
                                                                                    colas.setEstado("desarrollo");
                                                                                }
                                                                            }
                                                                            System.out.println("El Ticket fue trasladado a mesa de Desarrollo");
                                                                            Continuar();
                                                                            seleccionProceso = 0;
                                                                            break;
                                                                        case 2:
                                                                            System.out.println("Ingrese mensaje de solucion");
                                                                            String mensaje = DatosIngresoSistema.nextLine();
                                                                            Ticket.Agregar(nitIngresado, mensaje, TipoEvento.Solucion, fecha, hora, mesa);
                                                                            for (Ticket colas : ColaServicio.cola) {
                                                                                if (colas.getId() == mesa) {
                                                                                    colas.setEstado("Resuelto");
                                                                                }
                                                                            }
                                                                            System.out.println("El Ticket fue resuelto");
                                                                            Continuar();
                                                                            seleccionProceso = 0;
                                                                            break;
                                                                        default:
                                                                            if (seleccionProceso == 0) {
                                                                            } else {
                                                                                System.out.println("Seleccion no valida");
                                                                                Continuar();
                                                                            }
                                                                    }
                                                                } catch (Exception w) {
                                                                    System.out.println("Debe de Ingresar solo el indice del menu");
                                                                    Continuar();
                                                                }
                                                            } while (seleccionProceso != 0);
                                                        }
                                                        break;
                                                    default:
                                                        if (seleccion2 == 0) {
                                                        } else {
                                                            System.out.println("Seleccion no valida");
                                                            Continuar();
                                                        }
                                                }
                                            } catch (Exception w) {
                                                System.out.println("Debe de Ingresar solo el indice del menu");
                                                Continuar();
                                            }
                                        } while (seleccion2 != 0);
                                        rol = 0;
                                        break;
                                    case 3:
                                        int seleccion3 = 0;
                                        do {
                                            Scanner seleccionTrabajar = new Scanner(System.in);
                                            try {
                                                System.out.println("Seleccione una opcion para continuar");
                                                System.out.println("\t1.\tSolicitar Asignacion de Ticket");
                                                System.out.println("\t0.\tSalir");
                                                seleccion3 = seleccionTrabajar.nextInt();
                                                switch (seleccion3) {
                                                    case 1:

                                                        int mesa = 0;
                                                        int aleatorio = 0;
                                                        String nitResultado = null, DescripcionResultado = null;
                                                        ArrayList<Integer> set1 = new ArrayList<>();
                                                        for (Ticket colas : ColaServicio.cola) {
                                                            if (colas.getEstado().equals("desarrollo")) {
                                                                set1.add(colas.getId());
                                                                int random2 = (int) (Math.random() * set1.size());
                                                                if (colas.getId() == set1.get(random2)) {
                                                                    mesa = colas.getId();
                                                                    nitResultado = colas.getNitUsuario();
                                                                    DescripcionResultado = colas.getDescripcionProblema();
                                                                }
                                                            }
                                                        }
                                                        Ticket.Agregar(nitIngresado, "Solicito Asignacion de Ticket", TipoEvento.Asignar, fecha, hora, mesa);
                                                        if (mesa == 0) {
                                                            System.out.println("Felicidades no tiene datos en cola");
                                                            Continuar();
                                                            seleccion3 = 0;
                                                        } else {
                                                            int seleccionProceso = 0;
                                                            do {
                                                                Scanner seleccionProcesar = new Scanner(System.in);
                                                                try {
                                                                    Formatter mesaMascara = new Formatter();
                                                                    mesaMascara.format("%06d", mesa);
                                                                    System.out.println("NUEVO TOCKET A TRABAJAR");
                                                                    System.out.println("No.\t" + mesaMascara);
                                                                    System.out.println("Nit\t" + nitResultado);
                                                                    System.out.println("Detalle\t" + DescripcionResultado);
                                                                    System.out.println("Opciones a elegir con el ticket");
                                                                    System.out.println("\t1.\tSolucionar");
                                                                    System.out.println("\t0.\tSalir");
                                                                    seleccionProceso = seleccionProcesar.nextInt();
                                                                    switch (seleccionProceso) {
                                                                        case 1:
                                                                            System.out.println("Ingrese mensaje de solucion");
                                                                            String mensaje = DatosIngresoSistema.nextLine();
                                                                            Ticket.Agregar(nitIngresado, mensaje, TipoEvento.Solucion, fecha, hora, mesa);
                                                                            for (Ticket colas : ColaServicio.cola) {
                                                                                if (colas.getId() == mesa) {
                                                                                    colas.setEstado("Resuelto");
                                                                                }
                                                                            }
                                                                            System.out.println("El Ticket fue resuelto");
                                                                            Continuar();
                                                                            seleccionProceso = 0;
                                                                            break;
                                                                        default:
                                                                            if (seleccionProceso == 0) {
                                                                            } else {
                                                                                System.out.println("Seleccion no valida");
                                                                                Continuar();
                                                                            }
                                                                    }
                                                                } catch (Exception w) {
                                                                    System.out.println("Debe de Ingresar solo el indice del menu");
                                                                    Continuar();
                                                                }
                                                            } while (seleccionProceso != 0);
                                                        }
                                                        break;
                                                    default:
                                                        if (seleccion3 == 0) {
                                                        } else {
                                                            System.out.println("Seleccion no valida");
                                                            Continuar();
                                                        }
                                                }
                                            } catch (Exception w) {
                                                System.out.println("Debe de Ingresar solo el indice del menu");
                                                Continuar();
                                            }
                                        } while (seleccion3 != 0);
                                        rol = 0;
                                        break;
                                    default:
                                        if (rol == 0) {
                                        } else {
                                            System.out.println("Seleccione un rol Valido");
                                            Continuar();
                                        }
                                }
                            } catch (Exception w) {
                                System.out.println("El rol no existe");
                                Continuar();
                            }
                        } while (rol != 0);
                        break;
                    case 4:
                        ColaServicio.verReporteGeneral();
                        Continuar();
                        break;
                    case 5:
                        ColaServicio.verReporteCola();
                        Continuar();
                        break;
                    case 6:
                        ColaServicio.verReporteUsurios();
                        Continuar();
                        break;
                    default:
                        if (Opcion == 0) {
                        } else {
                            System.out.println("Por favor ingrese un numero valido del menu");
                            Continuar();
                        }
                }
            } catch (Exception e) {
                System.out.println("No se permiten letras ni caracteres especiales, vuelva a intentarlo");
                Continuar();
            }
        } while (Opcion != 0);
    }

    /** Carga un documento predefinido del sistema
     * * <p>
     *      *     Al seleccionar la opcion 1 el usuario carga automaticamente un archivo Json
     *      *     que esta predefinido en el sistema para iniciar a gestionar los ticket
     * @throws Exception mensjae si no carga el archivo
     */
    public static void CargarDocumento() throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("C:\\informacion.json"));
        JSONArray jsonarray = (JSONArray)obj;
        ArrayList<Ticket> Cargarticket = new ArrayList<>();
        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject jsonObject = (JSONObject)jsonarray.get(i);
            //System.out.println("ticket = "+jsonObject.get("ticket"));
            //System.out.println("nitUsuario = "+ jsonObject.get("nitUsuario"));
            //System.out.println("cola = "+jsonObject.get("cola"));
            //System.out.println("problema = "+ jsonObject.get("problema"));
            //int ticket= Integer.parseInt(jsonObject.get("ticket").toString());
            String nit= jsonObject.get("nitUsuario").toString();
            String problema= jsonObject.get("problema").toString();
            String cola= jsonObject.get("cola").toString();
            ColaServicio.Agregar(nit,problema,cola);
        }
    }

    /** Pausa el sistema al realizar un event
     * <p>
     *     Al realizar alguna operacion el sistema hace una pausa para mostrar algun mensaje
     *     el usuario para continuar debera de presionar la tecla enter
     * @throws  Exception e - muestra un error que solo debe de presionar enter
     */
    static public void Continuar()throws Exception {
        String seguir;
        Scanner ingreso = new Scanner(System.in);
        System.out.println("Presione ENTER para continuar...");
        try {
            seguir = ingreso.nextLine();
        }
        catch(Exception e) {
            System.out.println("Solo enter");
        }
    }
}
