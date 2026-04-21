// Clase Entrada que representa la compra de entradas para un concierto
// Contiene los atributos básicos y sus métodos getter/setter
public class Entrada {
    // Atributos privados de la clase (encapsulación)
    private int id;                 // Identificador único de la compra/entrada
    private int concierto;          // ID del concierto al que se asiste (referencia a Concierto)
    private String comprador;       // Nombre de la persona que compra las entradas
    private int cantidad;           // Número de entradas adquiridas
    private String fechaCompra;     // Fecha en que se realizó la compra

    // Constructor con todos los parámetros para inicializar un objeto Entrada
    public Entrada(int id, int concierto, String comprador, int cantidad, String fechaCompra) {
        this.id = id;
        this.concierto = concierto;   // Almacena el ID del concierto (FK hacia Concierto)
        this.comprador = comprador;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
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

    // Getter y Setter para concierto (almacena el ID del concierto)
    public int getConcierto() {
        return concierto;
    }

    public void setConcierto(int concierto) {
        this.concierto = concierto;
    }

    // Getter y Setter para comprador
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    // Getter y Setter para cantidad
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getter y Setter para fechaCompra
    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
