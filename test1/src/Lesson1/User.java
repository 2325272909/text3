package Lesson1;
import java.util.Scanner;
public abstract class User {
    private String name;
    private String password;
    private String role;

    User(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }

    public boolean changeSelfInfo(String password){
        // 更改密码
        if (DataProcessing.update(name, password, role)){
            this.password=password;
            System.out.println("修改成功");
            return true;
        }else
            System.out.println("修改失败");
        return false;
    }

    public abstract void showMenu();

    public void downloadFile()  {
        System.out.println("请输入档案号:");
        Scanner cin=new Scanner(System.in);
        String a=cin.next();
        System.out.println("下载文件... ...");
        System.out.println("下载成功！");
    }


    public void exitSystem(){
        System.out.println(" 欢迎下次使用 ! ");
        System.exit(0);
    }

    public void showFileList()  {

        System.out.println("以下是文件列表：");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}

