package Lesson1;

import java.util.Scanner;

public  class Main{
    public static void main(String[] args) {

        String tip_system="档案系统";
        String tip_menu="请选择菜单：";
        String infos="****欢迎进入"+tip_system+"*****\n\t   "+
                "1.登录\n   \t   2.退出\n"+
                "****************************";

        int a;
        do
        {
            System.out.println(infos);
            System.out.println(tip_menu);

            Scanner cin=new Scanner(System.in);
            a=cin.nextInt();
            switch (a) {
                case 1 :{
                    System.out.println("请输入用户名：");
                    String name = cin.next();
                    if (DataProcessing.searchUser(name) != null) {
                        System.out.println("请输入密码：");
                        String password = cin.next();
                        if (DataProcessing.search(name, password) != null)
                            DataProcessing.search(name, password).showMenu();
                        else System.out.println("密码错误！");
                    } else {
                        System.out.println("用户不存在！");
                    }
                }
                case 2 :
                    System.exit(0);
                default :
                    System.out.println("输入指令错误,请重新输入！");
            }
        }
        while(a!=0);
    }
}



