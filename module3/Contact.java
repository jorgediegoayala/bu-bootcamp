public class Contact{
    private String name;
    private String phone;
    
    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    @Override
    public String toString(){
        return name + " | " + phone;
    }

    public Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public boolean hasValidPrefix(){
        if (this.phone == null || this.phone.trim().isEmpty()) {
            return false;
        }
        return this.phone.startsWith("+");
    }
}