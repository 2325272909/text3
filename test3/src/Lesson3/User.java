package Lesson3;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public abstract class User {
    private String name;
    private String password;
    private String role;

    String uploadpath="D:\\uploadfile\\";
    String downloadpath="D:\\downloadfile\\";



    User(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }

    /**
     *
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean changeSelfInfo(String password) throws SQLException {
            if (DataProcessing.updateUser(name, password, role)){
                this.password=password;
                System.out.println("修改成功");
                return true;
            }else
                System.out.println("修改失败");

        return false;
    }

    public abstract void showMenu();

    /**
     *
     * @param ID
     * @return
     * @throws IOException
     */
   public boolean downloadFile(String ID) throws IOException {
        byte[] buffer=new byte[1024];
        Doc doc=null;
        try {
            doc = DataProcessing.searchDoc(ID);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(doc==null) return false;
        //输入文件对象
        File tempFile=new File(uploadpath+doc.getFilename());
        String filename=tempFile.getName();
        //输入过滤器流，建立在文件流上
        BufferedInputStream infile=new BufferedInputStream(new FileInputStream(tempFile));
        BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(new File(downloadpath+filename)));
        while(true) {
            int byteRead=infile.read(buffer);
            if(byteRead==-1)
                break;
            output.write(buffer,0,byteRead);
        }
        infile.close();
      output.close();
        return true;
    }


    public void exitSystem(){
        System.out.println(" 欢迎下次使用 ! ");
        System.exit(0);
    }

    /**
     *
     * @throws SQLException
     */
    public void showFileList() throws SQLException {

        Enumeration<Doc> e=null;
        try {
            e=DataProcessing.getAllDocs();
        }
        catch(SQLException e1) {
            System.out.println(e1.getMessage());
        }
        Doc doc;
        while(e.hasMoreElements()) {
            doc=e.nextElement();
            System.out.println("ID:"+doc.getID()+"\tCreator:"+doc.getCreator()+"\tTime:"+
                    doc.getTimestamp()+"\tFilename:"+doc.getFilename()+"\tDescription:"+doc.getDescription());
        }
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

