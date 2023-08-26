package co.com.citasmedicasinimuto.Controler;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.xml.transform.Result;

public class UserController {

    private Connection connection;
    private  String url = "jdbc:mysql://34.135.99.69:3306/base_citas_medicas";
    private  String userDB = "uniminuto";
    private  String password = "123456";
    private  String table = "users";
    private  String tableCitasMedicas = "citasMedicas";

    public boolean connectSQL(){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
          connection =  DriverManager.getConnection(url,userDB,password);

            return true;
        }catch (Exception ex){
            Log.e("error DB",ex.toString());
            return  false;
        }

    }

    public boolean inserDataUserSQL(String fecha,String name, String emeil, int id, String password){
        try {
            String inserDataUser = "INSERT INTO " + table + " VALUES("+fecha+" , '"+name+"' , '"+emeil+"' , '"+id+"' , '"+password+"')";
            connection.prepareStatement(inserDataUser).execute();

        }catch (Exception ex){
            Log.e("error DB",ex.toString());
            return  false;
        }


        return true;
    }

    public boolean inserDataCitaSQL(int id,String descripcion,String fecha, String especialida,  String clinica){
        try {
            String inserDataUser = "INSERT INTO " + tableCitasMedicas + " VALUES("+id+" , '"+descripcion+"' , '"+fecha+"' , '"+especialida+"', '"+clinica+"')";
            connection.prepareStatement(inserDataUser).execute();

        }catch (Exception ex){
            Log.e("error DB",ex.toString());
            return  false;
        }

        return true;
    }

    public ArrayList<String> consultarCitasSQL(){
        try {
            ArrayList<String> arrayListCitas = new ArrayList<>();
            String instrucionSQL = "SELECT * FROM " + tableCitasMedicas;
            ResultSet resultSet =connection.prepareStatement(instrucionSQL).executeQuery();
            while (resultSet.next()){
                String data = resultSet.getInt("id") + " " + resultSet.getString("descripcion") + " " +
                        resultSet.getString("fecha") + " " + resultSet.getString("especialidad") + " " +
                        resultSet.getString("clinica");
                arrayListCitas.add(data);

            }
            return arrayListCitas;
        }catch (Exception ex){
            Log.e("error DB",ex.toString());
            return  null;
        }
    }

}
