import java.util.List;
import java.util.Scanner;

public class Console {
    private final Service service;
    private final Scanner scanner = new Scanner(System.in);

    public Console(Service service) {
        this.service = service;
    }

    public void start() {

        boolean running = true;
        while (running) {
            System.out.println("Welcome ");
            System.out.println("1. See all Medicines");
            System.out.println("2. Add Medicine");
            System.out.println("3. Delete Medicine");
            System.out.println("4. Update Medicine Price");
            System.out.println("5. Update Medicine Disease");
            ;
            System.out.println("6. See all Patients");
            System.out.println("7. Add Patient");
            System.out.println("8. Delete Patient");
            System.out.println("9. Update Patient Name");
            System.out.println("10. Update Patient Age");
            System.out.println("11. Update Patient diagnosis");
            System.out.println("12. Prescribe medication to patient");
            System.out.println("13. Delete prescribed medicine from patient");
            System.out.println("14. Filter Patients by Diagnosis");
            System.out.println("15.See customers that where prescribed medications for given disease");
            System.out.println("16. Sort patients medications by price ");

            System.out.println("0. Exit");
            System.out.print("Please select an option: ");

            int choice;
            while (true) {
                try {
                    String input = scanner.nextLine();
                    if (!input.matches("\\d+")) {
                        throw new Exception("Input must be a number.");
                    }
                    choice = Integer.parseInt(input);
                    if (choice < 0 || choice > 16) {
                        throw new Exception("Please enter a number between 0 and 4.");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input: " + e.getMessage());
                    System.out.print("Please select a valid option: ");
                }
            }

            switch (choice) {
                case 1 -> printAllMedications();
                case 2 -> addMedication();
                case 3 -> deleteMedication();
                case 4 -> updateMedicationPrice();
                case 5 -> updateMedicationDisease();
                case 6 -> printAllPatients();
                case 7 -> addPatient();
                case 8 -> deletePatient();
                case 9 -> updatePatientName();
                case 10 -> updatePationtAge();
                case 11 -> updatePatientDiagnosis();
                case 12 -> prescribeMedicationToPatient();
                case 13 -> deletePrescribedMedicationForPatitent();
                case 14 -> filterPatientsByDiagnosis();
                case 15 -> customersThatWerePrescribedGivenMedicine();
                case 16 -> sortPaticentsMedicationsByPrice();


                case 0 -> {
                    running = false;
                }
            }
        }


    }


    public void printAllMedications(){
        (service.getAllMedications()).forEach(System.out::println);
    }

    public void addMedication(){
        System.out.println("Enter name, price and disease for new medicine");
        String name=scanner.nextLine();
        int price=scanner.nextInt();
        scanner.nextLine();
        String disease=scanner.nextLine();
        service.addMedication(name,price,disease);
    }

    public void deleteMedication(){
        System.out.println("Enter the name of the medicine to delete");
        String name=scanner.nextLine();
        service.deleteMedicine(name);
    }

    public void updateMedicationPrice(){
        System.out.println("Enter the name of the medication and the new price");
        String name=scanner.nextLine();
        int newPrice= scanner.nextInt();
        scanner.nextLine();
        service.updateMedicinePrice(name,newPrice);
    }

    public void updateMedicationDisease(){
        System.out.println("Enter the name of the medication and the new disease");
        String name=scanner.nextLine();
        String newDisease=scanner.nextLine();
        service.updateMedicineDisease(name,newDisease);
    }

    public void printAllPatients(){
        (service.getAllPatients()).forEach(System.out::println);
    }

    public void addPatient(){
        System.out.println("Enter the id, the name, the age and the diagnosis");
        int id= scanner.nextInt();
        scanner.nextLine();
        String name=scanner.nextLine();
        int age=scanner.nextInt();
        scanner.nextLine();
        String diagnosis=scanner.nextLine();
        service.addPatient(id,name,age,diagnosis);
    }

    public void deletePatient(){
        System.out.println("Enter the id of the patient to delete");
        int id=scanner.nextInt();
        service.deletePatient(id);
    }

    public void updatePationtAge(){
        System.out.println("Enter the id of the patient and the new age");
        int id= scanner.nextInt();
        scanner.nextLine();
        int age= scanner.nextInt();
        scanner.nextLine();

        service.updatePacientAge(id,age);
    }

    public void updatePatientName(){
        System.out.println("Enter the id of the patient and the new name");
        int id= scanner.nextInt();
        scanner.nextLine();
        String name=scanner.nextLine();
        service.updatePatientName(id,name);

    }


    public void updatePatientDiagnosis(){
        System.out.println("Enter the id of the patient and the new diagnosis");
        int id= scanner.nextInt();
        scanner.nextLine();
        String diagnosis=scanner.nextLine();
        service.updatePatientDiagnosis(id,diagnosis);

    }

    public void prescribeMedicationToPatient(){
        System.out.println("Enter the id of the patient and the name of the medication to be prescribed");;
        int id=scanner.nextInt();
        scanner.nextLine();
        String name=scanner.nextLine();
        service.prescribeMedication(id,name);
    }

    public void deletePrescribedMedicationForPatitent(){
        System.out.println("Enter the id of the patient and the name of the medication to be deleted ");;
        int id=scanner.nextInt();
        scanner.nextLine();
        String name=scanner.nextLine();
        service.removePrescribedMedication(id,name);
    }

    public void filterPatientsByDiagnosis(){
        System.out.println("Enter the diagnosis to filter by");
        String diagnosis=scanner.nextLine();
        (service.filterPatientsByDiagnosis(diagnosis)).forEach(System.out::println);
    }

    public void customersThatWerePrescribedGivenMedicine(){
        System.out.println("Enter the disease");
        String disease=scanner.nextLine();
        (service.patientsWithMedicationPrescribedFOrGiveDisease(disease)).forEach(System.out::println);
    }

    public void sortPaticentsMedicationsByPrice(){
        System.out.println("Enter the id of the patient you want to sort the medications of");
        int id= scanner.nextInt();
        scanner.nextLine();
        System.out.println("Do you want to sort the patient medications by price ");
        System.out.println("1.Ascending");
        System.out.println("2.Descending");
        int choice;
        while (true) {
            try {
                String input = scanner.nextLine();
                if (!input.matches("\\d+")) {
                    throw new Exception("Input must be a number.");
                }
                choice = Integer.parseInt(input);
                if (choice < 0 || choice > 2) {
                    throw new Exception("Please enter a number between 0 and 2.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
                System.out.print("Please select a valid option: ");
            }
        }

        switch (choice) {
            case 1 -> {
                (service.sortMedicinesPrescribedForPatientByPriceAscending(id)).forEach(System.out::println);
            }
            case 2 -> (service.sortMedicinesPrescribedForPatientByPriceDescending(id)).forEach(System.out::println);
        }
    }






}
