/**
 * Compares objects of name age class
 */
public class NameAgeComparator implements java.util.Comparator<NameAge> {

    @Override
    public int compare(NameAge o1, NameAge o2) {
        int age1 = o1.getAge();
        int age2 = o2.getAge();
        String name1 = o1.getName();
        String name2 = o2.getName();
        if (age1 != age2) {
            return age1 - age2;
        } else {
            return name1.compareTo(name2);
        }
    }
}
