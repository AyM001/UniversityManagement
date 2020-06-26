package Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Students")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cnp ;
    private String firstName;
    private String lastName;

    // Tony ManyToMany

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCnp() {
        return cnp;
    }

    public void setCnp(int cnp) {
        this.cnp = cnp;
    }
}
