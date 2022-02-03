import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class flightOperation {
    private void readFlights(ArrayList<Flight> flights) throws IOException {
        //将文件中的航班读入数组
        BufferedReader br = null;
        Reader fr = null;
        try {
            fr = new FileReader("D:\\javacode\\Plane\\line.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(fr);
        String str;
        int count = 0;
        while ((str = br.readLine()) != null) {
            String originAndDestinationstr;
            String flightNumberstr;
            String timestr;
            int seatNum;
            int seatSurplusNum;
            String[] nowEntry;
            if (str.startsWith("﻿")) {
                str = str.substring(1);
            }//排除异常
            if (str.endsWith(" ")) {
                for (int i = str.length() - 1; i >= 0; i--) {
                    if (str.charAt(i) == ' ') {
                        str = str.substring(0, i);
                    } else {
                        break;
                    }
                }
            }
            nowEntry = str.split(" {2}", 5);
            originAndDestinationstr = nowEntry[0];
            flightNumberstr = nowEntry[1];
            timestr = nowEntry[2];
            seatNum = Integer.valueOf(nowEntry[3]);
            seatSurplusNum = Integer.valueOf(nowEntry[4]);
            Flight flight = new Flight(originAndDestinationstr, flightNumberstr,
                    timestr, seatNum, seatSurplusNum);
            flights.add(flight);
        }
    }

    public void showMainWindow(ArrayList<Flight> flights, passengerOperation passengerOperation) throws IOException {
        readFlights(flights);
        JFrame jFrame = new JFrame("主界面");
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(null);
        JLabel jLabel = new JLabel("欢迎进入航空购票系统！");
        JButton button = new JButton("点击按钮查看目前航班");
        button.setLocation(300, 300);
        button.setSize(180, 90);
        jLabel.setLocation(320, 200);
        panel.setLocation(200, 200);
        jLabel.setSize(400, 20);
        JButton jButton = new JButton("个人中心");
        jButton.setBounds(550, 450, 100, 50);
        panel.add(jButton);
        panel.add(jLabel);
        panel.add(button);
        jFrame.setContentPane(panel);
        jFrame.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findFlights(flights, passengerOperation);
            }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //个人中心，查看自己的航班
                JFrame jframe = new JFrame("我的航班");
                JPanel jPanel = new JPanel(null);
                JLabel jl = new JLabel("请输入乘客名字：");
                JButton jb = new JButton("查询");
                jl.setBounds(180,60,600,20);
                jframe.setSize(600,670);
                jframe.setContentPane(jPanel);
                jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jframe.setLocationRelativeTo(null);
                jframe.setVisible(true);
                JTextField name = new JTextField();
                name.setBounds(180,100,200,50);
                jb.setBounds(210, 450, 100, 50);
                jPanel.add(name);
                jPanel.add(jl);
                jPanel.add(jb);
                jb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = name.getText();
                        findPassanger(flights,str,passengerOperation);
                    }
                });
        };});
    }

    public void findPassanger(ArrayList<Flight> flights,String str,passengerOperation passengerOperation){
//        System.out.println(flights.get(0).passengers.get(0).getName());
        int flag = 0;
        String flight = "起点-终点  航班号  时间  座位等级\n";
        for (int i = 0; i < flights.size(); i++) {
            for (int j = 0; j < flights.get(i).passengers.size(); j++) {
                String name = flights.get(i).passengers.get(j).getName();
                if (name.equals(str)){
                    flag = 1;//找到该乘客，接下来保存该乘客购买的航班
                    flight+=flights.get(i).originAndDestination+"  "+flights.get(i).flightId
                            +"  "+flights.get(i).time+"  "+flights.get(i).passengers.get(j).getRank()+ "\n";
                }
            }
        }
        System.out.println("flag:"+flag);
        System.out.println(str);
        JFrame jFrame = new JFrame(str + "的航班情况查询");
        JPanel jPanel = new JPanel(null);
        jFrame.setSize(600,670);
        jFrame.setVisible(true);
        jFrame.setContentPane(jPanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //flag=1;//debug
        if(flag == 0){
            JLabel jLabel = new JLabel("抱歉，未找到您的航班");
            jLabel.setBounds(180,60,600,40);
            jPanel.add(jLabel);
        }
        if(flag==1){
            JTextArea jTextArea = new JTextArea();
            jTextArea.setText(flight);
            jTextArea.setBounds(10,10,560,500);
            jTextArea.setFont(new Font(null, Font.PLAIN, 18));
            jTextArea.setLineWrap(true);
            JButton jButton = new JButton("我要退票");
            jButton.setBounds(210, 550, 100, 50);
            jPanel.add(jButton);
            jPanel.add(jTextArea);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //退票
                    passengerOperation.refundTicket(flights);
                }
            });
        }

    }

    //进入第二个界面的查询航班功能
    public void findFlights(ArrayList<Flight> flights, passengerOperation passengerOperation) {
        JFrame jf = new JFrame("选择航班");
        jf.setSize(600, 670);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        JPanel jp = new JPanel(null);
        JLabel jl = new JLabel("请在下列框中输入您的出发地和目的地");
        jl.setBounds(180, 100, 600, 20);
        JTextField origin = new JTextField();
        JTextField destination = new JTextField();
        origin.setBounds(150, 200, 100, 50);
        destination.setBounds(300, 200, 100, 50);
        JLabel point = new JLabel("——-—>");
        point.setBounds(250, 200, 50, 50);
        JButton reset = new JButton("查询");
        reset.setBounds(210, 450, 150, 70);
        JButton allFlight = new JButton("点击查看本周所有航班");
        allFlight.setBounds(300, 550, 200, 50);
        jp.add(allFlight);
        jp.add(reset);
        jp.add(point);
        jp.add(origin);
        jp.add(destination);
        jp.add(jl);
        jf.setContentPane(jp);
        jf.setVisible(true);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                查找航班
                String str1 = origin.getText();
                String str2 = destination.getText();
                int flag = 0;
                for (int i = 0; i < flights.size(); i++) {
                    if((str1+"-"+str2).equals(flights.get(i).originAndDestination)) {
                        flag = 1;
                    }
                }
//                System.out.println(str1+"-"+str2);
                if(flag == 1){
                    //显示查到的航班
                    JFrame jFrame = new JFrame(str1+"-"+str2+"航班情况一览");
                    jFrame.setBounds(50,30,600,650);
                    JPanel jPanel = new JPanel(null);
                    JTextArea jTextArea = new JTextArea();
                    jTextArea.setBounds(10,10,560,500);
                    jTextArea.setFont(new Font(null,Font.PLAIN,18));
                    JButton jButton = new JButton("我要订票");
                    jButton.setBounds(240,550,100,50);
                    String thisFlight = "起点-终点  航班号  时间  座位总数  剩余座位数\n";
                    for (int i = 0; i < flights.size(); i++) {
                        if((str1+"-"+str2).equals(flights.get(i).originAndDestination)) {
                            thisFlight += flights.get(i).originAndDestination + "  " +
                                    flights.get(i).flightId + "  " + flights.get(i).time +
                                    "  " + flights.get(i).seatNumber + "  " + flights.get(i).seatSurplus + "\n";
                        }

                    }
                    jTextArea.setText(thisFlight);
                    jTextArea.setLineWrap(true);
                    jPanel.add(jTextArea);
                    jPanel.add(jButton);
                    jFrame.setContentPane(jPanel);
                    jFrame.setVisible(true);
                    jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//                    //预订航班
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            passengerOperation.bookTicket(flights,str1,str2);
                        }
                    });
                }
                if(flag == 0){
                    JFrame jFrame = new JFrame("提示框");
                    JLabel jLabel = new JLabel("找不到该航班，请重新输入");
                    jFrame.setSize(300,200);
                    jFrame.add(jLabel);
                    jFrame.setVisible(true);
                    jFrame.setLocationRelativeTo(null);
                    jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                }
//                System.out.println(flag);
            }
        });
        allFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //显示所有航班
                JFrame jF = new JFrame("航班情况一览");
                jF.setBounds(50, 30, 600, 650);
                JTextArea textArea = new JTextArea();
                String allText = "起点-终点  航班号  时间  座位总数  剩余座位数\n";
                for (int i = 0; i < flights.size(); i++) {
                    allText += flights.get(i).originAndDestination + "  " +
                            flights.get(i).flightId + "  " + flights.get(i).time +
                            "  " + flights.get(i).seatNumber + "  " + flights.get(i).seatSurplus + "\n";
                }
                textArea.setText(allText);
                textArea.setLineWrap(true);                         // 自动换行
                textArea.setFont(new Font(null, Font.PLAIN, 18));   // 设置字体

                // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
                JScrollPane scrollPane = new JScrollPane(
                        textArea,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
                );
                jF.setContentPane(scrollPane);
                jF.setVisible(true);
            }
        });
    }
}

