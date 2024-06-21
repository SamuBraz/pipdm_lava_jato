package lava_jato.app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import pi.app.pipdm_lava_jato.R;

public class AgendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        Spinner CompleteTextViewLavagem = findViewById(R.id.spinner);
        Spinner CompleteTextViewCarro = findViewById(R.id.spinner2);

        // Adapte este ArrayAdapter ao seu array de strings
        ArrayAdapter<CharSequence> adapterItemsLavagem = ArrayAdapter.createFromResource(this, R.array.tiposlavagens, R.layout.spinner_item);
        ArrayAdapter<CharSequence> adapterItemCarro = ArrayAdapter.createFromResource(this, R.array.carros, R.layout.spinner_item);

        adapterItemsLavagem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterItemCarro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CompleteTextViewLavagem.setAdapter(adapterItemsLavagem);
        CompleteTextViewCarro.setAdapter(adapterItemCarro);

        CompleteTextViewLavagem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Log.d("AgendarActivity", "Item selecionado: " + item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        CompleteTextViewCarro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Log.d("AgendarActivity", "Item selecionado: " + item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
}