package co.edu.udistrital.vista;

import co.edu.udistrital.modelo.Categoria;
import co.edu.udistrital.modelo.ElCoreDelJuego;
import co.edu.udistrital.modelo.Juego;
import co.edu.udistrital.modelo.Jugador;

import java.util.List;

public class VistaJuego {

    private final ElCoreDelJuego elCoreDelJuego;

    public VistaJuego(ElCoreDelJuego elCoreDelJuego) {
        this.elCoreDelJuego = elCoreDelJuego;
    }

    public void ingresarJuego() {
        System.out.printf("Escriba su nombre: ");
        String nombre = InOut.leerLinea();
        Jugador jugador = elCoreDelJuego.buscarJugador(nombre);

        System.out.println("Bienvenido jugador " + jugador.getNombre());

        List<Categoria> categorias = elCoreDelJuego.getCategorias();
        for (int i = 0; i < categorias.size(); i++) {
            Categoria categoria = categorias.get(i);
            System.out.println((i + 1) + ". " + categoria.getNombre() + ": " + categoria.getDescripcion());
        }
        int numero = leerCategoria(categorias.size());
        Categoria categoria = categorias.get(numero - 1);


        Juego juego = elCoreDelJuego.obtenerNuevoJuego(jugador, categoria);

        System.out.println("\nLa cantidad de palabras de la frase es: " + juego.getCantidadPalabras());
        for(int i = 0; i < juego.getCantidadPalabras(); i++) {
            System.out.println("Palabra " + (i + 1) + ": " + juego.getPalabra(i).length() + " letras");
        }
        System.out.println("Tiene " + juego.getCantidadIntentosRestantes() + " intentos");

        do {
            System.out.printf("\nDigite la frase: ");
            String frase = InOut.leerLinea();
            if(elCoreDelJuego.validarCantidadEnFrase(juego, frase)) {
                String resultado = elCoreDelJuego.jugar(juego, frase);
                System.out.println(resultado);
            }
        } while (juego.continuar());
    }

    private int leerCategoria(int cantidadCategorias) {
        while(true) {
            System.out.printf("Digite el número de la categoría: ");
            String categoriaEscogida = InOut.leerLinea();
            try {
                int numero = Integer.parseInt(categoriaEscogida);
                if(numero > 0 && numero <= cantidadCategorias) {
                    return numero;
                } else {
                    System.err.println("Debe digitar un número dentro del rango [1, " + cantidadCategorias + "]");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Debe digitar un número");
            }
        }
    }
}
