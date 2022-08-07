import java.util.*;

class ListOperations {
    public static void transferAllElements(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        int size = arrayList.size();
        int count = 0;
        while (count != size) {
            linkedList.addLast(arrayList.get(count));
            arrayList.set(count, linkedList.removeFirst());
            count++;
        }
    }

    public static void main(String[] args) {


        Map<Integer, String> characters = new HashMap<>();

        characters.put(1000, "Cinderella");
        characters.put(2000, "Prince");
        characters.put(3000, "Evil stepmother");

        System.out.println(characters);

    }

}