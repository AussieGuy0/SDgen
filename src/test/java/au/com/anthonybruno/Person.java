package au.com.anthonybruno;

import java.util.Date;

public class Person {

    private final String name;
    private final int age;
    private final Date birthday;

    public Person(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
}
