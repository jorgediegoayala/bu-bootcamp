import java.util.ArrayList;
import java.util.HashMap;

public class ContactManager {
    public static void main(String[] args){
        
 
        // Step 4: add contacts here 
        HashMap<String, Contact> contacts = GetContacts();

        // Step 5: look up a contact 
        Contact ada = contacts.get("Ada Lovelace");
        if (ada == null)
            System.out.println("Contact Ada not found.");
        else
            System.out.println("Contact found :" + ada.toString());
        // Step 6: print sorted list 
       ArrayList<Contact> sorted = new ArrayList<>(contacts.values()); 
       sorted.sort((a, b) -> a.getName().compareTo(b.getName()));
       for (Contact contact : sorted) {
            System.out.println(contact.toString());
       }  
    }

    private static HashMap<String, Contact> GetContacts(){
        Contact ada = new Contact("Ada Lovelace","+1 617 555 0101"); 
        Contact donald = new Contact("Donald Duck","+1 301 445 0202"); 
        Contact mickey = new Contact("Mickey Mouse","+1 302 446 0303");
        Contact minnie = new Contact("Minnie Mouse","+1 303 447 0404");
        Contact elmo = new Contact("Elmo Sesame","+1 304 448 0404"); 
        HashMap<String, Contact> contacts = new HashMap<>(); 
        contacts.put(ada.getName(), ada);
        contacts.put(donald.getName(), donald);
        contacts.put(mickey.getName(), mickey);
        contacts.put(minnie.getName(), minnie);
        contacts.put(elmo.getName(), elmo);
        return contacts;
    }
}
