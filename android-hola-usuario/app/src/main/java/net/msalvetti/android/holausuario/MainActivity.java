package net.msalvetti.android.holausuario;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos una referencia a los controles de la interfaz
        txtNombre = (EditText)findViewById(R.id.TxtNombre);
        btnAceptar = (Button)findViewById(R.id.BtnAceptar);

        //Implementamos el evento click del botón
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();
            }
        });
    }

    private void onClickHandler() {
        String textInput = txtNombre.getText().toString();
        try {
            Integer intValue = Integer.parseInt(textInput);
            int visibility = View.VISIBLE;
            if (intValue < 6) {
                visibility = View.GONE;
            }
            findViewById(R.id.LytContenedorBotones).setVisibility(visibility);
        } catch (Exception e) {
            // not int? don't care
            launchActivity();
        }
    }

    private void launchActivity() {
        //Creamos el Intent
        Intent intent =
                new Intent(MainActivity.this, SaludoActivity.class);

        //Creamos la información a pasar entre actividades
        Bundle b = new Bundle();
        b.putString(AppConstants.BUNDLE_KEY_NOMBRE, txtNombre.getText().toString());


        //Ponemos la info en el intent
        intent.putExtras(b);


        //Iniciamos la nueva actividad
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
