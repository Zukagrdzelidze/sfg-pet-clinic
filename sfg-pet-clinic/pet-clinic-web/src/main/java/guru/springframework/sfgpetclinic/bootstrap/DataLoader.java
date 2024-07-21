package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialitiesService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Sherlock");
        owner1.setLastName("Holmes");
        owner1.setAddress("Baker Streer 221B");
        owner1.setCity("London");
        owner1.setPhone("221221221");

        Pet sherlockSPet = new Pet();
        sherlockSPet.setPetType(savedDogPetType);
        sherlockSPet.setOwner(owner1);
        sherlockSPet.setBirthDate(LocalDate.now());
        sherlockSPet.setName("Toby");
        owner1.getPets().add(sherlockSPet);

        ownerService.save(owner1);


        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Watson");
        owner2.setAddress("Baker Streer 221B");
        owner2.setCity("London");
        owner2.setPhone("221221221");
        ownerService.save(owner2);

        System.out.println("Loading Owners...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiologySpeciality = specialitiesService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgerySpeciality = specialitiesService.save(surgery);


        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistrySpeciality = specialitiesService.save(dentistry);


        Vet vet1 = new Vet();
        vet1.setFirstName("James");
        vet1.setLastName("Moriarty");
        vet1.getSpecialities().add(savedRadiologySpeciality);
        vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setFirstName("Irene");
        vet2.setLastName("Adler");
        vet2.getSpecialities().add(savedDentistrySpeciality);
        vetService.save(vet2);

        System.out.println("Loading Vets...");
    }
}
