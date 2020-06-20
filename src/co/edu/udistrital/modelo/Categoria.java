package co.edu.udistrital.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private final String nombre;
    private final String descripcion;
    private List<String> frases;

    public Categoria(String nombre, String descripcion, List<String> frases) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.frases = frases;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getFrases() {
        return frases;
    }

    public void setFrases(List<String> frases) {
        this.frases = frases;
    }
}
