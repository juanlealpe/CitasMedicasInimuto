package co.com.citasmedicasinimuto.Model;

public class CitasMedicasModel {



    String fecha;
    String description;
    String clinica;
    String direccion;
    String especializacion;
     public CitasMedicasModel(String fecha, String description, String clinica, String direccion, String especializacion) {
        this.fecha = fecha;
        this.description = description;
        this.clinica = clinica;
        this.direccion = direccion;
        this.especializacion = especializacion;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }



}
