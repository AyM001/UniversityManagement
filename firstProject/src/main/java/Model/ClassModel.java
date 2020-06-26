package Model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class ClassModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClass;
    private String name ;

    // Tony aici are OneToMany

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
