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
//        Room saveroom = player.getCurrentRoom();
//        player.setCurrentRoom(player.getCurrentRoom());
//        player.setCurrentRoom(player.getCurrentRoom());

//        if(player.moveHistory.size() > 1){
//            player.moveHistory.pop();
//            Room lastRoom = player.moveHistory.peek();
//            player.setCurrentRoom(lastRoom);
//            System.out.println("you are "+ player.getCurrentRoom().getLongDescription());
//        }else {
//            System.out.println("you are in the beginning......");
//        }

//        Room currentroom = player.getCurrentRoom();
//        if(!player.moveHistory.empty()){
//            player.moveHistory.pop();
//            if(!player.moveHistory.empty()){
//                 currentroom = player.moveHistory.peek();
//            }
//            System.out.println("you are "+ player.getCurrentRoom().getShortDescription());
//        }else {
//            System.out.println("you are in the beginning......");
//        }


        Room saveRoom = player.getCurrentRoom();
        player.setCurrentRoom(player.getCurrentRoom());
        player.setCurrentRoom(saveRoom);

        if(player.moveHistory.size() > 1){
            player.moveHistory.pop();
            Room lastRoom = player.moveHistory.peek();
            player.setCurrentRoom(lastRoom);
            System.out.println(player.moveHistory);
            System.out.println("you are " + player.getCurrentRoom().getLongDescription());
        }else{
            System.out.println("you are in the beginning......");
        }
    }
}
