package co.edu.upn.arena;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PartidoController {

    @FXML private ComboBox<Equipo> cbEquipoA;
    @FXML private ComboBox<Equipo> cbEquipoB;
    @FXML private DatePicker dpFecha;
    @FXML private TableView<Partido> tabla;
    @FXML private TableColumn<Partido, String> colEquipoA;
    @FXML private TableColumn<Partido, String> colEquipoB;
    @FXML private TableColumn<Partido, String> colFecha;
    @FXML private TableColumn<Partido, String> colMarcador;
    @FXML private TableColumn<Partido, String> colEstado;
    @FXML private TextField txtMarcadorA;
    @FXML private TextField txtMarcadorB;

    @FXML
    private void initialize() {
        colEquipoA.setCellValueFactory(new PropertyValueFactory<>("nombreA"));
        colEquipoB.setCellValueFactory(new PropertyValueFactory<>("nombreB"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMarcador.setCellValueFactory(new PropertyValueFactory<>("marcador"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        try {
            cbEquipoA.getItems().setAll(EquipoDAO.listar());
            cbEquipoB.getItems().setAll(EquipoDAO.listar());
        } catch (SQLException e) {
            Mensajes.error("No se pudieron cargar los equipos.");
        }
        cargar();
    }

    private void cargar() {
        try {
            tabla.getItems().setAll(PartidoDAO.listar());
        } catch (SQLException e) {
            Mensajes.error("No se pudieron cargar los partidos.");
        }
    }

    @FXML
    private void programar() {
        Equipo a = cbEquipoA.getValue();
        Equipo b = cbEquipoB.getValue();
        LocalDate fecha = dpFecha.getValue();
        if (a == null || b == null || fecha == null) {
            Mensajes.error("Elige los dos equipos y la fecha.");
            return;
        }
        if (a.getId() == b.getId()) {
            Mensajes.error("Un equipo no puede jugar contra sí mismo.");
            return;
        }
        try {
            PartidoDAO.programar(a.getId(), b.getId(), fecha.toString());
            cargar();
            Mensajes.info("Partido programado.");
        } catch (SQLException e) {
            Mensajes.error("No se pudo programar el partido.");
        }
    }

    @FXML
    private void registrarResultado() {
        Partido p = tabla.getSelectionModel().getSelectedItem();
        if (p == null) {
            Mensajes.error("Primero selecciona un partido de la tabla.");
            return;
        }
        try {
            int a = Integer.parseInt(txtMarcadorA.getText().trim());
            int b = Integer.parseInt(txtMarcadorB.getText().trim());
            p.registrarResultado(a, b);   // aquí el partido revisa sus reglas
            PartidoDAO.guardarResultado(p);
            txtMarcadorA.clear();
            txtMarcadorB.clear();
            cargar();
            Mensajes.info("Resultado guardado.");
        } catch (NumberFormatException e) {
            Mensajes.error("El marcador debe ser un número entero.");
        } catch (IllegalArgumentException e) {
            Mensajes.error(e.getMessage());   // muestra el mensaje que escribimos en Partido
        } catch (SQLException e) {
            Mensajes.error("No se pudo guardar el resultado.");
            cargar();
        }
    }
}
