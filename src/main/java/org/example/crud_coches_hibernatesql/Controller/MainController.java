package org.example.crud_coches_hibernatesql.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.crud_coches_hibernatesql.domain.Coche;
import org.example.crud_coches_hibernatesql.util.Alerts;
import org.example.crud_coches_hibernatesql.util.Comprobaciones;
import org.example.crud_coches_hibernatesql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.example.crud_coches_hibernatesql.DAO.CocheDao;
import org.example.crud_coches_hibernatesql.DAO.CocheDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonLimpiar;

    @FXML
    private Button buttonModificar;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TableColumn<Coche, String> columnaMarca;

    @FXML
    private TableColumn<Coche, String> columnaMatricula;

    @FXML
    private TableColumn<Coche, String> columnaModelo;

    @FXML
    private TableColumn<Coche, String> columnaTipo;

    @FXML
    private TableColumn<Coche,Integer> columnaId;

    @FXML
    private TableView<Coche> tableCoches;

    @FXML
    private TextField txtFieldMarca;

    @FXML
    private TextField txtFieldMatricula;

    @FXML
    private TextField txtFieldModelo;

    String[] listaTipos = {"Familiar","Monovolumen","Deportivo","SUV"};

    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = HibernateUtil.getSession();
    CocheDao dao = new CocheDaoImpl();

    @FXML
    void onButtonEliminarClick() {
        if (tableCoches.getSelectionModel().getSelectedItem() != null) {
            Coche coche = tableCoches.getSelectionModel().getSelectedItem();
            dao.eliminarCoche(coche.getId(),session);
            cargarTabla();
            setearTextFieldsVacios();
            Alerts.alertaGeneral("Coche eliminado correctamente","INFORMATION");
        } else {
            Alerts.alertaGeneral("Debe seleccionar un coche para eliminar","INFORMATION");
        }
    }

    @FXML
    void onButtonGuardarClick() {
        if (Comprobaciones.textosVacios(cbTipo,txtFieldMatricula,txtFieldMarca,txtFieldModelo)) {
            Coche coche = new Coche(txtFieldMatricula.getText(),txtFieldMarca.getText(),txtFieldModelo.getText(),cbTipo.getSelectionModel().getSelectedItem());
            if (!dao.existe(txtFieldMatricula.getText(),session)) {
                dao.insertarCoche(coche,session);
                cargarTabla();
                setearTextFieldsVacios();
                Alerts.alertaGeneral("Coche guardado correctamente","INFORMATION");
            } else {
                Alerts.alertaGeneral("Esa matrícula ya existe","INFORMATION");
            }
        } else {
            Alerts.alertaGeneral("Debe rellenar todos los campos","WARNING");
        }
    }

    @FXML
    void onButtonLimpiarClick() {

    }

    @FXML
    void onButtonModificarClick() {

    }

    @FXML
    void onTableClick() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Inicializamos los tipos en el comboBox
        cbTipo.getItems().addAll(listaTipos);
        cargarTabla();
    }
    public void cargarTabla() {
        //Cargamos los datos en la tabla
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        columnaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        ObservableList<Coche> observableList = dao.listar(session);
        tableCoches.setItems(observableList);
    }
    public void setearTextFieldsVacios() {
        //Metodo para limpiar los textFields y el comboBox
        txtFieldModelo.setText("");
        txtFieldMatricula.setText("");
        txtFieldMarca.setText("");
        cbTipo.setValue("");
    }

}