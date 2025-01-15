import java.util.*;
import java.util.stream.Collectors;

public class Service {

    IMRepository<Medicine> medicineRepo;
    IMRepository<Patient> patientRepo;

    public Service(IMRepository<Medicine> medicineRepo, IMRepository<Patient> patientRepo) {
        this.medicineRepo = medicineRepo;
        this.patientRepo = patientRepo;
    }


    public List<Patient> getAllCustomers() {
        return patientRepo.getAll();

    }

    public void addPatient(int id, String name,int age,String diagnosis){
        Patient pacient= new Patient(id,name,age,diagnosis);
        patientRepo.create(pacient);
    }


    public boolean deletePatient(int patientId){
        if(patientId<=0){
            return false;
        }
        patientRepo.delete(patientId);
        return true;
    }

    public boolean updatePatientName(int patientId, String newName) {
        if (patientId <= 0 || newName == null || newName.trim().isEmpty()) {
            return false;
        }
        Patient Pacient = patientRepo.read(patientId);
        if (Pacient == null) {
            return false;
        }
        Pacient.setName(newName);
        patientRepo.update(patientId,Pacient);
        return true;
    }
    public boolean updatePacientAge(int pacientId, int newAge) {
        if (pacientId <= 0 || newAge<0) {
            return false;
        }
        Patient Pacient = patientRepo.read(pacientId);
        if (Pacient == null) {
            return false;
        }
        Pacient.setAge(newAge);
        patientRepo.update(pacientId,Pacient);
        return true;
    }

    public boolean updatePatientDiagnosis(int pacientId, String newDiagnosis) {
        if (pacientId <= 0 || newDiagnosis == null || newDiagnosis.trim().isEmpty()) {
            return false;
        }
        Patient Pacient = patientRepo.read(pacientId);
        if (Pacient == null) {
            return false;
        }
        Pacient.setDiagnosis(newDiagnosis);
        patientRepo.update(pacientId,Pacient);
        return true;
    }

    public boolean prescribeMedication(int patientId,String medicationName){
        if (patientId <= 0 && medicationName == null) {
            return false;
        }

        Patient patient = patientRepo.read(patientId);
        Medicine medicine=medicineRepo.readMedicine(medicationName);

        if(patient==null && medicine==null){
            return false;
        }

        for (Patient p:patientRepo.getAll()) {
            if (p.getPrescribedMedicines().contains(medicine)) {
                System.out.println("Meidcation allready prescribed to someone else");
                return false;
            }
        }

        patient.addMedicine(medicine);
        patientRepo.update(patientId,patient);
        return true;
    }


    public boolean removePrescribedMedication(int patientId,String medicationName){
        if (patientId <= 0 && medicationName == null) {
            return false;
        }

        Patient patient = patientRepo.read(patientId);
        Medicine medicine=medicineRepo.readMedicine(medicationName);

        if(patient==null && medicine==null){
            return false;
        }

        if (!patient.getPrescribedMedicines().contains(medicine)) {
            System.out.println("Meidcation not prescribed to this patient");
            return false;
        }

        patient.removeMedicine(medicine);
        patientRepo.update(patientId,patient);
        return true;
    }

    public List<Medicine> getAllMedications(){
        return medicineRepo.getAll();
    }

    public void addMedication(String name, int price, String disease){
        Medicine medicine=new Medicine(name,price,disease);
        medicineRepo.create(medicine);
    }


    public boolean deleteFMedicine(String name){
        if(name==null){
            return false;
        }
        medicineRepo.deleteMedicine(name);
        return true;
    }

    public boolean updateMedicinePrice(String name,int newPrice){
        if(newPrice<=0){
            return false;
        }
        Medicine medicine=medicineRepo.readMedicine(name);
        if(medicine==null){
            return false;
        }
        medicine.setPrice(newPrice);
        medicineRepo.updateMedicine(name,medicine);
        return  true;

    }


    public boolean updateMedicineDisease(String name,String newDisease){
        if(newDisease==null){
            return false;
        }
        Medicine medicine=medicineRepo.readMedicine(name);
        if(medicine==null){
            return false;
        }
        medicine.setDisease(newDisease);
        medicineRepo.updateMedicine(name,medicine);
        return  true;

    }


    public List<Patient> filterPatientsByDiagnosis(String diagnosis){
        return patientRepo.getAll().stream().filter(patient -> patient.getDiagnosis().toLowerCase().contains(diagnosis.toLowerCase())).toList();
    }

    public List<Patient> patientsWithMedicationPrescribedFOrGiveDisease(String disease){
        List<Medicine> medicineForGivenDisease=new ArrayList<>();
        Set<Patient> targetedPatients=new HashSet<>();

        for(Medicine medicine:medicineRepo.getAll()){
            if(Objects.equals(medicine.getDisease(),disease)){
                medicineForGivenDisease.add(medicine);
            }
        }

        for(Patient patient:patientRepo.getAll()){
            for(Medicine medicine:medicineForGivenDisease){
                if(patient.getPrescribedMedicines().contains(medicine)){
                    targetedPatients.add(patient);
                    break;
                }
            }
        }
        return new ArrayList<>(targetedPatients);
    }


    public List<Medicine> sortMedicinesPrescribedForPatientByPriceAscending(int patientId){
        Patient patient=patientRepo.read(patientId);
        return patient.getPrescribedMedicines().stream().sorted(Comparator.comparing(Medicine::getPrice)).collect(Collectors.toList());
    }

    public List<Medicine> sortMedicinesPrescribedForPatientByPriceDescending(int patientId){
        Patient patient=patientRepo.read(patientId);
        return patient.getPrescribedMedicines().stream().sorted(Comparator.comparing(Medicine::getPrice).reversed()).toList();
    }





}
