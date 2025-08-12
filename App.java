package покупки;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

// Класс покупки.Product
class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Стоимость продукта не может быть отрицательным числом");
        }
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (₽" + cost + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.cost, cost) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }
}

// Класс покупки.Person
class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.name = name;
        this.money = money;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void buyProduct(Product product) {
        if (this.money >= product.getCost()) {
            this.products.add(product);
            this.money -= product.getCost();
            System.out.println(this.name + " купил " + product.getName());
        } else {
            System.out.println(this.name + " не может позволить себе " + product.getName());
        }
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return name + ": Ничего не куплено";
        }
        return name + " купил: " + products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(products, person.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, products);
    }
}

// Основной класс покупки.App
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод данных о покупателях
        System.out.println("Введите данные о покупателях в формате \"покупатель1=бюджет1, покупатель2=бюджет2, ...\":");
        String personsInput = scanner.nextLine();
        String[] personPairs = personsInput.split(",");
        List<Person> persons = new ArrayList<>();
        for (String pair : personPairs) {
            String[] parts = pair.trim().split("=");
            String personName = parts[0].trim();
            double personMoney = Double.parseDouble(parts[1].trim());
            persons.add(new Person(personName, personMoney));
        }

        // Ввод данных о продуктах
        System.out.println("Введите данные о продуктах в формате \"продукт1=цена1, продукт2=цена2, ...\":");
        String productsInput = scanner.nextLine();
        String[] productPairs = productsInput.split(",");
        List<Product> products = new ArrayList<>();
        for (String pair : productPairs) {
            String[] parts = pair.trim().split("=");
            String productName = parts[0].trim();
            double productCost = Double.parseDouble(parts[1].trim());
            products.add(new Product(productName, productCost));
        }

        // Цикл покупки продуктов для каждого покупателя
        int currentPersonIndex = 0;
        boolean continueShopping = true;
        while (continueShopping) {
            Person currentPerson = persons.get(currentPersonIndex);

            System.out.println("\nСейчас покупает " + currentPerson.getName() + ". Введите продукты для покупки (введите 'END' для завершения):");
            while (true) {
                System.out.print("Введите название продукта: ");
                String productName = scanner.nextLine();
                if (productName.equalsIgnoreCase("END")) {
                    break;
                }

                Product selectedProduct = null;
                for (Product product : products) {
                    if (product.getName().equalsIgnoreCase(productName)) {
                        selectedProduct = product;
                        break;
                    }
                }

                if (selectedProduct == null) {
                    System.out.println("Продукт с названием \"" + productName + "\" не найден.");
                } else {
                    currentPerson.buyProduct(selectedProduct);
                }
            }

            // Переход к следующему покупателю
            currentPersonIndex = (currentPersonIndex + 1) % persons.size();

            // Запрос на продолжение покупок
            System.out.print("Продолжить процесс покупок? (yes/no): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("no")) {
                continueShopping = false;
            }
        }

        // Вывод результатов
        System.out.println("\nРезультаты покупок:");
        for (Person person : persons) {
            System.out.println(person);
        }

        scanner.close();
    }
}