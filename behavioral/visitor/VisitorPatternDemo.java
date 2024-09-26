package behavioral.visitor;

interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}

class PriceVisitor implements Visitor {
    public void visit(Book book) {
        System.out.println("Book cost: " + book.getPrice());
    }
    
    public void visit(Fruit fruit) {
        System.out.println("Fruit cost: " + (fruit.getPricePerKg() * fruit.getWeight()));
    }
}

interface ItemElement {
    void accept(Visitor visitor);
}


class Book implements ItemElement {
    private int price;
    private String isbnNumber;

    public Book(int price, String isbnNumber) {
        this.price = price;
        this.isbnNumber = isbnNumber;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Fruit implements ItemElement {
    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int pricePerKg, int weight, String name) {
        this.pricePerKg = pricePerKg;
        this.weight = weight;
        this.name = name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

public class VisitorPatternDemo {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[] { 
            new Book(20, "1234"), 
            new Fruit(10, 2, "Apple"), 
            new Fruit(5, 5, "Banana")
        };

        Visitor visitor = new PriceVisitor();
        for(ItemElement item : items) {
            item.accept(visitor);
        }
    }
}
