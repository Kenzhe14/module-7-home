import java.util.Scanner;
interface IPaymentStrategy{
    void Pay();
}
class Card implements IPaymentStrategy{
    @Override
    public void Pay(){
        System.out.println("Card payed");
    }
}
class PayPal implements IPaymentStrategy{
    @Override
    public void Pay(){
        System.out.println("PayPal payed");
    }
}
class Bitcoin implements IPaymentStrategy{
    @Override
    public void Pay(){
        System.out.println("Bitcoin payed");
    }
}
class PaymentContext{
    private  IPaymentStrategy paymentStrategy;
    public void setPaymentStrategy(IPaymentStrategy strategy){
        this.paymentStrategy = strategy;
    }
    public void exePay(){
        if(paymentStrategy == null){
            System.out.println("No payment");
        }
        else paymentStrategy.Pay();
    }

}
public class Main{
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - Card, 2 - PayPal, 3 - Bitcoin, 4 - Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    paymentContext.setPaymentStrategy(new Card());
                    paymentContext.exePay();
                    break;
                case 2:
                    paymentContext.setPaymentStrategy(new PayPal());
                    paymentContext.exePay();
                    break;
                case 3:
                    paymentContext.setPaymentStrategy(new Bitcoin());
                    paymentContext.exePay();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("1-4");
                    break;
            }
        }

        scanner.close();
    }
}