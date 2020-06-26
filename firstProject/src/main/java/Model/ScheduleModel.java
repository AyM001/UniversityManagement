package Model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "schedules")
public class ScheduleModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSchedule ;
    private Date date ;
    private Time startHour ;
    private Time endHour;


    // Nu are cascade aici
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="class_id")
    private ClassModel classModel;

    //Tony ManyToMany de courseList

    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartHour() {
        return startHour;
    }

    public void setStartHour(Time startHour) {
        this.startHour = startHour;
    }

    public Time getEndHour() {
        return endHour;
    }

    public void setEndHour(Time endHour) {
        this.endHour = endHour;
    }
}
