public class Main {
    public static void main(String[] args) {

       IMRepository<Patient> patientRepo=new IMRepository<>();
        IMRepository<Medicine> medicineRepo=new IMRepository<>();

        Service service=new Service(medicineRepo,patientRepo);
        Console console=new Console(service);



        Patient p1=new Patient(1,"N1",20,"D1");
        Patient p2=new Patient(2,"N2",20,"D2");
        Patient p3=new Patient(3,"N3",20,"D1");
        Patient p4=new Patient(4,"N4",20,"D3");

        patientRepo.create(p1);
        patientRepo.create(p2);
        patientRepo.create(p3);
        patientRepo.create(p4);



        Medicine m1=new Medicine("M1",10,"Di1");
        Medicine m2=new Medicine("M2",30,"Di2");
        Medicine m3=new Medicine("M3",50,"Di1");
        Medicine m4=new Medicine("M4",60,"Di2");
        Medicine m5=new Medicine("M5",25,"Di3");

        medicineRepo.create(m1);
        medicineRepo.create(m2);
        medicineRepo.create(m3);
        medicineRepo.create(m4);
        medicineRepo.create(m5);

        service.prescribeMedication(1,"M1");
        service.prescribeMedication(1,"M2");
        service.prescribeMedication(2,"M3");
        service.prescribeMedication(2,"M4");
        service.prescribeMedication(3,"M5");


       console.start();


    }
}