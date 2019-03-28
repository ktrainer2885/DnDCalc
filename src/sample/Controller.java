package sample;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import monster.Monster;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {


    private Monster[] monsters;
    private ObjectMapper mapper;


    ObservableList<String> playerList = FXCollections.observableArrayList("Fighter", "Rogue", "Wizard", "Cleric");
    ArrayList<Monster> monsterArrayList = new ArrayList<>();
    ObservableList<Monster> monsterList =  FXCollections.observableArrayList(monsterArrayList);

    @FXML
    private TextField numSims;

    @FXML
    private ChoiceBox playerBox;
    @FXML
    private TextField playerNum;
    @FXML
    private TextField playerLvl;

    @FXML
    private ChoiceBox player1Box;
    @FXML
    private TextField player1Num;
    @FXML
    private TextField player1Lvl;

    @FXML
    private ChoiceBox player2Box;
    @FXML
    private TextField player2Num;
    @FXML
    private TextField player2Lvl;

    @FXML
    private ChoiceBox player3Box;
    @FXML
    private TextField player3Num;
    @FXML
    private TextField player3Lvl;

    @FXML
    private ChoiceBox monsterBox;
    @FXML
    private TextField monsterNum;
    @FXML
    private TextField monsterLvl;

    @FXML
    private ChoiceBox monster1Box;
    @FXML
    private TextField monster1Num;
    @FXML
    private TextField monster1Lvl;

    @FXML
    private ChoiceBox monster2Box;
    @FXML
    private TextField monster2Num;
    @FXML
    private TextField monster2Lvl;

    @FXML
    private ChoiceBox monster3Box;
    @FXML
    private TextField monster3Num;
    @FXML
    private TextField monster3Lvl;

    @FXML
    private void initialize(){
        getMonsterList();


        playerBox.setItems(playerList);
        player1Box.setItems(playerList);
        player2Box.setItems(playerList);
        player3Box.setItems(playerList);
        monsterBox.setItems(monsterList);
        monster1Box.setItems(monsterList);
        monster2Box.setItems(monsterList);
        monster3Box.setItems(monsterList);

    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Simulation");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Sims: " + numSims.getText());

        alert.showAndWait();
    }

    public void loginButtonClicked(){
        //System.out.println("Dance Monkeys!");
        //System.out.println(playerNum.getText());
        //System.out.println(playerBox.getValue());
        showAlertWithoutHeaderText();
    }

    public void removeFromMonsterList(){

    }

    public void removeFromPlayerList(){

    }

    public void getMonsterList(){
        try{
            mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            File files = new File("D:\\Workspace\\Java\\DnDCalc\\out\\production\\DnDCalc\\files\\monsters\\jsonBigTest.json");
            System.out.println(files.getCanonicalPath());
            monsters = mapper.readValue(files, Monster[].class);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        for (Monster m: monsters
             ) {
            monsterArrayList.add(m);
        }
        monsterList =  FXCollections.observableArrayList(monsterArrayList);

    }

}
