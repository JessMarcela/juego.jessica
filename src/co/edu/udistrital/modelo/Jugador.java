package co.edu.udistrital.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private final String nombre;
    private final List<Integer> ultimosPuntajes;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.ultimosPuntajes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Integer> getUltimosPuntajes() {
        return ultimosPuntajes;
    }

    public void agregarPuntaje(int puntaje) {
        ultimosPuntajes.add(puntaje);
    }
}
