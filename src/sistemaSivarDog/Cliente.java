package sistemaSivarDog;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private final List<Mascota> listaMascotas = new ArrayList<>();

    public Cliente(String nombreCompleto, String numeroIdentificacion, String telefono, String correoElectronico) {
        super(nombreCompleto, numeroIdentificacion, telefono, correoElectronico);
    }

    public void agregarMascota(Mascota mascota) {
        this.listaMascotas.add(mascota);
    }

    public List<Mascota> getListaMascotas() {
        return this.listaMascotas;
    }
}

