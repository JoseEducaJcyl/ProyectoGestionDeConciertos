import java.sql.*;
import java.util.Scanner;

public class MenuConciertos {
    public static void menuConciertos(Scanner sc,String url,String usuario,String contrasenia) throws SQLException {
        Connection conexion = null;
        PreparedStatement ps = null;
        boolean terminado = false;
        while (!terminado) {
            System.out.println("MENU DE CONCIERTOS");
            System.out.println("1. LISTAR CONCIERTOS.");
            System.out.println("2. AÑADIR CONCIERTOS.");
            System.out.println("3. ELIMINAR CONCIERTOS.");
            System.out.println("4. SALIR");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    try {
                        conexion = DriverManager.getConnection(url, usuario, contrasenia);
                        Statement st = conexion.createStatement();
                        String query = "SELECT * FROM CONCIERTO";
                        ResultSet rs = st.executeQuery(query);
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
                    }catch (SQLException e) {
                        System.out.println("Error al mostrar los datos: "  + e.getMessage());
                    }
                    break;
                case 2:
                    int id = 0;
                    conexion = DriverManager.getConnection(url, usuario, contrasenia);
                    conexion.setAutoCommit(false);
                    String query_id = "SELECT MAX(id) AS id FROM CONCIERTO";
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery(query_id);
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }
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
                    Concierto concierto = new Concierto(id, id_artista, fecha, lugar, precio);
                    try {

                        String query = "INSERT INTO CONCIERTO (id, artista_id, fecha, lugar, precioEntrada) VALUES "
                                + "(?,?,?,?,?)";
                        ps = conexion.prepareStatement(query);
                        if (id != 0) {
                            concierto.setId(id+1);
                            ps.setInt(1, concierto.getId());
                        }else{
                            concierto.setId(id+1);
                            ps.setInt(1, concierto.getId());
                        }
                        ps.setInt(2, concierto.getArtista());
                        ps.setString(3, concierto.getFecha());
                        ps.setString(4, concierto.getLugar());
                        ps.setDouble(5, concierto.getPrecioEntrada());
                        ps.execute();
                        conexion.commit();
                        System.out.println("CONCIERTO INSERTADO CORRECTAMENTE");
                    }catch (SQLException e) {
                        System.out.println("ERROR AL INSERTAR, NO SE HA INSERTADO. " +  e.getMessage());
                        conexion.rollback();
                    }finally {
                        if(ps != null) {
                            try {
                                ps.close();
                            } catch (SQLException e) {
                                System.out.println("Error al cerrar la Preparestatement: "  + e.getMessage());
                            }
                        }
                        if(conexion != null) {
                            try {
                                conexion.close();
                            } catch (SQLException e) {
                                System.out.println("Error al cerrar la Conexion: "  + e.getMessage());
                            }
                        }
                    }
                    break;
                case 3:
                    try {
                        System.out.println("SELECCIONA CONCIERTO A ELIMINAR (Por ID):");
                        int id_concierto = sc.nextInt();
                        sc.nextLine();
                        conexion = DriverManager.getConnection(url, usuario, contrasenia);
                        String sql = "DELETE FROM CONCIERTO WHERE id = ?";

                        ps = conexion.prepareStatement(sql);
                        ps.setInt(1, id_concierto);
                        ps.executeUpdate();
                        System.out.println("CONCIERTO ELIMINADO CORRECTAMENTE");
                    }catch (SQLException e) {
                        System.out.println("Error al eliminar el concierto: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del menu conciertos...");
                    terminado = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
