package reminder;

public class StringFormat {

    public static void main(String[] args) {
        int price = 10;
        String productName = "wallpaper";
        double discount = 0.05;

        System.out.printf("Produkt %s o cenie %d przeceniono o %.0f%%\n", productName,price,discount * 100);

        String sql = String.format("Produkt %s o cenie %d przeceniono o %.0f%%", productName,price,discount * 100);
        System.out.println(sql.toUpperCase());
    }
}
