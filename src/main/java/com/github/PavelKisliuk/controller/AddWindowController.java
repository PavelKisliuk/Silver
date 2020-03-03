/*  By Pavel Kisliuk, 03.03.2020
 *  This is class for education and nothing rights don't reserved.
 */

package com.github.PavelKisliuk.controller;

import com.github.PavelKisliuk.entity.Human;
import com.github.PavelKisliuk.service.AddService;
import com.github.PavelKisliuk.util.DataValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * The {@code AddWindowController} class is controller for obtaining data form client.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 1.8
 */
public class AddWindowController {
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
	private HBox dataHBox;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

	@FXML
	void initialize() {
		saveButton.setOnAction(event -> saveButtonOnAction());
		cancelButton.setOnAction(event -> cancelButtonOnAction());

		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > DataValidator.MAX_NAME_LENGTH) {
				nameTextField.setText(oldValue);
			}
		});
		ageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > DataValidator.MAX_AGE_LENGTH ||
					!DataValidator.isNumeric(newValue)) {
				ageTextField.setText(oldValue);
			}
		});
	}

	private void saveButtonOnAction() {
		if (validate()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Warning!!!");
			alert.setContentText("Enter data in every field!");
			alert.showAndWait();
			return;
		}

		human = AddService.INSTANCE.serve(dataHBox);
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

	private boolean validate() {
		return nameTextField == null ||
				nameTextField.getText().equals("") ||
				ageTextField == null ||
				ageTextField.getText().equals("") ||
				birthdayDatePicker.getValue() == null;
	}
}