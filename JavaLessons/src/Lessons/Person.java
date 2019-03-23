package Lessons;

/**
 * Author: Yohanna Taaptue
 * Tutorial for beginners
 * !
 */
public class Person {

    /* Instance: Members of the class or objects of the class*/

    //Instance variable
    private Name personName;
    // class variable: private static Name personName

    //default constructor: no parameters initialise an object
    public Person(){
        /*
        Empty on purpose
         */
    }
    //constructor with a parameter
        public Person(Name personName){
        //writing personName = personName; is wrong cuz the objects calls itself
        //the correct way is by changing the name of the parameter i.e Name PersonName for ex or by using this

        this.personName = personName; // java knows we are talking about the instance Name
    }
    //Instance method
    public String helloWorld(){
        return "Hello World";
    }

    public String hello(String marcus){
        return
    }
}
