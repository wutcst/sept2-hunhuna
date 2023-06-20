package cn.edu.whut.sept.zuul;

public class Item {
     int iweight;
//    item的重量
     String iname;
//    item的名字
     String idescription;
//    item的描述

    /**
     * 保存item信息
     * @param iname
     * @param iweight
     * @param idescription
     */
    public Item(String iname, int iweight, String idescription){
        this.iname = iname;
        this.iweight = iweight;
        this.idescription = idescription;
    }

    /**
     * 获取item名字
     * @return iname
     */
    public String getIname(){
        return iname;
    }

    /**
     * 构造函数
     * @param iname
     */
//    public void setIname(String iname){
//        this.iname = iname;
//    }

    /**
     * 获取item重量
     * @return iweight
     */
    public int getIweight(){
        return iweight;
    }

    /**
     * 构造函数
     * @param iweight
     */
//    public void setIweight(int iweight){
//        this.iweight = iweight;
//    }

    /**
     * 获取item描述信息
     * @return idescription
     */
    public String getIdescription(){
        return idescription;
    }

    /**
     * 构造函数
     * @param idescription
     */
//    public void setIdescription(String idescription){
//        this.idescription = idescription;
//    }
}
