import java.util.ArrayList;

public class PrimeTest {
    public static void main(String args[]){
        ArrayList<ArrayList<Integer>> nums = new ArrayList<ArrayList<Integer>>();
        for (int i = 2; i < Integer.parseInt(args[0]); i++) {
            boolean found = false;
            for (ArrayList<Integer> elem : nums) {
                if(i%elem.get(0)==0){
                    elem.add(i);
                    found = true;
                    break;
                }
            }
            if(!found){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                nums.add(temp);
            }
        }
        int count = 0;
        for (ArrayList arr : nums) {
            if (arr.size()==1){
                count++;

            }else{
                System.out.println(arr.size());
            }  
        }
        System.out.println(count);
    }
}
