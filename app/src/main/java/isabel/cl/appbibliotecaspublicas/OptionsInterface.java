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

        //addDataToDataBase();
        eliminateFromDataBase();
        addDataToDataBase();



    }

    private void eliminateFromDataBase() {
        SQLiteDatabase db=conn.getWritableDatabase();
        int idnumber = 1;
        int idnumber2= 2;
        String delete = "DELETE FROM " + Utilities.LIBRARY_TABLE+ " WHERE "+Utilities.ID_FIELD+"='"+idnumber+"'";
        String delete2 = "DELETE FROM " + Utilities.LIBRARY_TABLE+ " WHERE "+Utilities.ID_FIELD+"='"+idnumber2+"'";
        //db.execSQL("DELETE FROM " + Utilities.LIBRARY_TABLE+ " WHERE "+Utilities.ID_FIELD+"= 1,"+Utilities.NAME_FIELD+"='Biblioteca Gabriel Garcia Marquez',"+Utilities.TELEPHONE_FIELD+"='4776727',"+Utilities.ADDRESS_FIELD+"='CR 80 104 04',"+Utilities.MAIL_FIELD+"='docedeoctubre@bibliotecasmedellin.gov.co',"+Utilities.LINK_FIELD+"='http://bibliotecasmedellin.gov.co/parque-biblioteca-doce-de-octubre/',"+Utilities.TYPE_FIELD+"='Parque Biblioteca',"+Utilities.SCHEDULE_FIELD+"='Lunes a sábado: 9:00 am - 8:00 pm',"+Utilities.NEIGHBORHOOD_FIELD+"='Santander',"+Utilities.COMMUNE_FIELD+"='Doce de Octubre'");
        db.execSQL(delete);
        db.execSQL(delete2);
        db.close();
        Toast.makeText(this, "Info eliminate in the database",
                Toast.LENGTH_SHORT).show();
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
                " VALUES (2,'Biblioteca Julio Gonzalez Marín','4776728','CR 81 104 04','docedeoctubre@bibliotecasmedellin.gov.co','http://bibliotecasmedellin.gov.co/parque-biblioteca-doce-de-octubre/','Parque Biblioteca','Lunes a sábado: 9:00 am - 8:00 pm','Santander','Doce de Octubre')";

        db.execSQL(insert);
        db.execSQL(insert2);
        db.close();
        Toast.makeText(this, "Info added to the database",
                Toast.LENGTH_SHORT).show();
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
}
