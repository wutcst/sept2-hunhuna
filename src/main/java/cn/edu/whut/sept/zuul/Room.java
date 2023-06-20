package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

public class Room
{
    private String rdescription;
//    对房间的描述
    private HashMap<String, Room> exits;        // stores exits of this room.
//    创建room哈希表
    private ArrayList<Item> items;
//    创建item数组

    /**
     * 房间内的存储信息
     * @param rdescription
     */
    public Room(String rdescription)
    {
        this.rdescription = rdescription;
        exits = new HashMap<>();
        items = new ArrayList<Item>();
    }

    /**
     * 设置room的direction,neighbor
     * @param direction
     * @param neighbor
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * 获取room简单描述
     * @return description
     */
    public String getShortDescription()
    {
        return rdescription;
    }

    /**
     * 获取room详细描述
     * @return 目前所在房间和出口方向
     */
    public String getLongDescription()
    {
        return "You are " + rdescription + ".\n" + getExitString();
    }

//    定义出口方向
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * 获取下一房间的出口
     * @param direction
     * @return direction
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }


    public Item getItem(String itemname) {
        String name;
//        Item returnedItem = null;

        Item returnedItem = null;
        for (Item e : items) {
            System.out.println(e);
            name = e.getIname();
            if (name.compareTo(itemname) == 0) {
                returnedItem = e;
            }
        }
        return returnedItem;
    }


    /**
     * 添加item
     * @param item
     */
    public void addItem(Item item){
        items.add(item);
    }

    /**
     * 移除item
     * @param item
     */
    public void removeItem(Item item){
        items.remove(item);
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public void showRoomItems(){
        String str = "in this room there are the following items:";

        for (Item e :items)
            str += " " + e.getIname();
        System.out.println(str);
    }


}


