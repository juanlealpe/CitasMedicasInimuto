package co.com.citasmedicasinimuto.Ui.register;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import co.com.citasmedicasinimuto.Controler.UserController;
import co.com.citasmedicasinimuto.MainActivity;
import co.com.citasmedicasinimuto.Model.UserRegisterModel;
import co.com.citasmedicasinimuto.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    int year;
    int month;
    int day;

    private TextInputLayout fecha;
    private TextInputEditText textFecha;

    private AppCompatButton btn_registar;
    private UserController userController;

    private UserRegisterModel userRegisterModel;

    private TextInputEditText textName;

    private TextInputEditText textEmeil;
    private TextInputEditText textPasword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork.isConnectedOrConnecting();
        inicializacionCampos();

        btn_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser();

                boolean registroExitoso = userController.connectSQL();
                if(isConnected){
                    if (registroExitoso){
                        userController.inserDataUserSQL(userRegisterModel.getFecha(),userRegisterModel.getNombre(),userRegisterModel.getCorreo(),userRegisterModel.getId(),userRegisterModel.getContraseña());
                        showAlert("Se registro Exitosamente ","Exitoso");
                    }else {
                        showAlert("Error en conexion intente mas tarde","Error");
                    }
                }else {
                    showAlert("Error en internet intente mas tarde","Error");

                }



            }
        });

    }

    private void inicializacionCampos(){

        textName = findViewById(R.id.editTextname);
        textEmeil = findViewById(R.id.editTextcorreo);
        textPasword = findViewById(R.id.editTextPassword);
        fecha = findViewById(R.id.text_input_layout_fecha);
        textFecha = findViewById(R.id.editTextFecha);
        btn_registar = findViewById(R.id.btn_guardar);
        userRegisterModel = new UserRegisterModel();
         userController = new UserController();

    }
    private  void  dataUser(){
        userRegisterModel.setNombre(textName.getText().toString());
        userRegisterModel.setCorreo(textEmeil.getText().toString());
        userRegisterModel.setFecha(textFecha.getText().toString());
        userRegisterModel.setContraseña(textPasword.getText().toString());
    }

    public void showAlert(String message,String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       onBackPressed();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        if (v== fecha){
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,day,month,year);
            datePickerDialog.show();
        }
    }
}