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

public class Game
{
    private Parser parser;

    public Player player;
    private Room currentRoom;

    private  Room lastRoom;
    public Game()
    {
        createRooms();
        parser = new Parser();


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

        player = new Player("zzh", outside, 75 , 100);

        player.transRoom.add(outside);
        player.transRoom.add(square);
        player.transRoom.add(cafeteria);
        player.transRoom.add(pub);
        player.transRoom.add(theater);
        player.transRoom.add(surpermarket);
        player.transRoom.add(office);
        player.transRoom.add(lab);
        player.transRoom.add(library);
        player.transRoom.add(building);

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

        currentRoom = outside;  // start game outside
        player.moveHistory.add(currentRoom);

        Item book = new Item("book", 6, "this is a book");
        Item flashlight = new Item("flashlight", 7, "this is a flashlight");
        Item bottled_water = new Item("bottled_water", 4, "this is a bottled_water");
        Item map = new Item("map", 4, "this is a map");
        Item Magic_cookie = new Item("Magic_cookie", 3, "this is a Magic_cookie");



        office.addItem(book);
        theater.addItem(flashlight);
        pub.addItem(bottled_water);
        square.addItem(map);
        cafeteria.addItem(Magic_cookie);
        surpermarket.addItem(Magic_cookie);
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

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
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


//    public void goRoom(Command command){
//        if(!command.hasSecondWord()){
//            System.out.println("Go where?");
//            return;
//        }
//        String direction = command.getSecondWord();
//        Room currentRoom = room.getCurrentRoom();
//
//        Room nextRoom = game.getCurrentRoom().getExit(direction);
//
//        if (nextRoom == null) {
//            System.out.println("There is no door!");
//        }
//        else {
//            game.setLastRoom(currentRoom);
//            game.setCurrentRoom(nextRoom);
//            System.out.println(nextRoom.getLongDescription());
//        }
//
//    }
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

//    private void goRoom(Command command)
//    {
//        if(!command.hasSecondWord()) {
//            // if there is no second word, we don't know where to go...
//            System.out.println("Go where?");
//            return;
//        }
//
//        String direction = command.getSecondWord();
//
//        Room currentRoom= player.getCurrentRoom();
//        // Try to leave current room.
//        Room nextRoom = currentRoom.getExit(direction);
//
//        if (nextRoom == null) {
//            System.out.println("There is no door!");
//        }
//        else {
//
//            currentRoom = nextRoom;
//            player.setCurrentRoom(currentRoom);
//            player.moveHistory.push(currentRoom);
//            System.out.println(currentRoom.getLongDescription());
//        }
//    }

//    public boolean quit(Command command){
//        if(command.hasSecondWord()){
//            System.out.println("quit what?");
//            return false;
//        }else{
//            return true;
//        }
//    }

//    public void goBack(Command command){
//
//    }

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