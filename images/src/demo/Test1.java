package demo;

import java.util.Random;

public class Test1 {
    static void main(String[] args) {
        char[] arr = new char[52];
//65-90  A  97-122  a
        for (int i = 0; i < arr.length; i++) {
            if (i < 26) {
                arr[i] = (char) (65 + i);

            } else {
                arr[i] = (char) (97 + (i - 26));
            }
        }
        System.out.println(arr);
        //从数组char[]arr中随机获取4次

        Random r=new Random();


        String result = "";

        for (int i = 0; i < 4; i++) {
          int s=  r.nextInt(arr.length);
          char b=arr[s];
            result =result+ b;


        }
        System.out.println(result);


//生成一个0-9的数字
        int number=r.nextInt(10);
        String jg=result+number;
        char[]wrr=jg.toCharArray();

        Random c=new Random();
        int z=c.nextInt(wrr.length);
        //打乱顺序


        char q = wrr[0];
        wrr[0] = wrr[z];
        wrr[z] = q;
        String w = new String(wrr);
        System.out.println(w);






        //把所有的大小写字母 放在数组char[]arr中

        //a-z  A-Z
        //从数组char[]arr中随机获取4次

        //生成一个0-9的数字拼接

    }
}




