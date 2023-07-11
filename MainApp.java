package DaoDtoPattern;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    private static Scanner scan=new Scanner(System.in);
    static Dao dao=new Dao();
    public static void main(String[] args) {
         operations();
    }

    private static void operations()
    {
        boolean status=true;
        while (status) {


            System.out.println("1.Insert");
            System.out.println("2.update records");
            System.out.println("3.Delete Records");
            System.out.println("4.Display records");
            System.out.println("5.Exit");
            System.out.println("Enter the choice");
            int choice = scan.nextInt();

            switch (choice)
            {
                case 1:
                {
                    saveRecords();
                    break;
                }
                case 2:
                {
                    updateData();
                    break;
                }
                case 3:
                {
                    deleteData();
                    break;
                }

                case 4:
                {
                    displayData();
                    break;
                }

                case 5:
                {
                    System.out.println("Thank You For Visiting!");
                    System.exit(0);
                }
            }
        }
    }

    private static void saveRecords()
    {
        System.out.println("Enter the course name");
         String cname=scan.next();
        System.out.println("Enter the course fees");
          double cfee=scan.nextDouble();
          Dto dto=new Dto();
          dto.setCourse_name(cname);
          dto.setCourse_fee(cfee);
          int result=dao.insertRecord(dto);
          if(result>0)
          {
              System.out.println("Data inserted successfully");
          }
          else
          {
              System.out.println("Something went wrong");
          }
    }





    private static void updateData() {

        System.out.println("Enter the old course name");
        String cname = scan.next();
        Dto dto = new Dto();
        dto.setCourse_name(cname);
       boolean res= dao.courseName(dto);
        if (res) {
            System.out.println("Enter the new course name");
            String coursename=scan.next();
            dto.setCourse_name(coursename);
            int result = dao.updateRecord(dto);
            if (result > 0) {
                System.out.println("Updated successfully");
            } else {
                System.out.println("Something went wrong");
            }

        }
        else {
            System.out.println("Course is not available");
        }
    }

    private static void deleteData()
    {
        System.out.println("Enter course name");
        String name=scan.next();
        Dto dto=new Dto();
        dto.setCourse_name(name);
        boolean res=dao.courseName(dto);
        if(res) {
            int result = dao.deleteRecords(dto);
            if (result > 0) {
                System.out.println("Deleted Successfully");
            } else {
                System.out.println("something went wrong");
            }
        }
        else {
            System.out.println("course is not available");
        }
    }

    private static void displayData()
    {


        ArrayList<Dto> result=dao.displayRecord();

        for (Dto data:result) {
            System.out.println(data.getCourse_id()+"\t"+data.getCourse_name()+"\t"+data.getCourse_fee());
    }

    }



}
