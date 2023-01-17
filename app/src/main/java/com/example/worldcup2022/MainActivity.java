package com.example.worldcup2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements HomeFragment.Listener, AllStadiumsFragment.Listener2, TeamFragment.Listener3{
    public DrawerLayout drawer;
    private int STORAGE_PERMISSION_CODE = 1;
    private SQLiteOpenHelper worldCupOpenHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private View main;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        worldCupOpenHelper = new WorldCupDatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        ft.replace(R.id.fragment_container, new HomeFragment());
                        break;
                    case R.id.nav_matches:
                        ft.replace(R.id.fragment_container, new MatchFragment());
                        break;
                    case R.id.nav_teams:
                        ft.replace(R.id.fragment_container, new TeamFragment());
                        break;
                    case R.id.nav_stadiums:
                        ft.replace(R.id.fragment_container, new AllStadiumsFragment());
                        break;

                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.screenshot:
                takeScreenshot(item);
                return true;
            case R.id.action_settings:
                share();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void buttonClicked(int id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            GroupFragment group = new GroupFragment();
            group.setGroupId(id);
            ft.replace(R.id.fragment_container, group);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void itemClicked(int id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            StadiumFragment stadium = new StadiumFragment();
            stadium.setStadiumId(id);
            ft.replace(R.id.fragment_container, stadium);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    protected void takeScreenshot(MenuItem item) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            try {
                item.setEnabled(false);
                main = findViewById(R.id.main);
                image = findViewById(R.id.image1);
                image.setVisibility(View.VISIBLE);
                Bitmap b = Screenshot.takescreenshotOfRootView(image);
                image.setImageBitmap(b);
                Toast.makeText(this, "Screenshot taken", Toast.LENGTH_SHORT).show();
                main.setBackgroundColor(Color.parseColor("#55000000"));
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        main.setBackgroundColor(Color.parseColor("#00000000"));
                        image.setImageBitmap(null);
                        image.setVisibility(View.GONE);
                        item.setEnabled(true);
                    }
                }, 1000);

            } catch (Throwable e) {
                // Several error may come out with file handling or DOM
                e.printStackTrace();
            }
        }
        else {
            requestStoragePermission();
        }

    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This permission is needed to take screenshots")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String matches = getString(R.string.todays_matches) + "\n\n";

        try {
            db = worldCupOpenHelper.getReadableDatabase();

        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        Calendar now = Calendar.getInstance();

        String time = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);

        cursor = db.rawQuery("SELECT TEAM1, TEAM1_IMAGE, TEAM2, TEAM2_IMAGE, TEAM1_SCORE, TEAM2_SCORE, DATE, TIME FROM MATCHES WHERE DATE = ?" , new String[] {time});

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                matches += getString(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM1"))) + "   " + Integer.toString(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM1_SCORE"))) +
                        " : " + Integer.toString(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM2_SCORE"))) + "   " + getString(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM2"))) + "\n" +
                        cursor.getString(cursor.getColumnIndexOrThrow("DATE")) +  " " + cursor.getString(cursor.getColumnIndexOrThrow("TIME")) + "\n\n";

            }
        } else {
            matches += getString(R.string.No_Matches);

        }

        intent.putExtra(Intent.EXTRA_TEXT, matches);
        startActivity(intent);
    }

    @Override
    public void ViewClicked(int ID) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            TeamInfoFragment group = new TeamInfoFragment();
            group.setID(ID);
            ft.replace(R.id.fragment_container, group);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}