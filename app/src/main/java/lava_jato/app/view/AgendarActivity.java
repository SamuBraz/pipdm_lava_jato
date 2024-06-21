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

import java.util.Calendar;

import pi.app.pipdm_lava_jato.R;

public class AgendarActivity extends AppCompatActivity {

    private TextView price;
    private Button btnData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        price = findViewById(R.id.price);

        Spinner CompleteTextViewLavagem = findViewById(R.id.spnr_Agendar_Lavagem);
        Spinner CompleteTextViewCarro = findViewById(R.id.spnr_Agendar_TipoCarro);
        Spinner CompleteTextViewHorario = findViewById(R.id.spnr_Agendar_Horaio);

        ArrayAdapter<CharSequence> adapterItemsLavagem = ArrayAdapter.createFromResource(this, R.array.tiposlavagens, R.layout.spinner_item);
        ArrayAdapter<CharSequence> adapterItemCarro = ArrayAdapter.createFromResource(this, R.array.carros, R.layout.spinner_item);
        ArrayAdapter<CharSequence> adapterItemHorario = ArrayAdapter.createFromResource(this, R.array.horario, R.layout.spinner_item);

        adapterItemsLavagem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterItemCarro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterItemHorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CompleteTextViewLavagem.setAdapter(adapterItemsLavagem);
        CompleteTextViewCarro.setAdapter(adapterItemCarro);
        CompleteTextViewHorario.setAdapter(adapterItemHorario);

        CompleteTextViewLavagem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String lavagem = parent.getItemAtPosition(position).toString();
                String carro = CompleteTextViewCarro.getSelectedItem() != null ? CompleteTextViewCarro.getSelectedItem().toString() : "";
                Log.d("AgendarActivity", "Tipo de lavagem selecionada: " + lavagem);
                updatePrice(lavagem, carro);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        CompleteTextViewCarro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String carro = parent.getItemAtPosition(position).toString();
                String lavagem = CompleteTextViewLavagem.getSelectedItem() != null ? CompleteTextViewLavagem.getSelectedItem().toString() : "";
                Log.d("AgendarActivity", "Tipo de carro selecionado: " + carro);
                updatePrice(lavagem, carro);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        CompleteTextViewHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String horario = parent.getItemAtPosition(position).toString();
                Log.d("AgendarActivity", "Tipo de horario selecionado: " + horario);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        btnData = findViewById(R.id.spnr_Agendar_Dia);

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDataDialog();
            }
        });
    }

    private void openDataDialog() {
        Calendar cal = Calendar.getInstance();
        int yearInstance = cal.get(Calendar.YEAR);
        int monthInstance = cal.get(Calendar.MONTH);
        int dayInstance = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btnData.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
            }
        }, yearInstance, monthInstance, dayInstance);

        dialog.show();
    }

    private void updatePrice(String lavagem, String carro) {
        int priceValueInterna = 50;
        int priceValueExterna = 20;
        int finalPrice = 0;

        if ("Interna".equals(lavagem)) {
            switch (carro) {
                case "Sedan":
                    finalPrice = priceValueInterna;
                    break;
                case "SUV":
                    finalPrice = (int) (priceValueInterna * 1.5);
                    break;
                case "Hatch":
                    finalPrice = priceValueInterna * 2;
                    break;
                default:
                    finalPrice = 0;
                    break;
            }
        } else if ("Externa".equals(lavagem)) {
            switch (carro) {
                case "Sedan":
                    finalPrice = priceValueExterna;
                    break;
                case "SUV":
                    finalPrice = (int) (priceValueExterna * 1.5);
                    break;
                case "Hatch":
                    finalPrice = priceValueExterna * 2;
                    break;
                default:
                    finalPrice = 0;
                    break;
            }
        }

        String priceText = "R$ " + finalPrice + ",00";
        price.setText(priceText);
    }
}
