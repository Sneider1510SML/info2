package co.edu.upn.arena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidoDAO {

    public static void programar(int equipoA, int equipoB, String fecha) throws SQLException {
        String sql = "INSERT INTO partido (equipo_a, equipo_b, fecha) VALUES (?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, equipoA);
            ps.setInt(2, equipoB);
            ps.setString(3, fecha);
            ps.executeUpdate();
        }
    }

    public static void guardarResultado(Partido p) throws SQLException {
        String sql = "UPDATE partido SET marcador_a = ?, marcador_b = ?, finalizado = 1 WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getMarcadorA());
            ps.setInt(2, p.getMarcadorB());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
        }
    }

    public static List<Partido> listar() throws SQLException {
        List<Partido> lista = new ArrayList<>();
        // JOIN: trae también el nombre de cada equipo
        String sql = """
                SELECT p.*, a.nombre AS nombre_a, b.nombre AS nombre_b
                FROM partido p
                JOIN equipo a ON p.equipo_a = a.id
                JOIN equipo b ON p.equipo_b = b.id
                ORDER BY p.fecha
                """;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Partido(
                        rs.getInt("id"),
                        rs.getInt("equipo_a"),
                        rs.getString("nombre_a"),
                        rs.getInt("equipo_b"),
                        rs.getString("nombre_b"),
                        rs.getString("fecha"),
                        rs.getInt("marcador_a"),
                        rs.getInt("marcador_b"),
                        rs.getInt("finalizado") == 1));
            }
        }
        return lista;
    }
}
