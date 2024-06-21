package lava_jato.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import java.util.ArrayList;

import lava_jato.app.model.UsuarioVO;
import lava_jato.app.model.HorarioVO;
public class Maindao extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "PI-LAVA-JATO";
    private static final int DB_VERSION = 9;
    private static final String TB_USUARIO = "tb_usuario";

    private static final String KEY_ID = "id";
    private static final String NOME = "nome";
    private static final String SENHA = "senha";
    private static final String TELEFONE = "telefone";
    private static final String CPF = "cpf";
    private static final String EMAIL = "email";

    private static final String TB_HORARIO = "tb_horario";

    private static final String IDHORARIO = "id_horario";
    private static final String IDCLIENT = "id_client";
    private static final String HORARIOINCIO  = "horario_inicio";

    private static final String CREATE_TB_USUARIO = "CREATE TABLE " + TB_USUARIO + " ("
            +KEY_ID + " INTEGER PRIMARY KEY, "
            +NOME + " TEXT, "
            +SENHA + " TEXT, "
            +TELEFONE+ " TEXT, "
            +CPF+ " TEXT, "
            +EMAIL+ " TEXT )";

    private static final String CREATE_TB_HORARIO = "CREATE TABLE " + TB_HORARIO + " ("
            +IDHORARIO + " INTEGER PRIMARY KEY, "
            +IDCLIENT + " INTEGER, "
            +HORARIOINCIO + " TEXT )";



    public Maindao(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_HORARIO);
        db.execSQL(CREATE_TB_USUARIO);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TB_HORARIO);
        onCreate(db);
    }

    public void addClient(UsuarioVO usuarioVO){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NOME, usuarioVO.getNome());
        contentValues.put(SENHA, usuarioVO.getSenha());
        contentValues.put(TELEFONE, usuarioVO.getTelefone());
        contentValues.put(CPF, usuarioVO.getCpf());
        contentValues.put(EMAIL, usuarioVO.getEmail());

        db.insert(TB_USUARIO, null, contentValues);
    }

    public void addHorario(HorarioVO horarioVO){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(IDHORARIO, horarioVO.getIdHorario());
        contentValues.put(HORARIOINCIO, horarioVO.getHorarioInicio());
        contentValues.put(IDCLIENT, horarioVO.getIdClient());

        db.insert(TB_HORARIO, null, contentValues);
    }

    public UsuarioVO getUsuario(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TB_USUARIO, new String[] {NOME, SENHA, TELEFONE, CPF, EMAIL},
                EMAIL + "=?", new String[]{email}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            UsuarioVO usuario = new UsuarioVO();
            usuario.setNome(cursor.getString(0));
            usuario.setSenha(cursor.getString(1));
            usuario.setTelefone(cursor.getString(2));
            usuario.setCpf(cursor.getString(3));
            usuario.setEmail(cursor.getString(4));
            cursor.close();
            return usuario;
        }
        return null;
    }

    public LiveData<UsuarioVO> getUsuarioLiveData(String email) {
        MutableLiveData<UsuarioVO> liveData = new MutableLiveData<>();
        UsuarioVO usuario = getUsuario(email);
        liveData.setValue(usuario);
        return liveData;
    }

    public boolean isValidPassword(String email, String inputPassword) {
        UsuarioVO usuario = getUsuario(email);
        if (usuario != null) {
            return org.mindrot.jbcrypt.BCrypt.checkpw(inputPassword, usuario.getSenha());
        }
        return false;
    }


    public void getHorario(){
        String query = "SELECT * FROM " + TB_HORARIO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }
        ArrayList<String> hora_array = new ArrayList<>();
        while (cursor.moveToNext()){
            hora_array.add(cursor.getString(2));
        }
    }

}
