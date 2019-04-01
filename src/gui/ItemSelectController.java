package gui;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Commands;
import common.Creature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import java.lang.Object;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import monster.Monster;
import player.*;
import simulation.Sim;
import javafx.scene.Parent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemSelectController {

    ObservableList<String> itemList = FXCollections.observableArrayList("Sword", "Mace", "Chain mail", "Shield");

    @FXML
    private void initialize(){
        itemBox.setItems(itemList);
        item1Box.setItems(itemList);
    }

    @FXML
    private ChoiceBox itemBox;
    @FXML
    private TextField magicMod;

    @FXML
    private ChoiceBox item1Box;
    @FXML
    private TextField magic1Mod;

    @FXML
    void submitButtonClicked(){

        try{
            FXMLLoader load = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = (Parent) load.load();
            HomeController home = load.getController();

        if(home.item == 0) {
            if (itemBox.getValue() != null) {
                home.setItemA(itemBox.getValue().toString());
            }
            if (item1Box.getValue() != null){
                home.setItemB(item1Box.getValue().toString());
            }
            if (magicMod.getText() != null){
                home.setTextA(magicMod.getText());
            }
            if (magic1Mod.getText() != null){
                home.setTextB(magic1Mod.getText());
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items");
            alert.setHeaderText(null);
            alert.setContentText("\nItem A: " + home.playerItemA + "  Magic A: " + home.playerTextA + "  Item B: " + home.playerItemB + "  Magic B: " + home.playerTextB);
            alert.showAndWait();
        }
        if(home.item == 1) {
            if (itemBox.getValue() != null) {
                home.setItem1A(itemBox.getValue().toString());
            }
            if (item1Box.getValue() != null){
                home.setItem1B(item1Box.getValue().toString());
            }
            if (magicMod.getText() != null){
                home.setText1A(magicMod.getText());
            }
            if (magic1Mod.getText() != null){
                home.setText1B(magic1Mod.getText());
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items 1");
            alert.setHeaderText(null);
            alert.setContentText("\nItem 1A: " + home.player1ItemA + "  Magic 1A: " + home.player1TextA + "  Item 1B: " + home.player1ItemB + "  Magic 1B: " + home.player1TextB);
            alert.showAndWait();
        }
        if(home.item == 2) {
            if (itemBox.getValue() != null) {
                home.setItem2A(itemBox.getValue().toString());
            }
            if (item1Box.getValue() != null){
                home.setItem2B(item1Box.getValue().toString());
            }
            if (magicMod.getText() != null){
                home.setText2A(magicMod.getText());
            }
            if (magic1Mod.getText() != null){
                home.setText2B(magic1Mod.getText());
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items 2");
            alert.setHeaderText(null);
            alert.setContentText("\nItem 2A: " + home.player2ItemA + "  Magic 2A: " + home.player2TextA + "  Item 2B: " + home.player2ItemB + "  Magic 2B: " + home.player2TextB);
            alert.showAndWait();
        }
        if(home.item == 3) {
            if (itemBox.getValue() != null) {
                home.setItem3A(itemBox.getValue().toString());
            }
            if (item1Box.getValue() != null){
                home.setItem3B(item1Box.getValue().toString());
            }
            if (magicMod.getText() != null){
                home.setText3A(magicMod.getText());
            }
            if (magic1Mod.getText() != null){
                home.setText3B(magic1Mod.getText());
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items 3");
            alert.setHeaderText(null);
            alert.setContentText("\nItem 3A: " + home.player3ItemA + "  Magic 3A: " + home.player3TextA + "  Item 3B: " + home.player3ItemB + "  Magic 3B: " + home.player3TextB);
            alert.showAndWait();
        }

        home.stage.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
