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
import javafx.scene.control.SpinnerValueFactory;

public class Model {
	
	
	
	private ArrayList<String> listOfGivenNumbers ;
	private ObservableList<String> availableComboBoxOptions;
	
	private HashMap<String, List<Integer>> hashMap;
	private TreeMap<String, List<Integer>> treeMap;
	private Hashtable<String, List<Integer>> hashTable;
	private LinkedHashMap<String, List<Integer>> linkedHashMap;
	private List<Map<String, List<Integer>>> listOfMaps;
	
	public List<Map<String, List<Integer>>> getListOfMaps() {
		return listOfMaps;
	}

	public void setListOfMaps(List<Map<String, List<Integer>>> listOfMaps) {
		this.listOfMaps = listOfMaps;
	}

	private int numbersFrom;
	private int numbersTo;
	private SpinnerValueFactory<Integer> svfFrom = new SpinnerValueFactory.IntegerSpinnerValueFactory(1	, 20, 1);
	private SpinnerValueFactory<Integer> svfTo = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 20);
	
	
	
	
	public Model() {
		this.listOfGivenNumbers = new ArrayList<String>();
		this.hashMap = new HashMap<>();
		this.treeMap = new TreeMap<>();
		this.hashTable = new Hashtable<>();
		this.linkedHashMap = new LinkedHashMap<>();
		this.availableComboBoxOptions = FXCollections.observableArrayList("HashMap", "TreeMap", "Hashtable", "LinkedHashMap");
		this.numbersFrom = 1;
		this.numbersTo = 20;
		this.svfFrom = new SpinnerValueFactory.IntegerSpinnerValueFactory(getNumbersFrom()	, getNumbersTo(), 1);
		this.svfTo = new SpinnerValueFactory.IntegerSpinnerValueFactory(getNumbersFrom(), getNumbersTo(), 20);
		this.listOfMaps = new ArrayList<Map<String,List<Integer>>>();
		this.listOfMaps.add(hashMap);
		this.listOfMaps.add(treeMap);
		this.listOfMaps.add(hashTable);
		this.listOfMaps.add(linkedHashMap);
	}
	
	public int getNumbersFrom() {
		return numbersFrom;
	}

	public void setNumbersFrom(int numbersFrom) {
		this.numbersFrom = numbersFrom;
	}

	public int getNumbersTo() {
		return numbersTo;
	}

	public void setNumbersTo(int numbersTo) {
		this.numbersTo = numbersTo;
	}

	public SpinnerValueFactory<Integer> getSvfFrom() {
		return svfFrom;
	}

	public void setSvfFrom(SpinnerValueFactory<Integer> svfFrom) {
		this.svfFrom = svfFrom;
	}

	public SpinnerValueFactory<Integer> getSvfTo() {
		return svfTo;
	}

	public void setSvfTo(SpinnerValueFactory<Integer> svfTo) {
		this.svfTo = svfTo;
	}

	public ObservableList<String> getAvailableComboBoxOptions() {
		return availableComboBoxOptions;
	}
	
	public ArrayList<String> getListOfGivenNumbers() {
		return listOfGivenNumbers;
	}
	
	public HashMap<String, List<Integer>> getHashMap() {
		return hashMap;
	}
	
	public TreeMap<String, List<Integer>> getTreeMap() {
		return treeMap;
	}
	
	public Hashtable<String, List<Integer>> getHashTable() {
		return hashTable;
	}
	
	public LinkedHashMap<String, List<Integer>> getLinkedHashMap() {
		return linkedHashMap;
	}

	public void setListOfGivenNumbers(ArrayList<String> listOfGivenNumbers) {
		this.listOfGivenNumbers = listOfGivenNumbers;
	
	}

	public void setHashMap(HashMap<String, List<Integer>> hashMap) {
		this.hashMap = hashMap;
	}

	public void setTreeMap(TreeMap<String, List<Integer>> treeMap) {
		this.treeMap = treeMap;
	}

	public void setHashTable(Hashtable<String, List<Integer>> hashTable) {
		this.hashTable = hashTable;
	}

	public void setLinkedHashMap(LinkedHashMap<String, List<Integer>> linkedHashMap) {
		this.linkedHashMap = linkedHashMap;
	}

	public void setAvailableComboBoxOptions(ObservableList<String> availableComboBoxOptions) {
		this.availableComboBoxOptions = availableComboBoxOptions;
	}
	

}
