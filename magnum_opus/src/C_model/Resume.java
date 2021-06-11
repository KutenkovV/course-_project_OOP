package C_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Resume {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String telnumber;
    private String address;
    private String about_self;
    private String work_exp;
    private String education;
    private String skills;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelnumber() {
        return telnumber;
    }

    public String getAddress() {
        return address;
    }

    public String getAbout_self() {
        return about_self;
    }

    public String getWork_exp() {
        return work_exp;
    }

    public String getEducation() {
        return education;
    }

    public String getSkills() {
        return skills;
    }

    public Resume() {}

    public Resume(int id, String name, String surname, String email, String telnumber, String address, String about_self, String work_exp, String education, String skills) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telnumber = telnumber;
        this.address = address;
        this.about_self = about_self;
        this.work_exp = work_exp;
        this.education = education;
        this.skills = skills;
    }

    public static ObservableList<Resume> resumes_data = FXCollections.observableArrayList();

    public void add(Resume resume) {
        resumes_data.add(resume);
    }

    public void Clear(){
        resumes_data.clear();
    }
}
