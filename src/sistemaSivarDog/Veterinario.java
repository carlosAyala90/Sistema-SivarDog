package sistemaSivarDog;

import java.util.ArrayList;
import java.util.List;

//clase veterinario (hereda de clase persona)
public class Veterinario extends Persona{

    private final String especialidad; // Especialidad médica del veterinario
    private final List<Citas> agendaDeCitas; //Lista de citas asignadas al veterinario


    //Constructor de la clase Veterinario
    public Veterinario (String nombreCompleto, String numeroIdentificacion, String telefono, String correoElectronico, String especialidad){
        super (nombreCompleto, numeroIdentificacion, telefono,correoElectronico);
        this.especialidad = especialidad;
        this.agendaDeCitas = new ArrayList<>();
    }

    // Agrega una cita a la agenda del veterinario.
    public void agregarCita(Citas cita) {
        this.agendaDeCitas.add(cita);
    }


    //getteers y setters
    public String getEspecialidad(){
        return especialidad;
    }

}
