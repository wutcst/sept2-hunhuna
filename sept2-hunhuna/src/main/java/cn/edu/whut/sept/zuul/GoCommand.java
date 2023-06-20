package cn.edu.whut.sept.zuul;

public class GoCommand implements Action
{
    public GoCommand(){

    }

//    public boolean execute(Game game, Player player)
//    {
//        if(!hasSecondWord()) {
//            System.out.println("Go where?");
//            return false;
//        }
//
//        String direction = getSecondWord();
//        Room currentRoom = game.getCurrentRoom();
//
//        Room nextRoom = game.getCurrentRoom().getExit(direction);
//
//        if (nextRoom == null) {
//            System.out.println("There is no door!");
//        }
//        else {
//            game.setLastRoom(currentRoom);
//            player.addMoveHistory(currentRoom);
//            game.setCurrentRoom(nextRoom);
//            System.out.println(nextRoom.getLongDescription());
//        }
//
//        return false;
//    }

    @Override
    public void doAction(Command command, Player player) {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        try{

            Room currentRoom = player.getCurrentRoom();

            Room nextRoom = player.getCurrentRoom().getExit(direction);

            if (nextRoom == null) {
                System.out.println("There is no door!");
            }
            else {
                player.setLastRoom(currentRoom);
                player.moveHistory.add(currentRoom);
                player.setCurrentRoom(nextRoom);
                System.out.println(nextRoom.getLongDescription());
                System.out.println(player.moveHistory);
            }

        } catch (NullPointerException e){
            System.out.println("there is no exit " + direction);
        }



    }
}
