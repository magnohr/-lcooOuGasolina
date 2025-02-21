package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText DinheiroGasto, litrosAlcool, litrosGasolina, precoAlcool, precoGasolina,
            resuGalolina, resuAlcool;
    private Button calcular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DinheiroGasto = findViewById(R.id.DinheiroGasto);
        litrosAlcool = findViewById(R.id.litrosAlcool);
        litrosGasolina = findViewById(R.id.litrosGasolina);
        precoAlcool = findViewById(R.id.precoAlcool);
        precoGasolina = findViewById(R.id.precoGasolina);
        resuGalolina = findViewById(R.id.resuGasolina);
        resuAlcool = findViewById(R.id.resuAlcool);
        calcular = findViewById(R.id.calcular);


    }

    public void calcular1(View v) {
        // Obtendo os valores dos campos e removendo espaços em branco
        String dinheiroG = DinheiroGasto.getText().toString().trim();
        String litrosA = litrosAlcool.getText().toString().trim();
        String litrosG = litrosGasolina.getText().toString().trim();
        String precoA = precoAlcool.getText().toString().trim();
        String precoG = precoGasolina.getText().toString().trim();

        // Verifica se os preços foram preenchidos
        if (precoA.isEmpty() || precoG.isEmpty()) {
            Toast.makeText(this, "Preencha os preços de álcool e gasolina!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Convertendo valores essenciais para double
            double precoADouble = Double.parseDouble(precoA);
            double precoGDouble = Double.parseDouble(precoG);

            double dinheiroGDouble = dinheiroG.isEmpty() ? 0 : Double.parseDouble(dinheiroG);
            double litrosADouble = litrosA.isEmpty() ? 0 : Double.parseDouble(litrosA);
            double litrosGDouble = litrosG.isEmpty() ? 0 : Double.parseDouble(litrosG);

            // Se o dinheiro foi inserido, calcular quantos litros podem ser comprados
            if (!dinheiroG.isEmpty()) {
                double dinheiroDivididoPrecoG = dinheiroGDouble / precoGDouble;
                double dinheiroDivididoPrecoA = dinheiroGDouble / precoADouble;

                litrosAlcool.setText(String.format("%.2f", dinheiroDivididoPrecoA));
                litrosGasolina.setText(String.format("%.2f", dinheiroDivididoPrecoG));
            }

            // Se os litros foram inseridos, calcular o total gasto
            if (!litrosA.isEmpty() || !litrosG.isEmpty()) {
                double multiLitrosPrecoG = litrosGDouble * precoGDouble;
                double multiLitrosPrecoA = litrosADouble * precoADouble;

                resuGalolina.setText(String.format("%.2f", multiLitrosPrecoG));
                resuAlcool.setText(String.format("%.2f", multiLitrosPrecoA));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erro nos valores inseridos!", Toast.LENGTH_SHORT).show();
        }
    }

}
