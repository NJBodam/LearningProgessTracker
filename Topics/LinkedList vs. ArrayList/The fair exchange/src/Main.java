import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ListOperations {
    public static void changeHeadsAndTails(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        String linkedListlast = linkedList.removeLast();
        String linkdlistFirst = linkedList.removeFirst();
        String arrayListLast = arrayList.remove(arrayList.size() - 1);
        String arraylistFirst = arrayList.remove(0);

        arrayList.add(0, linkdlistFirst);
        arrayList.add(arrayList.size(), linkedListlast);

        linkedList.addLast(arrayListLast);
        linkedList.addFirst(arraylistFirst);
    }


}