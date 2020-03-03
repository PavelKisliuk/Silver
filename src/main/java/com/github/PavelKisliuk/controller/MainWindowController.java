package com.github.PavelKisliuk.controller;

import com.github.PavelKisliuk.entity.Human;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import javax.transaction.TransactionRequiredException;
import java.util.Date;

public class MainWindowController {

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

	private ObservableList<Human> accCollection = FXCollections.observableArrayList(); //тут храним аккаунты

	@FXML
	private TableView<Human> TV;

	@FXML
	private TableColumn<Human, String> TC1;

	@FXML
	private TableColumn<Human, Integer> TC2;

	@FXML
	private TableColumn<Human, Date> C3;

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

		accCollection.add(human);
		TV.setItems(accCollection);
		TC1.setCellValueFactory(new PropertyValueFactory<>("name"));
		TC2.setCellValueFactory(new PropertyValueFactory<>("age"));
		C3.setCellValueFactory(new PropertyValueFactory<>("birthday"));

		addButton.setOnAction(event -> {
			TreeItem<Human> root = mainTreeTableView.getRoot();
			root.getChildren().add(new TreeItem<>(human));
		});

		editButton.setOnAction(event -> {
			TreeItem<Human> temp = mainTreeTableView.getSelectionModel().getSelectedItem();
			temp.getValue().setName("Eduard");
			mainTreeTableView.refresh();
		});
	}
}