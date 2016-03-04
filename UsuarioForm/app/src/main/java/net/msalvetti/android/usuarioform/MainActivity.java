package net.msalvetti.android.usuarioform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int NOMBRE = 1;
    static final int APELLIDO = 2;
    private EditText etNombre, etApellido;
    private Button btNombre, btApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        btNombre = (Button) findViewById(R.id.btNombre);
        btApellido = (Button) findViewById(R.id.btApellido);

        btNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchConfirmActivity(NOMBRE, etNombre.getText().toString());
            }
        });

        btApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchConfirmActivity(APELLIDO, etApellido.getText().toString());
            }
        });

    }

    private void launchConfirmActivity(int requestCode, String editTextValue) {
        Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
        intent.putExtra("editTextValue", editTextValue);
        startActivityForResult(intent, requestCode);
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
