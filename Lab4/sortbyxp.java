package Ap_assignment.Lab4;

import java.util.Comparator;

public class sortbyxp implements Comparator<Side_kick>{
    public int compare(Side_kick s1,Side_kick s2){
        return s2.getXp()-s1.getXp();
    }
}