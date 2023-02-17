import java.io.File;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    System.out.println("Список товаров для покупки: ");
    String[] products = new String[]{"Хлеб", "Яблоки", "Молоко"};
    int[] prices = new int[]{100, 200, 300};

    File file = new File("basket.txt");
    Basket basket;
    if (file.exists()) {
      basket = Basket.loadFromBinFile(file);
    } else {
      basket = new Basket(products, prices);
    }
    Scanner scanner = new Scanner(System.in);
    for (int i = 0; i < products.length; i++) {
      System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт. ");
    }
    int sumProducts = 0;
    int productNum = 0;
    int count = 0;

    while (true) {
      System.out.println("Выберите товар и количество или введите 'end'");
      String input = scanner.nextLine();
      if ("end".equals(input)) {
        break;
      }
      String[] parts = input.split(" ");
      productNum = Integer.parseInt(parts[0]) - 1;
      count = Integer.parseInt(parts[1]);
      int sum = count * prices[productNum];
      sumProducts += sum;
      basket.addToCart(productNum, count);

    }
    basket.saveBin(file);
    basket.printCart();
    System.out.println("Итого " + sumProducts + " " + "рублей.");
  }
}