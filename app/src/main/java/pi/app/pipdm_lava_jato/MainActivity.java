package pi.app.pipdm_lava_jato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import lava_jato.app.dao.Maindao;
import lava_jato.app.model.HorarioVO;
import lava_jato.app.model.UsuarioVO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnOnClickCadastrarClient(View view) {
        Maindao maindao = new Maindao(this);

        UsuarioVO usuarioVO1 = new UsuarioVO();
        usuarioVO1.setId(132131321);
        usuarioVO1.setNome("Bruno");
        usuarioVO1.setSenha("21321321");
        usuarioVO1.setEmail("bbb.silva@gmail.com");
        usuarioVO1.setTelefone("321231");
        usuarioVO1.setCpf("1321321");


        HorarioVO horarioVO = new HorarioVO();
        horarioVO.setIdHorario(6);
        horarioVO.setIdClient(2);
        horarioVO.setHorarioInicio("18-06-2024-22:00");
        horarioVO.setHorarioTermino("18-06-2024-22:20");

        maindao.addClient(usuarioVO1);
        //maindao.addHorario(horarioVO);

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

    public void  btnOnClickGet(View view){
        Maindao maindao = new Maindao(this);
        UsuarioVO usuarioVO = maindao.getUsuario("bbb.silva@gmail.com");

        String email = usuarioVO.getEmail();
        String senha = usuarioVO.getSenha();
        Log.d("TAG", "Valor da variável: " + email);
        Log.d("TAG", "Valor da variável: " + senha);
    }

    public void testeHorario(View view){
        Maindao maindao = new Maindao(this);
        maindao.getHorario();
    }


}