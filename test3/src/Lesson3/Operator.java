package Lesson3;

import java.io.*;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Timestamp;

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
                System.out.println("请输入作者名字:");
                String name=cin.next();
                try {
                    uploadFile(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("请输入档案号:");
                String ID=cin.next();
                try {
                    if(downloadFile (ID))
                        System.out.println("下载成功！");
                    else
                        System.out.println("下载失败！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:

                try {
                    showFileList();
                } catch (SQLException t) {
                    t.printStackTrace();
                }
                break;
            case 4:
                System.out.println("请输入更改密码：");
                String password =cin.next();
                try {
                    changeSelfInfo( password);
                } catch (SQLException t) {
                    t.printStackTrace();
                }
                break;
            case 5:
                exitSystem();
            default:
                System.out.println("输入指令错误,请重新输入！");
                break;
        }
        showMenu();
    }


    public void uploadFile(String name) throws IOException {
        System.out.println("请输入源文件名:");
        String filename=cin.next();
        System.out.println("请输入档案号:");
        String ID=cin.next();
        System.out.println("请输入档案描述:");
        String description=cin.next();

        byte[] buffer=new byte[1024];
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        File tempFile=new File(filename.trim());
        String fileName=tempFile.getName();
        try {
            if(DataProcessing.insertDoc(ID,name,timestamp,description,fileName)) ;
            else System.out.println("存入数据库失败！");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        BufferedInputStream infile = null;
        infile = new BufferedInputStream(new FileInputStream(filename));
        BufferedOutputStream targetfile = null;
        targetfile = new BufferedOutputStream(new FileOutputStream(new File(uploadpath+fileName)));
        while(true) {
            int byteRead = 0;
            byteRead = infile.read(buffer);
            if(byteRead==-1)
                break;
            targetfile.write(buffer,0,byteRead);
        }
        infile.close();
        targetfile.close();
    }
}




