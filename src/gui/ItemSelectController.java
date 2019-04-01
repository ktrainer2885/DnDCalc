package gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.layout.Region;

public class ItemSelectController {

    //Array for weapon ChoiceBox is initialized
    ObservableList<String> weaponList = FXCollections.observableArrayList("Sword", "Mace", "Lance", "Bow");
    //Array for armor ChoiceBox is initialized
    ObservableList<String> armorList = FXCollections.observableArrayList("Cloth", "Leather", "Chain Mail", "Scale Mail");
    //Array for ammo ChoiceBox is initialized
    ObservableList<String> ammoList = FXCollections.observableArrayList("Arrows", "Bolts", "Bullets", "Rocks");

    // Initializes the items into the respective choiceBox
    @FXML
    private void initialize(){
        weaponBox.setItems(weaponList);
        armorBox.setItems(armorList);
        ammoBox.setItems(ammoList);
    }

    @FXML
    private ChoiceBox weaponBox;
    @FXML
    private TextField weapMagicMod;

    @FXML
    private ChoiceBox armorBox;
    @FXML
    private TextField armMagicMod;

    @FXML
    private ChoiceBox ammoBox;
    @FXML
    private TextField ammoCount;

    @FXML
    void submitButtonClicked(){
        
        try{
            //methods to communicate with HomoController class
            FXMLLoader load = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = (Parent) load.load();
            HomeController home = load.getController();

        if(home.item == 0) {
            if (weaponBox.getValue() != null) {
                home.setItemA(weaponBox.getValue().toString());
            }
            if (weapMagicMod.getText() != null){
                home.setTextA(weapMagicMod.getText());
            }
            if (armorBox.getValue() != null){
                home.setItemB(armorBox.getValue().toString());
            }
            if (armMagicMod.getText() != null){
                home.setTextB(armMagicMod.getText());
            }
            if (ammoBox.getValue() != null){
                home.setItemC(ammoBox.getValue().toString());
            }
            if (ammoCount.getText() != null){
                home.setTextC(ammoCount.getText());
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items");
            alert.setHeaderText(null);
            alert.setContentText("\nWeapon: " + home.playerWeap + "  Mag Mod: " + home.playerWeapMagMod + "  Armor: " + home.playerArm + "  Mag Mod: " + home.playerArmMagMod + "  Ammo: " + home.playerAmmo + "  Count: " + home.playerAmmoCount);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
        if(home.item == 1) {
            if (weaponBox.getValue() != null) {
                home.setItem1A(weaponBox.getValue().toString());
            }
            if (weapMagicMod.getText() != null){
                home.setText1A(weapMagicMod.getText());
            }
            if (armorBox.getValue() != null){
                home.setItem1B(armorBox.getValue().toString());
            }
            if (armMagicMod.getText() != null){
                home.setText1B(armMagicMod.getText());
            }
            if (ammoBox.getValue() != null){
                home.setItem1C(ammoBox.getValue().toString());
            }
            if (ammoCount.getText() != null){
                home.setText1C(ammoCount.getText());
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items 1");
            alert.setHeaderText(null);
            alert.setContentText("\nWeapon1: " + home.player1Weap + "  Mag Mod1: " + home.player1WeapMagMod + "  Armor1: " + home.player1Arm + "  Mag Mod1: " + home.player1ArmMagMod + "  Ammo1: " + home.player1Ammo + "  Count1: " + home.player1AmmoCount);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
        if(home.item == 2) {
            if (weaponBox.getValue() != null) {
                home.setItem2A(weaponBox.getValue().toString());
            }
            if (weapMagicMod.getText() != null){
                home.setText2A(weapMagicMod.getText());
            }
            if (armorBox.getValue() != null){
                home.setItem2B(armorBox.getValue().toString());
            }
            if (armMagicMod.getText() != null){
                home.setText2B(armMagicMod.getText());
            }
            if (ammoBox.getValue() != null){
                home.setItem2C(ammoBox.getValue().toString());
            }
            if (ammoCount.getText() != null){
                home.setText2C(ammoCount.getText());
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items 2");
            alert.setHeaderText(null);
            alert.setContentText("\nWeapon2: " + home.player2Weap + "  Mag Mod2: " + home.player2WeapMagMod + "  Armor2: " + home.player2Arm + "  Mag Mod2: " + home.player2ArmMagMod + "  Ammo2: " + home.player2Ammo + "  Count2: " + home.player2AmmoCount);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
        if(home.item == 3) {
            if (weaponBox.getValue() != null) {
                home.setItem3A(weaponBox.getValue().toString());
            }
            if (weapMagicMod.getText() != null){
                home.setText3A(weapMagicMod.getText());
            }
            if (armorBox.getValue() != null){
                home.setItem3B(armorBox.getValue().toString());
            }
            if (armMagicMod.getText() != null){
                home.setText3B(armMagicMod.getText());
            }
            if (ammoBox.getValue() != null){
                home.setItem3C(ammoBox.getValue().toString());
            }
            if (ammoCount.getText() != null){
                home.setText3C(ammoCount.getText());
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items 3");
            alert.setHeaderText(null);
            alert.setContentText("\nWeapon3: " + home.player3Weap + "  Mag Mod3: " + home.player3WeapMagMod + "  Armor3: " + home.player3Arm + "  Mag Mod3: " + home.player3ArmMagMod + "  Ammo3: " + home.player3Ammo + "  Count3: " + home.player3AmmoCount);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }

        home.stage.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
