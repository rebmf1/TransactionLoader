package transactionloader.domain;

import java.time.LocalDate;

/**
 * Created by rebeccafulton on 27/03/2017.
 */
public class AccountTrackerTransaction {

    private String account;
    private LocalDate date;
    private String details;
    private String category;
    private String notes;
    private String chequeNumber;
    private double amount;
    private boolean reconciled;

    public AccountTrackerTransaction(String account, LocalDate date, String details, String notes,
                                     String chequeNumber, double amount, boolean reconciled) {
        this.account = account;
        this.date = date;
        this.details = details;
        this.notes = notes;
        this.chequeNumber = chequeNumber;
        this.amount = amount;
        this.reconciled = reconciled;
    }

    public String getAccount() {
        return account;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getCategory() {
        return category;
    }

    public String getNotes() {
        return notes;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isReconciled() {
        return reconciled;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}