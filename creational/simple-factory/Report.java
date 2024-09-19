
public interface Report {
    void generate(String data);
}


public class PDFReport implements Report {
    @Override
    public void generate(String data) {
        System.out.println("Generating PDF Report with data: " + data);
    }
}

public class ExcelReport implements Report {
    @Override
    public void generate(String data) {
        System.out.println("Generating Excel Report with data: " + data);
    }
}

public class HTMLReport implements Report {
    @Override
    public void generate(String data) {
        System.out.println("Generating HTML Report with data: " + data);
    }
}


public class ReportFactory {

    // Factory method to create reports
    public Report createReport(String reportType) {
        switch (reportType.toLowerCase()) {
            case "pdf":
                return new PDFReport();
            case "excel":
                return new ExcelReport();
            case "html":
                return new HTMLReport();
            default:
                throw new IllegalArgumentException("Invalid report type: " + reportType);
        }
    }
}


public class ReportGenerator {

    private final ReportFactory reportFactory;

    public ReportGenerator(ReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }

    public void generateReport(String reportType, String data) {
        Report report = reportFactory.createReport(reportType);
        report.generate(data);
    }
}

