package mlachaw.uiMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Light.Spot;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainStageController {

	ArrayList<String> listOfGivenNumbers = new ArrayList<>();
	HashMap<String, List<Integer>> hashMap = new HashMap<>();
	TreeMap<String, List<Integer>> treeMap = new TreeMap<>();
	Hashtable<String, List<Integer>> hashTable = new Hashtable<>();
	LinkedHashMap<String, List<Integer>> linkedHashMap = new LinkedHashMap<>();
	
	private int numbersFrom;
	private int numbersTo;
	private SpinnerValueFactory<Integer> svfFrom = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
	private SpinnerValueFactory<Integer> svfTo = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 20);
	
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
		 * dla jakiego bedzie operowa?? spinner
		 */
		comboBoxMaps.setValue(mapOptions.get(0));
		comboBoxMaps.setItems(mapOptions);
		spinnerFrom.setValueFactory(svfFrom);
		spinnerTo.setValueFactory(svfTo);

		/*
		 * Liczby dla kt??rych ustalony jest zakres przyjmowania danych
		*/
		numbersFrom = 1;
		numbersTo = 20;

	}

	/* ObservableList dla ComboBox */
	ObservableList<String> mapOptions = FXCollections.observableArrayList("HashMap", "TreeMap", "Hashtable",
			"LinkedHashMap");

	/*************** *************** ***************/
	/*************** Metody wywo??ane po klikni??ciu ***************/
	/*************** *************** ***************/

	/*
	 * metoda wywo??ana po klikni??ciu przycisku "buttonAdd" wykorzystuje metody do: 
	 * 1-sformatowania podanego tekstu 
	 * 2- dodania sformatowanych danych do mapy aktualnie wybranej w combo box 
	 * 3- wyswietlenia tekstu w polu tekstowym textAreaDisplay
	 */
	public void actionOnClickAdd(ActionEvent event) {
		separateOnlyInts(textFieldGivenText.getText());
		findDividersToHashMap(textAreaGivenNumbers.getText());
		textAreaDisplay.setText(displayTextMethod());
		
	}

	/*
	 * metoda wywo??ana po klikni??ciu przycisku "buttonReaplace" wykorzystuje metody do: 
	 * 1 -sformatowania podanego tekstu 
	 * 2- dodania sformatowanych danych do mapy aktualnie wybranej w combo box i ca??kowitego zastapienia wszystkich istniejacyj w mapie p??l 3- wyswietlenia tekstu w polu tekstowym textAreaDisplay
	 */
	@FXML
	public void actoinOnClickReaplace(ActionEvent event) {
		separateOnlyInts(textFieldGivenText.getText());
		clearHashMap(comboBoxMaps.getValue());
		findDividersToHashMap(textAreaGivenNumbers.getText());
		textAreaDisplay.setText(displayTextMethod());

	}

	/*
	 * metoda wywo??ana po klikni??ciu przycisku "buttonAddAll" wykorzystuje metody do: 
	 * 1 -sformatowania podanego tekstu
	 * 2- dodania sformatowanych danych do wszystkich map istniejacych w combobox 
	 * 3- wyswietlenia tekstu w polu tekstowym textAreaDisplay
	 */
	@FXML
	void actionOnClickAddAll(ActionEvent event) {

		separateOnlyInts(textFieldGivenText.getText());
		findDividersToAllHashMap(textAreaGivenNumbers.getText());
		textAreaDisplay.setText(displayTextMethod());

	}

	/*
	 * metoda wywo??ana po klikni??ciu przycisku "buttonSpecific" metoda odpowiada za wyspierlenie dzielnik??w dla liczb w okre??lonym zakresie 
	 * 1 -pobranie warto??ci okre??lonych w spinerach i wyswietlenie komunikatu je??li zakres jest niezgodny
	 * 2- uzywajac petli foreach przeszukuje wybran?? map?? w combo box w poszukiwaniu liczb z podanego zakresu 
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
			for (Map.Entry<String, List<Integer>> entry : choseHashMap(comboBoxMaps.getValue()).entrySet()) {
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
	 * metoda wywo??ana po klikni??ciu przycisku "buttonClearAll" 
	 * 1 - metoda wykorzystuje inne metody do usuni??cia danych z WSZYSTKICH map znajduj??cych si?? na liscie combobox
	 */
	@FXML
	void actoinOnClickClearAll(ActionEvent event) {

		choseHashMap(mapOptions.get(0)).clear();
		choseHashMap(mapOptions.get(1)).clear();
		choseHashMap(mapOptions.get(2)).clear();
		choseHashMap(mapOptions.get(3)).clear();
		textAreaDisplay.setText(displayTextMethod());

	}

	/*
	 * metoda wywo??ana po klikni??ciu przycisku "buttonClear" 
	 * 1 - metoda wykorzystuje inne metody do usuni??cia danych z obecnie wybranej map w combobox
	 */
	@FXML
	public void actoinOnClickClear(ActionEvent event) {

		clearHashMap(comboBoxMaps.getValue());
		textAreaDisplay.setText(displayTextMethod());

	}

	/*************** *************** ***************/
	/*************** Metody wywo??ywane przez inne metody ***************/
	/*************** *************** ***************/

	public void separateOnlyInts(String textToValidate) {

		/*
		 * usuwamy z tekstu wszystkie znaki poza liczbami i zamieniamy je na spacje dodajemy 
		 * "1 " na poczatek tekstu aby uniknac sytuacji ze w tekscie nie ma ani jednej liczby
		 */

		String numberOnly = textToValidate.replaceAll("[^0-9]", " ");
		String zeroStartNumbersOnly = "1 " + numberOnly;

		/*
		 * tworzymy liste String-??w zawierajaca same liczby uzywajac pozostawionych
		 * spacji jako dzielnik??w a nastepnie jesli liczby spe??niaj?? warunki zakresu s?? dodawane do listy
		*/
		String[] strArr = zeroStartNumbersOnly.split("\\s+");
		for (String e : strArr) {
			if (Integer.parseInt(e) >= numbersFrom && Integer.parseInt(e) <= numbersTo) {
				listOfGivenNumbers.add(e);
			}

		}
		/*
		 * jesli w liscie w liscie znajduja sie elementy usowamy nasze dodane "1 "
		 */
		if (listOfGivenNumbers.size() > 1) {
			listOfGivenNumbers.remove(0);
		}

		textAreaGivenNumbers.setText(listOfGivenNumbers.toString());
		listOfGivenNumbers.clear();

	}

	/*
	 * metoda odpowiadajaca za wype??nianie konkretnej mapy
	 */
	private void populateHashMap(String e, List<Integer> n, String choseHashMap) {
		choseHashMap(choseHashMap).put(e, n);

	}
	
	
	/*
	 * metoda odpowiadajaca za stworzenie listy kluczy dla ktorych bed?? policzone wybrane dzielniki
	 * 1-metoda przyjmuje stringa i przypisuje do tablicy String??w dziel??c ge na cz????ci
	 * 2-petla for each zmienia posortowanego stringa na inty i dodaje je do listy uzywajac metody addtDivisorsToHashMapKey ktora zwraca list??
	 * 3-uzywa metody populateHashMap zeby wyslac zebrane dane do konkretnej mapy
	 */
	public void findDividersToHashMap(String string) {

		String s = string.substring(1, string.length() - 1);
		String[] strArr = s.split(", ");
		for (String e : strArr) {
			int n = Integer.parseInt(e);

			List<Integer> dividersList = addtDivisorsToHashMapKey(n);
//			System.out.println("Dla: " + n);
//			System.out.println(dividersList);

			populateHashMap(e, dividersList, comboBoxMaps.getValue());

		}

	}

	/*
	 * metoda odpowiadajaca za stworzenie listy kluczy dla ktorych bed?? policzone wybrane dzielniki
	 * 1-metoda przyjmuje stringa i przypisuje do tablicy String??w dziel??c ge na cz????ci
	 * 2-petla for each zmienia posortowanego stringa na inty i dodaje je do listy uzywajac metody addtDivisorsToHashMapKey ktora zwraca list??
	 * 3-uzywa metody populateHashMap zeby wyslac zebrane dane do WSZYSTKICH map
	 */
	public void findDividersToAllHashMap(String string) {

		String s = string.substring(1, string.length() - 1);
		String[] strArr = s.split(", ");
		for (String e : strArr) {
			int n = Integer.parseInt(e);

			List<Integer> dividersList = addtDivisorsToHashMapKey(n);

			populateHashMap(e, dividersList, mapOptions.get(0));
			populateHashMap(e, dividersList, mapOptions.get(1));
			populateHashMap(e, dividersList, mapOptions.get(2));
			populateHashMap(e, dividersList, mapOptions.get(3));

		}

	}

	
	/*
	 * metoda odpowiadajaca sprawdzenie czy dana liczba dzieli si?? bez reszy przez siebie
	 * 1-metoda przyjmuje intigera i strawdza przez jakie dzielniki dzieli si?? bez reszty ko??ystaj???? z dzielenia modulo
	 * 2-metoda zwraca zebrane dzielniki jako list??
	 */
	public List<Integer> addtDivisorsToHashMapKey(int n) {
		List<Integer> dividers = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++)
			if (n % i == 0) {
//				choseHashMap(comboBoxMaps.getValue()).put("" + n, i);
//				System.out.println(i);
				dividers.add(i);
			}
		return dividers;
	}

	
	
	/*
	 * metoda odpowiadajaca za wybranie kt??r?? map?? nale??y wyczy??ci??
	 * 1-metoda wykorzystuje metod?? choseHashMap do wybrania mapy kt??ra jest obecnie wybrana i wykonuje na niej metod?? .clear();
	 * 	 */
	public void clearHashMap(String hashMapName) {

		choseHashMap(hashMapName).clear();

	}

	/*
	 * Metoda odpowiadajaca za to w jakim formacie wy??wietlany jest tekst
	 * 	 */
	private String displayTextMethod() {

		String newline = System.lineSeparator();
		String textToDisplay = "Hash Map: " + hashMap + newline + "Tree Map: " + treeMap + newline + "Hash Table: "
				+ hashTable + newline + "Linked Hash Map: " + linkedHashMap;

		return textToDisplay;
	}

	
	/*
	 * metoda odpowiadajaca za wybranie mapy kt??a jest aktualnie wybrana w comboboxie
	 * na podstawie operacji if wybiera z observableList kt??ra mapa ma by?? wybrana
	 * 	 */
	public Map<String, List<Integer>> choseHashMap(String hashMapName) {

		if (hashMapName.equals(mapOptions.get(0))) {

			return hashMap;
		} else if (hashMapName.equals(mapOptions.get(1))) {

			return treeMap;
		} else if (hashMapName.equals(mapOptions.get(2))) {

			return hashTable;
		} else if (hashMapName.equals(mapOptions.get(3))) {

			return linkedHashMap;
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Something went wrong with chosing corect map \nChose difrent map");
			alert.setTitle("Map problem!");
			alert.showAndWait();
		}
		return null;
	}

}