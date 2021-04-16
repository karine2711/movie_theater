import java.util.Objects;

public class Director {
    private String firstName;
    private String lastName;


    public Director (String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Director(Director director){
        firstName = director.firstName;
        lastName = director.lastName;
    }

    public String getFirstName () {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName (String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String toString(){
        return (firstName + " " + lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(firstName, director.firstName) && Objects.equals(lastName, director.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
