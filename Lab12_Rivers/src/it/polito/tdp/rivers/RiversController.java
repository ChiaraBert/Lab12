package it.polito.tdp.rivers;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Simulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {

	private Model m;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;
    
    private double flussoMedio;
    LinkedList<Flow> flussi = new LinkedList<Flow>();
        
    
    void setModel(Model m){
    	this.m=m;
    	boxRiver.getItems().addAll(m.getFiumi());
    }

    @FXML
    void doDati(ActionEvent event) {
    	flussoMedio=0;
    	flussi.clear();
    	double somma =0;
    	flussoMedio=0;
    	txtStartDate.clear();
    	txtEndDate.clear();
    	txtNumMeasurements.clear();
    	txtFMed.clear();
    	
    	River r = boxRiver.getValue();
    	if(r==null){
    		System.out.println("Selezionare un fiume");
    		return;
    	}
    	    	
    	
    	flussi=m.getDati(r);
    	
    	for(Flow f: flussi){
    		somma+=f.getFlow();
    	}
    	
    	flussoMedio=somma/flussi.size();
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
    	 
    	
    	txtStartDate.appendText(""+flussi.getFirst().getDay().format(formatter));
    	txtEndDate.appendText(""+flussi.getLast().getDay().format(formatter));
    	txtNumMeasurements.appendText(""+flussi.size());
    	txtFMed.appendText(String.format("%.2f", flussoMedio));
    }

    @FXML
    void doSimula(ActionEvent event) {
    	double k=0;
    	txtResult.clear();
    	try{
    	k=Double.parseDouble(txtK.getText());
    	}catch(Exception e){
    		txtResult.appendText("Inserire un valore di k");
    		return;
    	}
    	
    	    	
    	Simulator sim= new Simulator(k,flussoMedio);
    	sim.setFlusso(flussi);
    	sim.aggiungiEvento();
    	sim.run();
    	
    	txtResult.appendText("Numero di giorni in cui non si è potuta garantire l'erogazione minima "+sim.getNumeroGiorni());
    	txtResult.appendText("\nl’occupazione media del bacino nel corso della simulazione è "+sim.getCmedia());
    	
    	
    	

    }
    
    public static double arrotonda(double value, int numCifreDecimali) {
        double temp = Math.pow(10, numCifreDecimali);
        return Math.round(value * temp) / temp; 
     }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }
}

