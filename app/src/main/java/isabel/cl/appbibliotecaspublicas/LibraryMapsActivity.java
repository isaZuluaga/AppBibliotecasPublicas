package isabel.cl.appbibliotecaspublicas;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


//import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.LocationListener;
import android.widget.Toast;

public class LibraryMapsActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    /* añadimos un marcador el cual correspondera a la ubicación actual del usuario,
    y las varibles latitud y longitud donde almacenaremos las coordenadas actuales
    de la posición para luego crear el marcador con dichas variables
    */
    private Marker myMarker;
    double lat = 0.0;
    double lng = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        myLocation();

        // Se establecen las latitudes y longitudes de los lugares donde van los markers tipo de dato: LatLng (latitud, longitud)
        LatLng filialJuanZuletaFerrer = new LatLng(6.274005957000043, -75.55843265099998);
        LatLng bibliotecaGabrielGarciaMarquez = new LatLng(6.304412644000024, -75.57773820199998);
        LatLng bibliotecaPublicaCentroOccidental = new LatLng(6.253418128000021, -75.62479067399994);
        LatLng archivoHistoricodeMedellin = new LatLng(6.247632202000034, -75.56339566099996);

        //(6.274005957000043, -75.55843265099998)Filial Juan Zuleta Ferrer
        //Biblioteca Gabriel García Márquez (6.304412644000024, -75.57773820199998)
        //Biblioteca Pública Centro Occidental (6.253418128000021, -75.62479067399994)
        //Archivo Histórico de Medellín (6.247632202000034, -75.56339566099996)

        // en los proximos bloques de codigo se inicializa el marker y se establecen opciones como el titulo subtitulo y el color....

        Marker filialjuanzuletaferrer = mMap. addMarker( new MarkerOptions()
                .position(filialJuanZuletaFerrer)
                .title("Filial Juan Zuleta Ferrer")
                .snippet("CR 49A 80 46  Brasilia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory. HUE_CYAN))

        );

        Marker bibliotecagabrielgarciamarquez  = mMap. addMarker( new MarkerOptions()
                .position(bibliotecaGabrielGarciaMarquez)
                .title("Biblioteca Gabriel García Márquez")
                .snippet("CR 80 104 04 Santander")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))

        );

        Marker bibliotecapublicacentrooccidental  = mMap. addMarker( new MarkerOptions()
                .position(bibliotecaPublicaCentroOccidental)
                .title("Biblioteca Pública Centro Occidental")
                .snippet("CL 39D 112 81 La Floresta")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))

        );

        Marker archivohistoricodemedellin  = mMap. addMarker( new MarkerOptions()
                .position(archivoHistoricodeMedellin)
                .title("Archivo Histórico de Medellín")
                .snippet("CL 50 43 64 La Candelaria")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

        );


        // para auto-zoom y auto center
        LatLngBounds bounds = new LatLngBounds.Builder()

                .include(filialJuanZuletaFerrer)
                .include(bibliotecaGabrielGarciaMarquez)
                .include(bibliotecaPublicaCentroOccidental)
                .include(archivoHistoricodeMedellin)

                .build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 300));


        mMap.setOnInfoWindowClickListener(this); // acabada de añadir



    }

    // eventos de click en la ventana de información de cada Marker acabado de agregar
    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LibraryMapsActivity.this, SeeLibraryInfo.class);
        startActivity(intent);

    }


    // metodo para añadir el marcador con mi ubicación
    private void addMarker(double lat, double lng) {
        LatLng coordinates = new LatLng(lat, lng);
        CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(coordinates, 16);
        if (myMarker != null) myMarker.remove();
        myMarker = mMap.addMarker(new MarkerOptions()
                .position(coordinates)
                .title("Mi Posición Actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_userlocation)));
        mMap.animateCamera(myLocation);
    }

    // metodo para actualizar la ubicación actual
    private void updateLocation(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            addMarker(lat, lng);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void myLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        updateLocation(location);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,15000,0,locListener);
    }

}
