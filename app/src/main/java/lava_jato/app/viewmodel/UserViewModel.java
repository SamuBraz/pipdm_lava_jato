package lava_jato.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import lava_jato.app.dao.Maindao;
import lava_jato.app.model.UsuarioVO;

public class UserViewModel extends ViewModel {


    private Maindao maindao;

    public UserViewModel(Maindao maindao) {
        super();
        this.maindao = maindao;
    }

    public boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
    }

    public boolean authenticateUser(String email, String password) {
        UsuarioVO usuariovo = maindao  .getUsuario(email);
        if (usuariovo != null) {
            return org.mindrot.jbcrypt.BCrypt.checkpw(password, usuariovo.getSenha());
        }
        return false;
    }

    public void registerUser(String nome, String email, String senha, String telefone, String cpf) {
        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(senha, org.mindrot.jbcrypt.BCrypt.gensalt());
        UsuarioVO usuarioVO = new UsuarioVO(0, nome, hashedPassword, telefone, cpf, email);
        maindao.addClient(usuarioVO);
    }

    public LiveData<UsuarioVO> getUsuarioLiveData(String email) {
        return maindao.getUsuarioLiveData(email);
    }
}
