package edu.hw3;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {
    public static String @NotNull [] clusterize(@NotNull String str) {

        String[] brackets = str.trim().replace(" ", "").split("");
        StringBuilder cluster = new StringBuilder();
        ArrayList<String> clusters = new ArrayList<>();
        int counter = 0;

        for (String bracket : brackets) {
            switch (bracket) {
                case "(":
                    counter++;
                    cluster.append(bracket);
                    break;
                case ")":
                    counter--;
                    cluster.append(bracket);
                    break;
                default:
                    return new String[]{};
            }

            if (counter == 0) {
                clusters.add(cluster.toString());
                cluster.setLength(0);
            }
        }

        String[] result = new String[clusters.size()];
        return clusters.toArray(result);
    }
}
