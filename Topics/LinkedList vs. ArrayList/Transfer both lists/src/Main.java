import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

}