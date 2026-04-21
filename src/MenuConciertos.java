// Importes necesarios para el programa
import java.sql.*;
import java.util.Scanner;

// Clase MenuConciertos que gestiona el CRUD de conciertos
public class MenuConciertos {
    
    // Método estático que muestra el menú y gestiona las operaciones de conciertos
    public static void menuConciertos(Scanner sc, String url, String usuario, String contrasenia) throws SQLException {
        // Declarar conexión y PreparedStatement fuera para usarlos en varios bloques
        Connection conexion = null;
        PreparedStatement ps = null;
        
        // Variable de control para el bucle del menú
        boolean terminado = false;
        
        // Bucle principal del menú de conciertos
        while (!terminado) {
            // Mostrar las opciones del menú
            System.out.println("MENU DE CONCIERTOS");
            System.out.println("1. LISTAR CONCIERTOS.");
            System.out.println("2. AÑADIR CONCIERTOS.");
            System.out.println("3. ELIMINAR CONCIERTOS.");
            System.out.println("4. SALIR");
            
            // Leer la opción del usuario
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            
            // Ejecutar la opción seleccionada
            switch (opcion) {
                case 1: // LISTAR CONCIERTOS
                    try {
                        conexion = DriverManager.getConnection(url, usuario, contrasenia);
                        Statement st = conexion.createStatement();
                        String query = "SELECT * FROM CONCIERTO";
                        ResultSet rs = st.executeQuery(query);
                        
                        // Recorrer y mostrar todos los conciertos
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            int id_artista = rs.getInt("artista_id");
                            String fecha = rs.getString("fecha");
                            String lugar = rs.getString("lugar");
                            double precio = rs.getDouble("precioEntrada");
                            System.out.println("ID: " + id);
                            System.out.println("ID artista: " + id_artista);
                            System.out.println("Fecha: " + fecha);
                            System.out.println("Lugar: " + lugar);
                            System.out.println("Precio de la entrada: " + precio);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error al mostrar los datos: " + e.getMessage());
                    }
                    break;
                    
                case 2: // AÑADIR CONCIERTO
                    int id = 0;
                    // Abrir conexión y desactivar autocommit para transacción
                    conexion = DriverManager.getConnection(url, usuario, contrasenia);
                    conexion.setAutoCommit(false);
                    
                    // Obtener el ID máximo actual para asignar el siguiente
                    String query_id = "SELECT MAX(id) AS id FROM CONCIERTO";
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery(query_id);
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }
                    
                    // Pedir los datos del nuevo concierto al usuario
                    System.out.println("Ingrese el id del artista: ");
                    int id_artista = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Inserte fecha del concierto: ");
                    String fecha = sc.nextLine();
                    System.out.println("Inserte lugar del concierto: ");
                    String lugar = sc.nextLine();
                    System.out.println("Inserte precio de la entrada del concierto: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    
                    // Crear objeto Concierto con los datos
                    Concierto concierto = new Concierto(id, id_artista, fecha, lugar, precio);
                    
                    try {
                        // Preparar la consulta de inserción
                        String query = "INSERT INTO CONCIERTO (id, artista_id, fecha, lugar, precioEntrada) VALUES (?,?,?,?,?)";
                        ps = conexion.prepareStatement(query);
                        
                        // Asignar el ID (actual + 1) - MISMA LÓGICA REDUNDANTE que en MenuArtista
                        if (id != 0) {
                            concierto.setId(id + 1);
                            ps.setInt(1, concierto.getId());
                        } else {
                            concierto.setId(id + 1);
                            ps.setInt(1, concierto.getId());
                        }
                        
                        ps.setInt(2, concierto.getArtista());
                        ps.setString(3, concierto.getFecha());
                        ps.setString(4, concierto.getLugar());
                        ps.setDouble(5, concierto.getPrecioEntrada());
                        ps.execute();
                        conexion.commit();
                        System.out.println("CONCIERTO INSERTADO CORRECTAMENTE");
                        
                    } catch (SQLException e) {
                        System.out.println("ERROR AL INSERTAR, NO SE HA INSERTADO. " + e.getMessage());
                        conexion.rollback();
                    } finally {
                        // Cerrar recursos manualmente
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (SQLException e) {
                                System.out.println("Error al cerrar la Preparestatement: " + e.getMessage());
                            }
                        }
                        if (conexion != null) {
                            try {
                                conexion.close();
                            } catch (SQLException e) {
                                System.out.println("Error al cerrar la Conexion: " + e.getMessage());
                            }
                        }
                    }
                    break;
                    
                case 3: // ELIMINAR CONCIERTO (solo si no tiene entradas)
                    try {
                        System.out.println("SELECCIONA CONCIERTO A ELIMINAR (Por ID):");
                        int id_concierto = sc.nextInt();
                        sc.nextLine();
        
                        conexion = DriverManager.getConnection(url, usuario, contrasenia);
        
                        // Verificar si tiene entradas
                        String checkSql = "SELECT COUNT(*) AS total FROM ENTRADA WHERE concierto_id = ?";
                        int totalEntradas = 0;
                        try (PreparedStatement checkPs = conexion.prepareStatement(checkSql)) {
                            checkPs.setInt(1, id_concierto);
                            rs = checkPs.executeQuery();
                            if (rs.next()) {
                                totalEntradas = rs.getInt("total");
                            }
                        }
        
                if (totalEntradas > 0) {
                    System.out.println("ERROR: No se puede eliminar el concierto porque tiene " + 
                               totalEntradas + " entradas vendidas.");
                    System.out.println("Elimine primero las entradas asociadas.");
                } else {
                    String sql = "DELETE FROM CONCIERTO WHERE id = ?";
                    try (PreparedStatement psDelete = conexion.prepareStatement(sql)) {
                        psDelete.setInt(1, id_concierto);
                        psDelete.executeUpdate();
                        System.out.println("CONCIERTO ELIMINADO CORRECTAMENTE");
                    }
                }
        
                    } catch (SQLException e) {
                        System.out.println("Error al eliminar el concierto: " + e.getMessage());
                    } finally {
                        if (conexion != null) conexion.close();
                    }
                break;
                    
                case 4: // SALIR
                    System.out.println("Saliendo del menu conciertos...");
                    terminado = true;
                    break;
                    
                default: // Opción no válida
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
