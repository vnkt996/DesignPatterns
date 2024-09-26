import java.util.ArrayList;
import java.util.List;

// A Page class representing individual pages in a document
class Page {
    private String content;

    public Page(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Page{" +
                "content='" + content + '\'' +
                '}';
    }
}

// Prototype interface for cloning documents
abstract class Document implements Cloneable {
    private String title;
    private List<Page> pages = new ArrayList<>();

    public Document(String title) {
        this.title = title;
    }

    public void addPage(Page page) {
        pages.add(page);
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Clone method to create a deep copy of the document
    @Override
    public Document clone() {
        try {
            Document clone = (Document) super.clone();  // Shallow copy
            clone.pages = new ArrayList<>();  // Create a new list for deep copy
            for (Page page : this.pages) {
                clone.pages.add(new Page(page.getContent()));  // Deep copy each Page
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", pages=" + pages +
                '}';
    }
}

// Concrete prototype: Report document
class Report extends Document {
    public Report(String title) {
        super(title);
    }

    @Override
    public Report clone() {
        return (Report) super.clone();  // Uses the parent class's clone method
    }
}

// Concrete prototype: Invoice document
class Invoice extends Document {
    public Invoice(String title) {
        super(title);
    }

    @Override
    public Invoice clone() {
        return (Invoice) super.clone();  // Uses the parent class's clone method
    }
}

// Prototype Registry class for managing different document prototypes
class DocumentRegistry {
    private static final Map<String, Document> prototypes = new HashMap<>();

    // Add document prototypes to the registry
    public static void addPrototype(String key, Document document) {
        prototypes.put(key, document);
    }

    // Retrieve and clone document prototypes from the registry
    public static Document getPrototype(String key) {
        return prototypes.get(key).clone();
    }
}

// Client code
public class PrototypePatternRealExample {
    public static void main(String[] args) {
        // Create an initial report document
        Report reportPrototype = new Report("Annual Report");
        reportPrototype.addPage(new Page("Introduction"));
        reportPrototype.addPage(new Page("Financial Overview"));
        reportPrototype.addPage(new Page("Conclusion"));

        // Create an initial invoice document
        Invoice invoicePrototype = new Invoice("Invoice #12345");
        invoicePrototype.addPage(new Page("Customer Details"));
        invoicePrototype.addPage(new Page("Items List"));
        invoicePrototype.addPage(new Page("Total Amount"));

        // Register the prototypes in the registry
        DocumentRegistry.addPrototype("report", reportPrototype);
        DocumentRegistry.addPrototype("invoice", invoicePrototype);

        // Clone the report document
        Document clonedReport = DocumentRegistry.getPrototype("report");
        clonedReport.setTitle("Cloned Annual Report");
        clonedReport.getPages().get(0).setContent("Modified Introduction");

        // Clone the invoice document
        Document clonedInvoice = DocumentRegistry.getPrototype("invoice");
        clonedInvoice.setTitle("Cloned Invoice #54321");

        // Show original and cloned documents
        System.out.println("Original Report: " + reportPrototype);
        System.out.println("Cloned Report: " + clonedReport);

        System.out.println("Original Invoice: " + invoicePrototype);
        System.out.println("Cloned Invoice: " + clonedInvoice);
    }
}
