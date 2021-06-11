package B_resume_editor;

import C_model.Resume;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import C_model.ImageEditor;

import java.io.*;

import static C_model.JavaConnectSQL.*;

public class CreatingResume_controller {

    @FXML
    private ImageView r_photo_load, photo_load;

    @FXML
    private AnchorPane resume_example, ap, main_data, menu;

    @FXML
    private Pane page_personal_data, page_workExp, page_edu, page_skills;

    @FXML
    private TextArea work_exp_r, education_r, about_self_r, skills_r;

    @FXML
    private Label r_full_name, r_tel_num, r_email, r_address, r_work_exp, r_skills, r_education, r_about_self, lbl_error,
            lbl_work_exp, lbl_skills, lbl_education;

    @FXML
    private TextField name, surname, address, telephone_num, email;

    @FXML
    private JFXButton btn_del_resume, btn_open_resume, btn_createResum, btn_personal_data, btn_workExp, btn_edu, btn_Skills, btn_save, btn_edit, btn_create_resume, btn_menu_first, btn_menu_second;

    @FXML
    private TableView<Resume> resume_table;

    @FXML
    private TableColumn<Resume, Integer> resume_table_id;

    @FXML
    private TableColumn<Resume, String> resume_table_name, resume_table_surename, resume_table_email;

    // Объекты окна и класса редактора
    private final Stage primaryStage = new Stage();
    private final ImageEditor ImgSave = new ImageEditor();
    public static Resume resume;

    @FXML // слушатель, который обрабатывает клик мышкой для загрузки картинки
    private void handleMouseEvent(MouseEvent event) {

        if(event.getSource() == photo_load) {

            ImgSave.OpenImg(photo_load, primaryStage);
        }
    }

    @FXML // тут обрабатываем все наши кнопки
    private void handleButtonAction(ActionEvent event) throws IOException {

        // делаем скриншот области и сохраняем в нужный формат
        if (event.getSource() == btn_save) {

            ImgSave.saveImgAs(ap, primaryStage);
        }

        //открываем существующий шаблон
        if (event.getSource() == btn_open_resume) {

            resume = resume_table.getSelectionModel().getSelectedItem();
            if (resume != null) {
                name.setText(resume.getName());
                surname.setText(resume.getSurname());
                telephone_num.setText(resume.getTelnumber());
                email.setText(resume.getEmail());
                address.setText(resume.getAddress());
                about_self_r.setText(resume.getAbout_self());
                education_r.setText(resume.getEducation());
                work_exp_r.setText(resume.getWork_exp());
                skills_r.setText(resume.getSkills());

                main_data.toFront();
            }
        }

        // разделы для заполнения резюме (при нажатии выводим страницу в топ)
        if (event.getSource() == btn_personal_data) {
            page_personal_data.toFront();
        } else if (event.getSource() == btn_workExp) {
            page_workExp.toFront();
        } else if (event.getSource() == btn_edu) {
            page_edu.toFront();
        } else if (event.getSource() == btn_Skills) {
            page_skills.toFront();
        } else if(event.getSource() == btn_create_resume) {
            main_data.toFront();
        } else if(event.getSource() == btn_menu_first || event.getSource() == btn_menu_second) {

            //чистим конструктор
            name.clear(); surname.clear(); telephone_num.clear();
            email.clear(); address.clear(); about_self_r.clear();
            education_r.clear(); work_exp_r.clear(); skills_r.clear();
            menu.toFront();

        } else if (event.getSource() == btn_edit) {
            main_data.toFront();
        }

        //кнопка удаления
        if(event.getSource() == btn_del_resume) {

            resume = resume_table.getSelectionModel().getSelectedItem();
            if(resume != null) {

                int id = resume.getId();
                DeleteResume(id);
            }
        }

        //кнопка для создания и переноса данных в шаблон
        if (event.getSource() == btn_createResum)  {

            if(address.getText().isEmpty() || email.getText().isEmpty() || telephone_num.getText().isEmpty()
            || name.getText().isEmpty() || surname.getText().isEmpty() || about_self_r.getText().isEmpty()) {

                lbl_error.setText("Заполните все поля помеченные звездочкой '*', это важно для Вашего резюме");
            } else {

                if(resume != null) {
                    UpdateResume(resume.getId(), name.getText(), surname.getText(), email.getText(), telephone_num.getText(), address.getText(), about_self_r.getText(),
                            work_exp_r.getText(), education_r.getText(), skills_r.getText());

                } else {
                    InsertResume(name.getText(), surname.getText(), email.getText(), telephone_num.getText(), address.getText(), about_self_r.getText(),
                            work_exp_r.getText(), education_r.getText(), skills_r.getText());
                }

                // заполняем шаблон
                r_email.setText(String.valueOf(email.getText()));
                r_tel_num.setText(String.valueOf(telephone_num.getText()));
                r_address.setText(address.getText());
                r_full_name.setText(name.getText() + " " + surname.getText());
                r_about_self.setText(about_self_r.getText());
                r_photo_load.setImage(photo_load.getImage());

                // заполняем, если пользователь что-то написал
                if(!skills_r.getText().isEmpty()) {
                    r_skills.setText(skills_r.getText()); lbl_skills.setText("Навыки");
                }

                if(!work_exp_r.getText().isEmpty()) {
                    r_work_exp.setText(work_exp_r.getText()); lbl_work_exp.setText("Опыт работы");
                }

                if(!education_r.getText().isEmpty()) {
                    r_education.setText(education_r.getText()); lbl_education.setText("Образование");
                }
                resume_example.toFront();
            }
        }
    }

    @FXML
    void initialize() {
        Connector();

        resume_table_id.setCellValueFactory(new PropertyValueFactory<Resume, Integer>("id"));
        resume_table_name.setCellValueFactory(new PropertyValueFactory<Resume, String>("name"));
        resume_table_surename.setCellValueFactory(new PropertyValueFactory<Resume, String>("surname"));
        resume_table_email.setCellValueFactory(new PropertyValueFactory<Resume, String>("email"));

        resume_table.setItems(Resume.resumes_data);
    }
}
