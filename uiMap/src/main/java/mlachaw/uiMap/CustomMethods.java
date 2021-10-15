package mlachaw.uiMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class CustomMethods {
	
	
	
	/*
	 * metoda odpowiadajaca za wybranie z tekstu samych wartosci liczbowych
	 * 
	 */
	
	public ArrayList<String> separateOnlyInts(String textToValidate, int numberFrom, int numberTo, ArrayList<String> listToStoreValidatedText) {

		/*
		 * usuwamy z tekstu wszystkie znaki poza liczbami i zamieniamy je na spacje dodajemy 
		 * "1 " na poczatek tekstu aby uniknac sytuacji ze w tekscie nie ma ani jednej liczby
		 */

		String numberOnly = textToValidate.replaceAll("[^0-9]", " ");
		String noNumbers = "1 " + numberOnly;

		/*
		 * tworzymy liste String-ów zawierajaca same liczby uzywajac pozostawionych
		 * spacji jako dzielników a nastepnie jesli liczby spełniają warunki zakresu są dodawane do listy
		*/
		String[] strArr = noNumbers.split("\\s+");
		listToStoreValidatedText.clear();
		for (String e : strArr) {
			if (Integer.parseInt(e) >= numberFrom && Integer.parseInt(e) <= numberTo) {
				//listOfGivenNumbers.add(e);
				
				listToStoreValidatedText.add(e);
			}

		}
		/*
		 * jesli w liscie w liscie znajduja sie elementy usowamy nasze dodane "1 "
		 */
		if (listToStoreValidatedText.size() > 1) {
			listToStoreValidatedText.remove(0);
		}
		
		return listToStoreValidatedText;

	}
	

	
	/*
	 * metoda odpowiadajaca za wypełnianie konkretnej mapy
	 */
	
	
	
	
	
	/*
	 * metoda odpowiadajaca za stworzenie listy kluczy dla ktorych bedą policzone wybrane dzielniki
	 * 1-metoda przyjmuje stringa i przypisuje do tablicy Stringów dzieląc ge na części dodatkowe parametry potrzebne ze wzgledu na to ze metoda populateHashMap kozysta z metody choseHashMap
	 * 2-petla for each zmienia posortowanego stringa na inty i dodaje je do listy uzywajac metody addtDivisorsToHashMapKey ktora zwraca listę
	 * 3-uzywa metody populateHashMap zeby wyslac zebrane dane do konkretnej mapy
	 */
	public void findDividersToHashMap(String stringWithNumbersToCalculate, String hashMapName , List<Map<String, List<Integer>>> listOfMaps, ObservableList<String> comboBoxOptions) {

		String s = stringWithNumbersToCalculate.substring(1, stringWithNumbersToCalculate.length() - 1);
		String[] strArr = s.split(", ");
		for (String e : strArr) {
			int n = Integer.parseInt(e);

			List<Integer> dividersList = addtDivisorsToHashMapKey(n);

			populateHashMap(e, dividersList, hashMapName ,listOfMaps,comboBoxOptions);

		}

	}
//	
//	
//	
//	
//	/*
//	 * metoda odpowiadajaca za stworzenie listy kluczy dla ktorych bedą policzone wybrane dzielniki
//	 * 1-metoda przyjmuje stringa i przypisuje do tablicy Stringów dzieląc ge na części dodatkowe parametry potrzebne ze wzgledu na to ze metoda populateHashMap kozysta z metody choseHashMap
//	 * 2-petla for each zmienia posortowanego stringa na inty i dodaje je do listy uzywajac metody addtDivisorsToHashMapKey ktora zwraca listę
//	 * 3-uzywa metody populateHashMap zeby wyslac zebrane dane do WSZYSTKICH map
//	 */
	public void findDividersToAllHashMap(String stringWithNumbersToCalculate, String hashMapName , List<Map<String, List<Integer>>> listOfMaps, ObservableList<String> comboBoxOptions) {

		String s = stringWithNumbersToCalculate.substring(1, stringWithNumbersToCalculate.length() - 1);
		String[] strArr = s.split(", ");
		for (String e : strArr) {
			int n = Integer.parseInt(e);

			List<Integer> dividersList = addtDivisorsToHashMapKey(n);

			for(int i = 0 ;i<= comboBoxOptions.size()-1 ; i++) {
				
				populateHashMap(e, dividersList, comboBoxOptions.get(i), listOfMaps, comboBoxOptions);

				
			}

		}

	}
//	
//	
//	
//	
//	/*
//	 * metoda odpowiadajaca sprawdzenie czy dana liczba dzieli się bez reszy przez siebie
//	 * 1-metoda przyjmuje intigera i strawdza przez jakie dzielniki dzieli się bez reszty kożystająć z dzielenia modulo
//	 * 2-metoda zwraca zebrane dzielniki jako listę
//	 */
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
//
//	
//	
//	/*
//	 * metoda odpowiadajaca za wybranie którą mapę należy wyczyścić
//	 * 1-metoda wykorzystuje metodę choseHashMap do wybrania mapy która jest obecnie wybrana i wykonuje na niej metodę .clear();
//	 * 	 */
	public void clearSelectedHashMap(String hashMapName , List<Map<String, List<Integer>>> listOfMaps, ObservableList<String> comboBoxOptions) {

		choseHashMap(hashMapName, listOfMaps, comboBoxOptions).clear();

	}
//
//	/*
//	 * Metoda odpowiadajaca za to w jakim formacie wyświetlany jest tekst
//	 * 	 */
	public String displayTextMethod(List<Map<String, List<Integer>>> listOfMaps) {

		String newline = System.lineSeparator();
		
		
		String textToDisplay = "Hash Map: " + listOfMaps.get(0) + newline + "Tree Map: " + listOfMaps.get(1) + newline + "Hash Table: "
				+ listOfMaps.get(2) + newline + "Linked Hash Map: " + listOfMaps.get(3);

		return textToDisplay;
	}
//	
//	
//	
//	
//	
//	
//	/*
//	 * metoda odpowiadajaca za wybranie mapy któa jest aktualnie wybrana w comboboxie
//	 * na podstawie operacji if wybiera z observableList która mapa ma być wybrana
//	 * 	 */
	
	
	private void populateHashMap(String key, List<Integer> value, String hashMapName , List<Map<String, List<Integer>>> listOfMaps, ObservableList<String> comboBoxOptions) {
		choseHashMap(hashMapName, listOfMaps, comboBoxOptions).put(key,value);

	}
	
	
	
	public Map<String, List<Integer>> choseHashMap (String hashMapName , List<Map<String, List<Integer>>> listOfMaps, ObservableList<String> comboBoxOptions) {

		
		if (hashMapName.equals(comboBoxOptions.get(0))) {

			return listOfMaps.get(0);
		} else if (hashMapName.equals(comboBoxOptions.get(1))) {

			return listOfMaps.get(1);
		} else if (hashMapName.equals(comboBoxOptions.get(2))) {

			return listOfMaps.get(2);
		} else if (hashMapName.equals(comboBoxOptions.get(3))) {

			return listOfMaps.get(3);
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
//	
//	
//	
//	
//	
//	

}
