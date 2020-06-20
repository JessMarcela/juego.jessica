package co.edu.udistrital.modelo;

public class Juego {

    private final Jugador jugador;
    private final String[] palabras;
    private int cantidadIntentos;
    private int cantidadIntentosRestantes;
    private boolean continuar;

    public Juego(Jugador jugador, String[] palabras, int cantidadIntentos) {
        this.jugador = jugador;
        this.palabras = palabras;
        this.cantidadIntentos = cantidadIntentos;
        this.cantidadIntentosRestantes = cantidadIntentos;
        this.continuar = true;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getCantidadPalabras() {
        return palabras.length;
    }

    public int getCantidadIntentosRestantes() {
        return cantidadIntentosRestantes;
    }

    public void decrementarCantidadIntentos() {
        cantidadIntentosRestantes--;
    }

    public String getPalabra(int i) {
        return palabras[i];
    }

    public boolean continuar() {
        return continuar;
    }

    public void terminar() {
        continuar = false;
        jugador.agregarPuntaje(cantidadIntentosRestantes * 100 / cantidadIntentos);
    }
}
