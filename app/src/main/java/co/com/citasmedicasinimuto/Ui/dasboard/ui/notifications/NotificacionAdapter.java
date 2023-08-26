package co.com.citasmedicasinimuto.Ui.dasboard.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.com.citasmedicasinimuto.Controler.UserController;
import co.com.citasmedicasinimuto.Model.CitasMedicasModel;
import co.com.citasmedicasinimuto.R;

public class NotificacionAdapter  extends RecyclerView.Adapter<NotificacionAdapter.ViewHolder>  {

    private ArrayList<CitasMedicasModel> citasMedicasModels;
    private UserController userController;

    private  ArrayList<String>arrayList = userController.consultarCitasSQL();


    // Constructor
    public  NotificacionAdapter(ArrayList<String> arrayList) {
        this.citasMedicasModels = citasMedicasModels;
    }

    @NonNull
    @Override
    public NotificacionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification,null,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificacionAdapter.ViewHolder holder, int position) {

        holder.fecha.setText(citasMedicasModels.get(position).getFecha());
        holder.descripcion.setText(citasMedicasModels.get(position).getDescription());
        holder.espcialidad.setText(citasMedicasModels.get(position).getEspecializacion());
        holder.clinica.setText(citasMedicasModels.get(position).getClinica());
        holder.direccion.setText(citasMedicasModels.get(position).getDireccion());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView fecha;
        TextView clinica;
        TextView espcialidad;

        TextView direccion;
        TextView descripcion;


        public ViewHolder(View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.tv_fecha);
            clinica = itemView.findViewById(R.id.text_view_clinica_value);
            espcialidad = itemView.findViewById(R.id.text_view_specialist_value);
            direccion = itemView.findViewById(R.id.text_view_direccion_value);
            descripcion = itemView.findViewById(R.id.text_view_description_value);


        }

    }

    @Override
    public int getItemCount() {
        return citasMedicasModels.size();
    }
}
