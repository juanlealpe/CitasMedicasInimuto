package co.com.citasmedicasinimuto.Ui.dasboard.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.com.citasmedicasinimuto.Model.CitasMedicasModel;
import co.com.citasmedicasinimuto.R;
import co.com.citasmedicasinimuto.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    ArrayList<CitasMedicasModel> citasMedicasModels;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        View root = binding.getRoot();


        final RecyclerView recyclerView = binding.rvDescriptions;


        recyclerView.findViewById(R.id.rvDescriptions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        populatePersons();
        NotificacionAdapter listAdapter = new NotificacionAdapter(citasMedicasModels);
        recyclerView.setAdapter(listAdapter);



       // notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void populatePersons(){
        citasMedicasModels = new ArrayList<>();
        citasMedicasModels.add(new CitasMedicasModel("12/08/2023","cita medica para mi hijo ","chico norte","calle 49 # 10D-20","Medicina General"));
        citasMedicasModels.add(new CitasMedicasModel("18/08/2023","cita medica optometrìa","chico norte","calle 49 # 10D-20","Optometrìa"));
        citasMedicasModels.add(new CitasMedicasModel("17/08/2023","cita medica odontologìa","chico norte","calle 3a # 34-55 ","Odontologìa"));
        citasMedicasModels.add(new CitasMedicasModel("14/08/2023","cita medica medicina general","chico norte","calle 88 # 10 sur 22-20","Medicina General"));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}