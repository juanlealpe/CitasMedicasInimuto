package co.com.citasmedicasinimuto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.citasmedicasinimuto.Ui.dasboard.DasdBoardActivity;
import co.com.citasmedicasinimuto.Ui.register.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton agendar;
    private EditText user;
    private  EditText password;
    private AppCompatButton registar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        inicializacionCampos();


        agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Si cambia");
               validacionCampos();

            }
        });

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicializacionCampos(){

        agendar = findViewById(R.id.btn_agendar);
        user =findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        registar = findViewById(R.id.btn_registrar);

    }

    private void   validacionCampos(){

        if(user.getText().toString().isEmpty()|| password.getText().toString().isEmpty()){
            user.setError("campo obligario");
            password.setError("campo obligario ");
        }
        else {
            if (!esEmailValido(user.getEditableText().toString().trim())){
                Intent intent = new Intent(MainActivity.this, DasdBoardActivity.class);
                startActivity(intent);
            }

        }

    }

    public static boolean esEmailValido(String email) {
        String expresiones = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expresiones, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }





}