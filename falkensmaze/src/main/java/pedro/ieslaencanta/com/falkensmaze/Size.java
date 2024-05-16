/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit este template
 */
package pedro.ieslaencanta.com.falkensmaze;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 
 * Autor: PGS
 */
@XmlRootElement
public class Size implements Cloneable, Comparable<Size>, Serializable {
    // Ancho
    private int width;
    // Alto
    private int height;

    /**
     * Constructor por defecto.
     */
    public Size() {
    }

    /**
     * Constructor que inicializa el ancho y el alto.
     * @param width El ancho del tamaño.
     * @param height El alto del tamaño.
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Método para clonar el objeto Size.
     * @return Un nuevo objeto Size con los mismos valores.
     * @throws CloneNotSupportedException Si el objeto no se puede clonar.
     */
    public Object clone() throws CloneNotSupportedException {
        return new Size(this.getWidth(), this.getHeight());
    }

    /**
     * Método para comparar si dos objetos Size son iguales.
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Size)) {
            return false;
        }
        if (this.getWidth() == ((Size) (o)).getWidth() && this.getHeight() == ((Size) (o)).getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para comparar dos objetos Size.
     * @param o El objeto Size a comparar.
     * @return 0 si los objetos son iguales, -1 si este objeto es menor, 1 si es mayor.
     */
    @Override
    public int compareTo(Size o) {
        if (this.getWidth() == o.getWidth() && this.getHeight() == o.getHeight())
            return 0;
        if (this.getWidth() < o.getWidth())
            return -1;
        else
            return 1;
    }

    /**
     * Método para obtener una representación en cadena del objeto.
     * @return Una cadena que representa el ancho y el alto.
     */
    public String toString() {
        return "W:" + this.width + " H:" + this.height;
    }

    /**
     * @return El ancho.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return El alto.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param width El ancho a establecer.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param height El alto a establecer.
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
