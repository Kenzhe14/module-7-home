import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface IObserver {
    void update(String currency, double rate);
}

interface ISubject {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

class CurrencyExchange implements ISubject {
    private Map<String, Double> exchangeRates = new HashMap<>();
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
                observer.update(entry.getKey(), entry.getValue());
            }
        }
    }

    public void setExchangeRate(String currency, double rate) {
        exchangeRates.put(currency, rate);
        System.out.println("Курс " + currency + " обновлен " + rate);
        notifyObservers();
    }
}

class Bank implements IObserver {
    private String name;

    public Bank(String name) {
        this.name = name;
    }

    @Override
    public void update(String currency, double rate) {
        System.out.println(name + " Получил " + currency + " по " + rate);
    }
}

class Investor implements IObserver {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String currency, double rate) {
        System.out.println(name + " Получил " + currency + " по " + rate);
    }
}

// Главный класс
public class Main2 {
    public static void main(String[] args) {
        CurrencyExchange currencyExchange = new CurrencyExchange();

        Bank bank1 = new Bank("Kaspi Bank");
        Investor investor1 = new Investor("Erke Buulaan");

        currencyExchange.addObserver(bank1);
        currencyExchange.addObserver(investor1);

        currencyExchange.setExchangeRate("USD", 74.85);
        currencyExchange.setExchangeRate("EUR", 88.90);

        currencyExchange.removeObserver(investor1);

        currencyExchange.setExchangeRate("JPN", 101.75);
    }
}
