import java.util.ArrayList;

class Person {
    protected String name;
    protected String email;

    public Person() {}
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
    @Override
    public String toString() {
        return "Person: " + this.name;
    }
}

class MyStack {
    private ArrayList<Object> list = new ArrayList<Object>();

    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    public int getSize() {
        return this.list.size();
    }
    public Object peek() {
        return this.list.get(list.size() - 1);
    }
    public Object pop() {
        Object popped = this.list.get(this.list.size() - 1);
        this.list.remove(this.list.size() - 1);
        return popped;
    }
    public void push(Object o) {
        this.list.add(o);
    }
    @Override /** Override the toString in the Object class */
    public String toString() {
        String s = "";
        for (Object o : this.list) {
            s += o.toString();
            s += " ";
        }
        return s;
    }
}

public class MyStackTest {

    public void test1() {
        MyStack m = new MyStack();

        System.out.println("1.1: " + (0 == m.getSize()));
        Person p = new Person("James", "j@nku.edu");
        m.push(p);
        System.out.println("1.2: " + (p == m.peek()));
        System.out.println("1.3: " + (1 == m.getSize()));
        System.out.println("1.4: " + (!m.isEmpty()));
        System.out.println("1.5: " + (m.toString().equals("Person: James ")));
        System.out.println("1.6: " + (p == m.pop()));
        System.out.println("1.7: " + (0 == m.getSize()));
        System.out.println("1.8: " + (m.isEmpty()));
    }

    public static void main(String[] args) {
        MyStackTest t = new MyStackTest();
        t.test1();
    }
}

/** You should get eight trues
 * 1.1: true
 * 1.2: true
 * 1.3: true
 * 1.4: true
 * 1.5: true
 * 1.6: true
 * 1.7: true
 * 1.8: true
*/