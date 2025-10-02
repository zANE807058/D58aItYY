// 代码生成时间: 2025-10-03 02:07:24
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// Entity class for Loan Application
class LoanApplication {
    private int id;
    private String customerName;
    private double loanAmount;
    private String loanStatus;

    // Constructors, getters and setters
    public LoanApplication() {}

    public LoanApplication(int id, String customerName, double loanAmount, String loanStatus) {
        this.id = id;
        this.customerName = customerName;
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }
    public String getLoanStatus() { return loanStatus; }
    public void setLoanStatus(String loanStatus) { this.loanStatus = loanStatus; }
}

// DAO class for LoanApplication
class LoanApplicationDAO {
    public void save(LoanApplication loanApplication) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(loanApplication);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public LoanApplication get(int id) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        LoanApplication loanApplication = null;
        try {
            loanApplication = session.get(LoanApplication.class, id);
        } finally {
            session.close();
        }
        return loanApplication;
    }

    public List<LoanApplication> listAll() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        List<LoanApplication> list = null;
        try {
            list = session.createQuery("from LoanApplication").list();
        } finally {
            session.close();
        }
        return list;
    }
}

// Main class for Loan Approval System
public class LoanApprovalSystem {
    public static void main(String[] args) {
        LoanApplicationDAO loanApplicationDAO = new LoanApplicationDAO();

        // List all loans
        List<LoanApplication> loanApplications = loanApplicationDAO.listAll();
        for (LoanApplication loanApplication : loanApplications) {
            System.out.println("ID: " + loanApplication.getId() + ", Customer: " + loanApplication.getCustomerName() + ", Amount: " + loanApplication.getLoanAmount() + ", Status: " + loanApplication.getLoanStatus());
        }

        // Example of approving a loan
        LoanApplication newLoan = new LoanApplication(1, "John Doe", 5000.00, "Pending");
        loanApplicationDAO.save(newLoan);
        newLoan.setLoanStatus("Approved");
        loanApplicationDAO.save(newLoan);
        System.out.println("Loan approved: " + newLoan.getCustomerName());
    }
}