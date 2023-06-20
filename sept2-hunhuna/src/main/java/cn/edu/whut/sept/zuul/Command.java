package cn.edu.whut.sept.zuul;

public class Command
{

    private String secondWord;
    private String commandWord;

    protected static boolean wantToQuit = false;

//    public Command(){
//
//    }

    /***
     * 定义指令字
     */

    public Command(String firstword, String secondWord)
    {
        commandWord = firstword;
        this.secondWord = secondWord;
    }





    /**
     * 获取第一个指令
     * @return commandWord
     */
    public String getCommandWord(){
        return commandWord;
    }

    /**
     * 获取指令字
     * @return 返回指令
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    public boolean isUnknow(){
        return (commandWord == null);
    }

    /**
     * 是否有指令
     * @return 返回非空指令
     */
    public boolean hasSecondWord()
    {
        return secondWord != null;
    }

    /**
     * 读入指令
     * @param secondWord
     */
    public void setSecondWord(String secondWord)
    {
        this.secondWord = secondWord;
    }

    public static boolean getWantToQuit() {
        return wantToQuit;
    }

    public static void setWantToQuit(boolean arg) {
        wantToQuit = arg;
    }
//    public abstract boolean execute(Game game, Player player);
}
