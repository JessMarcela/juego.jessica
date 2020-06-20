package co.edu.udistrital.vista;

import co.edu.udistrital.modelo.Categoria;
import co.edu.udistrital.modelo.ElCoreDelJuego;

import java.util.ArrayList;
import java.util.List;

public class MenuAdministracion {

    private final ElCoreDelJuego elCoreDelJuego;

    public MenuAdministracion(ElCoreDelJuego elCoreDelJuego) {
        this.elCoreDelJuego = elCoreDelJuego;
    }

    public void ingresarAdministrar() {
        if(validarAdministrador()) {
            String opcion = "";
            do {
                System.out.println("\nAdministración");
                System.out.println("1. Mostrar categorías");
                System.out.println("2. Crear categoría");
                System.out.println("3. Editar categoría");
                System.out.println("4. Eliminar categoría");
                System.out.println("0. Salir al menú principal.");
                System.out.printf("Digite la opción: ");
                opcion = InOut.leerLinea();

                switch (opcion) {
                    case "1":
                        mostrarCategorias();
                        break;
                    case "2":
                        agregarCategoria();
                        break;
                    case "3":
                        editarCategoria();
                        break;
                    case "4":
                        eliminarCategoria();
                        break;
                    case "test":
                        cargarCategoriasPorDefecto();
                        break;
                    default:
                        break;
                }
            } while (!opcion.equals("0"));
        } else {
            System.out.println("Contraseña inválida");
        }
    }

    private boolean validarAdministrador() {
        System.out.printf("Digite la contraseña del administrador: ");
        String contrasena = InOut.leerLinea();
        return elCoreDelJuego.validarAdministrador(contrasena);
    }

    private void mostrarCategorias() {
        System.out.println("\n* MOSTRAR CATEGORÍAS *");
        List<Categoria> categorias = elCoreDelJuego.getCategorias();
        if(categorias.isEmpty()) {
            System.err.println("\nNo existen categorías creadas");
        } else {
            System.out.println("\nLas categorías existentes son:");
            for (int i = 0; i < categorias.size(); i++) {
                Categoria categoria = categorias.get(i);
                System.out.println("\n" + (i + 1) + ". " + categoria.getNombre() + ": " + categoria.getDescripcion());
                System.out.println(categoria.getFrases());
            }
        }
    }

    private void agregarCategoria() {
        System.out.println("\n* NUEVA CATEGORÍA *\n");
        System.out.printf("Nombre de la nueva categoría: ");
        String nombre = InOut.leerLinea();
        System.out.printf("Escriba la descripción para la categoría '" + nombre + "'");
        String descripcion = InOut.leerLinea();

        List<String> frases = new ArrayList<>();
        System.out.println("Escriba una frase por línea. No se permiten frases repetidas. Para dejar de agregar frases deje una línea en blanco");
        String frase;
        while (true) {
            frase = InOut.leerLinea();
            if(esLineaEnBlanco(frase)) {
                break;
            }
            if(esFraseValida(frase)) {
                if(esFraseRepetida(frases, frase)) {
                    System.err.println("No se permiten frases repetidas");
                } else {
                    frases.add(frase);
                }
            } else {
                System.err.println("Solamente se permiten caracteres alfanuméricos.");
            }
        }
        elCoreDelJuego.agregarCategoria(new Categoria(nombre, descripcion, frases));
    }

    private void editarCategoria() {
        System.out.println("\n* EDITAR CATEGORÍA *\n");
        List<Categoria> categorias = elCoreDelJuego.getCategorias();
        if(categorias.isEmpty()) {
            System.err.println("\nNo existen categorías creadas");
        } else {
            for (int i = 0; i < categorias.size(); i++) {
                Categoria categoria = categorias.get(i);
                System.out.println((i + 1) + ". " + categoria.getNombre() + ": " + categoria.getDescripcion());
            }
            int numero = leerCategoria(categorias.size());
            Categoria categoria = categorias.get(numero - 1);
            List<String> frases = categoria.getFrases();

            String opcion;
            do {
                System.out.println("\nEditar categoría '" + categoria.getNombre() + "'\n");
                System.out.println("1. Mostrar frases");
                System.out.println("2. Agregar frase");
                System.out.println("3. Eliminar frase");
                System.out.println("0. Salir al menú de editar categoría");
                System.out.printf("Digite la opción: ");
                opcion = InOut.leerLinea();
                switch (opcion) {
                    case "1":
                        mostrarFrases(categoria);
                        break;
                    case "2":
                        agregarFrase(categoria);
                        break;
                    case "3":
                        eliminarFrase(categoria);
                        break;
                    default:
                        break;
                }
            } while (!opcion.equals("0"));

            System.out.println("las frases de la categoría '" + categoria.getNombre() + "' son :");
            for(int i = 0; i < categoria.getFrases().size(); i++) {
                System.out.println((i + 1) + ". " + frases.get(i));
            }
            System.out.println("Digite la frase ");
        }
    }

