package cn.edu.whut.sept.zuul;

public class MagicCookieCommand implements Action{
    @Override
    public void doAction(Command command, Player player) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Eat what?");
            return;
        }
        Item Magic_Cookie;
        String str = "Magic_cookie";
        Magic_Cookie = player.getItem(str);

        if(command.getSecondWord().equals(str)){
            player.maxWeight += 5;
            player.currentStamina += 5;
            System.out.println("you have eat Magic_cookie,your maxWeight will plus 5");
            player.tokenitems.remove(Magic_Cookie);
            player.currentWeight -= 3;
            System.out.println("now: " + player.currentWeight + "/" + player.maxWeight);
        }
    }
}
