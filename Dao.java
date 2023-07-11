package DaoDtoPattern;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Dao {

    static Connection con;
  static String cname;
    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja9", "root", "madhuri123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String insertrecord = "insert into course values(?,?,?)";
    private static final String updaterecord = "update course set course_name=? where course_name=?";
    private static final String coursename = "select course_name from course";
    private static final String deleterecord = "delete from course where course_name=?";
    private static final String displayrecord="select * from course";

    public int insertRecord(Dto dto) {
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(insertrecord);
            pstmt.setInt(1, 0);
            pstmt.setString(2, dto.getCourse_name());
            pstmt.setDouble(3, dto.getCourse_fee());
            int count = pstmt.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
     public boolean courseName(Dto dto)
     {
         PreparedStatement pstmt;
         ResultSet rs;
         try {
             pstmt=con.prepareStatement(coursename);
             rs=pstmt.executeQuery();
             while (rs.next()) {
                 cname = rs.getString(1);

                 if (cname.equals(dto.getCourse_name())) {
                     return true;
                 }
             }
             return false;
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

     }


    public int updateRecord(Dto dto) {
        PreparedStatement pstmt;
               try {
                pstmt = con.prepareStatement(updaterecord);
                pstmt.setString(1, dto.getCourse_name());
                pstmt.setString(2,cname);
                int count = pstmt.executeUpdate();
                return count;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



    }




    public int deleteRecords(Dto dto) {
        PreparedStatement pstmt;
        try {
            pstmt=con.prepareStatement(deleterecord);
            pstmt.setString(1,cname);
           int count= pstmt.executeUpdate();
           return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Dto> displayRecord() {

        PreparedStatement pstmt;
        ResultSet rs;
        ArrayList<Dto> data=new ArrayList<>();
        try {
           pstmt= con.prepareStatement(displayrecord);
           rs=pstmt.executeQuery();
           Dto dt=null;
           while (rs.next())
           {
               dt=new Dto();
               int id=rs.getInt(1);
              String name=rs.getString(2);
              double fee=rs.getDouble(3);
              dt.setCourse_id(id);
              dt.setCourse_name(name);
              dt.setCourse_fee(fee);
              data.add(dt);
           }
           return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
