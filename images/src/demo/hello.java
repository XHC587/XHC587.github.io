package demo;

public class hello {
    static void main(String[] args) {
        int[]air={1,2,3,4,5,6,7,8,9,10};

        for (int i = 0; i < air.length; i++) {
            if(air[i]%2==0){
                air[i]=air[i]/2;

            }else if(air[i]%2==1){
                air[i]=air[i]*2;

            }

        }
        for (int i = 0; i < air.length; i++) {
                System.out.println(air[i]);
        }
        }
}
