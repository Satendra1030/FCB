package com.example.healthmonitoringsystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class HealthMonitoringApp extends Application {
    private HealthMonitoringServer server;
    private ObservableList<HealthRecord> healthRecordList;
    private ComboBox<User> userComboBox;

    @Override
    public void start(Stage primaryStage) {
        server = new HealthMonitoringServer();

        // Add a sample user
        User sampleUser = new User("John Doe");
        server.addUser(sampleUser);

        primaryStage.setTitle("Health Monitoring System");

        // Menu
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem addHealthDataMenuItem = new MenuItem("Add Health Data");
        MenuItem viewHealthDataMenuItem = new MenuItem("View Health Data");
        menu.getItems().addAll(addHealthDataMenuItem, viewHealthDataMenuItem);
        menuBar.getMenus().add(menu);

        VBox vbox = new VBox(menuBar);
        Scene scene = new Scene(vbox, 800, 600);

        addHealthDataMenuItem.setOnAction(e -> showAddHealthDataDialog());
        viewHealthDataMenuItem.setOnAction(e -> showViewHealthDataPanel(vbox));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddHealthDataDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Health Data");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label userLabel = new Label("User:");
        userComboBox = new ComboBox<>(FXCollections.observableArrayList(server.getUsers()));
        userComboBox.getSelectionModel().selectFirst();

        Label weightLabel = new Label("Weight:");
        TextField weightField = new TextField();
        Label exerciseLabel = new Label("Exercise:");
        TextField exerciseField = new TextField();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            User selectedUser = userComboBox.getSelectionModel().getSelectedItem();
            double weight = Double.parseDouble(weightField.getText());
            String exercise = exerciseField.getText();
            HealthRecord healthRecord = new HealthRecord(selectedUser.getId(), weight, exercise);
            server.addHealthRecord(healthRecord);
            dialog.close();
        });

        grid.add(userLabel, 0, 0);
        grid.add(userComboBox, 1, 0);
        grid.add(weightLabel, 0, 1);
        grid.add(weightField, 1, 1);
        grid.add(exerciseLabel, 0, 2);
        grid.add(exerciseField, 1, 2);
        grid.add(addButton, 1, 3);

        Scene scene = new Scene(grid, 400, 300);
        dialog.setScene(scene);
        dialog.show();
    }

    private void showViewHealthDataPanel(VBox vbox) {
        TableView<HealthRecord> table = new TableView<>();
        healthRecordList = FXCollections.observableArrayList(server.getHealthRecords());
        table.setItems(healthRecordList);

        TableColumn<HealthRecord, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        TableColumn<HealthRecord, Integer> userIdColumn = new TableColumn<>("User ID");
        userIdColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getUserId()).asObject());

        TableColumn<HealthRecord, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getWeight()).asObject());

        TableColumn<HealthRecord, String> exerciseColumn = new TableColumn<>("Exercise");
        exerciseColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getExercise()));

        TableColumn<HealthRecord, String> timestampColumn = new TableColumn<>("Timestamp");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timestampColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTimestamp().format(formatter)));

        table.getColumns().addAll(idColumn, userIdColumn, weightColumn, exerciseColumn, timestampColumn);

        vbox.getChildren().clear();
        vbox.getChildren().add(table);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
