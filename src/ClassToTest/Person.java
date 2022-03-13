package ClassToTest;

public class Person{
    private String name;
    private int age;

    public Person(){
        name = "";
        age = 0;
    }

    public Person(String s, int number){
        this.name = s;
        this.age = number;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int number) {
        this.age = number;
    }

    public int getAge(){
        return this.age;
    }

}
