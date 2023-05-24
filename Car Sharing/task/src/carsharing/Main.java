package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:C:\\Users\\Lorenzo\\IdeaProjects\\Car Sharing\\Car Sharing\\task\\src\\carsharing\\db\\";

    public static void main(String[] args) {

        String db = DB_URL;
        if(args[0].equals("-databaseFileName")) {
           db += args[1];
        } else db += "Testing";

        initTables(db);
        Scanner in = new Scanner(System.in);
        String input;

        do {
            System.out.println("1. Log in as a manager");
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit");
            input = in.nextLine();

            switch (input) {
                case "1" -> manager(db);
                case "2" -> readCustomerTable(db);
                case "3" -> createNewCustomer(db);
            }
        } while(!input.equals("0"));

    }
    public static void initTables(String url){
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(url);
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            //creates COMPANY table
            String sql =  "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE not NULL)";

            System.out.println("Created COMPANY in given database...");
            stmt.executeUpdate(sql);

            //creates CAR table
            sql =  "CREATE TABLE IF NOT EXISTS CAR " +
                    "(ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE not NULL," +
                    "COMPANY_ID INTEGER NOT NULL," +
                    "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID))";

            stmt.executeUpdate(sql);
            System.out.println("Created CAR in given database...");

            //creates CUSTOMER table
            sql =  "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "(ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE not NULL," +
                    "RENTED_CAR_ID INTEGER," +
                    "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID))";
            stmt.executeUpdate(sql);
            System.out.println("Created CUSTOMER in given database...");

            // STEP 4: Clean-up environment
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }
    public static void createNewCompany(String url) {
        Scanner in = new Scanner(System.in);
        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);
            System.out.println("Enter the company name:");
            String input = in.nextLine();
            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO COMPANY (NAME)" + "VALUES ('"+ input +"')";
            System.out.println("The company was created!");
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }
    public static void createNewCar(String url, int companyId) {
        Scanner in = new Scanner(System.in);
        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);
            System.out.println("Enter the car name:");
            String input = in.nextLine();
            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO CAR (NAME, COMPANY_ID)" + "VALUES ('"+ input +"', " + companyId + ")";
            System.out.println("The car was added!");
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }
    public static void createNewCustomer(String url) {
        Scanner in = new Scanner(System.in);
        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);
            System.out.println("Enter the customer name:");
            String input = in.nextLine();
            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO CUSTOMER (NAME, RENTED_CAR_ID)" + "VALUES ('"+ input +"', NULL )";
            System.out.println("The customer was added!");
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }
    public static int readCompanyTable(String url) {
        Scanner in = new Scanner(System.in);
        Connection conn = null;
        Statement stmt = null;
        List<String> names = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT ID, NAME FROM COMPANY ORDER BY ID ASC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            boolean something = false;
            System.out.println("Choose a company:");
            while(rs.next()) {
                something = true;
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                names.add(name);
                // Display values
                System.out.print(id + ". ");
                System.out.println(name);
            }
            if(!something) {
                System.out.println("The company list is empty");
                return 0;
            }
            System.out.println("0. Back");
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        int index = Integer.parseInt(in.nextLine());
        if(index == 0) return 0;
        System.out.println(names.get(index - 1) + " company:");
        return index;
    }
    public static String readCarTable(String url,int companyId, Boolean customer) {
        Connection conn = null;
        Statement stmt = null;
        Scanner in = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        int count = 1;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT ID, NAME FROM CAR " +
                    "WHERE COMPANY_ID = " + companyId + " AND " +
                    "ID NOT IN " +
                        "(SELECT RENTED_CAR_ID FROM CUSTOMER " +
                        "WHERE RENTED_CAR_ID IS NOT NULL)" +
                    "ORDER BY ID ASC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            boolean something = false;
            while(rs.next()) {
                if(customer && !something) System.out.println("Choose a car:");
                something = true;
                // Retrieve by column name
                int id  = rs.getInt("ID");

                String name = rs.getString("NAME");
                names.add(name);
                // Display values
                System.out.print(count + ". ");
                count++;
                System.out.println(name);
            }
            if(!something) {
                System.out.println("The car list is empty!");
                return "";
            }
            if(customer) {
                return names.get(in.nextInt() - 1);
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        return "";
    }

    public static void readCustomerTable(String url) {
        Scanner in = new Scanner(System.in);
        Connection conn = null;
        Statement stmt = null;
        List<String> names = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT ID, NAME FROM CUSTOMER ORDER BY ID ASC";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            boolean something = false;
            System.out.println("Choose a customer:");
            while(rs.next()) {
                something = true;
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                names.add(name);
                // Display values
                System.out.print(id + ". ");
                System.out.println(name);
            }
            if(!something) {
                System.out.println("The customer list is empty!");
                return;
            }
            System.out.println("0. Back");
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        int index = Integer.parseInt(in.nextLine());
        if(index == 0) return;
        customer(url, names.get(index - 1));
    }

    public static void manager(String url) {
        Scanner in = new Scanner(System.in);
        String input;
        do {
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");
            input = in.nextLine();

            switch (input) {
                case "1":
                    int id = readCompanyTable(url);
                    if (id == 0) break;
                    String input2;

                    do {
                        System.out.println("1. Car list");
                        System.out.println("2. Create a car");
                        System.out.println("0. Back");
                        input2 = in.nextLine();
                        switch (input2) {
                            case "1" -> readCarTable(url, id, false);
                            case "2" -> createNewCar(url, id);
                        }
                    } while (!input2.equals("0"));
                    break;

                case "2":
                    createNewCompany(url);
                    break;

            }
        } while(!input.equals("0"));
    }
    public static void customer(String url, String name) {
        Scanner in = new Scanner(System.in);

        String input;
        do {
            System.out.println(name + " company:");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a rented car");
            System.out.println("3. My rented car");
            System.out.println("0. Back");
            input = in.nextLine();
            switch (input) {
                case "1" -> rentACar(url, name);
                case "2" -> returnCar(url, name);
                case "3" -> myRentedCar(url, name);
            }
        }
        while (!input.equals("0"));
    }
    public static void rentACar(String url, String name) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);

            // STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE NAME = '" + name + "'" ;
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String carId = rs.getString("RENTED_CAR_ID");
            // STEP 4: Extract data from result set


            if(carId != null) {
                System.out.println("You've already rented a car!");
                return;
            }

            int companyId = readCompanyTable(url);
            if(companyId == 0) return;
            String carName = readCarTable(url, companyId, true);
            sql = "SELECT ID FROM CAR WHERE NAME = '" + carName + "'";
            rs = stmt.executeQuery(sql);
            rs.next();
            int newCarId = rs.getInt("ID");
            sql = "UPDATE CUSTOMER " +
                    "SET RENTED_CAR_ID = " + newCarId +
                    " WHERE NAME = '" + name + "'" ;
            stmt.executeUpdate(sql);
            System.out.println("You rented '" + carName + "'");
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try


    }
    public static void returnCar(String url, String name) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);

            // STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE NAME = '" + name + "'" ;
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String carId = rs.getString("RENTED_CAR_ID");
            // STEP 4: Extract data from result set
            if(carId == null) {
                System.out.println("You didn't rent a car!");
                return;
            }



            sql = "UPDATE CUSTOMER " +
                    "SET RENTED_CAR_ID = NULL " +
                    "WHERE NAME = '" + name + "'" ;
            stmt.execute(sql);
            System.out.println("You've returned a rented car!");
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }
    public static void myRentedCar(String url, String name) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(url);

            // STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE NAME = '" + name + "'" ;
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String carId = rs.getString("RENTED_CAR_ID");
            if(carId == null) {
                System.out.println("You didn't rent a car!");
                return;
            }


            sql = "SELECT NAME, COMPANY_ID FROM CAR WHERE ID = " + carId ;
            rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println("Your rented car:");
            System.out.println(rs.getString("NAME"));
            int companyId = rs.getInt("COMPANY_ID");
            System.out.println("Company:");
            sql = "SELECT NAME FROM COMPANY WHERE ID = " + companyId ;
            rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString("NAME"));

            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }


}
