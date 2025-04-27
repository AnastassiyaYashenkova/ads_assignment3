import java.util.Random;

public class Main {
    public static Random rand = new Random();

    public static void main(String[] args) {

        MyHashTable<MyTest, Student> table = new MyHashTable<>();

        table.put(new MyTest(1, "abc"), new Student("abc", "def"));

        for (int i = 0; i < 10000; i++) {
            table.put(new MyTest(i + 1, generateRandomString(3)),
                    new Student(generateRandomString(3), generateRandomString(3)));
        }

        myHashTest(table);
    }

    public static void myHashTest(MyHashTable<MyTest, Student> table) {
        int totalBuckets = table.getM();
        int totalElements = table.size();
        double average = (double) totalElements / totalBuckets;

        System.out.println("Number of elements in each bucket:");
        for (int i = 0; i < totalBuckets; i++) {
            int count = table.countElements(i);
            System.out.println("Bucket " + i + ": " + count + " elements");
        }


        System.out.println("Total buckets: " + totalBuckets);
        System.out.println("Total elements: " + totalElements);
        System.out.println("Average elements per bucket: " + average);

        int deviationCount = 0;
        for (int i = 0; i < totalBuckets; i++) {
            int count = table.countElements(i);
            if (Math.abs(count - average) > average * 0.5) {
                deviationCount++;
            }
        }
        System.out.println("Buckets with significant deviation: " + deviationCount);
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return result.toString();
    }
}


