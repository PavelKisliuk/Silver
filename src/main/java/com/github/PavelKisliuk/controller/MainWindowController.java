package com.github.PavelKisliuk.controller;

import com.github.PavelKisliuk.entity.Human;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class MainWindowController {
	private static final String ADD_WINDOW_PATH = "/fxml/AddWindow.fxml";

	private AddWindowController addWindowController;

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
		Human human = new Human();
		human.setName("Pavel");
		human.setAge(26);
		human.setBirthday(new Date(759099600000L));


		nameTreeTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		ageTreeTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("age"));
		birthdayTreeTableColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("birthday"));

		TreeItem<Human> itemRoot = new TreeItem<>(new Human());
		TreeItem<Human> itemRoot1 = new TreeItem<>(human);
		TreeItem<Human> itemRoot2 = new TreeItem<>(human);

		itemRoot.getChildren().setAll(itemRoot2, itemRoot1);


		mainTreeTableView.setRoot(itemRoot);
		mainTreeTableView.setShowRoot(false);
		//_________________________________________________

		addButton.setOnAction(event -> addButtonOnAction());

		editButton.setOnAction(event -> {
			TreeItem<Human> temp = mainTreeTableView.getSelectionModel().getSelectedItem();
			temp.getValue().setName("Eduard");
			mainTreeTableView.refresh();
		});
	}

	private void addButtonOnAction() {
		startDialogueWindow(ADD_WINDOW_PATH);
		if(!(addWindowController.isCancelPressed())) {
			TreeItem<Human> newItem = new TreeItem<>(addWindowController.getHuman());
			TreeItem<Human> root = mainTreeTableView.getRoot();
			root.getChildren().add(newItem);
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
		Parent fxmlDialogue = null;
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
}