package by.it.berdnik.jd02_03;

import java.util.concurrent.Semaphore;

class Buyer extends Thread implements IBuyer {

    private boolean waiting = false;
    Semaphore semaphore = new Semaphore(20);

    void stopWait(){
        synchronized (this) {
            waiting=false;
            this.notify();
        }
    }

    Buyer(int number) {
        super("Buyer №" + number);
        Dispatcher.addBuyer();
    }


    @Override
    public void run() {
        enterToMarket();
        takeBacket();
        chooseGoods();
        putGoodsToBacket();
        goToQueue();
        goOut();
    }


    @Override
    public void enterToMarket() {
        System.out.println(this + "enter to market.");
    }

    @Override
    public void takeBacket() {
        System.out.println(this + "take a backet.");
        int timeout = Util.random(100, 200);
        Util.sleep(timeout);
        System.out.println(this + "go shoping with backet");
    }

    @Override
    public void chooseGoods() {
        try {
            semaphore.acquire();
            System.out.println(this + "started to choose goods");
            int timeout = Util.random(500, 2000);
            Util.sleep(timeout);
            System.out.println(this + "choosed goods.");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putGoodsToBacket() {
        int x = Util.random(1, 4);
        for (int i = 0; i < x; i++) {
            System.out.println(this + "put " + x + " down in backet" + GoodShop.products(x));
            System.out.println("Final price:" + GoodShop.FinPrice());
            int timeout = Util.random(100, 200);
            Util.sleep(timeout);
        }
    }

    @Override
    public void goToQueue() {
        QueueBuyer.add(this);
        waiting = true;
        synchronized (this) {
            while (waiting)
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void goOut() {
        System.out.println(this + "go out from market.");
        Dispatcher.removeBuyer();
    }

    @Override
    public String toString() {
        return getName() + ": ";
    }
}
