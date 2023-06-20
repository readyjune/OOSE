import java.util.*;

public class lecCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Could change to link list and it would work the same
        List<Integer> myList = new ArrayList<>();
        int number = sc.nextInt();
        while (number >= 0) {
            myList.add(number);
            number = sc.nextInt();
        }
        // This uses iterators behind the scenes
        // for (int number : myList) {
        // System.out.println(number);
        // }
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }
}