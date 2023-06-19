package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player{
    private String pname;


//    玩家的名字

    int pid;
    int path;

    private Room currentRoom;
//    玩家当前所在房间
    private Room lastRoom;
    List<Item> tokenitems;
//    玩家已经拿到的item
    int maxWeight = 75;
//    玩家的最大负重

    int maxStamina = 100;
//    玩家的最大体力
    int currentStamina;
//    玩家当前体力
    int currentWeight;
//    玩家当前负重
    public Stack<Room> moveHistory;
//    玩家移动路径
    public Item item;

    AtomicBoolean isResting = new AtomicBoolean(false);


    public ArrayList<Room> transRoom;
    public Random random;



//    public Player(){
//
//    }


    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public void setCurrentStamina(int currentStamina) {
        this.currentStamina = currentStamina;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }


    public  int r_path;
    public int t_item;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * 初始化玩家信息
     * @param
     * @param
     *
     */
    public Player(){

        tokenitems = new ArrayList<>();
        random = new Random();
        this.transRoom = new ArrayList<>();
        this.pname = pname;
//        this.pid = id;
        moveHistory = new Stack<>();

        this.currentWeight = 0;
        this.currentStamina =maxStamina;
        this.maxWeight = maxWeight;
        this.maxStamina = maxStamina;
        this.r_path = r_path;
        this.t_item = t_item;

    }

    /**
     * 获取玩家姓名
     * @return pname
     */
//    public String getPname(){
//        return this.pname;
//    }
//
//    public void setPname(String name){
//
//    }



    /**
     * 获取当前房间
     * @return currentRoom
     */
//    public Room getCurrentRoom(){
//        return currentRoom;
//    }
//
//    public void setCurrentRoom(Room room){
//        currentRoom = room;
//    }

    /**
     * 设置当前房间信息
     * @param room
     */
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    /**
     * 获得当前房间的信息
     * @return currentRoom
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * 设置上一个房间信息
     * @param room
     */
    public void setLastRoom(Room room){
        lastRoom = room;
    }

    /**
     * 获取上一个房间信息
     * @return lastRoom
     */
    public Room getLastRoom(){
        return this.lastRoom;
    }



//    public void addMoveHistory(Room room){
//
//        moveHistory.push();
//    }

//    public Stack<Room> getMoveHistory{
//        System.out.println(moveHistory);
//    }


    public void addTransportRoom(Room room) {
        transRoom.add(room);
    }

    /**
     * 玩家拾取item
     * @param item
     */
    public void takeItem(Item item){
        tokenitems.add(item);
    }

    /**
     * 玩家丢弃item
     * @param item
     */
    public void dropItem(Item item){
        tokenitems.remove(item);
        currentWeight -= item.getIweight();
        currentRoom.addItem(item);
        System.out.println("you dropped the item:" + item.getIname());
    }

    /**
     * 玩家丢弃身上的所有items
     */
    public void dropAllItems(){
        for(Item item : tokenitems){
            currentRoom.addItem(item);
        }
        tokenitems.clear();
        System.out.println("You dropped all items");
        currentWeight = 0;
    }

    /**
     * 展示玩家已经拾取的items
     */
    public void showTokenitems(){
        System.out.println("your items:");
        for(Item item : tokenitems){
            System.out.println(" ," + item.getIname());
        }
        System.out.println("now total weight:" + currentWeight + " maxweight:" + maxWeight);
    }

    public void goBack(){
        if(moveHistory.isEmpty()){
            System.out.println("you don't have movehistory!");
        }
        else{
            Room lastRoom = moveHistory.pop();
            currentRoom = lastRoom;
            System.out.println("you returned last room:" + currentRoom.getLongDescription());
        }
    }

    public boolean doItemExist(String itemName) {
        boolean doExist = false;
        String name;

        for (Item e : tokenitems) {
            name = e.getIname();
            /*
             * If the item is found in the ArrayList make the variable doExist
             * equals true
             */
            if (name.compareTo(itemName) == 0) {
                doExist = true;
            }
        }
        return doExist;
    }


    public List<Item> getTokenitems(){
        return tokenitems;
    }


    public Item getItem(String itemname) {
        String name;
//        Item returnedItem = null;

        Item returnedItem = null;
        for (Item e : tokenitems) {
//            System.out.println(e);
            name = e.getIname();
            if (name.compareTo(itemname) == 0) {
                returnedItem = e;
            }
        }
        return returnedItem;
    }

    public void printProgressBar(String label, int currentValue, int maxValue){
        int width = 50;

        int progress = (int) (width * ((double)currentValue / maxValue));

        System.out.print(label + ": [");
        for (int j = 0; j < width; j++) {
            if (j < progress) {
                System.out.print("-");
            } else if (j == progress) {
                System.out.print(">");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println("] " + currentValue + "/" + maxValue);
    }
}
