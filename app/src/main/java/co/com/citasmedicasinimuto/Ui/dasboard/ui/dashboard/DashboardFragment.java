package co.com.citasmedicasinimuto.Ui.dasboard.ui.dashboard;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import co.com.citasmedicasinimuto.R;
import co.com.citasmedicasinimuto.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    int year;
    int month;
    int day;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        final TextInputEditText fecha = binding.editTextFecha;



        Calendar calendar = Calendar.getInstance();

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fecha.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

       spinnerEspecilidad();

        spinnerclinica();

       // dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void spinnerEspecilidad(){
        final  AutoCompleteTextView autoCompleteTextView = binding.autoCompleteEspecilidad;
        String[] listEspecialistas = new String[]{"Medico generaral", "Optometría", "Odontología "};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.drow_down_item,
                listEspecialistas
        );
        autoCompleteTextView.findViewById(R.id.autoCompleteEspecilidad);
        autoCompleteTextView.setAdapter(adapter);

    }

    private void spinnerclinica(){
        final  AutoCompleteTextView autoCompleteTextView = binding.autoCompleteClinica;
        String[] listEspecialistas = new String[]{"Chico centro", "Restrepo", "Odontología "};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.drow_down_item,
                listEspecialistas
        );
        autoCompleteTextView.findViewById(R.id.autoCompleteClinica);
        autoCompleteTextView.setAdapter(adapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}