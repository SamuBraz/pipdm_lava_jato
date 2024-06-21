package lava_jato.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import pi.app.pipdm_lava_jato.R;

import lava_jato.app.dao.Maindao;
import lava_jato.app.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, senhaEditText;
    private Maindao mainDAO;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.te_Login_Email);
        senhaEditText = findViewById(R.id.te_Login_Senha);

        mainDAO = new Maindao(this);
        userViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(UserViewModel.class);

        Button button = findViewById(R.id.btn_Login_Entrar);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void btn_onLoginClick(View view) {
        String email = emailEditText.getText().toString();
        String senha = senhaEditText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        userViewModel.getUsuarioLiveData(email).observe(this, usuario -> {
            if (usuario != null && userViewModel.authenticateUser(email, senha)) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_agendamento);
            } else {
                Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
            }

        });

    }
}