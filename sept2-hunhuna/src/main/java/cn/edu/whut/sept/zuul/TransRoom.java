package cn.edu.whut.sept.zuul;

import java.util.Random;

public class TransRoom extends Room{
    private Room[] rooms;

    private Random random;
    /**
     * 房间内的存储信息
     *
     * @param rdescription
     */
    public TransRoom(String rdescription, Room[] rooms) {
        super(rdescription);
        setExit("transporter", this);
        this.rooms = rooms;
        random = new Random();
    }

    @Override
    public Room getExit(String direction){
        return rooms[random.nextInt(rooms.length)];
    }
}
