import core.alg.isomorphism.edge.DDPEdge;
import core.constant.JavaTypeDependencies;
import core.helper.Helper;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extracText {
    public static void main(String[] args) {
        /**
         * \<(.+?)\>
         *  \[(.+?)\]
         */
        Pattern pattern = Pattern.compile("^new (.*)\\[");
//        Pattern pattern = Pattern.compile("\\[(.+?)\\]");
        String input = "new ItemElement[] { new Boo()}";
        String[] ss = input.split("\n");
        for (String i : ss) {
            Matcher m = pattern.matcher(i);
            if (m.find())
                System.out.println(m.group(1));
        }

//        System.out.println(Helper.extracType("Object"));
    }


    @Test
    public void testCompareTowSet()
    {
        Set<JavaTypeDependencies> set1 = new HashSet<>();
        set1.add(JavaTypeDependencies.F);
        set1.add(JavaTypeDependencies.G);

        DDPEdge ddpEdge = new DDPEdge(set1);



        Set<JavaTypeDependencies> set2 = new HashSet<>();
        set2.add(JavaTypeDependencies.G);
        set2.add(JavaTypeDependencies.F);
        DDPEdge ddpEdge2 = new DDPEdge(set2);
        System.out.println(ddpEdge.getDependencies().equals(ddpEdge2.getDependencies()));


        for (JavaTypeDependencies dependencies : set2) System.out.println(dependencies);


        for (JavaTypeDependencies dependencies : set1) System.out.println(dependencies);


    }
}
