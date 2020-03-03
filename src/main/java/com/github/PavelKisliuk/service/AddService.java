/*  By Pavel Kisliuk, 03.03.2020
 *  This is class for education and nothing rights don't reserved.
 */

package com.github.PavelKisliuk.service;

import com.github.PavelKisliuk.entity.Human;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

/**
 * The {@code AddService} singleton class is {@code Service} realization for
 * insertion data from {@code HBox} container to {@code Human} instance.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 1.8
 */
public class AddService implements Service {
	/**
	 * Instance of this class.
	 */
	public static final AddService INSTANCE = new AddService();

	/**
	 * Private constructor of singleton.
	 */
	private AddService() {
		if (INSTANCE != null) {
			throw new AssertionError();
		}
	}

	/**
	 * Simple service method for insertion data from {@code HBox} container to {@code Human} instance.
	 *
	 * @param hBox is container for elements with data.
	 * @return {@code Human} instance.
	 */
	@Override
	public Human serve(HBox hBox) {
		TextField nameTextField = (TextField) hBox.lookup("#nameTextField");
		TextField ageTextField = (TextField) hBox.lookup("#ageTextField");
		DatePicker birthdayDatePicker = (DatePicker) hBox.lookup("#birthdayDatePicker");

		String enteredName = nameTextField.getText();
		Integer enteredAge = Integer.parseInt(ageTextField.getText());
		LocalDate enteredBirthday = birthdayDatePicker.getValue();

		return new Human.Builder()
				.name(enteredName)
				.age(enteredAge)
				.birthday(enteredBirthday)
				.build();
	}
}