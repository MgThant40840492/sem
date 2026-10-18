package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get the Sales department
        Department dept = a.getDepartment("Sales");

        // Get and print the current salaries of employees in Sales
        ArrayList<Employee> employees = a.getSalariesByDepartment(dept);
        a.printSalaries(employees);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Employee getEmployee(int ID) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT e.emp_no, e.first_name, e.last_name, "
                            + "t.title, s.salary, d.dept_no, d.dept_name, "
                            + "m.emp_no AS manager_emp_no, "
                            + "m.first_name AS manager_first_name, "
                            + "m.last_name AS manager_last_name "
                            + "FROM employees e "
                            + "JOIN titles t ON e.emp_no = t.emp_no "
                            + "AND t.to_date = '9999-01-01' "
                            + "JOIN salaries s ON e.emp_no = s.emp_no "
                            + "AND s.to_date = '9999-01-01' "
                            + "JOIN dept_emp de ON e.emp_no = de.emp_no "
                            + "AND de.to_date = '9999-01-01' "
                            + "JOIN departments d ON de.dept_no = d.dept_no "
                            + "JOIN dept_manager dm ON de.dept_no = dm.dept_no "
                            + "AND dm.to_date = '9999-01-01' "
                            + "JOIN employees m ON dm.emp_no = m.emp_no "
                            + "WHERE e.emp_no = " + ID;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Return new employee if valid
            if (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("title");
                emp.salary = rset.getInt("salary");

                // Create the employee's department
                Department dept = new Department();
                dept.dept_no = rset.getString("dept_no");
                dept.dept_name = rset.getString("dept_name");
                emp.dept = dept;

                // Create the employee's manager
                Employee manager = new Employee();
                manager.emp_no = rset.getInt("manager_emp_no");
                manager.first_name = rset.getString("manager_first_name");
                manager.last_name = rset.getString("manager_last_name");
                emp.manager = manager;

                return emp;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayEmployee(Employee emp) {
        if (emp != null) {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept.dept_name + "\n"
                            + "Manager: " + emp.manager.first_name + " "
                            + emp.manager.last_name + "\n");
        }
    }

    /**
     * Gets all the current employees and salaries.
     *
     * @return A list of all employees and salaries, or null if there is an error.
     */
    public ArrayList<Employee> getAllSalaries() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                            + "FROM employees, salaries "
                            + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                            + "ORDER BY employees.emp_no ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                employees.add(emp);
            }
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Prints a list of employees.
     *
     * @param employees The list of employees to print.
     */
    public void printSalaries(ArrayList<Employee> employees) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the list
        for (Employee emp : employees) {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
            System.out.println(emp_string);
        }
    }

    /**
     * Gets a department by name, including its current manager.
     *
     * @param dept_name The name of the department.
     * @return The department, or null if it cannot be found.
     */
    public Department getDepartment(String dept_name)
    {
        try
        {
            String strSelect =
                    "SELECT d.dept_no, d.dept_name, "
                            + "m.emp_no, m.first_name, m.last_name "
                            + "FROM departments d "
                            + "JOIN dept_manager dm ON d.dept_no = dm.dept_no "
                            + "AND dm.to_date = '9999-01-01' "
                            + "JOIN employees m ON dm.emp_no = m.emp_no "
                            + "WHERE d.dept_name = ?";

            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1, dept_name);

            ResultSet rset = stmt.executeQuery();

            if (rset.next())
            {
                Department dept = new Department();
                dept.dept_no = rset.getString("dept_no");
                dept.dept_name = rset.getString("dept_name");

                Employee manager = new Employee();
                manager.emp_no = rset.getInt("emp_no");
                manager.first_name = rset.getString("first_name");
                manager.last_name = rset.getString("last_name");

                dept.manager = manager;

                return dept;
            }

            return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get department details");
            return null;
        }
    }

    /**
     * Gets current salaries for employees in a department.
     *
     * @param dept The department whose employees are required.
     * @return A list of employees and their current salaries.
     */
    public ArrayList<Employee> getSalariesByDepartment(Department dept)
    {
        if (dept == null)
        {
            System.out.println("Department not found");
            return null;
        }

        try
        {
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, "
                            + "employees.last_name, salaries.salary "
                            + "FROM employees, salaries, dept_emp, departments "
                            + "WHERE employees.emp_no = salaries.emp_no "
                            + "AND employees.emp_no = dept_emp.emp_no "
                            + "AND dept_emp.dept_no = departments.dept_no "
                            + "AND salaries.to_date = '9999-01-01' "
                            + "AND departments.dept_no = ? "
                            + "ORDER BY employees.emp_no ASC";

            PreparedStatement stmt = con.prepareStatement(strSelect);
            stmt.setString(1, dept.dept_no);

            ResultSet rset = stmt.executeQuery();

            ArrayList<Employee> employees = new ArrayList<Employee>();

            while (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.salary = rset.getInt("salary");
                emp.dept = dept;

                employees.add(emp);
            }

            return employees;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salaries by department");
            return null;
        }
    }
}
