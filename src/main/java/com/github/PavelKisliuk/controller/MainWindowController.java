package com.github.PavelKisliuk.controller;

import com.github.PavelKisliuk.entity.Human;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class MainWindowController {
	private static final String ADD_WINDOW_PATH = "/fxml/AddWindow.fxml";
	private static final String EDIT_WINDOW_PATH = "/fxml/EditWindow.fxml";

	private AddWindowController addWindowController;
	private EditWindowController editWindowController;

	@FXML
	private TreeTableView<Human> mainTreeTableView;

	@FXML
	private TreeTableColumn<Human, String> nameTreeTableColumn;

	@FXML
	private TreeTableColumn<Human, Integer> ageTreeTableColumn;

	@FXML
	private TreeTableColumn<Human, Date> birthdayTreeTableColumn;

	@FXML
	private Button addButton;

	@FXML
	private Button editButton;

	@FXML
	private Button deleteButton;

	@FXML
	void initialize() {
		nameTreeTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		ageTreeTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("age"));
		birthdayTreeTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("birthday"));

		TreeItem<Human> itemRoot = new TreeItem<>();
		mainTreeTableView.setRoot(itemRoot);
		mainTreeTableView.setShowRoot(false);
		//_________________________________________________

		addButton.setOnAction(event -> addButtonOnAction());
		editButton.setOnAction(event -> editButtonOnAction());
		deleteButton.setOnAction(event -> deleteButtonOnAction());

		mainTreeTableView.setOnMouseClicked(this::mainTreeTableViewOnDoubleClick);
	}

	private void addButtonOnAction() {
		startDialogueWindow(ADD_WINDOW_PATH);
		if (!(addWindowController.isCancelPressed())) {
			TreeItem<Human> newItem = new TreeItem<>(addWindowController.getHuman());
			TreeItem<Human> root = mainTreeTableView.getRoot();
			root.getChildren().add(newItem);
		}
	}

	private void editButtonOnAction() {
		if(isSelected()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Warning!!!");
			alert.setContentText("Need to choice element!");
			alert.showAndWait();
			return;
		}

		startDialogueWindow(EDIT_WINDOW_PATH);
		if (!(editWindowController.isCancelPressed())) {
			mainTreeTableView.refresh();
		}
	}

	private void deleteButtonOnAction() {
		if(isSelected()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Warning!!!");
			alert.setContentText("Need to choice element!");
			alert.showAndWait();
			return;
		}

		TreeItem<Human> deleteItem = mainTreeTableView.getSelectionModel().getSelectedItem();
		TreeItem<Human> root = mainTreeTableView.getRoot();
		root.getChildren().remove(deleteItem);

		mainTreeTableView.getSelectionModel().clearSelection();
		mainTreeTableView.refresh();
	}

	private void mainTreeTableViewOnDoubleClick(MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() == 2) {
			Human human = mainTreeTableView.getSelectionModel().getSelectedItem().getValue();
			LocalDate birthday = human.getBirthday();
			LocalDate today = LocalDate.now();
			if (birthday.getDayOfMonth() == today.getDayOfMonth() &&
					birthday.getMonth() == today.getMonth()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Warning!");
				alert.setContentText(human.getName() + " observe a birthday today.");
				alert.showAndWait();
			}
		}
	}

	private void startDialogueWindow(String FXMLFile) {
		//Stage adjustment
		//-----------------------------------------------
		Stage dialogueStage = new Stage();
		dialogueStage.setResizable(false);
		dialogueStage.sizeToScene();
		dialogueStage.setTitle("Dialogue");
		dialogueStage.centerOnScreen();

		//FXML adjustment
		//-----------------------------------------------
		FXMLLoader fxmlLoaderDialogue = new FXMLLoader();
		fxmlLoaderDialogue.setLocation(getClass().getResource(FXMLFile));
		Parent fxmlDialogue;
		try {
			fxmlDialogue = fxmlLoaderDialogue.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		switch (FXMLFile) {
			case ADD_WINDOW_PATH:
				addWindowController = fxmlLoaderDialogue.getController();
				break;
			case EDIT_WINDOW_PATH:
				editWindowController = fxmlLoaderDialogue.getController();
				TreeItem<Human> item = mainTreeTableView.getSelectionModel().getSelectedItem();
				editWindowController.setHuman(item.getValue());
				editWindowController.setForms();
				break;
			default:
				break;
		}

		//modality adjustment
		//-----------------------------------------------
		dialogueStage.initModality(Modality.WINDOW_MODAL);
		dialogueStage.initOwner(addButton.getScene().getWindow());

		//start-up window
		//-----------------------------------------------
		Scene dialogue = new Scene(fxmlDialogue);
		dialogueStage.setScene(dialogue);
		dialogueStage.showAndWait();
	}

	private boolean isSelected(){
		return mainTreeTableView.getSelectionModel().getSelectedItem() == null;
	}
}