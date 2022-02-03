import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class passengerOperation {
//实现订票，退票功能
    public void bookTicket(ArrayList<Flight> flights,String str1,String str2){
        JFrame jFrame = new JFrame("订票窗口");
        jFrame.setBounds(100,50,600,670);
        JPanel jPanel = new JPanel(null);
        JTextArea jTextArea1 = new JTextArea();
        JTextArea jTextArea2 = new JTextArea();
        JLabel jLabel1 = new JLabel("——-—>");
        JLabel jLabel = new JLabel("请再确认起点和终点，并填入购买日期（周*）、座位等级");
        JTextArea time = new JTextArea();
        JTextArea rank = new JTextArea();
        JLabel timestr = new JLabel("购买日期");
        JLabel rankstr = new JLabel("等级");
        JButton jButton = new JButton("确认订票");
        JLabel jLabel2 = new JLabel("姓名");
        JTextArea name = new JTextArea();
        jLabel2.setBounds(150,105,50,20);
        name.setBounds(200,105,100,50);
        jButton.setBounds(210,450,100,50);
        time.setBounds(150,300,100,50);
        rank.setBounds(300,300,100,50);
        jTextArea1.setBounds(150,205,100,50);
        jTextArea2.setBounds(300,205,100,50);
        jLabel.setBounds(150,170,600,20);
        jLabel1.setBounds(250,210,50,20);
        timestr.setBounds(150,280,100,20);
        rankstr.setBounds(300,280,100,20);
        jPanel.add(jLabel2);
        jPanel.add(name);
        jPanel.add(rankstr);
        jPanel.add(rank);
        jPanel.add(jButton);
        jPanel.add(timestr);
        jPanel.add(time);
        jPanel.add(jLabel1);
        jPanel.add(jLabel);
        jPanel.add(jTextArea1);
        jPanel.add(jTextArea2);
        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oriAndEnd;
                if((!(str1.equals(jTextArea1.getText())))||(!(str2
                    .equals(jTextArea2.getText())))){
                    JFrame jf = new JFrame("提示框");
                    JLabel jLabel = new JLabel("找不到该航班，请重新输入");
                    jf.setSize(300,200);
                    jf.add(jLabel);
                    jf.setVisible(true);
                    jf.setLocationRelativeTo(null);
                    jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                }else {
                    oriAndEnd = str1+"-"+str2;
                    int flag=0;
                    for (int i = 0; i < flights.size(); i++) {
                        if(oriAndEnd.equals(flights.get(i).originAndDestination)){
                            if(time.getText().equals(flights.get(i).time)){//输入正确
                                flag=1;
                                break;
                            }else {
                                continue;
                            }
                        }
                    }
                        if (flag == 0) {
                            JFrame jf = new JFrame("提示框");
                            JLabel jLabel = new JLabel("找不到该航班，请重新输入");
                            jf.setSize(300, 200);
                            jf.add(jLabel);
                            jf.setVisible(true);
                            jf.setLocationRelativeTo(null);
                            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        }
                        if (flag == 1) {
                            JFrame jf = new JFrame("提示框");
                            JLabel jLabel = new JLabel("订票成功！");
                            jf.setSize(300, 200);
                            jf.add(jLabel);
                            jf.setVisible(true);
                            jf.setLocationRelativeTo(null);
                            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            //相应操作
                            for (int i = 0; i < flights.size(); i++) {
                                if ((oriAndEnd.equals(flights.get(i).originAndDestination)) &&
                                        time.getText().equals(flights.get(i).time)) {
                                    //找到了对应航班
                                    //int passangerNum = flights.get(i).seatNumber-flights.get(i).seatSurplus;
                                    int intRank = Integer.valueOf(rank.getText());
                                    Passenger passenger = new Passenger(name.getText(), intRank);
                                    flights.get(i).passengers.add(passenger);
                                    flights.get(i).seatSurplus--;
                                    //System.out.println(flights.get(i).passengers[0].getName());
                                }
                            }
                        }
                    }
                }
                //System.out.println(flights.get(0).passengers[0].getName());
        });
    }

    public void refundTicket(ArrayList<Flight> flights){
            JFrame jFrame = new JFrame("退票窗口");
            jFrame.setBounds(100,50,600,670);
            JPanel jPanel = new JPanel(null);
            JTextArea jTextArea1 = new JTextArea();
            JTextArea jTextArea2 = new JTextArea();
            JLabel jLabel1 = new JLabel("——-—>");
            JLabel jLabel = new JLabel("请再确认起点和终点，并填入购买日期（周*）、座位等级");
            JTextArea time = new JTextArea();
            JTextArea rank = new JTextArea();
            JLabel timestr = new JLabel("购买日期");
            JLabel rankstr = new JLabel("等级");
            JButton jButton = new JButton("确认退票");
            JLabel jLabel2 = new JLabel("姓名");
            JTextArea name = new JTextArea();
            jLabel2.setBounds(150,105,50,20);
            name.setBounds(200,105,100,50);
            jButton.setBounds(210,450,100,50);
            time.setBounds(150,300,100,50);
            rank.setBounds(300,300,100,50);
            jTextArea1.setBounds(150,205,100,50);
            jTextArea2.setBounds(300,205,100,50);
            jLabel.setBounds(150,170,600,20);
            jLabel1.setBounds(250,210,50,20);
            timestr.setBounds(150,280,100,20);
            rankstr.setBounds(300,280,100,20);
            jPanel.add(jLabel2);
            jPanel.add(name);
            jPanel.add(rankstr);
            jPanel.add(rank);
            jPanel.add(jButton);
            jPanel.add(timestr);
            jPanel.add(time);
            jPanel.add(jLabel1);
            jPanel.add(jLabel);
            jPanel.add(jTextArea1);
            jPanel.add(jTextArea2);
            jFrame.setContentPane(jPanel);
            jFrame.setVisible(true);
            jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String oriAndEnd;
                    String str1 = jTextArea1.getText();
                    String str2 = jTextArea2.getText();
                        oriAndEnd = str1+"-"+str2;
                        int flag=0;
                        for (int i = 0; i < flights.size(); i++) {
                            if ((oriAndEnd.equals(flights.get(i).originAndDestination))&&
                                    (time.getText().equals(flights.get(i).time))) {
                                    flag = 1;
                                    break;
                            }
                        }
                        if(flag==0){
                            JFrame jf = new JFrame("提示框");
                            JLabel jLabel = new JLabel("找不到该航班，请重新输入");
                            jf.setSize(300,200);
                            jf.add(jLabel);
                            jf.setVisible(true);
                            jf.setLocationRelativeTo(null);
                            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        }
                        if(flag==1){
                            JFrame jf = new JFrame("提示框");
                            JLabel jLabel = new JLabel("退票成功！");
                            jf.setSize(300,200);
                            jf.add(jLabel);
                            jf.setVisible(true);
                            jf.setLocationRelativeTo(null);
                            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            //相应操作
                            for (int i = 0; i < flights.size(); i++) {
                                if((oriAndEnd.equals(flights.get(i).originAndDestination))&&
                                        time.getText().equals(flights.get(i).time)){
                                    //找到了对应航班
                                    //int passangerNum = flights.get(i).seatNumber-flights.get(i).seatSurplus;
                                    for (int j = 0; j < flights.get(i).passengers.size(); j++) {
                                        Passenger passenger = flights.get(i).passengers.get(j);
                                        if(passenger.getName().equals(name.getText())){
                                            flights.get(i).passengers.remove(passenger);
                                            flights.get(i).seatSurplus++;
                                        }
                                    }

                                    //System.out.println(flights.get(i).passengers[0].getName());
                                }
                            }
                        }
                    }
                    //System.out.println(flights.get(0).passengers[0].getName());
            });
    }
    }


