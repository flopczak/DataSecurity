import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BBS bbs = context.getBean("myBBS", BBS.class);


        BBSGenerator gen = new BBSGenerator(20000);
        FipsTests fipsTests = new FipsTests();
        gen.generateStream(bbs);

        System.out.println("p: " + bbs.getP());
        System.out.println("q: " + bbs.getQ());
        System.out.println("seed: " + bbs.getSeed());
        System.out.println("M: "  + bbs.getmFactor());

        gen.cout();
        System.out.println("");
        System.out.println("One bit test: " + fipsTests.oneBitTest(gen));
        fipsTests.doubleBitTest(gen);
        fipsTests.pokerTest(gen);

        context.close();
    }
}
