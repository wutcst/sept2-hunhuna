package cn.edu.whut.sept.zuul;

public class BackCommand implements Action{

    public BackCommand(){

    }

    /**
     * 重写
     * @param
     * @return false
     */
//    @Override
//    public boolean execute(Game game, Player player) {
////        Room saveRoom = game.getCurrentRoom();
////        game.setCurrentRoom(game.getCurrentRoom());
////        game.setCurrentRoom(saveRoom);
//
//        if(player.moveHistory.size() > 1){
//            player.moveHistory.pop();
//            Room lastRoom = player.moveHistory.peek();
//            game.setCurrentRoom(lastRoom);
//            System.out.println("you are " + game.getCurrentRoom().getLongDescription());
//        }else{
//            System.out.println("you are in the beginning......");
//        }
//
//        return false;
//    }

    @Override
    public void doAction(Command command, Player player) {




        if(player.isResting.get()){
            System.out.println("you are resting,please wait 5s... ");
        }else {
            if(!player.moveHistory.empty()){
                Room saveRoom = player.getCurrentRoom();
                player.moveHistory.pop();
                if(!player.moveHistory.empty()){
                    Room currentRoom = player.moveHistory.peek();
                    player.setCurrentRoom(currentRoom);
//                System.out.println(player.moveHistory);
                    System.out.println(player.getCurrentRoom().getLongDescription());
                }else{
                    System.out.println("you are in the beginning......");
                }
                player.printProgressBar("Capacity", player.currentWeight, player.maxWeight);
                player.printProgressBar("Stamina", player.currentStamina, player.maxStamina);
//            Room currentRoom = player.moveHistory.peek();
//            player.setCurrentRoom(currentRoom);
//            System.out.println(player.moveHistory);
//            System.out.println("you are " + player.getCurrentRoom().getLongDescription());
            }else{
                System.out.println("you are in the beginning......");
            }
        }


//        if(!player.moveHistory.empty()){
//            player.moveHistory.pop();
//            if(!player.moveHistory.empty()){
//                Room currentRoom = player.moveHistory.peek();
//                player.setCurrentRoom(currentRoom);
//                System.out.println(player.moveHistory);
//                System.out.println("you are " + player.getCurrentRoom().getLongDescription());
//            }else{
//                System.out.println("you are in the beginning......");
//            }
//        }else{
//            System.out.println("you are in the beginning......");
//        }
    }
}
