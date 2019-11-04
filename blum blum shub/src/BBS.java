import java.util.Random;
import java.math.*;
import java.lang.*;
public class BBS {
    private int p;
    private int q;
    private int seed;
    private BigInteger xn;
    private static int mFactor;
    Random rand = new Random();


    public BBS()
    {
        this.p = generateRandomPrime(rand);
        this.q = generateRandomPrime(rand);
        this.seed = generateProperSeed(rand) ;
        //this.xn = (int)Math.pow(seed,2)%(p*q) ;
        this.xn = BigInteger.valueOf((int)Math.pow(seed,2)%(p*q));

        this.mFactor = p*q;
    }

    public boolean ifPrime(int n)
    {
        if(n<2)
            return false; //gdy liczba jest mniejsza niż 2 to nie jest pierwszą

        for(int i=2;i*i<=n;i++)
            if(n%i==0)
                return false; //gdy znajdziemy dzielnik, to dana liczba nie jest pierwsza
        return true;
    }

    public int generateRandomPrime(Random rand)
    {
        int temp;
        while(true)
        {
            int p = rand.nextInt(100000);

            if((p%4)==3&&ifPrime(p)==true) {
                temp = p;
                break;
            }
        }
        return temp;
    }
    public int generateProperSeed(Random rand)
    {
        setmFactor(getP()*getQ());
        int temp;
        while(true)
        {
            int p = rand.nextInt(100000);

            if(NWD_1(mFactor,p) == 1&&p!=1) {
                temp = p;
                break;
            }
        }
        return temp;
    }

    public static int NWD_1(int pierwsza, int druga)
    {
        while (pierwsza != druga) // dopóki dwie liczby nie są sobie równe
        {
            if (pierwsza > druga)  // sprawdzamy, która z nich jest większa
            {
                pierwsza = pierwsza - druga; // odejmujemy mniejszą liczbę
            }                               // od większej
            else
            {
                druga = druga - pierwsza;
            }
        }
        return pierwsza;
    }

    public int computeXn()
    {
        BigInteger temp = getXn().pow(2).mod(BigInteger.valueOf(mFactor)); //((int)Math.pow(getXn().intValue(),2)%(p*q));
        setXn(temp);
        return  temp.mod(BigInteger.valueOf(2)).intValue() ;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public BigInteger getXn() {
        return xn;
    }

    public void setXn(BigInteger xn) {
        this.xn = xn;
    }

    public int getmFactor() {
        return mFactor;
    }

    public void setmFactor(int mFactor) {
        this.mFactor = mFactor;
    }
}
