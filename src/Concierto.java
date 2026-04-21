// Clase Concierto que representa un evento musical en el sistema
// Contiene los atributos básicos y sus métodos getter/setter
public class Concierto {
    // Atributos privados de la clase (encapsulación)
    private int id;                 // Identificador único del concierto
    private int artista;            // ID del artista que actúa (referencia a Artista)
    private String fecha;           // Fecha del concierto (formato pendiente de definir)
    private String lugar;           // Lugar/ubicación donde se realiza el concierto
    private double precioEntrada;   // Precio de la entrada (permite decimales)

    // Constructor con todos los parámetros para inicializar un objeto Concierto
    public Concierto(int id, int artista, String fecha, String lugar, double precioEntrada) {
        this.id = id;
        this.artista = artista;       // Almacena el ID, no el objeto Artista completo
        this.fecha = fecha;
        this.lugar = lugar;
        this.precioEntrada = precioEntrada;
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

    // Getter y Setter para artista (almacena el ID del artista)
    public int getArtista() {
        return artista;
    }

    public void setArtista(int artista) {
        this.artista = artista;
    }

    // Getter y Setter para fecha
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    // Getter y Setter para lugar
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    // Getter y Setter para precioEntrada
    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }
}
