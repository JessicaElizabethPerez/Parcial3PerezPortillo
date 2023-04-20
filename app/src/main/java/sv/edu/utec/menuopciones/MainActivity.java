package sv.edu.utec.menuopciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import java.sql.SQLData;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sv.edu.utec.menuopciones.clases.CamFragment;
import sv.edu.utec.menuopciones.clases.FavFragment;
import sv.edu.utec.menuopciones.clases.PricipalFragment;
import sv.edu.utec.menuopciones.clases.SearchFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btnNav;
    EditText txtNombre1,txtApellido1,txtTelefono1, txtCorreo1;
    Button btnInsertar1,btnActualizar1,btnEliminar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNav=findViewById(R.id.btnNav);

        txtNombre1=findViewById(R.id.txtNombre);
        txtApellido1=findViewById(R.id.txtApellido);
        txtCorreo1=findViewById(R.id.txtCorreo);
        txtTelefono1=findViewById(R.id.txtTelefono);

        btnInsertar1=findViewById(R.id.btnInsertar);
        btnActualizar1=findViewById(R.id.btnActualizar);
        btnEliminar=findViewById(R.id.btnEliminar);


        btnNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) navaListener);

        btnInsertar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"contactos",null,2);
                SQLiteDatabase bd= admin.getWritableDatabase();
                String nom=txtNombre1.getText().toString();
                String ape=txtApellido1.getText().toString();

                String tel=txtTelefono1.getText().toString();
                String cor=txtCorreo1.getText().toString();


                ContentValues informacion =new ContentValues();
                informacion.put("nombre",nom);
                informacion.put("apellido",ape);
                informacion.put("telefono",tel);
                informacion.put("correo",cor);

                try {
                    bd.insert("parcial", null, informacion);

                    Toast.makeText(getApplicationContext(), "Se inserto el contacto", Toast.LENGTH_LONG).show();
                    bd.close();

                } catch (Exception e){
                    //pendiente imprimir error
                    Toast.makeText(getApplicationContext(), ""+e.getCause(), Toast.LENGTH_LONG).show();
                }

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"contactos",null,2);
                SQLiteDatabase bd= admin.getWritableDatabase();
                String nom=txtNombre1.getText().toString();

                int cat=bd.delete("parcial",
                        "nombre="+nom,null);
                bd.close();
                txtNombre1.setText("");
                txtApellido1.setText("");
                txtTelefono1.setText("");
                txtCorreo1.setText("");

                if(cat==1){
                    Toast.makeText(getApplicationContext(),"Se borro el contacto",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"No se borro el contacto",Toast.LENGTH_LONG).show();

                }
            }
        });
        btnActualizar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"contactos",null,2);
                SQLiteDatabase bd= admin.getWritableDatabase();
                String nom=txtNombre1.getText().toString();
                String ape=txtApellido1.getText().toString();

                String tel=txtTelefono1.getText().toString();
                String cor=txtCorreo1.getText().toString();

                ContentValues informacion =new ContentValues();
                informacion.put("nombre",nom);
                informacion.put("apellido",ape);
                informacion.put("telefono",tel);
                informacion.put("correo",cor);


                int cat=bd.update("parcial",informacion,
                        "nombre="+nom,null);
                bd.close();
                if(cat==1){
                    Toast.makeText(getApplicationContext(),"Se modifico el nombre",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"No se modifico el nombre",Toast.LENGTH_LONG).show();

                }
            }
        });

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navaListener= new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment seleccionFrag= null;

            switch (item.getItemId()){
                case R.id.nav_home:
                    seleccionFrag = new PricipalFragment();
                    break;
                case R.id.nav_fav:
                    seleccionFrag = new FavFragment();
                    break;
                case R.id.nav_cam:
                    seleccionFrag = new CamFragment();
                    break;
                case R.id.nav_src:
                    seleccionFrag = new SearchFragment();
                    break;
        }



            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCont, seleccionFrag).commit();
            return true;

        }



    };
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_configuraciones,menu);
        return true ;

    }

    public boolean onOptionsItemSelected(MenuItem items){

        Intent intent = new Intent(this, Sumar.class);

        switch (items.getItemId()){

            case R.id.opcion1:
                Toast.makeText(this,"Selecciono la opcion 1"+1,Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.opcion2:
                Toast.makeText(this,"Selecciono la opcion 2"+1,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcion3:
                Toast.makeText(this,"Selecciono la opcion 3"+1,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcion4:
                Toast.makeText(this,"Selecciono la opcion 4"+1,Toast.LENGTH_SHORT).show();
                return true;


        }

        return super.onOptionsItemSelected(items);
    }
}