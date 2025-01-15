import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String diagnosis;
    private List<Medicine> prescribedMedicines;


    public Patient(int id, String name, int age, String diagnosis) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.prescribedMedicines=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Medicine> getPrescribedMedicines() {
        return prescribedMedicines;
    }

    public void setPrescribedMedicines(List<Medicine> prescribedMedicines) {
        this.prescribedMedicines = prescribedMedicines;
    }

    public void addMedicine(Medicine medicine){
        prescribedMedicines.add(medicine);
    }

    public void removeMedicine(Medicine medicine){
        prescribedMedicines.remove(medicine);
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescribedMedicines=" + prescribedMedicines +
                '}';
    }
}
