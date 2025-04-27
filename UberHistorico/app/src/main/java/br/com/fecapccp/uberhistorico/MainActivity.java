package br.com.fecapccp.uberhistorico;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        // 💡 Criando dados fictícios para exibir na lista
        listaViagens = new ArrayList<>();
        listaViagens.add(new Viagem("1", "101", "2024-04-01 08:00", "Rua das Flores, 100 Margaridas-SÃO PAULO-SP, 02266-009",
                "2024-04-01 08:30", "Avenida Brasil, 200, Brooklyn-SÃO PAULO-SP, 05522-008", "001",""));
        listaViagens.add(new Viagem("1", "102","2024-04-02 09:00", "Rua das Palmeiras, 20",
                "2024-04-02 09:40", "Rua Augusta, 45, Brooklyn-SÃO PAULO-SP, 05522-008", "002",""));
        listaViagens.add(new Viagem("1", "103","2024-04-03 10:15", "Av. Liberdade, 532",
                "2024-04-03 10:45", "Av. Paulista, 1500, Brooklyn-SÃO PAULO-SP, 05522-008", "003",""));

        // 🔁 Conectando a lista ao Adapter
        adapter = new ViagemAdapter(this, listaViagens);
        HistoricoDeViagens.setAdapter(adapter);
    }
}