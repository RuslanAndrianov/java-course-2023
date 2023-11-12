package edu.hw5;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task8 {

    public static final String REGEXP1 = "^[01]([01]{2})*$";

    public static final String REGEXP2 = "^0([01]{2})*$|^1[01]([01]{2})*$";

    public static final String REGEXP3 = "^1*(01*01*01*)+$";

    public static final String REGEXP4 = "^(0|10|11(0)+|111[01]+)[01]*$|^1$";

    public static final String REGEXP5 = "^1([01]1)*[01]?$";

    public static final String REGEXP6 = "^(0{2,}1?0*)$|^(0+1?0+)$|^(0*1?0{2,})$";

    public static final String REGEXP7 = "^(1?0+)*[01]?$";

}
