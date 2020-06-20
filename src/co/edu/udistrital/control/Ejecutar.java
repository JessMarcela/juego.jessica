package co.edu.udistrital.control;

import co.edu.udistrital.modelo.ElCoreDelJuego;
import co.edu.udistrital.vista.MenuPrincipal;

public class Ejecutar {

    public static void main(String[] args) {
        ElCoreDelJuego elCoreDelJuego = new ElCoreDelJuego();
        MenuPrincipal menuPrincipal = new MenuPrincipal(elCoreDelJuego);
        menuPrincipal.jugar();
    }
}
