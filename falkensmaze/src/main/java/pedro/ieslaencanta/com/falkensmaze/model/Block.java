/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Autor: PGS
 */
@XmlRootElement
public class Block implements Serializable {
    // Valor del bloque
    private String value;

    /**
     * Constructor por defecto que inicializa el bloque sin valor.
     */
    public Block() {
        this.value = null;
    }

    /**
     * Obtiene el valor del bloque.
     * @return El valor del bloque.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Establece el valor del bloque.
     * @param value El valor a establecer.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Comprueba si el bloque está vacío.
     * @return true si el bloque no tiene valor, false en caso contrario.
     */
    public boolean isEmpty() {
        return this.value == null;
    }
}