    private void mostrarFrases(Categoria categoria) {
        List<String> frases = categoria.getFrases();
        System.out.println("\nLas frases de la categoría '" + categoria.getNombre() + "' son :");
        for(int i = 0; i < frases.size(); i++) {
            System.out.println((i + 1) + ". " + frases.get(i));
        }
    }

    private void agregarFrase(Categoria categoria) {
        List<String> frases = categoria.getFrases();
        System.out.printf("Escriba una frase, no se permiten frases repetidas: ");
        String frase = InOut.leerLinea();
        if(esLineaEnBlanco(frase)) {
            System.err.println("No se permiten líneas en blanco");
        } else if(esFraseValida(frase)) {
            if(esFraseRepetida(frases, frase)) {
                System.err.println("No se permiten frases repetidas");
            } else {
                frases.add(frase);
            }
        } else {
            System.err.println("Solamente se permiten caracteres alfanuméricos.");
        }
    }

    private void eliminarFrase(Categoria categoria) {
        List<String> frases = categoria.getFrases();
        System.out.println("\nLas frases de la categoría '" + categoria.getNombre() + "' son :");
        for(int i = 0; i < categoria.getFrases().size(); i++) {
            System.out.println((i + 1) + ". " + frases.get(i));
        }
        int numeroFrase = leerFrase(frases.size());
        String frase = frases.get(numeroFrase - 1);
        System.out.printf("¿Desea elminar la frase '" + frase + "'? si/no: ");
        if(InOut.leerLinea().equalsIgnoreCase("si")) {
            frases.remove(frase);
        }
    }

    private void eliminarCategoria() {
        System.out.println("\n* ELIMINAR CATEGORÍA *");
        List<Categoria> categorias = elCoreDelJuego.getCategorias();
        if(categorias.isEmpty()) {
            System.err.println("\nNo existen categorías creadas");
        } else {
            for (int i = 0; i < categorias.size(); i++) {
                Categoria categoria = categorias.get(i);
                System.out.println((i + 1) + ". " + categoria.getNombre() + ": " + categoria.getDescripcion());
            }
            int numero = leerCategoria(categorias.size());
            Categoria categoria = categorias.get(numero - 1);
            System.out.printf("¿Desea elminar la categoría '" + categoria.getNombre() + "'? si/no: ");
            if(InOut.leerLinea().equalsIgnoreCase("si")) {
                categorias.remove(categoria);
            }
        }
    }

    private void cargarCategoriasPorDefecto() {
        List<Categoria> categorias = elCoreDelJuego.getCategorias();
        categorias.clear();

        List<String> frasesBiologia = new ArrayList<>();
        frasesBiologia.add("El humano es triste");
        frasesBiologia.add("El animal es feliz");
        frasesBiologia.add("La jirafa es de color amarillo");
        frasesBiologia.add("Algunos pelajes son rubios");
        frasesBiologia.add("Los peces no ven el agua");
        frasesBiologia.add("Las ballenas no pueden ser comidas por peces");

        List<String> frasesInformatica = new ArrayList<>();
        frasesInformatica.add("La CPU va a explotar");
        frasesInformatica.add("Ese computador tiene mucha capacidad");
        frasesInformatica.add("Al teclado le falta una tecla");
        frasesInformatica.add("Los parlantes anda fallando");
        frasesInformatica.add("La memoria se agota");

        categorias.add(new Categoria("Biología", "Frases de biología", frasesBiologia));
        categorias.add(new Categoria("Informática", "Frases de informática", frasesInformatica));
    }

    private static boolean esLineaEnBlanco(String frase) {
        for(char caracter : frase.toCharArray()) {
            if(caracter != ' ') {
                return false;
            }
        }
        return true;
    }

    private static boolean esFraseValida(String frase) {
        for(char caracter : frase.toCharArray()) {
            if((caracter < ' ') || (caracter > ' ' && caracter < '0') || (caracter > '9' && caracter < 'A') || (caracter > 'Z' && caracter < 'a') || (caracter > 'z')) {
                return false;
            }

        }
        return true;
    }

    private boolean esFraseRepetida(List<String> frases, String fraseNueva) {
        for(String frase : frases) {
            if(frase.equalsIgnoreCase(fraseNueva)) {
                return true;
            }
        }
        return false;
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

    private int leerFrase(int cantidadFrases) {
        while(true) {
            System.out.printf("Digite el número de la frase: ");
            String categoriaEscogida = InOut.leerLinea();
            try {
                int numero = Integer.parseInt(categoriaEscogida);
                if(numero > 0 && numero <= cantidadFrases) {
                    return numero;
                } else {
                    System.err.println("Debe digitar un número dentro del rango [1, " + cantidadFrases + "]");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Debe digitar un número");
            }
        }
    }
}
