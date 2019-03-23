package Lessons;

import Lessons.Person;


public class PersonTest {

    @Test
    public void shouldreturnHelloWorld(){
        Person marcus = new Person();
        assertEquals("Hello World", marcus.helloWorld());
    }

    @Test
    public void shouldReturnHelloMarcus(){
        Person person = new Person();
        /*
        On the object person we want to call a method hello
         */
        assertEquals("Hello Marcus", person.hello("Marcus));
    }
}
