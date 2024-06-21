package lava_jato.app.view;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
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

import pi.app.pipdm_lava_jato.R;

public class AgendarActivity extends AppCompatActivity {

    // Constantes de preço
    private static final int PRICE_INTERNA = 50;
    private static final int PRICE_EXTERNA = 20;

    // Views
    private TextView price;
    private Button btnData;
    private Spinner spinnerLavagem, spinnerCarro, spinnerHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

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

    private void setupDatePicker() {
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog();
            }
        });
    }

    private void openDatePickerDialog() {
        Calendar cal = Calendar.getInstance();
        int yearInstance = cal.get(Calendar.YEAR);
        int monthInstance = cal.get(Calendar.MONTH);
        int dayInstance = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                if (selectedDate.before(Calendar.getInstance())) {
                    Toast.makeText(AgendarActivity.this, "Selecione uma data futura", Toast.LENGTH_SHORT).show();
                } else {
                    String dia = String.format("%d/%d/%d", dayOfMonth, month + 1, year);
                    Log.d("AgendarActivity", "Dia: " + dia);
                    btnData.setText(dia);
                }
            }
        }, yearInstance, monthInstance, dayInstance);

        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();
    }

    private void handleSpinnerSelection() {
        String lavagem = spinnerLavagem.getSelectedItem() != null ? spinnerLavagem.getSelectedItem().toString() : "";
        String carro = spinnerCarro.getSelectedItem() != null ? spinnerCarro.getSelectedItem().toString() : "";

        Log.d("AgendarActivity", "Tipo de lavagem selecionada: " + lavagem);
        Log.d("AgendarActivity", "Tipo de carro selecionado: " + carro);

        updatePrice(lavagem, carro);
    }

    private void updatePrice(String lavagem, String carro) {
        int finalPrice = 0;

        if ("Interna".equals(lavagem)) {
            finalPrice = getPriceForCarType(carro, PRICE_INTERNA);
        } else if ("Externa".equals(lavagem)) {
            finalPrice = getPriceForCarType(carro, PRICE_EXTERNA);
        }

        price.setText(String.format("R$ %d,00", finalPrice));
    }

    private int getPriceForCarType(String carro, int basePrice) {
        switch (carro) {
            case "Sedan":
                return basePrice;
            case "SUV":
                return (int) (basePrice * 1.5);
            case "Hatch":
                return basePrice * 2;
            default:
                return 0;
        }
    }
}
