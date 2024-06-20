package lava_jato.app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import lava_jato.app.dao.Maindao;
import lava_jato.app.model.HorarioVO;
import lava_jato.app.model.UsuarioVO;
import pi.app.pipdm_lava_jato.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_dev_cadastrar);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, AgendarActivity.class);
                startActivity(intent);
                //finishAffinity(); desliga todas as activities no backstack ou usar intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            }
        });
    }
    public void btnOnClickCadastrarClient(View view) {
        Maindao maindao = new Maindao(this);

        UsuarioVO usuarioVO1 = new UsuarioVO();
        usuarioVO1.setId(212222);
        usuarioVO1.setNome("Bruno");
        usuarioVO1.setSenha("21321321");
        usuarioVO1.setEmail("bb.silva@gmail.com");
        usuarioVO1.setTelefone("321231");
        usuarioVO1.setCpf("1321321");


        HorarioVO horarioVO = new HorarioVO();
        horarioVO.setIdHorario(6);
        horarioVO.setIdClient(2);
        horarioVO.setHorarioInicio("18-06-2024-22:00");
        horarioVO.setHorarioTermino("18-06-2024-22:20");

        maindao.addClient(usuarioVO1);
        maindao.addHorario(horarioVO);

        Log.d("insert: ", "Registros inseridos no banco de dados...");

    }

    public void btnOnClickCadastrarHora(View view){
        Maindao maindao = new Maindao(this);
        HorarioVO horarioVO = new HorarioVO();
        horarioVO.setIdHorario(1);
        horarioVO.setIdClient(1);
        horarioVO.setHorarioInicio("18-06-2024-22:00");
        horarioVO.setHorarioTermino("18-06-2024-22:20");

        maindao.addHorario(horarioVO);
    }

}