import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringLearning {

    public static void main(String[] args) {
        String[] strings = new String[5];
        for (int i = 0; i < 5; i++) {
            strings[i] = "hello" + i;
        }

        String[] s1 = Arrays.copyOf(strings,6);
        for (int i = 5; i >0; i--) {
            s1[i] = s1[i-1];
        }
        s1[0] = "hello";
        for (String s:strings
             ) {
            System.out.println(s);
        }
    }
}
