package com.example.debuggerylogcat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etA, etB;
    private Button btnDividir;
    private TextView tvResultado;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // Conectar elementos del XML
        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        btnDividir = findViewById(R.id.btnDividir);
        tvResultado = findViewById(R.id.tvResultado);

        Log.i(TAG, "App iniciada correctamente (onCreate)");

        // Evento del botón dividir
        btnDividir.setOnClickListener(v -> {

            Log.d(TAG, "Botón presionado");

            String valorA = etA.getText().toString().trim();
            String valorB = etB.getText().toString().trim();

            Log.d(TAG, "Entrada A: " + valorA);
            Log.d(TAG, "Entrada B: " + valorB);

            // Verificar campos vacíos
            if (valorA.isEmpty() || valorB.isEmpty()) {

                Log.w(TAG, "Uno o ambos campos están vacíos");

                Toast.makeText(
                        this,
                        "Completa ambos campos",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            try {

                // Convertir los textos a números
                int a = Integer.parseInt(valorA);
                int b = Integer.parseInt(valorB);

                Log.d(TAG, "Conversión correcta: A=" + a + ", B=" + b);

                // Realizar división
                int resultado = a / b;

                // Mostrar resultado
                tvResultado.setText("Resultado: " + resultado);

                Log.i(TAG, "Resultado mostrado: " + resultado);

            } catch (ArithmeticException e) {

                // División entre cero
                Log.e(TAG, "División entre cero: " + e.getMessage());

                Toast.makeText(
                        this,
                        "No se puede dividir entre 0",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (NumberFormatException e) {

                // Error al convertir texto a número
                Log.e(TAG, "Formato inválido: " + e.getMessage());

                Toast.makeText(
                        this,
                        "Ingresa números válidos",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (Exception e) {

                // Cualquier otro error
                Log.e(TAG, "Error inesperado: " + e.getMessage());

                Toast.makeText(
                        this,
                        "Ocurrió un error",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}