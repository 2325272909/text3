package Lesson1;

import java.util.Scanner;

public  class Operator extends User{
    private Scanner cin;

    Operator(String name, String password, String role) {
        super(name, password, role);
        cin=new Scanner(System.in);

    }


    public void showMenu() {
        System.out.println("*****欢迎进入档案录入人员菜单******");
        System.out.println("   1.上传文件\n   2.下载文件\n   3.文件列表\n   4.修改密码\n   5.退出\n");
        System.out.println("请选择菜单：");

        int a=cin.nextInt();
        switch(a) {
            case 1:
                uploadFile();
                break;
            case 2:

                downloadFile ();
                break;
            case 3:

                showFileList();
                break;
            case 4:
                System.out.println("请输入更改密码：");
                String password =cin.next();
                changeSelfInfo( password);
                break;
            case 5:
                exitSystem();
            default:
                System.out.println("输入指令错误,请重新输入！");
                break;
        }
        showMenu();
    }


    private void uploadFile() {
        System.out.println("请输入源文件名:");
        String a=cin.next();
        System.out.println("请输入档案号:");
        String s=cin.next();
        System.out.println("请输入档案描述:");
        String d=cin.next();

        System.out.println("上传成功!");

    }



}
