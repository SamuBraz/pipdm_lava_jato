package lava_jato.app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import lava_jato.app.dao.Maindao;
import lava_jato.app.model.HorarioVO;
import pi.app.pipdm_lava_jato.R;

public class AgendamentoActivity extends AppCompatActivity {

    // Constantes de preço
    private static final int PRICE_INTERNA = 50;
    private static final int PRICE_EXTERNA = 20;

    // Views
    private TextView price;
    private Button btnData;
    private Spinner spinnerLavagem, spinnerCarro, spinnerHorario;
    private Maindao maindao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        maindao = new Maindao(this);

        initializeViews();
        setupSpinners();
        setupDatePicker();

    }

    private void initializeViews() {
        price = findViewById(R.id.price);
        btnData = findViewById(R.id.spnr_Agendar_Dia);
        spinnerLavagem = findViewById(R.id.spnr_Agendar_Lavagem);
        spinnerCarro = findViewById(R.id.spnr_Agendar_TipoCarro);
        spinnerHorario = findViewById(R.id.spnr_Agendar_Horaio);
    }

    private void setupSpinners() {
        setupSpinner(spinnerLavagem, R.array.tiposlavagens);
        setupSpinner(spinnerCarro, R.array.carros);
        setupSpinner(spinnerHorario, R.array.horario);

        spinnerLavagem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handleSpinnerSelection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerCarro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handleSpinnerSelection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String horario = parent.getItemAtPosition(position).toString();
                Log.d("AgendarActivity", "Tipo de horario selecionado: " + horario);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupSpinner(Spinner spinner, int arrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayResId, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void handleSpinnerSelection() {
        String tipoLavagem = spinnerLavagem.getSelectedItem().toString();
        String tipoCarro = spinnerCarro.getSelectedItem().toString();
        int preco = calculatePrice(tipoLavagem, tipoCarro);
        price.setText("R$ " + preco + ",00");
    }

    private int calculatePrice(String tipoLavagem, String tipoCarro) {
        int precoBase;
        if (tipoLavagem.equals("INTERNA") && tipoCarro.equals("Carro pequeno")) {
            precoBase = PRICE_INTERNA;
        } else if (tipoLavagem.equals("EXTERNA") && tipoCarro.equals("Carro pequeno")) {
            precoBase = PRICE_EXTERNA;
        } else {
            precoBase = PRICE_INTERNA + PRICE_EXTERNA; // Valor para outros tipos de combinações
        }
        return precoBase;
    }

    private void setupDatePicker() {
        btnData.setOnClickListener(v -> showDatePicker());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> btnData.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear),
                year, month, day);
        datePickerDialog.show();
    }

    public void reservarHorario(View view) {
        String horarioSelecionado = spinnerHorario.getSelectedItem().toString();
        String dataSelecionada = btnData.getText().toString();
        String horarioCompleto = dataSelecionada + " " + horarioSelecionado;

        HorarioVO horarioVO = new HorarioVO();
        horarioVO.setHorarioInicio(horarioCompleto);
        // ID de exemplo para o cliente, isso deve ser obtido do contexto real da aplicação
        horarioVO.setIdClient(1);

        try {
            maindao.addHorario(horarioVO);
            Toast.makeText(this, "Horário reservado com sucesso!", Toast.LENGTH_SHORT).show();
            // Voltar para a tela de login após a reserva
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Horário já reservado. Por favor, escolha outro horário.", Toast.LENGTH_SHORT).show();
        }
    }
}