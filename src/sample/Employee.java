package sample;

import java.util.regex.Pattern;

public class Employee {

  private String name;
  private String username;
  private String password;
  private String email;

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

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  private void setUsername(String name) {
    String[] nameSplit = name.split(" ");
    username = (nameSplit[0].charAt(0) + nameSplit[nameSplit.length - 1]).toLowerCase();
  }

  private boolean checkName(String name) {
    return name.contains(" ");
  }

  private void setEmail(String name) {
    String[] nameSplit = name.split(" ");
    email = (nameSplit[0] + "." + nameSplit[nameSplit.length - 1]).toLowerCase()
        + "@oracleacademy.Test";
  }

  private boolean isValidPassword(String password) {
    return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[$&+,:;=?@#|'<>.^*()%!-]).*$", password);
  }

  @Override
  public String toString() {
    return "Employee Details\n" +
        "Name : " + name + "\n" +
        "Username : " + username + "\n" +
        "Email : " + email + "\n" +
        "Initial Password : " + password;
  }
}