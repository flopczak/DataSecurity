import java.math.BigInteger;
import java.util.Arrays;

public class FipsTests {

    public boolean oneBitTest( BBSGenerator bbsGenerator){
        int counter = 0;
        int []temp = bbsGenerator.getStream();
        for (int i = 0; i < bbsGenerator.getStream().length ; i++) {
            if (temp[i] == 1)
                counter++;
        }
        //if(counter >= bbsGenerator.getStream().length*0.45 && counter <= bbsGenerator.getStream().length*0.55)
        if(counter >= 9654 && counter <= 10346)
        {
            return true;
        }
        else return false;
    }
    public String toString(int[] hey) {
        String tekst = new String();
        for (int i = 0; i < hey.length; i++) {
        tekst=tekst+hey[i];
        }
        return tekst ;
    }

    public void doubleBitTest(BBSGenerator bbsGenerator){
        int counter0 = 0, counter1 = 0,counter2 = 0,counter3 = 0;
        int []temp = bbsGenerator.getStream();
        int pom[] = new int[2];
        for (int i = 1; i < bbsGenerator.getStream().length  ; i+=2) {
        pom[0] = temp[i - 1];
        pom[1] = temp[i];

        if(pom[0]==0&&pom[1]==0){
            counter0++;
        }
        else if(pom[0]==0&&pom[1]==1){
            counter1++;
        }
        else if(pom[0]==1&&pom[1]==0){
            counter2++;
        }
        else if(pom[0]==1&&pom[1]==1){
            counter3++;
        }

        }

        System.out.println("00: " + counter0);
        System.out.println("01: " + counter1);
        System.out.println("10: " + counter2);
        System.out.println("11: " + counter3);
    }


    public void pokerTest(BBSGenerator bbsGenerator) {
        int[] temp = bbsGenerator.getStream();
        String end[] = new String[bbsGenerator.getStream().length/4];
        int[] temp2 = new int[16];
        int pom[] = new int[4];
        int sum = 0;
        for (int i = 4; i < bbsGenerator.getStream().length; i += 4) {
            pom[0] = temp[i - 3];
            pom[1] = temp[i - 2];
            pom[2] = temp[i - 1];
            pom[3] = temp[i];
            end[i/4] = toString(pom);

        }
        for (int i = 1; i < end.length ; i++) {
            String s = end[i];
            int temporary = Integer.parseInt(s,2);
            temp2[temporary]++;
        }
        for (int i = 0; i <temp2.length ; i++) {
           // System.out.println(temp2[i]);
            sum += Math.pow(temp2[i],2);
        }
        double x = 0.0032*sum-5000;
        System.out.println("poker test:" + x);
        if(x<57.4&&x>1.03){
            System.out.println("poker test zdany");
        }
        else System.out.println("poker test nie zdany");
    }
    public void longRuns(BBSGenerator bbsGenerator){
        int[] temp = bbsGenerator.getStream();
        boolean flag;
        int counter = 0;
        int biggest = 0;
        for (int i = 1; i < bbsGenerator.getStream().length ; i++) {
            if(temp[i]==temp[i-1])
            {
                counter++;
            }
            else if(temp[i]!=temp[i-1])
                counter = 0;
            if(biggest<counter){
                biggest=counter;
            }
        }
        if(biggest >26)
        {
            System.out.println("long runs test nie zdany: "+biggest);
        }
        else{
            System.out.println("long runs zdany: "+biggest);
        }
    }
    public void runsTest(BBSGenerator bbsGenerator){
        int[] temp = bbsGenerator.getStream();
        int[] end = {0,0,0,0,0,0,0};
        int wiel = 0;
        int old = temp[0];
        for (int i = 1; i < bbsGenerator.getStream().length ; i++) {
        if(old == temp[i]){
            wiel++;
        }
        else{
            if(wiel<7){
                end[wiel]++;
            }
            else{
                end[6]++;
            }
            wiel = 0;
        }
        }
        for (int i = 1; i < end.length; i++) {
            System.out.println(i+": "+end[i]);
        }
    }
}
