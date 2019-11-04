import java.util.ArrayList;

public class BBSGenerator {
    private int[] stream;

    public int[] getStream() {
        return stream;
    }

    public void setStream(int[] stream) {
        this.stream = stream;
    }

    public BBSGenerator(){}
    public BBSGenerator(int length)
    {
        this.stream = new int[length];
    }


    //public byte transferToStream(BBS bbs)
    public void generateStream( BBS bbs)
    {

        stream[0] = (bbs.getXn().intValue()%2);
        for (int i = 1; i < stream.length ; i++) {
            stream[i] = (bbs.computeXn());
        }
    }

    public void cout()
    {
        for(int i=0;i<stream.length;i++ ) {
            System.out.print(stream[i]);
        }
    }
}


