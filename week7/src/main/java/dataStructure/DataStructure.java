package dataStructure;

import java.util.Arrays;
import java.util.Stack;

public class DataStructure {
    public static void main(String[] args) {
        int[] nums1={4,1,2};
        int[] nums2={1,3,4,2};
        System.out.println(Arrays.toString(fun1(nums1, nums2)));

        int[] pushed={1,2,3,4,5};
        int[] popped={4,5,3,2,1};
        System.out.println(fun2(pushed, popped));

        int[] nums={1,2,3,4,5};
        System.out.println(fun3(nums));

    }
    public static int[] fun1(int[] nums1, int[] nums2) {
        Stack<Integer> myStack1 = new Stack<>();
        Stack<Integer> myStack2 = new Stack<>();
        int usedSize = 0;
        int[] arr = new int[nums1.length];
        for(int i = nums2.length - 1;i >= 0;i--){
            myStack1.push(nums2[i]);
        }
        for(int num1:nums1){
            while(!myStack1.empty() && num1 != myStack1.peek()){
                myStack2.push(myStack1.peek());
                myStack1.pop();
            }

            while(!myStack1.empty()){
                if(num1 < myStack1.peek()){
                    arr[usedSize++] = myStack1.peek();
                    break;
                }
                myStack2.push(myStack1.peek());
                myStack1.pop();
            }
            if(myStack1.empty()){
                arr[usedSize++] = -1;
            }
            while(!myStack2.empty()){
                myStack1.push(myStack2.peek());
                myStack2.pop();
            }
        }
        return arr;
    }
    public static boolean fun2(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int length = pushed.length;

        int i,j = 0;
        for(i = 0;i < length;i++){
            stack.push(pushed[i]);
            while(j<length&&!stack.isEmpty()&&stack.peek() == popped[j]){
                stack.pop();
                j++;
            }
        }
        return j==length;
    }
    public static int fun3(int[] nums){
        int[] arr=new int[100];
        int sum=0;
        for (int num:nums){
            arr[num]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==1){
                sum+=i;
            }
        }
        return sum;
    }
}

