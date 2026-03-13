package com.biblioteca.domain;

import java.time.LocalDate;

public class Loan {
    private final int code;
    private final int bookId;
    private final String personName;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public Loan(int code, int bookId, String personName, LocalDate loanDate, LocalDate dueDate) {
        this(code, bookId, personName, loanDate, dueDate, null, LoanStatus.OPEN);
    }

    public Loan(
        int code,
        int bookId,
        String personName,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate,
        LoanStatus status
    ) {
        if (code <= 0) {
            throw new IllegalArgumentException("Code must be greater than zero");
        }
        if (bookId <= 0) {
            throw new IllegalArgumentException("Book id must be greater than zero");
        }
        if (personName == null || personName.isBlank()) {
            throw new IllegalArgumentException("Person name is required");
        }
        if (loanDate == null) {
            throw new IllegalArgumentException("Loan date is required");
        }
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date is required");
        }
        if (dueDate.isBefore(loanDate)) {
            throw new IllegalArgumentException("Due date cannot be before loan date");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }
        if (status == LoanStatus.RETURNED && returnDate == null) {
            throw new IllegalArgumentException("Return date is required when status is RETURNED");
        }
        if (returnDate != null && returnDate.isBefore(loanDate)) {
            throw new IllegalArgumentException("Return date cannot be before loan date");
        }

        this.code = code;
        this.bookId = bookId;
        this.personName = personName;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public int getBookId() {
        return bookId;
    }

    public String getPersonName() {
        return personName;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void markAsReturned(LocalDate returnedOn) {
        if (status == LoanStatus.RETURNED) {
            throw new IllegalStateException("Loan is already returned");
        }
        if (returnedOn == null) {
            throw new IllegalArgumentException("Return date is required");
        }
        if (returnedOn.isBefore(loanDate)) {
            throw new IllegalArgumentException("Return date cannot be before loan date");
        }

        this.returnDate = returnedOn;
        this.status = LoanStatus.RETURNED;
    }
}
