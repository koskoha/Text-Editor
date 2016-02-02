package spelling;

import java.util.List;

/**
 * Created by klyuc on 11/9/2015.
 */
public class Test {
    public static void main(String[] args) {
        String temp = "kostya";
        for (int i = 0; i < temp.length()+1; i ++) {
            for (int charCode = (int) 'a'; charCode <= (int) 'b'; charCode++) {
                StringBuffer sb = new StringBuffer(temp);
                sb.insert(i,(char) charCode);

                System.out.println(sb.toString());
            }
        }


    }
}
