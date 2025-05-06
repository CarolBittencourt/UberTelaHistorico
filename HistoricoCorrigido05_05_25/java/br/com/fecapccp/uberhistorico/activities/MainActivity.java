package br.com.fecapccp.uberhistorico.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.fecapccp.uberhistorico.R;
import br.com.fecapccp.uberhistorico.model.Motorista;
import br.com.fecapccp.uberhistorico.model.Viagem;
import br.com.fecapccp.uberhistorico.utils.ViagemAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView HistoricoDeViagens;
    private ViagemAdapter adapter;
    private List<Viagem> listaViagens;


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

        HistoricoDeViagens = findViewById(R.id.HistoricoDeViagens);
        HistoricoDeViagens.setLayoutManager(new LinearLayoutManager(this));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                int idMotorista = 2;
                Motorista motorista = Motorista.buscarMotoristaPorId(idMotorista);
                listaViagens = Viagem.listarViagensPorMotorista(motorista);

                runOnUiThread(() -> {
                    if (listaViagens != null && !listaViagens.isEmpty()) {
                        adapter = new ViagemAdapter(this, listaViagens);
                        HistoricoDeViagens.setAdapter(adapter);
                    } else {
                        Log.e("MainActivity", "Nenhuma viagem encontrada para o motorista ID: " + idMotorista);
                    }
                });
            } catch (Exception e) {
                Log.e("MainActivity", "Erro ao carregar viagens: " + e.getMessage(), e);
            }
        });
    }
}
