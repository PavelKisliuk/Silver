package com.github.PavelKisliuk.controller;

import com.github.PavelKisliuk.entity.Human;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;

public class AddWindowController {
	private Human human;
	private boolean isCancelPressed = true;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField ageTextField;

	@FXML
	private DatePicker birthdayDatePicker;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

	@FXML
	void initialize() {
		saveButton.setOnAction(event -> saveButtonOnAction());
		cancelButton.setOnAction(event -> cancelButtonOnAction());
	}

	private void saveButtonOnAction() {
		human = new Human();
		human.setName(nameTextField.getText());
		human.setAge(Integer.parseInt(ageTextField.getText()));
		LocalDate localDate = birthdayDatePicker.getValue();
		human.setBirthday(new Date(localDate.toEpochDay()));
		isCancelPressed = false;
		cancelButtonOnAction();
	}

	private void cancelButtonOnAction() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	public Human getHuman() {
		return human;
	}

	public boolean isCancelPressed() {
		return isCancelPressed;
	}
}