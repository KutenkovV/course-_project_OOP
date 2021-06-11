package C_model;

import com.sun.security.jgss.GSSUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaConnectSQL {

    public static Resume resume;
    public static void Connector() {
        try {
            //подключаемся к нашей БД
            String url = "jdbc:sqlserver://WHAT_IS_VLADISL;databaseName=resumeData";
            String username = "sa";
            String password = "123";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM resume_data");

                while (resultSet.next()) {

                    // Получаем данные из БД
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    String telnumber = resultSet.getString("telnumber");
                    String address = resultSet.getString("address");
                    String about_self = resultSet.getString("about_self");
                    String work_exp = resultSet.getString("work_exp");
                    String education = resultSet.getString("education");
                    String skills = resultSet.getString("skills");

                    resume = new Resume(id, name, surname, email, telnumber, address, about_self, work_exp, education, skills);
                    resume.add(resume);
                }

            } catch (Exception ex) {
                System.out.println("Что-то пошло не так: " + ex);
            }

        } catch (Exception ex) {
            System.out.println("Что-то пошло не так: " + ex);
        }
    }

    //добавляем резюме
    public static void InsertResume(String name, String surname, String email, String telnumber, String address, String about_self, String work_exp, String education, String skills) {
        try {
            String url = "jdbc:sqlserver://WHAT_IS_VLADISL;databaseName=resumeData";
            String username = "sa";
            String password = "123";
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                String result = "insert resume_data values (" + "'" + name + "', '" + surname + "', '" + email + "', '" + telnumber + "', '" + address + "', '" + about_self + "', '" + work_exp + "', '" + education +  "', '" + skills + "')";
                statement.executeUpdate(result);

                resume.Clear();
                Connector();
            }

        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    //добавляем резюме
    public static void UpdateResume(int id, String name, String surname, String email, String telnumber, String address, String about_self, String work_exp, String education, String skills) {
        try {
            String url = "jdbc:sqlserver://WHAT_IS_VLADISL;databaseName=resumeData";
            String username = "sa";
            String password = "123";
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                String result = "insertupd" + "'" + id + "', " + "'" + name + "', '" + surname + "', '" + email + "', '" + telnumber + "', '" + address + "', '" + about_self + "', '" + work_exp + "', '" + education +  "', '" + skills + "'";
                statement.executeUpdate(result);

                resume.Clear();
                Connector();
            }

        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    //удаляем резюме
    public static void DeleteResume (int id){
        try {
            String url = "jdbc:sqlserver://WHAT_IS_VLADISL;databaseName=resumeData";
            String username = "sa";
            String password = "123";
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                String result = "delete from resume_data where id = " + id;
                statement.executeUpdate(result);

                resume.Clear();
                Connector();
            }
            // Отлавливаем ошибки при подключении к БД и выводим их на экран
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }
}
