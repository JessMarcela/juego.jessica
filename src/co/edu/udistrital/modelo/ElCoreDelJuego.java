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

    public Jugador buscarJugador(String nombre) {
        for(Jugador jugador : jugadores) {
            if(jugador.getNombre().equalsIgnoreCase(nombre)) {
                return jugador;
            }
        }
        Jugador jugador = new Jugador(nombre);
        jugadores.add(jugador);

        return new Jugador(nombre);
    }

    public String obtenerFraseAleatoria(Categoria categoria) {
        List<String> frases = categoria.getFrases();
        return frases.get((int) (Math.random() * frases.size()));
    }

    public String[] obtenerPalabras(String fraseAleatoria) {
        return fraseAleatoria.split(" ");
    }

    public int obtenerCantidadIntentos(String fraseAleatoria) {
        return fraseAleatoria.replaceAll(" ", "").toCharArray().length / 2;
    }

    public boolean validarCantidadEnFrase(Juego juego, String frase) {
        String[] frasePartida = obtenerPalabras(frase);
        if(frasePartida.length != juego.getCantidadPalabras()) {
            System.err.println("La cantidad de palabras no cumple con las pistas. (Cantidad de palabras: " + juego.getCantidadPalabras() + ")");
            return false;
        }
        for(int i = 0; i < juego.getCantidadPalabras(); i++) {
            if(juego.getPalabra(i).length() != frasePartida[i].length()) {
                System.err.println("La cantidad de letras de la palabra número " + (i + 1) + " no cumple con las pistas. (Cantidad de letras: " + juego.getPalabra(i).length() + ")");
                return false;
            }
        }
        return true;
    }

    public Juego obtenerNuevoJuego(Jugador jugador, Categoria categoria) {
        String fraseAleatoria = obtenerFraseAleatoria(categoria);
        String[] palabras = obtenerPalabras(fraseAleatoria);
        int cantidadIntentos = obtenerCantidadIntentos(fraseAleatoria);

        return new Juego(jugador, palabras, cantidadIntentos);
    }

    public String jugar(Juego juego, String frase) {
        int cantidadTotal100P = 0;
        int cantidadTotal50P = 0;

        String[] palabrasIngresadas = frase.split(" ");
        String aciertos100Frase = "";
        String aciertos50Frase = "";
        for(int indicePalabra = 0; indicePalabra < juego.getCantidadPalabras(); indicePalabra++) {
            int cantidadPalabra100P = 0;
            int cantidadPalabra50P = 0;
            char[] letrasPalabraJuego = juego.getPalabra(indicePalabra).toUpperCase().toCharArray();
            char[] letrasPalabraIngresada = palabrasIngresadas[indicePalabra].toUpperCase().toCharArray();

            //100% Palabra
            String aciertos100Palabra = "";
            for(int indiceLetra = 0; indiceLetra < letrasPalabraJuego.length; indiceLetra++) {
                if(letrasPalabraJuego[indiceLetra] == letrasPalabraIngresada[indiceLetra]) {
                    aciertos100Palabra += letrasPalabraJuego[indiceLetra] + ", ";
                    letrasPalabraJuego[indiceLetra] = '$';
                    letrasPalabraIngresada[indiceLetra] = '#';
                    cantidadPalabra100P++;
                    cantidadTotal100P++;
                }
            }

            //50% Palabra
            String aciertos50Palabra = "";
            for(int indiceLetraJuego = 0; indiceLetraJuego < letrasPalabraJuego.length; indiceLetraJuego++) {
                for(int indiceLetraIngresada = 0; indiceLetraIngresada < letrasPalabraIngresada.length; indiceLetraIngresada++) {
                    if(letrasPalabraJuego[indiceLetraJuego] == letrasPalabraIngresada[indiceLetraIngresada]) {
                        aciertos50Palabra += letrasPalabraJuego[indiceLetraJuego] + ", ";
                        letrasPalabraJuego[indiceLetraJuego] = '&';
                        letrasPalabraIngresada[indiceLetraIngresada] = '!';
                        cantidadPalabra50P++;
                        cantidadTotal50P++;
                    }
                }
            }
            aciertos100Frase += "\nPalabra " + (indicePalabra + 1 ) + ": " + cantidadPalabra100P + " acierto(s): " + aciertos100Palabra;
            aciertos50Frase += "\nPalabra " + (indicePalabra + 1 ) + ": " + cantidadPalabra50P + " acierto(s): " + aciertos50Palabra;
        }

        int cantidadLetras = 0;
        for(int indicePalabra = 0; indicePalabra < juego.getCantidadPalabras(); indicePalabra++) {
            cantidadLetras += juego.getPalabra(indicePalabra).length();
        }
        if(cantidadLetras == cantidadTotal100P) {
            juego.terminar();
            return "FELICITACIONES, usted acertó la frase '" + frase + "'";
        }
        juego.decrementarCantidadIntentos();
        if(juego.getCantidadIntentosRestantes() == 0) {
            juego.terminar();
        }
        return "\n\nAcierto 100%: " + cantidadTotal100P + " de la siguiente manera: " + aciertos100Frase + "" +
                "\n\nAcierto 50%: " + cantidadTotal50P + " de la siguiente manera: " + aciertos50Frase + "" +
                "\n\nTuvo " + cantidadTotal100P + " aciertos al 100% y " + cantidadTotal50P + " al 50%";
    }
}
