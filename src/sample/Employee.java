/**
 * @author: Alexander Thieler
 * @description: This file contains the Employee class
 * @Date: Dec 7 2019
 */

package sample;

import java.util.regex.Pattern;

/**
 * A class that represents an Employee
 */
public class Employee {

  private String name;
  private String username;
  private String password;
  private String email;

  /**
   * Constructor for the Employee class
   *
   * @param name     of employee
   * @param password of employee
   */
  public Employee(String name, String password) {
    this.name = name;
    if (checkName(name)) {
      setUsername(name);
      setEmail(name);
    } else {
      username = "default";
      email = "user@oracleacademy.Test";
    }
    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }

  /**
   * Returns employee's username
   *
   * @return username of employee
   */
  public String getUsername() {
    return username;
  }

  /**
   * Returns employee's password
   *
   * @return password of employee
   */
  public String getPassword() {
    return password;
  }

  /**
   * Returns employee's email
   *
   * @return email of employee
   */
  public String getEmail() {
    return email;
  }

  /**
   * Splits the name of the employee and creates a username based on the first and last name
   *
   * @param name full name of employee
   */
  private void setUsername(String name) {
    String[] nameSplit = name.split(" ");
    username = (nameSplit[0].charAt(0) + nameSplit[nameSplit.length - 1]).toLowerCase();
  }

  /**
   * Verifies that user entered at least a first and last name
   *
   * @param name full name of employee
   * @return true if name contains a space else false
   */
  private boolean checkName(String name) {
    return name.contains(" ");
  }

  /**
   * Creates a email for the user based on his/her first and last name
   *
   * @param name full name of employee
   */
  private void setEmail(String name) {
    String[] nameSplit = name.split(" ");
    email = (nameSplit[0] + "." + nameSplit[nameSplit.length - 1]).toLowerCase()
        + "@oracleacademy.Test";
  }

  /**
   * Makes sure that the password entered by the user follows the correct pattern
   *
   * @param password entered by user
   * @return true if password is valid else false
   */
  private boolean isValidPassword(String password) {
    return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[$&+,:;=?@#|'<>.^*()%!-]).*$", password);
  }

  /**
   * Creates a string that contains information about the employee
   *
   * @return string with employee information
   */
  @Override
  public String toString() {
    return "Employee Details\n" +
        "Name : " + name + "\n" +
        "Username : " + username + "\n" +
        "Email : " + email + "\n" +
        "Initial Password : " + password;
  }
}