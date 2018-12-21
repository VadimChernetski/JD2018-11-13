package by.it.lobkova.jd02_02;

import java.util.ArrayList;
import java.util.List;

public class Market {

    public static void main(String[] args) {
        List<Buyer> buyerList = new ArrayList<>();
        System.out.println("Market is open");

        for (int millis = 0; millis < 120; millis++) {
            int buyerCount = Util.random(2);
            for (int i = 0; i < buyerCount; i++) {
                Buyer buyer = new Buyer(Dispatcher.buyerCounter++);
                buyerList.add(buyer);
                buyer.start();
            }
            Util.sleep(100);
        }

        for (Buyer buyer : buyerList) {
            try {
                buyer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Market is closed");
    }
}
