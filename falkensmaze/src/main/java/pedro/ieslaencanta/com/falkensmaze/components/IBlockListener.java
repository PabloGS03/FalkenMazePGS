/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java para editar esta plantilla
 */
package pedro.ieslaencanta.com.falkensmaze.components;

/**
 * Interfaz que define los métodos para escuchar eventos en un bloque.
 * Autor: PGS
 */
public interface IBlockListener {

    /**
     * Método que se llama cuando se hace clic en un bloque.
     * @param b El bloque que se ha clicado.
     */
    public void onClicked(Block b);

    /**
     * Método que se llama cuando se hace doble clic en un bloque.
     * @param b El bloque que se ha doble clicado.
     */
    public void onDoubleClicked(Block b);
}
