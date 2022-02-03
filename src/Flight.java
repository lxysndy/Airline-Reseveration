import java.util.ArrayList;

/*航空机票订票系统设计（四选一选做部分）
（1）设计每条航线所涉及的信息，如终点站名、航班号、飞机号、
飞机周日（星期几）、 乘员定额、余票量、订定票的客户名单（包括姓名、订票量、舱位等级 1，2 或 3）等；
 （2）结合基本操作的单链表、队列、二叉树等数据结构以及排序算法，设计机票系统 的查询、订票、退票等功能；
  （3）设计并实现人机交互友好的界面或菜单。
 */
public class Flight {//定义航班类
    public String originAndDestination;
    public String flightId;
    public String time;
    public int seatNumber;
    public int seatSurplus;

    public ArrayList<Passenger> passengers = new ArrayList<>();

    public Flight(String originAndDestination, String flightNumber,
                  String time, int seatNumber, int seatSurplus) {
        this.originAndDestination = originAndDestination;
        this.flightId = flightNumber;
        this.time = time;
        this.seatNumber = seatNumber;
        this.seatSurplus = seatSurplus;
    }

    public Flight() {
    }

}
