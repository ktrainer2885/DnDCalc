package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import monster.Monster;

public class MonsterStatsController {

    @FXML
    private Label monsterName;
    @FXML
    private Label monsterHp;
    @FXML
    private Label monsterMaxHP;
    @FXML
    private Label monsterProf;
    @FXML
    private Label monsterWeap;
    @FXML
    private Label monsterDice;
    @FXML
    private Label monsterDamConst;
    @FXML
    private Label monsterAC;
    @FXML
    private Label monsterInit;
    @FXML
    private Label monsterCR;
    @FXML
    private Label monsterSTR;
    @FXML
    private Label monsterDEX;
    @FXML
    private Label monsterCON;
    @FXML
    private Label monsterINT;
    @FXML
    private Label monsterWIS;
    @FXML
    private Label monsterCHA;

    public static String monName;
    public static int monHp;
    public static int monMaxHp;
    public static int monProf;
    public static String  monWeap;
    public static String monDice;
    public static int monDamConst;
    public static int monAC;
    public static int monInit;
    public static String monCR;
    public static int monSTR;
    public static int monDEX;
    public static int monCON;
    public static int monINT;
    public static int monWIS;
    public static int monCHA;

    public MonsterStatsController(Monster monster){
        setName(monster.getName());
        setHp(monster.getHp());
        setMaxHp(monster.getMaxHp());
        setProf(monster.getProf());
        setWeap("Claw");
        setDice("2d6");
        setDamConst(monster.getDamConst());
        setAC(monster.getAc());
//        setInit(monster.getInit());
        setCR(monster.getCR());
        setSTR(monster.getStr());
        setDEX(monster.getDex());
        setCON(monster.getCon());
        setINT(monster.getIntel());
        setWIS(monster.getWis());
        setCHA(monster.getCha());
    }


    @FXML
    public void initialize(){
    //    getStats();
        String s;
        monsterName.setText(monName);
        s = monCR;
        monsterCR.setText("CR: " + s);
        s = String.valueOf(monAC);
        monsterAC.setText("AC: " + s);
        s = String.valueOf(monHp);
        monsterHp.setText("HP: " + s);
        s = toString().valueOf(monMaxHp);
        monsterMaxHP.setText("Max HP: " + s);
        monsterDice.setText("Hit Die: " + monDice);
        s = toString().valueOf(monInit);
//        monsterInit.setText("Init: " + s);
 //       s = toString().valueOf(monProf);
        monsterProf.setText("Prof: " + s);
        s = toString().valueOf(monDamConst);
        monsterDamConst.setText("Dam Const: " + s);
        s = toString().valueOf(monSTR);
        monsterSTR.setText("STR: " + s);
        s = toString().valueOf(monDEX);
        monsterDEX.setText("DEX: " + s);
        s = toString().valueOf(monINT);
        monsterINT.setText("INT: " + s);
        s = toString().valueOf(monWIS);
        monsterWIS.setText("WIS: " + s);
        s = toString().valueOf(monCON);
        monsterCON.setText("CON: " + s);
        s = toString().valueOf(monCHA);
        monsterCHA.setText("CHA: " + s);

    }

    public void setName(String x){
        monName = x;
    }

    public void setHp(int x){
        monHp = x;
    }

    public void setMaxHp(int x){
        monMaxHp = x;
    }

    public void setProf(int x){
        monProf = x;
    }

    public void setWeap(String x){
        monWeap = x;
    }

    public void setDice(String x){
        monDice = x;
    }

    public void setDamConst(int x){
        monDamConst = x;
    }

    public void setAC(int x){
        monAC = x;
    }

    public void setInit(int x){
        monInit = x;
    }

    public void setCR(String x){
        monCR = x;
    }

    public void setSTR(int x){
        monSTR = x;
    }

    public void setDEX(int x){
        monDEX = x;
    }

    public void setCON(int x){
        monCON = x;
    }

    public void setINT(int x){
        monINT = x;
    }

    public void setWIS(int x){
        monWIS = x;
    }

    public void setCHA(int x){
        monCHA = x;
    }

    public void getStats(Monster monster){
        setName(monster.getName());
        setHp(monster.getHp());
        setMaxHp(monster.getMaxHp());
        setProf(monster.getProf());
        setWeap("Claw");
        setDice("2d6");
        setDamConst(monster.getDamConst());
        setAC(monster.getAc());
//        setInit(monster.getInit());
        setCR(monster.getCR());
        setSTR(monster.getStr());
        setDEX(monster.getDex());
        setCON(monster.getCon());
        setINT(monster.getIntel());
        setWIS(monster.getWis());
        setCHA(monster.getCha());
    }
}
