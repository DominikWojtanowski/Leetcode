import java.util.ArrayList;
import java.util.HashMap;

class Tuple<X, Y> {
    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X first() {
        return this.x;
    }
    public Y second() {
        return this.y;
    }
}



class TimeMap {


    private final HashMap<String, ArrayList<Tuple<Integer, String>>> keyStorage;
    public TimeMap() {
        this.keyStorage = new HashMap<>();
    }

    public String binarySearch(ArrayList<Tuple<Integer, String>> array, int timestamp) {
        int arrayLow = 0;
        int arrayHigh = array.size() - 1;

        while (arrayLow <= arrayHigh) {
            int mid = arrayLow + (arrayHigh - arrayLow) / 2;
            if (array.get(mid).first() == timestamp) {
                return array.get(mid).second();
            } else if (array.get(mid).first() > timestamp) {
                arrayHigh = mid - 1;
            } else {
                arrayLow = mid + 1;
            }
        }
        if (arrayHigh >= 0 && array.size() > arrayHigh && array.get(arrayHigh).first() <= timestamp) {
            return array.get(arrayHigh).second();
        }
        return "";
    }


    public void set(String key, String value, int timestamp) {
        this.keyStorage.compute(key, (_,v) -> {
            if (v == null) {
                v = new ArrayList<>(53);
            }
            return v;
        }).add(new Tuple<>(timestamp, value));
    }

    public String get(String key, int timestamp) {
        ArrayList<Tuple<Integer,String>> array = this.keyStorage.get(key);
        if (array != null) {
            return binarySearch(array,timestamp);
        } else {
            return "";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. "TimeMap" i []
        TimeMap timeMap = new TimeMap();
        System.out.println("Utworzono TimeMap");

        // 2. "set" i ["foo", "bar", 1]
        timeMap.set("foo", "bar", 1);
        System.out.println("set(\"foo\", \"bar\", 1)");

        // 3. "get" i ["foo", 1]
        String res1 = timeMap.get("foo", 1);
        System.out.println("get(\"foo\", 1) -> Zwróciło: \"" + res1 + "\" (Oczekiwane: \"bar\")");

        // 4. "get" i ["foo", 3]
        String res2 = timeMap.get("foo", 3);
        System.out.println("get(\"foo\", 3) -> Zwróciło: \"" + res2 + "\" (Oczekiwane: \"bar\")");

        // 5. "set" i ["foo", "bar 2", 4]
        timeMap.set("foo", "bar 2", 4);
        System.out.println("set(\"foo\", \"bar 2\", 4)");

        // 6. "get" i ["foo", 4]
        String res3 = timeMap.get("foo", 4);
        System.out.println("get(\"foo\", 4) -> Zwróciło: \"" + res3 + "\" (Oczekiwane: \"bar 2\")");

        // 7. "get" i ["foo", 5]
        String res4 = timeMap.get("foo", 5);
        System.out.println("get(\"foo\", 5) -> Zwróciło: \"" + res4 + "\" (Oczekiwane: \"bar 2\")");
    }
}