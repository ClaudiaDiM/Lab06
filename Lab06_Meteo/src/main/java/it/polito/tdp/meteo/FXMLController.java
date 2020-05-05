package it.polito.tdp.meteo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Integer> boxMese;

    @FXML
    private Button btnUmidita;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaSequenza(ActionEvent event) {
    	
    	Integer m = boxMese.getValue();
    	
    	if(m!=null) {
    		List<Citta> best = model.trovaSequenza(m);
    		
    		txtResult.appendText(String.format("Sequenza ottima per il mese di %s", Integer.toString(m)));
    		txtResult.appendText(best +"\n");
    	}

    }

    @FXML
    void doCalcolaUmidita(ActionEvent event) {
    	txtResult.clear();
    	
    	Integer m = boxMese.getValue();
    	
    	if(m!=null) {
    		txtResult.appendText(String.format("Dati del mese %s\n",Integer.toString(m)));
    		
    		for(Citta c : model.getLeCitta()) {
    			Double u = model.getUmiditaMedia(m,c);
    			txtResult.appendText(String.format("Città %s: umidità %f\n", c.getNome(), u));
    		}
    	}
    	
    	

    }
    
    public void setChoiseMese() {
    	for(int mese=1; mese<=12; mese++) {
    		boxMese.getItems().add(mese);
    	}
    }
    
    public void setModel(Model m) {
    	this.model = m;
    }

    @FXML
    void initialize() {
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}


