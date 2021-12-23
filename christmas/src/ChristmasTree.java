import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ChristmasTree {
    public static void main(String[] args){
        HashMap<String, Integer> toys = new HashMap<>() {{

            put("%", 5);

            put("O", 6);

            put("^", 3);

        }};
        printChristmasTree(7, toys);
    }

    public static void printChristmasTree(int n, HashMap<String, Integer> toys){
        String[][] theArrayOfTriangles = getArrayOfTriangles(n, toys);
        int theLargestBase = (theArrayOfTriangles[n-1][theArrayOfTriangles[n-1].length-1].length());
        for (int i = 0; i < theArrayOfTriangles.length; i++){
            for (int j = 0; j < theArrayOfTriangles[i].length; j++){
                int numberOfSpaces = (theLargestBase - theArrayOfTriangles[i][j].length())/2;
                String spaces = "";
                while(numberOfSpaces!=0){
                    spaces+=" ";
                    numberOfSpaces--;
                }
                System.out.println(spaces+theArrayOfTriangles[i][j]);
            }
        }
        if(theLargestBase%4==0){
            int poleSpaces = (theLargestBase - 6-2)/2;
            String theSpaces = "";
            while (poleSpaces!=0){
                theSpaces += " ";
                poleSpaces--;
            }
            for (int i = 0; i < 6; i++){
                System.out.println(theSpaces+"******");
            }
        }
        else{
            int poleSpaces = (theLargestBase - 6 - 1)/2;
            String theSpaces = "";
            while (poleSpaces!=0){
                theSpaces += " ";
                poleSpaces--;
            }
            for (int i = 0; i < 6; i++){
                System.out.println(theSpaces+"*******");
            }
        }
    }


    public static String getStarRow(int n){
        StringBuilder result = new StringBuilder();
        for (int i=0; i<=n; i++){
            result.append("*").append(" ");
        }
        return result.toString();
    }

    public static String[] getTriangle(int n){
        String[] triangle = new String[n-1];
        for (int i = 0; i<triangle.length; i++){
            triangle[triangle.length-i-1] = getStarRow(n-i);
        }
        return triangle;
    }

    public static String[] getTriangleToyed (String[] arr, HashMap<String, Integer> toys){
        List<Integer> valuesLists = new ArrayList<>(toys.values());
        int[] valuesArray = new int[valuesLists.size()];
        for (int i = 0; i < valuesArray.length; i++){
            valuesArray[i] = valuesLists.get(i);
        }
        List<String> keysList = new ArrayList<>(toys.keySet());
        Random rand = new Random();
        for (int i = 0; i<arr.length; i++){
            int randomToyApproval = rand.nextInt(2);
            if(randomToyApproval == 1){
                boolean untoyed = true;
                while (untoyed){
                    int randomToy = rand.nextInt(keysList.size());
                    int randomToyPosition = rand.nextInt(arr[i].length());
                    if (arr[i].charAt(randomToyPosition) != ' ' && valuesArray[randomToy]!= 0){
                        char[] line = arr[i].toCharArray();
                        line[randomToyPosition] = keysList.get(randomToy).charAt(0);
                        arr[i] = String.valueOf(line);
                        valuesArray[randomToy]--;
                        untoyed = false;
                    }
                }
            }
        }
        return arr;
    }

    public static String[][] getArrayOfTriangles(int n, HashMap<String, Integer> toys){
        Random rand = new Random();
        String[] firstTriangle = {"* ", "* * ", "* * * ", "* * * * "};
        String[][] arrayOfTriangles = new String[n][];
        arrayOfTriangles[0]=getTriangleToyed(firstTriangle, toys);
        for (int i = 1; i <arrayOfTriangles.length; i++){
            int a = ((arrayOfTriangles[i-1][arrayOfTriangles[i-1].length-1].length()+1))/2;
            int b = a + 3;
            arrayOfTriangles[i] = getTriangleToyed(getTriangle(rand.nextInt(a, b)), toys);

        }
        return arrayOfTriangles;
    }

}
