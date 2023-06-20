package cn.edu.whut.sept.zuul;

import java.util.Scanner;

public class Parser
{
    private CommandWords commands;  // holds all valid command words
//    创建command类
    private Scanner reader;         // source of command input
//    创建scanner类

    /**
     * 获取用户输入的指令
     */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * 读入指令
     * @return command
     */
    public Command getCommand()
    {
        String inputLine;   // will hold the full input line
        String word1 ;
        String word2 ;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
//        if(tokenizer.hasNext()) {
//            word1 = tokenizer.next();      // get first word
//            if(tokenizer.hasNext()) {
//                word2 = tokenizer.next();      // get second word
//            }
//        }
//
//        Command command = commands.get(word1);
//        if(command != null) {
//            command.setSecondWord(word2);
//        }
//        return command;
        if(tokenizer.hasNext()){
            word1 = tokenizer.next();
        }else{
            word1 = null;
        }

        if(tokenizer.hasNext()){
            word2 = tokenizer.next();
        } else{
          word2 = null;
        }
        return new Command(word1, word2);
    }

    /**
     * 显示指令
     */
    public void showCommands()
    {
        commands.showAll();
    }
}

