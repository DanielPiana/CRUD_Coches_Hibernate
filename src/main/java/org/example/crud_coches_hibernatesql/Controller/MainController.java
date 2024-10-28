package org.example.crud_coches_hibernatesql.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonLimpiar;

    @FXML
    private Button buttonModificar;

    @FXML
    private ComboBox<?> cbTipo;

    @FXML
    private TableColumn<?, ?> columnaMarca;

    @FXML
    private TableColumn<?, ?> columnaMatricula;

    @FXML
    private TableColumn<?, ?> columnaModelo;

    @FXML
    private TableColumn<?, ?> columnaTipo;

    @FXML
    private TableView<?> tableCoches;

    @FXML
    private TextField txtFieldMarca;

    @FXML
    private TextField txtFieldMatricula;

    @FXML
    private TextField txtFieldModelo;

    @FXML
    void onButtonEliminarClick(ActionEvent event) {

    }

    @FXML
    void onButtonGuardarClick(ActionEvent event) {

    }

    @FXML
    void onButtonLimpiarClick(ActionEvent event) {

    }

    @FXML
    void onButtonModificarClick(ActionEvent event) {

    }

    @FXML
    void onTableClick(MouseEvent event) {

    }

}
