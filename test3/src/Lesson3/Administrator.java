package Lesson3;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;


public  class Administrator extends User{
    private Scanner cin;

    Administrator(String name, String password, String role) {
        super(name,password,role);
        cin=new Scanner(System.in);
    }

    @SuppressWarnings("resource")
    public  void changeUserInfo() {

        // 更改密码
        System.out.println("请输入用户名：");
        String name=cin.next();

        System.out.println("请输入新的用户密码：");
        String password1=cin.next();

        System.out.println("请输入新的用户角色：");
        String role=cin.next();

        try {
            if (DataProcessing.updateUser( name, password1,role))
            {
                System.out.println("修改成功");

            }else
                System.out.println("修改失败");
        } catch (SQLException t) {
            t .printStackTrace();
        }

    }

    //删除
    @SuppressWarnings("resource")
    public void delUser() {
        System.out.println("请输入要删除的用户名：");
        String name1=cin. next();
        try {
            if ( DataProcessing.deleteUser(name1)){
                System.out.println("删除成功！");
            }else
                System.out.println("删除失败!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //添加
    @SuppressWarnings("resource")
    public void addUser() {
        System.out.println("请输入要增加的用户姓名:");
        String name1=cin. next();

        System.out.println("请输入要增加的用户密码:");
        String password=cin. next();

        System.out.println("请输入要增加的用户角色:");
        String role=cin. next();
        try {
            if(DataProcessing.insertUser(name1,password,role)) {
                System.out.println("增加用户"+name1+"成功！");
            }
            else System.out.println("增加用户"+name1+"失败！");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //列表
    public void listUser() {

        Enumeration<User> e  = null;
        try {
            e = DataProcessing.getAllUser();
        } catch (SQLException t ) {
            t .printStackTrace();
        }
        User user;
        while(e.hasMoreElements()) {
            user=e.nextElement();
            System.out.println("姓名："+user.getName()+"  密码："+user.getPassword()+"  角色："+user.getRole());
        }

    }

    public void showMenu()
    {
        System.out.println("******欢迎进入系统管理员员菜单******");
        System.out.println("  1.修改用户\n  2.删除用户\n  3.新增用户\n  4.列出用户\n  5.下载文件\n  6.文件列表\n  7.修改（本人）密码 \n  8.退出");
        System.out.println("请选择菜单：");
        @SuppressWarnings("resource")
        int a=cin.nextInt();
        switch(a) {
            case 1:
                changeUserInfo();
                break;
            case 2:
                delUser() ;
                break;
            case 3:
                addUser ();
                break;
            case 4:
                listUser();
                break;
            case 5:
                System.out.println("请输入档案号:");
                String ID=cin.next();
                try {

                    if(downloadFile(ID))
                        System.out.println("下载成功！");
                    else
                        System.out.println("下载失败!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                try {
                    showFileList();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 7:
                System.out.println("请输入更改后的密码：");
                String  passwords=cin. nextLine();
                try {
                    changeSelfInfo(passwords);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case 8:
                exitSystem();
            default:
                System.out.println("输入指令错误,请重新输入！");
                break;

        }
        showMenu();
    }


}
