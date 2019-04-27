package gui;

import item.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
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
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class HomeController {

    public static Scene ISController;
    public static Stage stage;
    public static Integer item;
    public static Integer monster;

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

    //fullPath = relativePath + "\\files\\monsters\\jsonBigTest.json";
    //Media sword = new Media("file:///../media.audio/sword.mp3");
    //MediaPlayer mediaPlayer = new MediaPlayer(sword);


    //Attributes associated with Player
    @FXML
    private ChoiceBox playerBox;
    @FXML
    private TextField playerNum;
    @FXML
    private TextField playerLvl;
    @FXML
    private Button playerItems;

    public static Weapon playerWeap; //Weapon
    public static String playerWeapMagMod; //Magic Modifier Associated with Weapon
    public static Armor playerArm; //Armor
    public static String playerArmMagMod; //Magic Modifier Associated with Armor
    public static Ammunition playerAmmo; //Ammunition
    public static String playerAmmoCount; //Ammunition Qty

    //Attributes associated with Player 1
    @FXML
    private ChoiceBox player1Box;
    @FXML
    private TextField player1Num;
    @FXML
    private TextField player1Lvl;
    @FXML
    private Button player1Items;

    public static Weapon player1Weap;
    public static String player1WeapMagMod;
    public static Armor player1Arm;
    public static String player1ArmMagMod;
    public static Ammunition player1Ammo; //Ammunition
    public static String player1AmmoCount; //Ammunition Qty

    //Attributes associated with Player 2
    @FXML
    private ChoiceBox player2Box;
    @FXML
    private TextField player2Num;
    @FXML
    private TextField player2Lvl;
    @FXML
    private Button player2Items;

    public static Weapon player2Weap;
    public static String player2WeapMagMod;
    public static Armor player2Arm;
    public static String player2ArmMagMod;
    public static Ammunition player2Ammo; //Ammunition
    public static String player2AmmoCount; //Ammunition Qty

    //Attributes associated with Player 3
    @FXML
    private ChoiceBox player3Box;
    @FXML
    private TextField player3Num;
    @FXML
    private TextField player3Lvl;
    @FXML
    private Button player3Items;

    public static Weapon player3Weap;
    public static String player3WeapMagMod;
    public static Armor player3Arm;
    public static String player3ArmMagMod;
    public static Ammunition player3Ammo; //Ammunition
    public static String player3AmmoCount; //Ammunition Qty

    //Attributes associated with Monster
    @FXML
    private ChoiceBox monsterBox;
    @FXML
    private TextField monsterNum;
    @FXML
    private Button monsterStats;

    //Attributes associated with Monster 1
    @FXML
    private ChoiceBox monster1Box;
    @FXML
    private TextField monster1Num;
    @FXML
    private Button monster1Stats;

    //Attributes associated with Monster 2
    @FXML
    private ChoiceBox monster2Box;
    @FXML
    private TextField monster2Num;
    @FXML
    private Button monster2Stats;

    //Attributes associated with Monster 3
    @FXML
    private ChoiceBox monster3Box;
    @FXML
    private TextField monster3Num;
    @FXML
    private Button monster3Stats;

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
        playSound("cheer.wav");
        Sim sim = new Sim(numberSimulations,combatArrayList);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Simulation");

        // Header Text: null
        alert.setHeaderText(null);
 //       alert.setContentText(playerBox.getValue().toString());
        alert.setContentText("\nWin Rate: " + String.format("%.2f", sim.simulationWinRate()) +"%");
 //       alert.setContentText(combatArrayList.toString());
        //playSound("cheer.mp3", 2);
        alert.showAndWait();
    }

    public void fightButtonClicked(){

        //playSound("sword.mp3", 1);
        //playSound("cheer.mp3");
        playSound("sword.wav");
//        numberSimulations = Integer.parseInt(numSims.getText());
        combatArrayList.clear();
        addPlayer(playerBox,playerNum,playerLvl, 0);
        addPlayer(player1Box,player1Num,player1Lvl, 1);
        addPlayer(player2Box,player2Num,player2Lvl, 2);
        addPlayer(player3Box,player3Num,player3Lvl, 3);
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

        playerItems.setDisable(true);
        playSound("items.wav");
        try {
            item = 0;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemSelect.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 245, 302);
            Image image = new Image("file:///../media.img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Items");
            stage.setScene(ISController);
            stage.show();
            stage.setOnHiding(event -> {playerItems.setDisable(false);});
        }
        catch (Exception e){
            System.out.println("Can't load new window!");
        }
    }
    public void itemButton1Clicked(){

        player1Items.setDisable(true);
        playSound("items.wav");
        try {
            item = 1;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemSelect.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 245, 302);
            Image image = new Image("file:///../media.img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Items");
            stage.setScene(ISController);
            stage.show();
            stage.setOnHiding(event -> {player1Items.setDisable(false);});
        }
        catch (Exception e){
            System.out.println("Can't load new window!");
        }
    }

    public void itemButton2Clicked(){

        player2Items.setDisable(true);
        playSound("items.wav");
        try {
            item = 2;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemSelect.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 245, 302);
            Image image = new Image("file:///../media.img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Items");
            stage.setScene(ISController);
            stage.show();
            stage.setOnHiding(event -> {player2Items.setDisable(false);});
        }
        catch (Exception e){
            System.out.println("Can't load new window!");
        }
    }

    public void itemButton3Clicked(){

        player3Items.setDisable(true);
        playSound("items.wav");
        try {
            item = 3;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemSelect.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 245, 302);
            Image image = new Image("file:///../media.img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Items");
            stage.setScene(ISController);
            stage.show();
            stage.setOnHiding(event -> {player3Items.setDisable(false);});
        }
        catch (Exception e){
            System.out.println("Can't load new window!");
        }
    }

    public void statsButtonClicked(){

        monsterStats.setDisable(true);
        playSound("scroll.wav");
        try {

            MonsterStatsController monsterStatsController = new MonsterStatsController((Monster) monsterBox.getValue());

            monster = 0;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MonsterStats.fxml"));
            fxmlLoader.setController(monsterStatsController);
            Parent root1 = (Parent) fxmlLoader.load();

//            MonsterStatsController mStatsCon =fxmlLoader.getController();
//            mStatsCon.getStats((Monster) monsterBox.getValue());

            ISController = new Scene(root1, 600, 400);
            Image image = new Image("file:///../media.img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Monster Manual");
            stage.setScene(ISController);
            stage.show();
            stage.setOnHiding(event -> {monsterStats.setDisable(false);});
        }
        catch (Exception e){
            System.out.println("Can't load new window!");
            System.out.println(e.toString());
            e.printStackTrace();
            monsterStats.setDisable(false);
        }
    }

    public void statsButton1Clicked(){

        monster1Stats.setDisable(true);
        playSound("scroll.wav");
        try {
            MonsterStatsController monsterStatsController = new MonsterStatsController((Monster) monster1Box.getValue());
            monster = 1;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MonsterStats.fxml"));
            fxmlLoader.setController(monsterStatsController);
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 600, 400);
            Image image = new Image("file:///../media.img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Monster Manual");
            stage.setScene(ISController);
            stage.show();
            stage.setOnHiding(event -> {monster1Stats.setDisable(false);});
        }
        catch (Exception e){
            System.out.println("Can't load new window!");
            System.out.println(e.toString());
            e.printStackTrace();
            monsterStats.setDisable(false);
        }
    }

    public void statsButton2Clicked(){

        monster2Stats.setDisable(true);
        playSound("scroll.wav");
        try {
            MonsterStatsController monsterStatsController = new MonsterStatsController((Monster) monster2Box.getValue());
            monster = 2;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MonsterStats.fxml"));
            fxmlLoader.setController(monsterStatsController);
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 600, 400);
            Image image = new Image("file:///../media.img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Monster Manual");
            stage.setScene(ISController);
            stage.show();
            stage.setOnHiding(event -> {monster2Stats.setDisable(false);});
        }
        catch (Exception e){
            System.out.println("Can't load new window!");
            System.out.println(e.toString());
            e.printStackTrace();
            monsterStats.setDisable(false);
        }
    }

    public void statsButton3Clicked(){

        monster3Stats.setDisable(true);
        playSound("scroll.wav");
        try {
            MonsterStatsController monsterStatsController = new MonsterStatsController((Monster) monster3Box.getValue());
            monster = 3;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MonsterStats.fxml"));
            fxmlLoader.setController(monsterStatsController);
            Parent root1 = (Parent) fxmlLoader.load();

            ISController = new Scene(root1, 600, 400);
            Image image = new Image("file:///../media.img/sword.png");
            ISController.setCursor(new ImageCursor(image));

            stage = new Stage();
            stage.setTitle("Monster Manual");
            stage.setScene(ISController);
            stage.show();
            stage.setOnHiding(event -> {monster3Stats.setDisable(false);});
        }
        catch (Exception e){
            System.out.println("Can't load new window!");
            System.out.println(e.toString());
            e.printStackTrace();
            monsterStats.setDisable(false);
        }
    }

    //set functions for player take from ItemSelectController
    public void setWeapon(Weapon weapon){
        playerWeap = weapon;
    }

    public void setArmor(Armor armor){
        playerArm = armor;
    }

    public void setAmmunition(Ammunition ammunition){
        playerAmmo = ammunition;
    }

    public void setWeaponMod(String textA){
        playerWeapMagMod = textA;
    }

    public void setArmorMod(String textB){
        playerArmMagMod = textB;
    }

    public void setAmmunitionCount(String textB){
        playerAmmoCount = textB;
    }

    //set functions for player 1 take from ItemSelectController
    public void setWeapon1(Weapon weapon){
        player1Weap = weapon;
    }

    public void setArmor1(Armor armor){
        player1Arm = armor;
    }

    public void setAmmunition1(Ammunition ammunition){
        player1Ammo = ammunition;
    }

    public void setWeaponMod1(String textA){
        player1WeapMagMod = textA;
    }

    public void setArmorMod1(String textB){
        player1ArmMagMod = textB;
    }

    public void setAmmunitionCount1(String textB){
        player1AmmoCount = textB;
    }

    //set functions for player 2 take from ItemSelectController
    public void setWeapon2(Weapon weapon){
        player2Weap = weapon;
    }

    public void setArmor2(Armor armor){
        player2Arm = armor;
    }

    public void setAmmunition2(Ammunition ammunition){
        player2Ammo = ammunition;
    }

    public void setWeaponMod2(String textA){
        player2WeapMagMod = textA;
    }

    public void setArmorMod2(String textB){
        player2ArmMagMod = textB;
    }

    public void setAmmunitionCount2(String textB){
        player2AmmoCount = textB;
    }

    //set functions for player 3 take from ItemSelectController
    public void setWeapon3(Weapon weapon){
        player3Weap = weapon;
    }

    public void setArmor3(Armor armor){
        player3Arm = armor;
    }

    public void setAmmunition3(Ammunition ammunition){
        player3Ammo = ammunition;
    }

    public void setWeaponMod3(String textA){
        player3WeapMagMod = textA;
    }

    public void setArmorMod3(String textB){
        player3ArmMagMod = textB;
    }

    public void setAmmunitionCount3(String textB){
        player3AmmoCount = textB;
    }


    public void getMonsterList(){
        try{
            mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String os = System.getProperty("os.name");
            String relativePath = System.getProperty("user.dir");
            String fullPath;
            File files;

            if (os.equals("Linux")) {
                fullPath = relativePath + "/src/files/monsters/bestiary-mm.json";
                files = new File(fullPath);
            }
            else {
                fullPath = relativePath + "\\src\\files\\monsters\\bestiary-mm.json";
                files = new File(fullPath);
            }
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

    public void addPlayer(ChoiceBox playerBox, TextField playerNum, TextField playerLvl, Integer playerIndex){

        if (!checkPlayerBoxValid(playerBox, playerNum, playerLvl)) {
            return;
        }
        String type = playerBox.getValue().toString();
        numberPlayers = Integer.parseInt(playerNum.getText());
        numberLevels = Integer.parseInt(playerLvl.getText());

        if (type.equalsIgnoreCase("fighter")){
            addPlayerToCombatList(type, numberPlayers, numberLevels, playerIndex);
        }
        if (type.equalsIgnoreCase("rogue")){
            addPlayerToCombatList(type, numberPlayers, numberLevels, playerIndex);
        }
        if (type.equalsIgnoreCase("wizard")){
            addPlayerToCombatList(type, numberPlayers, numberLevels, playerIndex);
        }
        if (type.equalsIgnoreCase("cleric")){
            addPlayerToCombatList(type, numberPlayers, numberLevels, playerIndex);
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

    public void addPlayerToCombatList(String type, Integer numberPlayers, Integer numberLevels, Integer playerSet){
        Player player = new Fighter();

        for (int i = 0; i < numberPlayers; i++){

            if(type.equalsIgnoreCase("fighter")){
                player = new Fighter();
                player = setPlayerItems(player, playerSet);
            }
            if(type.equalsIgnoreCase("rogue")){
                player = new Rogue();
                player = setPlayerItems(player, playerSet);
            }
            if(type.equalsIgnoreCase("cleric")){
                player = new Cleric();
                player = setPlayerItems(player, playerSet);
            }
            if(type.equalsIgnoreCase("wizard")){
                player = new Wizard();
                player = setPlayerItems(player, playerSet);
            }

            for (int j = 1; j < numberLevels; j++) { // int j = 1 because chars start at level 1
                player.levelUp();
            }
            combatArrayList.add(player);
        }
    }

    public Player setPlayerItems(Player player, Integer playerSet) {

        if (playerSet == 0) {
            if (playerWeap != null) {
                player.setWeapon(playerWeap);
            }
            if (playerArm != null) {
                player.setArmor(playerArm);
            }
            if (playerAmmo != null) {
                player.setAmmunition(playerAmmo);
            }
        }
        else if (playerSet == 1) {
            if (player1Weap != null) {
                player.setWeapon(player1Weap);
            }
            if (player1Arm != null) {
                player.setArmor(player1Arm);
            }
            if (player1Ammo != null) {
                player.setAmmunition(player1Ammo);
            }
        }
        else if (playerSet == 2) {
            if (player2Weap != null) {
                player.setWeapon(player2Weap);
            }
            if (player2Arm != null) {
                player.setArmor(player2Arm);
            }
            if (player2Ammo != null) {
                player.setAmmunition(player2Ammo);
            }
        }
        else if (playerSet == 3) {
            if (player3Weap != null) {
                player.setWeapon(player3Weap);
            }
            if (player3Arm != null) {
                player.setArmor(player3Arm);
            }
            if (player3Ammo != null) {
                player.setAmmunition(player3Ammo);
            }
        }

        return player;
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

   /*public void playSound(String sound,int i){
        String os = System.getProperty("os.name");
        String relativeSoundPath = System.getProperty("user.dir");
        String fullSoundPath;

        if(os.equals("Linux")){
            fullSoundPath = relativeSoundPath + "/media" + sound;
        }
        else {
            fullSoundPath = relativeSoundPath + "\\media\\audio\\" + sound;
        }

        Media sword = new Media(new File(fullSoundPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sword);
        mediaPlayer.setCycleCount(i);
        mediaPlayer.play();
    }*/

   public void playSound(String sound){
       File soundPath;
       String os = System.getProperty("os.name");
       String relativeSoundPath = System.getProperty("user.dir");
       String fullSoundPath;
       if(os.equals("Linux")){
           fullSoundPath = relativeSoundPath + "/media/audio/" + sound;
       }
       else {
           fullSoundPath = relativeSoundPath + "\\media\\audio\\" + sound;
       }
       soundPath = new File(fullSoundPath);
       AudioClip clip = new AudioClip(soundPath.toURI().toString());
       clip.play();
   }

   public void secretButtonClicked(){
       playSound("dragonWings.wav");
       playSound("roar.wav");
   }
}
