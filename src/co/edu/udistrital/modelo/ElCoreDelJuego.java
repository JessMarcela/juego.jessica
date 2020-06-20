package co.edu.udistrital.modelo;

import java.util.ArrayList;
import java.util.List;

public class ElCoreDelJuego {

    private static final String CONTRASENA = "F";//"Jessica Garcia";

    private final List<Categoria> categorias;
    private final List<Jugador> jugadores;

    public ElCoreDelJuego() {
        this.categorias = new ArrayList<>();
        this.jugadores = new ArrayList<>();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public boolean validarAdministrador(String contrasena) {
        return contrasena.equals(CONTRASENA);
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
}
