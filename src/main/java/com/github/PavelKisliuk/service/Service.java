/*  By Pavel Kisliuk, 03.03.2020
 *  This is class for education and nothing rights don't reserved.
 */

package com.github.PavelKisliuk.service;

import com.github.PavelKisliuk.entity.Human;
import javafx.scene.layout.HBox;

/**
 * The {@code Service} interface is simple service interface.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 1.8
 */
public interface Service {
	/**
	 * Simple service method for data actions.
	 *
	 * @param hBox is container for elements with data.
	 * @return {@code Human} created by {@param hBox}.
	 */
	Human serve(HBox hBox);
}