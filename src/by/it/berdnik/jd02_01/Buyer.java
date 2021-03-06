package by.it.berdnik.jd02_01;

import java.util.HashMap;

class Buyer extends Thread implements IBuyer, IUseBacket {

    Buyer(int number) {
        super("Buyer №" + number + " ");
    }


    @Override
    public void run() {
        enterToMarket();
        takeBacket();
        chooseGoods();
        putGoodsToBacket();
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
        System.out.println(this + "started to choose goods");
        int timeout = Util.random(500, 2000);
        Util.sleep(timeout);
        System.out.println(this + "choosed goods");
    }

    @Override
    public void putGoodsToBacket() {
        int x = Util.random(1, 4);
        for (int i = 0; i < x; i++) {
            int prod = Util.random(GoodShop.product.size() - 1);
            GoodShop.getPosition((HashMap) GoodShop.product, prod);
            System.out.println(this + "put down in backet");
            int timeout = Util.random(100, 200);
            Util.sleep(timeout);
        }
    }

    @Override
    public void goOut() {
        System.out.println(this + "go out from market.");
    }

    @Override
    public String toString() {
        return getName() + ": ";
    }

}
