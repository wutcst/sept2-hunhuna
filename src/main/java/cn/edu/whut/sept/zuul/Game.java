/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul;

import Dao.playerDAO;
import Model.tPlayer;

import java.sql.SQLException;
import java.util.Scanner;

public class Game
{
    private Parser parser;

    public Player player;



//    tPlayer player = new tPlayer();
//    tItems items = new tItems();

    private Room currentRoom;

    private  Room lastRoom;
    public Game()
    {
        createRooms();
        parser = new Parser();

//        tPlayer tp = new tPlayer();

    }



    public void createRooms()
    {

        Room outside, theater, pub, lab, office, square, surpermarket, library,building, cafeteria, transporter;


        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        square = new Room("in the center square of university");
        surpermarket = new Room("in a educational supermarket");
        library = new Room("in the university library");
        building = new Room("in a teaching building");
        cafeteria = new Room("in a student cafeteria");
//        transporter = new TransRoom("in a transporter room", new Room[]{outside, theater, pub, lab, office, square, surpermarket, library,building, cafeteria});

//        player = new Player(player.getPname());

//        player.transRoom.add(outside);
//        player.transRoom.add(square);
//        player.transRoom.add(cafeteria);
//        player.transRoom.add(pub);
//        player.transRoom.add(theater);
//        player.transRoom.add(surpermarket);
//        player.transRoom.add(office);
//        player.transRoom.add(lab);
//        player.transRoom.add(library);
//        player.transRoom.add(building);

        player = new Player();

        player.addTransportRoom(office);
        player.addTransportRoom(outside);
        player.addTransportRoom(square);
        player.addTransportRoom(cafeteria);
        player.addTransportRoom(pub);
        player.addTransportRoom(theater);
        player.addTransportRoom(surpermarket);
        player.addTransportRoom(lab);
        player.addTransportRoom(library);
        player.addTransportRoom(building);


        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", square);

        theater.setExit("west", outside);
        theater.setExit("east", surpermarket);

        surpermarket.setExit("south", library);
        surpermarket.setExit("west", theater);

        library.setExit("north", surpermarket);
        library.setExit("west", office);

        pub.setExit("east", outside);
        pub.setExit("north", cafeteria);

        cafeteria.setExit("south", pub);
        cafeteria.setExit("east", square);

        square.setExit("west", cafeteria);
        square.setExit("south", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("south", building);
        office.setExit("east", library);

        building.setExit("north", office);

        if(player.moveHistory.empty()){
            player.setCurrentRoom(outside);
            player.moveHistory.add(currentRoom);
            player.path++;
        }else{
            player.setCurrentRoom(player.moveHistory.peek());
        }
//        currentRoom = outside;  // start game outside


        Item book = new Item("book", 6, "this is a book");
        Item flashlight = new Item("flashlight", 7, "this is a flashlight");
        Item bottled_water = new Item("bottled_water", 4, "this is a bottled_water");
        Item map = new Item("map", 4, "this is a map");
        Item Magic_cookie = new Item("Magic_cookie", 3, "this is a Magic_cookie");
        Item battery = new Item("battery", 1, "this is a battery");
        Item knife = new Item("knife", 2,"this is a knife");

        outside.addItem(battery);

        office.addItem(book);

        theater.addItem(flashlight);

        pub.addItem(bottled_water);
        pub.addItem(flashlight);

        square.addItem(map);

        cafeteria.addItem(Magic_cookie);

        surpermarket.addItem(Magic_cookie);

        lab.addItem(knife);

        library.addItem(book);

    }


//    public boolean isLog(){
//
//    }

    public boolean Log(){
        tPlayer pl = new tPlayer();
        playerDAO pd = new playerDAO();

        Scanner scanner = new Scanner(System.in);

        System.out.println("please input YOUR PLAYERNAME:");
        String inputline1 = scanner.nextLine();
        pl.setPlayer_name(inputline1);

        System.out.println("please input YOUR PASSWORD:");
        String inputline2 = scanner.nextLine();
        pl.setPlayer_password(inputline2);
        boolean loginSuccess = false;
        try {
            loginSuccess = pd.login(pl.getPlayer_name(), pl.getPlayer_password());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(loginSuccess){
            System.out.println("Welcome into Word-Of-Zuul " + pl.getPlayer_name());

            player.setPname(player.getPname());

            return true;

        }else{
            System.out.println("this player_name doesn't exists,please register first! ");

            return loginSuccess;
        }

    }

    public boolean Register(){
        tPlayer pl = new tPlayer();
        playerDAO pd = new playerDAO();

        Scanner scanner = new Scanner(System.in);


        System.out.println("you are redistering a account....");
        System.out.println("please input YOUR PLAYERNAME:");
        String inputline1 = scanner.nextLine();
//            tPlayer pl = new tPlayer();
//            playerDAO pd = new playerDAO();
        pl.setPlayer_name(inputline1);

        System.out.println("please input YOUR PASSWORD:");
        String inputline2 = scanner.nextLine();
        pl.setPlayer_password(inputline2);


        boolean registSuccess = false;
        try {
            registSuccess = pd.add(pl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(registSuccess){
            System.out.println("successfully registered.");
            System.out.println("Welcome into Word-Of-Zuul " + pl.getPlayer_name());

//            Game game = new Game();
            return true;

        }else {
            System.out.println("the player_name already exists,please re-enter.");
        }
        return false;
    }


    public void play()
    {
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            if(command == null) {
                System.out.println("I don't understand...");
            } else {
                finished = processCommand(command);
//                finished = command.execute(this);
            }
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    public void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    public boolean processCommand(Command command){
        if(command.isUnknow()){
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        CommandWords cWords = new CommandWords();



        cWords.constructCommands();
        if(cWords.idCommand(commandWord)){

            cWords.getCommand(commandWord).doAction(command, player);
        }else{
            System.out.println("I don't know what you mean...");
        }
        return Command.getWantToQuit();
    }

    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }


    /**
     * 获得当前房间的信息
     * @return currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * 设置当前房间信息
     * @param room
     */
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    /**
     * 获取上一个房间信息
     * @return lastRoom
     */
    public Room getLastRoom(){
        return lastRoom;
    }

    /**
     * 设置上一个房间信息
     * @param room
     */
    public void setLastRoom(Room room){
        this.lastRoom = room;
    }
}