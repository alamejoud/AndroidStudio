package com.example.worldcup2022;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.ConnectException;

public class StadiumFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private int stadiumId;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SQLiteOpenHelper worldCupOpenHelper;
    private TextView name;
    private TextView location;
    private LinearLayout image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stadium, container, false);

        worldCupOpenHelper = new WorldCupDatabaseHelper(getContext());

        mapView = view.findViewById(R.id.mapView);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            stadiumId = savedInstanceState.getInt("ID");
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        try {
            db = worldCupOpenHelper.getReadableDatabase();

        } catch (SQLException e) {
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        cursor = db.query("STADIUMS", new String[]{"NAME", "IMAGE", "LOCATION", "LONG", "LAT"}, null, null, null, null, null);

        cursor.moveToPosition(stadiumId);

        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng ll = new LatLng(cursor.getDouble(cursor.getColumnIndexOrThrow("LAT")),cursor.getDouble(cursor.getColumnIndexOrThrow("LONG")));
        googleMap.addMarker(new MarkerOptions().position(ll).title(getString(cursor.getInt(cursor.getColumnIndexOrThrow("NAME")))));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(10));
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();

        View view = getView();

        if (view != null) {
            name = view.findViewById(R.id.name);
            location = view.findViewById(R.id.location);
            image = view.findViewById(R.id.image);

            name.setText(getString(cursor.getInt(cursor.getColumnIndexOrThrow("NAME"))));
            location.setText(getString(cursor.getInt(cursor.getColumnIndexOrThrow("LOCATION"))));
            image.setBackground(getResources().getDrawable(cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE"))));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
            outState.putInt("ID", stadiumId);
        }
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void setStadiumId(int id) {
        stadiumId = id;
    }
}