package pi.app.pipdm_lava_jato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import lava_jato.app.dao.Maindao;
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
        usuarioVO1.setId(1);
        usuarioVO1.setNome("Bruno");
        usuarioVO1.setSenha("21321321");
        usuarioVO1.setEmail("bb.silva@gmail.com");
        usuarioVO1.setTelefone("321231");
        usuarioVO1.setCpf("1321321");

        maindao.addClient(usuarioVO1);

        Log.d("insert: ", "Registros inseridos no banco de dados...");

    }
}