package net.msalvetti.android.usuarioform;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
@Author Gaston Kanzepolsky
 */
public class MainActivity extends AppCompatActivity {
    static final int NOMBRE = 1;
    static final int APELLIDO = 2;
    private EditText etNombre, etApellido;
    private Button btNombre, btApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Aca asocio los elementos del Layout con el codigo Java
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        btNombre = (Button) findViewById(R.id.btNombre);
        btApellido = (Button) findViewById(R.id.btApellido);

        btNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchConfirmActivity(NOMBRE);
            }
        });

        btApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchConfirmActivity(APELLIDO);
            }
        });

    }
    private void launchConfirmActivity(int requestCode) {
        Intent i = new Intent(MainActivity.this, SegundaActivity.class);
        if (requestCode==NOMBRE)
            i.putExtra("palabra", etNombre.getText().toString());
        else
            i.putExtra("palabra", etApellido.getText().toString());
        startActivityForResult(i, requestCode);
    }

    // Este método nos trae la información de para qué se llamó la segunda actividad,
// cuál fue el resultado ("OK" o "CANCELED"), y el intent que nos traerá la
// información que necesitamos de la segunda actividad.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos si el resultado de la segunda actividad es "RESULT_CANCELED".
        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT).show();
        } else {
            // De lo contrario, tomamos el resultado de la segunda actividad.
            String resultado = data.getExtras().getString("RESULTADO");
            // Y tratamos el resultado en función de si se lanzó para rellenar el
            // nombre o el apellido.
            switch (requestCode) {
                case NOMBRE:
                    etNombre.setText(resultado);
                    break;
                case APELLIDO:
                    etApellido.setText(resultado);
                    break;
            }
        }
    }
}
