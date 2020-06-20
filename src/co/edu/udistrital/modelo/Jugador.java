package co.edu.udistrital.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private final String nombre;
    private final List<Integer> puntajes;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntajes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Integer> getPuntajes() {
        return puntajes;
    }

    public void agregarPuntaje(int puntaje) {
        puntajes.add(puntaje);
    }
}
