package co.edu.upn.arena;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InicioController {

    @FXML private Label lblJugadores;
    @FXML private Label lblEquipos;
    @FXML private Label lblPartidos;
    @FXML private Label lblJugados;

    // Se ejecuta solo, apenas se abre la pantalla: cuenta lo que hay en la base de datos
    @FXML
    private void initialize() {
        try {
            lblJugadores.setText(String.valueOf(JugadorDAO.listar().size()));
            lblEquipos.setText(String.valueOf(EquipoDAO.listar().size()));

            int programados = 0;
            int jugados = 0;
            for (Partido p : PartidoDAO.listar()) {
                if (p.isFinalizado()) {
                    jugados++;
                } else {
                    programados++;
                }
            }
            lblPartidos.setText(String.valueOf(programados));
            lblJugados.setText(String.valueOf(jugados));
        } catch (SQLException e) {
            Mensajes.error("No se pudo cargar el resumen del torneo.");
        }
    }
}
