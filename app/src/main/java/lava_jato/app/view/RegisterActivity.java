package lava_jato.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import pi.app.pipdm_lava_jato.R;

import lava_jato.app.viewmodel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {


    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
    private EditText nomeEditText, senhaEditText, emailEditText, telefoneEditText, cpfEditText;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nomeEditText = findViewById(R.id.te_Cadastro_Nome);
        senhaEditText = findViewById(R.id.te_Cadastro_Senha);
        emailEditText = findViewById(R.id.te_Cadastro_Email);
        telefoneEditText = findViewById(R.id.te_Cadastro_Telefone);
        cpfEditText = findViewById(R.id.te_Cadastro_CPF);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUsuarioLiveData(emailEditText.getText().toString()).observe(this, usuario -> {
            if (usuario != null) {
                Toast.makeText(this, "UsuÃ¡rio registrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    public void btn_onRegisterClick(View view) {
        String nome = nomeEditText.getText().toString();
        String senha = senhaEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String telefone = telefoneEditText.getText().toString();
        String cpf = cpfEditText.getText().toString();

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(telefone) || TextUtils.isEmpty(cpf)) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        userViewModel.registerUser(nome, email, senha, telefone, cpf);

        startActivity(intent);
        finish();

    }
}