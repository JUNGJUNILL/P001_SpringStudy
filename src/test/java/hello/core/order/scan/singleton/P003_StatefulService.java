package hello.core.order.scan.singleton;

public class P003_StatefulService {



    private int price;

    public void order(String name, int price){
        System.out.println("name="+name+ " price="+price);
        this.price =price; //여기가 문제!
    }


    //무상태
    public int order2(String name, int price){
        return price;
    }

    //공유필드 발생(공유변수)
    public int getPrice(){
        return  price;
    }

}
