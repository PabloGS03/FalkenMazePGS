/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import pedro.ieslaencanta.com.falkensmaze.Size;

/**
 * Autor: PGS
 */
@XmlRootElement
public class Maze implements Serializable {

    private Size size; // Tamaño del laberinto
    private Block[][] blocks; // Matriz de bloques que componen el laberinto
    private double time; // Tiempo asociado al laberinto
    private String sound; // Sonido asociado al laberinto

    /**
     * Constructor por defecto.
     */
    public Maze() {
    }

    /**
     * Inicializa el laberinto con bloques vacíos.
     */
    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = new Block();
            }
        }
    }

    /**
     * Reinicia el laberinto, eliminando todos los bloques.
     */
    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = null;
            }
        }
        this.setBlocks(null);
    }

    /**
     * Reinicia el laberinto con un nuevo tamaño y lo inicializa.
     * @param newsize El nuevo tamaño del laberinto.
     */
    public void reset(Size newsize) {
        this.reset();
        this.setSize(newsize);
        this.init();
    }

    /**
     * Establece el valor de un bloque en una posición específica.
     * @param value El valor del bloque.
     * @param row La fila del bloque.
     * @param col La columna del bloque.
     */
    public void setBlockValue(String value, int row, int col) {
        this.getBlocks()[col][row].setValue(value);
    }

    /**
     * Obtiene el valor de un bloque en una posición específica.
     * @param row La fila del bloque.
     * @param col La columna del bloque.
     * @return El valor del bloque.
     */
    public String getBlockValue(int row, int col) {
        return this.getBlocks()[row][col].getValue();
    }

    // Getters y setters para los atributos de la clase

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * Carga un laberinto desde un archivo.
     * @param file El archivo desde el cual se cargará el laberinto.
     * @return El laberinto cargado.
     * @throws JAXBException Si ocurre un error durante la carga del archivo XML.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws FileNotFoundException Si el archivo no se encuentra.
     * @throws ClassNotFoundException Si ocurre un error al deserializar el archivo.
     * @throws Exception Si ocurre cualquier otro error.
     */
    public static Maze load(File file) throws JAXBException, IOException, FileNotFoundException, ClassNotFoundException, Exception {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        Maze m = null;
        switch (extension) {
            case "xml":
                m = Maze.loadXML(file);
                break;
            case "json":
                m = Maze.loadJSON(file);
                break;
            case "bin":
                m = Maze.loadBin(file);
                break;
            default:
                throw new Exception("Extensión " + extension + " no permitida");
        }
        return m;
    }

    /**
     * Guarda un laberinto en un archivo.
     * @param maze El laberinto a guardar.
     * @param file El archivo donde se guardará el laberinto.
     * @throws JAXBException Si ocurre un error durante la operación con XML.
     * @throws Exception Si ocurre cualquier otro error.
     */
    public static void save(Maze maze, File file) throws JAXBException, Exception {
        if (maze.sound == null || maze.sound.isEmpty()) {
            throw new Exception("Es necesario indicar el sonido del laberinto");
        }
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        switch (extension) {
            case "xml":
                Maze.saveXML(maze, file);
                break;
            case "json":
                Maze.saveJSON(maze, file);
                break;
            case "bin":
                Maze.saveBin(maze, file);
                break;
            default:
                throw new Exception("Extensión " + extension + " no permitida");
        }
    }

    // Métodos privados para cargar y guardar laberintos en diferentes formatos

    private static Maze loadJSON(File file) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(file.toPath());
        Maze m = gson.fromJson(reader, Maze.class);
        reader.close();
        return m;
    }

    private static Maze loadXML(File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Maze.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Maze) unmarshaller.unmarshal(file);
    }

    public static Maze loadBin(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream os = new FileInputStream(file);
        ObjectInputStream oos = new ObjectInputStream(os);
        Maze m = (Maze) oos.readObject();
        oos.close();
        os.close();
        return m;
    }

    private static void saveJSON(Maze maze, File file) throws FileNotFoundException, UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = gson.toJson(maze);
        PrintWriter pw = new PrintWriter(file, "UTF-8");
        pw.print(json);
        pw.close();
    }

    private static void saveXML(Maze maze, File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(maze.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);
        marshaller.marshal(maze, fw);
        fw.close();
    }

    public static void saveBin(Maze maze, File file) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(maze);
        oos.close();
        os.close();
    }
}
