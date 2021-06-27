package Lesson3;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

//档案浏览人员

public  class Browser extends User {


    Browser(String name, String password, String role) {
        super(name, password, role);
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("resource")
    public void showMenu() {

        System.out.println("*****欢迎进入档案浏览员菜单******");
        System.out.println("   1.下载文件\n   2.文件列表\n   3.修改密码\n   4.退出\n");
        System.out.println("请选择菜单：");

        Scanner cin=new Scanner(System.in);
        int a=cin.nextInt();
        switch(a) {
            case 1:
                System.out.println("请输入作者名字:");
                String name=cin.next();
                try {
                    if(downloadFile(name))
                        System.out.println("下载成功！");
                    else
                        System.out.println("下载失败!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:

                try {
                    showFileList();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 3:
                System.out.println("请输入更改密码：");
                Scanner cin11 =new Scanner(System.in);
                String password =cin11.next();
                try {
                    changeSelfInfo( password);
                } catch (SQLException t) {
                    t.printStackTrace();
                }
                break;
            case 4:
                exitSystem();
            default:
                System.out.println("输入指令错误,请重新输入！");
                break;

        }
        showMenu();



    }

}
