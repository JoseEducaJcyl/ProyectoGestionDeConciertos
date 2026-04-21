// Clase Artista que representa un artista musical en el sistema
// Contiene los atributos básicos y sus métodos getter/setter
public class Artista {
    // Atributos privados de la clase (encapsulación)
    private int id;                   // Identificador único del artista
    private String nombre;            // Nombre del artista
    private String generoMusical;     // Género musical que interpreta (Rock, Pop, etc.)
    private String paisOrigen;        // País de procedencia del artista

    // Constructor con todos los parámetros para inicializar un objeto Artista
    public Artista(int id, String nombre, String generoMusical, String paisOrigen) {
        this.id = id;
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.paisOrigen = paisOrigen;
    }

    // Métodos Getter y Setter (accesadores y mutadores)
    // Permiten acceder y modificar los atributos privados de forma controlada

    // Getter y Setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para generoMusical
    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    // Getter y Setter para paisOrigen
    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
}
