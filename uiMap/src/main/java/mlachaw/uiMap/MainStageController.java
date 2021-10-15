package mlachaw.uiMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainStageController {
	
	Model model = new Model();
	CustomMethods customMethods = new CustomMethods();
	
	@FXML
	private Label textLable;
	@FXML
	private TextField textFieldGivenText;
	@FXML
	private TextArea textAreaDisplay;
	@FXML
	private TextArea textAreaGivenNumbers;
	@FXML
	private Button buttonAdd;
	@FXML
	private Button buttonAddAll;
	@FXML
	private Button buttonReaplace;
	@FXML
	private Button buttonSpecific;
	@FXML
	private Button buttonClear;
	@FXML
	private Button buttonClearAll;
	@FXML
	private Spinner<Integer> spinnerFrom;
	@FXML
	private Spinner<Integer> spinnerTo;
	@FXML
	ComboBox<String> comboBoxMaps;

	

	@FXML
	private void initialize() {
		/*
		 * przypisanie wartosci ComboBox z wartosciami z observableList dodanie zakresu
		 * dla jakiego bedzie operował spinner
		 */
		
		comboBoxMaps.setValue(model.getAvailableComboBoxOptions().get(0));
		comboBoxMaps.setItems(model.getAvailableComboBoxOptions());
		spinnerFrom.setValueFactory(model.getSvfFrom());
		spinnerTo.setValueFactory(model.getSvfTo());

		
	}


	/*************** *************** ***************/
	/*************** Metody wywołane po kliknięciu ***************/
	/*************** *************** ***************/

	/*
	 * metoda wywołana po kliknięciu przycisku "buttonAdd" wykorzystuje metody do: 
	 * 1-sformatowania podanego tekstu 
	 * 2- dodania sformatowanych danych do mapy aktualnie wybranej w combo box 
	 * 3- wyswietlenia tekstu w polu tekstowym textAreaDisplay
	 */
	public void actionOnClickAdd(ActionEvent event) {
		
		model.setListOfGivenNumbers(customMethods.separateOnlyInts(textFieldGivenText.getText(), model.getNumbersFrom(), model.getNumbersTo(), model.getListOfGivenNumbers()));
		textAreaGivenNumbers.setText(model.getListOfGivenNumbers().toString());
		customMethods.findDividersToHashMap(model.getListOfGivenNumbers().toString(), comboBoxMaps.getValue(), model.getListOfMaps(), model.getAvailableComboBoxOptions());
		textAreaDisplay.setText(customMethods.displayTextMethod(model.getListOfMaps()));
		
	}
	
	
	/*
	 * metoda wywołana po kliknięciu przycisku "buttonAddAll" wykorzystuje metody do: 
	 * 1 -sformatowania podanego tekstu
	 * 2- dodania sformatowanych danych do wszystkich map istniejacych w combobox 
	 * 3- wyswietlenia tekstu w polu tekstowym textAreaDisplay
	 */
	@FXML
	void actionOnClickAddAll(ActionEvent event) {

		model.setListOfGivenNumbers(customMethods.separateOnlyInts(textFieldGivenText.getText(), model.getNumbersFrom(), model.getNumbersTo(), model.getListOfGivenNumbers()));
		textAreaGivenNumbers.setText(model.getListOfGivenNumbers().toString());
		customMethods.findDividersToAllHashMap(model.getListOfGivenNumbers().toString(), comboBoxMaps.getValue(), model.getListOfMaps(), model.getAvailableComboBoxOptions());
		textAreaDisplay.setText(customMethods.displayTextMethod(model.getListOfMaps()));

	}

	/*
	 * metoda wywołana po kliknięciu przycisku "buttonReaplace" wykorzystuje metody do: 
	 * 1 -sformatowania podanego tekstu 
	 * 2- dodania sformatowanych danych do mapy aktualnie wybranej w combo box i całkowitego zastapienia wszystkich istniejacyj w mapie pól 3- wyswietlenia tekstu w polu tekstowym textAreaDisplay
	 */
	@FXML
	public void actoinOnClickReaplace(ActionEvent event) {
		model.setListOfGivenNumbers(customMethods.separateOnlyInts(textFieldGivenText.getText(), model.getNumbersFrom(), model.getNumbersTo(), model.getListOfGivenNumbers()));
		customMethods.clearSelectedHashMap(comboBoxMaps.getValue(), model.getListOfMaps(), model.getAvailableComboBoxOptions());
		customMethods.findDividersToHashMap(model.getListOfGivenNumbers().toString(), comboBoxMaps.getValue(), model.getListOfMaps(), model.getAvailableComboBoxOptions());
		textAreaDisplay.setText(customMethods.displayTextMethod(model.getListOfMaps()));

	}

	

	/*
	 * metoda wywołana po kliknięciu przycisku "buttonSpecific" metoda odpowiada za wyspierlenie dzielników dla liczb w określonym zakresie 
	 * 1 -pobranie wartości określonych w spinerach i wyswietlenie komunikatu jeśli zakres jest niezgodny
	 * 2- uzywajac petli foreach przeszukuje wybraną mapę w combo box w poszukiwaniu liczb z podanego zakresu 
	 * 3- wyswietlenia tekstu w polu tekstowym textAreaDisplay
	 */
	@FXML
	void actionOnClickDisplaySpecificKey(ActionEvent event) {

		if (spinnerFrom.getValue() > spinnerTo.getValue()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Value from box [to:] must be biger than from box [from:] \nPlease change values");
			alert.setTitle("Wrong value!");
			alert.showAndWait();
		} else {
			textAreaDisplay.clear();
			textAreaDisplay.appendText("\n" + comboBoxMaps.getValue());
			textAreaDisplay.appendText(" for chosen map key value dividers are:");
			
			//choseHashMap(comboBoxMaps.getValue()).entrySet())
			for (Map.Entry<String, List<Integer>> entry : customMethods.choseHashMap(comboBoxMaps.getValue(), model.getListOfMaps(), model.getAvailableComboBoxOptions()).entrySet()) {
				if (Integer.parseInt(entry.getKey()) >= spinnerFrom.getValue()
						&& Integer.parseInt(entry.getKey()) <= spinnerTo.getValue()) {

					textAreaDisplay.appendText("\nFor Key: " + entry.getKey() + " dividers are: ");

					for (int dividersNo : entry.getValue()) {
						textAreaDisplay.appendText(dividersNo + " ");
						// System.out.print(dividersNo+" ");
					}
					System.out.println();
				}
			}

		}

	}

	
	/*
	 * metoda wywołana po kliknięciu przycisku "buttonClearAll" 
	 * 1 - metoda wykorzystuje inne metody do usunięcia danych z WSZYSTKICH map znajdujących się na liscie combobox
	 */
	@FXML
	void actoinOnClickClearAll(ActionEvent event) {

		for(int i =0 ; i<= model.getAvailableComboBoxOptions().size()-1 ; i++) {
			customMethods.choseHashMap(model.getAvailableComboBoxOptions().get(i), model.getListOfMaps(), model.getAvailableComboBoxOptions()).clear();
			
		}
		
		textAreaDisplay.setText(customMethods.displayTextMethod(model.getListOfMaps()));
	}

	
	/*
	 * metoda wywołana po kliknięciu przycisku "buttonClear" 
	 * 1 - metoda wykorzystuje inne metody do usunięcia danych z obecnie wybranej map w combobox
	 */
	@FXML
	public void actoinOnClickClear(ActionEvent event) {

		customMethods.clearSelectedHashMap(comboBoxMaps.getValue(),model.getListOfMaps(), model.getAvailableComboBoxOptions());
		textAreaDisplay.setText(customMethods.displayTextMethod(model.getListOfMaps()));

	}


}