public class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return "Student {first name: " + firstName + ", last name: " + lastName + "}" ;
    }
}
