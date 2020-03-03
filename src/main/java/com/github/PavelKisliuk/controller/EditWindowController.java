/*  By Pavel Kisliuk, 03.03.2020
 *  This is class for education and nothing rights don't reserved.
 */

package com.github.PavelKisliuk.controller;

import com.github.PavelKisliuk.entity.Human;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * The {@code EditWindowController} class is controller for updating data from client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 1.8
 */
public class EditWindowController {
	/**
	 * Instance for data insertion.
	 */
	private Human human;

	/**
	 * Flag for checking data insertion.
	 */
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
		if(validate()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Warning!!!");
			alert.setContentText("Enter data in every field!");
			alert.showAndWait();
			return;
		}

		human.setName(nameTextField.getText());
		human.setAge(Integer.parseInt(ageTextField.getText()));
		human.setBirthday(birthdayDatePicker.getValue());
		isCancelPressed = false;
		cancelButtonOnAction();
	}

	private void cancelButtonOnAction() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public boolean isCancelPressed() {
		return isCancelPressed;
	}

	public void setForms() {
		String name = human.getName();
		nameTextField.setText(name);
		String age = String.valueOf(human.getAge());
		ageTextField.setText(age);
		LocalDate localDate = human.getBirthday();
		birthdayDatePicker.setValue(localDate);
	}

	private boolean validate() {
		return nameTextField == null ||
				nameTextField.getText().equals("") ||
				ageTextField == null ||
				ageTextField.getText().equals("") ||
				birthdayDatePicker.getValue() == null;
	}
}