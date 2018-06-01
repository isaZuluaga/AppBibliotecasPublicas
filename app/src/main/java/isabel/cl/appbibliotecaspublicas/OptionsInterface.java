package isabel.cl.appbibliotecaspublicas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import isabel.cl.appbibliotecaspublicas.utilities.Utilities;

public class OptionsInterface extends AppCompatActivity {
    SQliteConnectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_interface);
        // pasa como parametros (el contexto de la app = this, el nombre de la bd = "bd_libraries, null, version de la bd = 1");

        conn=new SQliteConnectionHelper(getApplicationContext(),"bd_libraries",null,1);


        eliminateFromDataBase();
        addDataToDataBase();

    }

    private void eliminateFromDataBase() {
        SQLiteDatabase db=conn.getWritableDatabase();
        int idnumber = 1;
        int idnumber2= 2;
        int idnumber3= 3;
        int idnumber4= 4;

        String delete = "DELETE FROM " + Utilities.LIBRARY_TABLE+ " WHERE "+Utilities.ID_FIELD+"='"+idnumber+"'";
        String delete2 = "DELETE FROM " + Utilities.LIBRARY_TABLE+ " WHERE "+Utilities.ID_FIELD+"='"+idnumber2+"'";
        String delete3 = "DELETE FROM " + Utilities.LIBRARY_TABLE+ " WHERE "+Utilities.ID_FIELD+"='"+idnumber3+"'";
        String delete4 = "DELETE FROM " + Utilities.LIBRARY_TABLE+ " WHERE "+Utilities.ID_FIELD+"='"+idnumber4+"'";

        db.execSQL(delete);
        db.execSQL(delete2);
        db.execSQL(delete3);
        db.execSQL(delete4);
        db.close();
        Toast.makeText(this, "Info eliminate in the database", Toast.LENGTH_SHORT).show();
    }

    private void addDataToDataBase (){
        // para la conexion a la bd se pasa como parametros (el contxto de la app = this, el nombre de la bd = "bd_libraries, null, version de la bd = 1");

        SQLiteDatabase db=conn.getWritableDatabase();

        //insert into libraries (id,nombre,telefono,direccion,correo,link,tipo,horario,barrio,comuna) values (valores que van en los campos en orden,'texto entre comilla simple')
        String insert="INSERT INTO "+ Utilities.LIBRARY_TABLE
                +" ("
                +Utilities.ID_FIELD+","+Utilities.NAME_FIELD+","+Utilities.TELEPHONE_FIELD+","+Utilities.ADDRESS_FIELD+","+Utilities.MAIL_FIELD+","+Utilities.LINK_FIELD+","+Utilities.TYPE_FIELD+","+Utilities.SCHEDULE_FIELD+","+Utilities.NEIGHBORHOOD_FIELD+","+Utilities.COMMUNE_FIELD+")" +
                " VALUES (1,'Biblioteca Gabriel Garcia Marquez','4776727','CR 80 104 04','docedeoctubre@bibliotecasmedellin.gov.co','http://bibliotecasmedellin.gov.co/parque-biblioteca-doce-de-octubre/','Parque Biblioteca','Lunes a sábado: 9:00 am - 8:00 pm','Santander','Doce de Octubre')";



        String insert2="INSERT INTO "+ Utilities.LIBRARY_TABLE
                +" ("
                +Utilities.ID_FIELD+","+Utilities.NAME_FIELD+","+Utilities.TELEPHONE_FIELD+","+Utilities.ADDRESS_FIELD+","+Utilities.MAIL_FIELD+","+Utilities.LINK_FIELD+","+Utilities.TYPE_FIELD+","+Utilities.SCHEDULE_FIELD+","+Utilities.NEIGHBORHOOD_FIELD+","+Utilities.COMMUNE_FIELD+")" +
                " VALUES (2,'Biblioteca Pública Centro Occidental','3870812','CL 39D 112 81','floresta@biblotecasmedellin.gov.co','http://bibliotecasmedellin.gov.co/biblioteca-publico-barrial-la-floresta/','Biblioteca de proximidad','Lunes a viernes: 8:00 am - 7:00 pm','La Floresta','La América')";


        String insert3="INSERT INTO "+ Utilities.LIBRARY_TABLE
                +" ("
                +Utilities.ID_FIELD+","+Utilities.NAME_FIELD+","+Utilities.TELEPHONE_FIELD+","+Utilities.ADDRESS_FIELD+","+Utilities.MAIL_FIELD+","+Utilities.LINK_FIELD+","+Utilities.TYPE_FIELD+","+Utilities.SCHEDULE_FIELD+","+Utilities.NEIGHBORHOOD_FIELD+","+Utilities.COMMUNE_FIELD+")" +
                " VALUES (3,'Archivo Histórico de Medellín','3857346 ','CL 50 43 64','archivo.historico@medellin.gov.co','http://bibliotecasmedellin.gov.co/archivo-historico-de-medellin/','Archivo Histórico','Lunes a jueves: 7:30 am-12:00 m y 1:30-5:30 pm','La Candelaria','La Candelaria')";


        String insert4="INSERT INTO "+ Utilities.LIBRARY_TABLE
                +" ("
                +Utilities.ID_FIELD+","+Utilities.NAME_FIELD+","+Utilities.TELEPHONE_FIELD+","+Utilities.ADDRESS_FIELD+","+Utilities.MAIL_FIELD+","+Utilities.LINK_FIELD+","+Utilities.TYPE_FIELD+","+Utilities.SCHEDULE_FIELD+","+Utilities.NEIGHBORHOOD_FIELD+","+Utilities.COMMUNE_FIELD+")" +
                " VALUES (4,'Filial Juan Zuleta Ferrer','2118232','CR 49A 80 46','bjzuletaferrer@bibliotecapiloto.gov.co','http://www.reddebibliotecas.org.co/bibliotecas/bpp-filial-juan-zuleta-ferrer-campo-vald%C3%A9s','Biblioteca Pública Piloto y filiales','Lunes a viernes: 10:00 am - 5:45 pm','Brasilia','Aranjuez')";



        db.execSQL(insert);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.close();
        Toast.makeText(this, "Info added to the database",Toast.LENGTH_SHORT).show();
    }

    void goLibraryMap (View v)
    {
        Intent intention = new Intent(this, LibraryMapsActivity.class);
        startActivity(intention);

    }
    void goSeeLibraryInfo (View v){

        Intent intention = new Intent(this, SeeLibraryInfo.class);
        startActivity(intention);

    }
    void goFilterConsult (View v){

        Intent intention = new Intent(this, FilterOptionsInterface.class);
        startActivity(intention);

    }
}
