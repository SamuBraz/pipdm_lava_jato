package lava_jato.app.dao;

import android.content.ContentValues;
import android.content.Context;
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


import lava_jato.app.model.UsuarioVO;
public class Maindao extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "PI-LAVA-JATO";
    private static final int DB_VERSION = 1;
    private static final String TB_USUARIO = "tb_usuario";

    private static final String KEY_ID = "id";
    private static final String NOME = "nome";
    private static final String SENHA = "senha";
    private static final String TELEFONE = "telefone";
    private static final String CPF = "cpf";
    private static final String EMAIL = "email";

    public Maindao(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_USUARIO = "CREATE TABLE " + TB_USUARIO + " ("
                +KEY_ID + " INTEGER PRIMARY KEY, "
                +NOME + " TEXT, "
                +SENHA + " TEXT, "
                +TELEFONE+ " TEXT, "
                +CPF+ " TEXT, "
                +EMAIL+ " TEXT )";

        db.execSQL(CREATE_TB_USUARIO);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_USUARIO);
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

}
