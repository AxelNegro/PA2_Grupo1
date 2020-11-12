package com.example.tp_integrador.ui.cliente.Mapa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class fragMapa extends Fragment implements OnMapReadyCallback{

      private GoogleMap mMap;
/*    private OnMapReadyCallback callback = new OnMapReadyCallback() {


         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng capital = new LatLng(-34.6075682, -58.4370894);
            googleMap.addMarker(new MarkerOptions().position(capital).title("Capital Federal"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(capital, 13));
        }
    };
*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng inst1 = new LatLng(-34.6064003,-58.5281944);
        mMap.addMarker(new MarkerOptions().position(inst1).title("Instituto de Lengua de Señas Argentina ilsa").snippet("Pres. Tte. Gral. Juan Domingo Perón 1515, C1042 CABA"));

        LatLng inst2 = new LatLng(-34.4527123,-58.812627);
        mMap.addMarker(new MarkerOptions().position(inst2).title("Instituto Lengua de Señas Argentina").snippet("Francisco N. de Laprida 1802, B1602EFJ Florida, Provincia de Buenos Aires"));

        LatLng inst3 = new LatLng(-34.6010235,-58.5406727);
        mMap.addMarker(new MarkerOptions().position(inst3).title("Ilsa Instituto De Lengua De Señas Argentinas").snippet("Viamonte 2367, C1056 ABK, Buenos Aires"));

        LatLng inst4 = new LatLng(-34.5201675,-58.8124172);
        mMap.addMarker(new MarkerOptions().position(inst4).title("Instituto De Lengua De Señas").snippet("Av. Pres. Juan Domingo Perón 4559, B1665KQX José C. Paz, Provincia de Buenos Aires"));

        LatLng inst5 = new LatLng(-34.6285087,-58.5544725);
        mMap.addMarker(new MarkerOptions().position(inst5).title("Escuela de Lengua de Señas Argentina José A. Terry").snippet("Maza 1486, C1240 ADF, Buenos Aires"));

        LatLng inst6 = new LatLng(-34.7598876,-58.5458207);
        mMap.addMarker(new MarkerOptions().position(inst6).title("Manos que Hablan Cursos Lengua de Señas Argentinas").snippet("HOM, Laprida 629, B1832 Lomas de Zamora, Provincia de Buenos Aires"));

        LatLng inst7 = new LatLng(-34.5623625,-58.584628);
        mMap.addMarker(new MarkerOptions().position(inst7).title("Instituto Argentino de La Audición y El Lenguaje").snippet("Av. Luis María Campos 1582, C1426 BPN, Buenos Aires"));

        LatLng inst8 = new LatLng(-34.5965491,-58.5808248);
        mMap.addMarker(new MarkerOptions().position(inst8).title("Villasoles").snippet("DHL, Gurruchaga 568, C1414 CABA"));

        LatLng inst9 = new LatLng(-34.6355498,-58.5823088);
        mMap.addMarker(new MarkerOptions().position(inst9).title("Instituto de Formación Técnica Superior Nº 27").snippet("C1406HVC, Av. Asamblea 1221, C1406HVC CABA"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(inst1));
    }
}