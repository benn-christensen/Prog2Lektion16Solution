package opgave01;

import opgave01.models.LinkedList;

public class Opgave01 {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Januar");
        linkedList.add("Februar");
        linkedList.add("Marts");
        linkedList.add("April");
        linkedList.forEach(m -> System.out.println(m));
        linkedList.remove("Februar");
        System.out.println();
        linkedList.forEach(System.out::println);
        System.out.println("Element på index 2 = " + linkedList.get(2));
        System.out.println("Element på index 2 = " + linkedList.get2(2));

    }
}
