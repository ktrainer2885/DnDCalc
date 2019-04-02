package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Commands;
import common.Creature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import monster.Monster;
import player.*;
import simulation.Sim;
import javafx.scene.image.Image;
import javafx.scene.ImageCursor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeController {

    public static Scene ISController;
    public static Stage stage;
    public static Integer item;

    private Monster[] monsters;
    private ObjectMapper mapper;

    ArrayList<Fighter> fighters;
    ArrayList<Rogue> rogues;
    ArrayList<Cleric> clerics;
    ArrayList<Wizard> wizards;
    Integer numberPlayers;
    Integer numberLevels;
    Integer numberMonsters;
    Integer numberSimulations = 99999;

    ArrayList<Creature> combatArrayList = new ArrayList<>();

    ObservableList<String> playerList = FXCollections.observableArrayList("Fighter", "Rogue", "Wizard", "Cleric");
    ArrayList<Monster> monsterArrayList = new ArrayList<>();
    ObservableList<Monster> monsterList =  FXCollections.observableArrayList(monsterArrayList);

    //Attributes associated with Player
    @FXML
    private ChoiceBox playerBox;
    @FXML
    private TextField playerNum;
    @FXML
    private TextField playerLvl;
    public static String playerWeap; //Weapon
    public static String playerWeapMagMod; //Magic Modifier Associated with Weapon
    public static String playerArm; //Armor
    public static String playerArmMagMod; //Magic Modifier Associated with Armor
    public static String playerAmmo; //Ammunition
    public static String playerAmmoCount; //Ammunition Qty

    //Attributes associated with Player 1
    @FXML
    private ChoiceBox player1Box;
    @FXML
    private TextField player1Num;
    @FXML
    private TextField player1Lvl;
    public static String player1Weap;
    public static String player1WeapMagMod;
    public static String player1Arm;
    public static String player1ArmMagMod;
    public static String player1Ammo; //Ammunition
    public static String player1AmmoCount; //Ammunition Qty

    //Attributes associated with Player 2
    @FXML
    private ChoiceBox player2Box;
    @FXML
    private TextField player2Num;
    @FXML
    private TextField player2Lvl;
    public static String player2Weap;
    public static String player2WeapMagMod;
    public static String player2Arm;
    public static String player2ArmMagMod;
    public static String player2Ammo; //Ammunition
    public static String player2AmmoCount; //Ammunition Qty

    //Attributes associated with Player 3
    @FXML
    private ChoiceBox player3Box;
    @FXML
    private TextField player3Num;
    @FXML
    private TextField player3Lvl;
    public static String player3Weap;
    public static String player3WeapMagMod;
    public static String player3Arm;
    public static String player3ArmMagMod;
    public static String player3Ammo; //Ammunition
    public static String player3AmmoCount; //Ammunition Qty

    @FXML
    private ChoiceBox monsterBox;
    @FXML
    private TextField monsterNum;

    @FXML
    private ChoiceBox monster1Box;
    @FXML
    private TextField monster1Num;

    @FXML
    private ChoiceBox monster2Box;
    @FXML
    private TextField monster2Num;

    @FXML
    private ChoiceBox monster3Box;
    @FXML
    private TextField monster3Num;

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

        Sim sim = new Sim(numberSimulations,combatArrayList);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Simulation");

        // Header Text: null
        alert.setHeaderText(null);
 //       alert.setContentText(playerBox.getValue().toString());
        alert.setContentText("\nWin Rate: " + String.format("%.2f", sim.simulationWinRate()) +"%");
 //       alert.setContentText(combatArrayList.toString());

        alert.showAndWait();
    }

    public void fightButtonClicked(){

//        numberSimulations = Integer.parseInt(numSims.getText());
        combatArrayList.clear();
        addPlayer(playerBox,playerNum,playerLvl);
        addPlayer(player1Box,player1Num,player1Lvl);
        addPlayer(player2Box,player2Num,player2Lvl);
        addPlayer(player3Box,player3Num,player3Lvl);
        addMonster(monsterBox, monsterNum);
        addMonster(monster1Box, monster1Num);
        addMonster(monster2Box, monster2Num);
        addMonster(monster3Box, monster3Num);
        //System.out.println("Dance Monkeys!");
        //System.out.println(playerNum.getText());
        //System.out.println(playerBox.getValue());
        showAlertWithoutHeaderText();
    }

    public void itemButtonClicked() {
        try {
            item = 0;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemSelect.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 245, 302);
            Image image = new Image("file:///../img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Items");
            stage.setScene(ISController);
            stage.show();

        }catch (Exception e){
            System.out.println("Can't load new window!");
        }
    }
    public void itemButton1Clicked(){
        try {
            item = 1;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemSelect.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 245, 302);
            Image image = new Image("file:///../img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Items");
            stage.setScene(ISController);
            stage.show();

        }catch (Exception e){
            System.out.println("Can't load new window!");
        }
    }

    public void itemButton2Clicked(){
        try {
            item = 2;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemSelect.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 245, 302);
            Image image = new Image("file:///../img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Items");
            stage.setScene(ISController);
            stage.show();

        }catch (Exception e){
            System.out.println("Can't load new window!");
        }
    }

    public void itemButton3Clicked(){
        try {
            item = 3;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemSelect.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 245, 302);
            Image image = new Image("file:///../img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Items");
            stage.setScene(ISController);
            stage.show();

        }catch (Exception e){
            System.out.println("Can't load new window!");
        }
    }

    //set functions for player take from ItemSelectController
    public void setItemA(String itemA){
        playerWeap = itemA;
    }

    public void setItemB(String itemB){
        playerArm = itemB;
    }

    public void setItemC(String textA){
        playerAmmo = textA;
    }

    public void setTextA(String textA){
        playerWeapMagMod = textA;
    }

    public void setTextB(String textB){
        playerArmMagMod = textB;
    }

    public void setTextC(String textB){
        playerAmmoCount = textB;
    }

    //set functions for player 1 take from ItemSelectController
    public void setItem1A(String itemA){
        player1Weap = itemA;
    }

    public void setItem1B(String itemB){
        player1Arm = itemB;
    }

    public void setItem1C(String textA){
        player1Ammo = textA;
    }

    public void setText1A(String textA){
        player1WeapMagMod = textA;
    }

    public void setText1B(String textB){
        player1ArmMagMod = textB;
    }

    public void setText1C(String textB){
        player1AmmoCount = textB;
    }

    //set functions for player 2 take from ItemSelectController
    public void setItem2A(String itemA){
        player2Weap = itemA;
    }

    public void setItem2B(String itemB){
        player2Arm = itemB;
    }

    public void setItem2C(String textA){
        player2Ammo = textA;
    }

    public void setText2A(String textA){
        player2WeapMagMod = textA;
    }

    public void setText2B(String textB){
        player2ArmMagMod = textB;
    }

    public void setText2C(String textB){
        player2AmmoCount = textB;
    }

    //set functions for player 3 take from ItemSelectController
    public void setItem3A(String itemA){
        player3Weap = itemA;
    }

    public void setItem3B(String itemB){
        player3Arm = itemB;
    }

    public void setItem3C(String textA){
        player3Ammo = textA;
    }

    public void setText3A(String textA){
        player3WeapMagMod = textA;
    }

    public void setText3B(String textB){
        player3ArmMagMod = textB;
    }

    public void setText3C(String textB){
        player3AmmoCount = textB;
    }


    public void getMonsterList(){
        try{
            mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            File files = new File("files\\monsters\\jsonBigTest.json");
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

    public void addPlayer(ChoiceBox playerBox, TextField playerNum, TextField playerLvl){

        if (!checkPlayerBoxValid(playerBox, playerNum, playerLvl)) {
            return;
        }
        String type = playerBox.getValue().toString();
        numberPlayers = Integer.parseInt(playerNum.getText());
        numberLevels = Integer.parseInt(playerLvl.getText());

        if (type.equalsIgnoreCase("fighter")){
            addPlayerToCombatList(type, numberPlayers, numberLevels);
        }
        if (type.equalsIgnoreCase("rogue")){
            addPlayerToCombatList(type, numberPlayers, numberLevels);
        }
        if (type.equalsIgnoreCase("wizard")){
            addPlayerToCombatList(type, numberPlayers, numberLevels);
        }
        if (type.equalsIgnoreCase("cleric")){
            addPlayerToCombatList(type, numberPlayers, numberLevels);
        }
    }
    public boolean checkPlayerBoxValid(ChoiceBox playerBox, TextField playerNum, TextField playerLvl){
        if (playerBox.getValue() == null){
            return false;
        }
        if (playerNum.getText() == null || !Commands.isNumeric(playerNum.getText())){
            return false;
        }
        if (playerLvl.getText() == null || !Commands.isNumeric(playerLvl.getText())){
            return false;
        }
        else {
            return true;
        }
    }

    public void addMonster(ChoiceBox monsterBox, TextField monsterNum){
        if (!checkMonsterBoxValid(monsterBox, monsterNum)) {
            return;
        }

        Monster monster = (Monster) monsterBox.getValue();
        numberMonsters = Integer.parseInt(monsterNum.getText());

        for (int i = 0; i < numberMonsters; i++) {
            combatArrayList.add(new Monster(monster));
        }
    }


    public boolean checkMonsterBoxValid(ChoiceBox monsterBox, TextField monsterNum){
        if (monsterBox.getValue() == null){
            return false;
        }
        if (monsterNum.getText() == null || !Commands.isNumeric(monsterNum.getText())){
            return false;
        }
        else {
            return true;
        }
    }
    public void addPlayerToCombatList(String type, Integer numberPlayers, Integer numberLevels){
        Player player = new Fighter();

        for (int i = 0; i < numberPlayers; i++){

            if(type.equalsIgnoreCase("fighter")){
                player = new Fighter();
            }
            if(type.equalsIgnoreCase("rogue")){
                player = new Rogue();
            }
            if(type.equalsIgnoreCase("cleric")){
                player = new Cleric();
            }
            if(type.equalsIgnoreCase("wizard")){
                player = new Wizard();
            }

            for (int j = 1; j < numberLevels; j++) { // int j = 1 because chars start at level 1
                player.levelUp();
            }
            combatArrayList.add(player);
        }
    }

    static public int[] selectClass(String c) {
        int[] classArray;
        int size;
        int level;

        Scanner reader = new Scanner(System.in);

        /* Character Selection */
        System.out.print("Please type in number of " + c + "s in your party: ");
        size = reader.nextInt();
        classArray = new int[size];

        /* Character Level Selection */
        for (int x = 0; x < classArray.length; x++) {
            do {
                System.out.print(c + ' ' + (x + 1) + " Level: ");
                level = reader.nextInt();

                if (level > 0 || level < 21) {
                    classArray[x] = level;
                } else {
                    System.out.println("Invalid Level, Try Again");
                }
            } while (level < 1 || level > 20);
        }
        // Return array
        return classArray;
    }

}
