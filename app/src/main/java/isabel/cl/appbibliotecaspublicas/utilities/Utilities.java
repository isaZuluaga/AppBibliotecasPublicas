package isabel.cl.appbibliotecaspublicas.utilities;

/**
 * Created by Isabel on 28/05/18.
 */
public class Utilities {

    //constantes campos tabla libraries
    public static final String LIBRARY_TABLE="libraries";
    public static final String ID_FIELD="id";
    public static final String NAME_FIELD="nombre";
    public static final String TELEPHONE_FIELD="telefono";
    public static final String ADDRESS_FIELD="direccion";
    public static final String MAIL_FIELD="correo";
    public static final String LINK_FIELD="link";
    public static final String TYPE_FIELD="tipo";
    public static final String SCHEDULE_FIELD="horario";
    public static final String NEIGHBORHOOD_FIELD="barrio";
    public static final String COMMUNE_FIELD="comuna";

   public static final String CREATE_LIBRARY_TABLE = "CREATE TABLE "+LIBRARY_TABLE+" ("+ID_FIELD+" INTEGER, "+NAME_FIELD+" TEXT, "+TELEPHONE_FIELD+" TEXT, "+ADDRESS_FIELD+" TEXT, "+MAIL_FIELD+" TEXT, "+LINK_FIELD+" TEXT, "+TYPE_FIELD+" TEXT, "+SCHEDULE_FIELD+" TEXT, "+NEIGHBORHOOD_FIELD+" TEXT, "+COMMUNE_FIELD+" TEXT)";
}