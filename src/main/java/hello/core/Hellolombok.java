package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Hellolombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        Hellolombok hellolombok=new Hellolombok();
        hellolombok.setName("정준일");
        hellolombok.setAge(99);
        System.out.println(hellolombok.getName());
        System.out.println(hellolombok.getAge());
        System.out.println(hellolombok.toString());
    }
}
