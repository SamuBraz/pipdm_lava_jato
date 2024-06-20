package lava_jato.app.model;

public class UsuarioVO {
    private int id;
    private String nome;
    private String senha;
    private String telefone;
    private String cpf;
    private String email;

    public UsuarioVO(){}
    public UsuarioVO(int id){
        this.id = id;
    }
    public UsuarioVO(int id, String nome, String senha, String telefone, String cpf, String email){
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
