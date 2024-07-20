package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

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

        Vet vet1 = new Vet();
        vet1.setFirstName("James");
        vet1.setLastName("Moriarty");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Irene");
        vet2.setLastName("Adler");
        vetService.save(vet2);

        System.out.println("Loading Vets...");
    }
}
