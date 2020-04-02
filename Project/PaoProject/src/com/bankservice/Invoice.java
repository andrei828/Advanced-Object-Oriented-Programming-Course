package com.bankservice;

import java.text.SimpleDateFormat;

import java.util.Date;

public class Invoice {

    private Date dueDate;

    private int installmentValue;

    private String clientReceiveMode;

    private boolean clientPayedInvoice;

    private boolean clientReceivedInvoice;

    public Invoice() {
        this.installmentValue = -1;
    }

    public Invoice(int installmentValue, Date dueDate, String clientReceiveMode) {
        this.installmentValue = installmentValue;
        this.dueDate = dueDate;
        this.clientReceiveMode = clientReceiveMode;
        this.clientPayedInvoice = false;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getInstallmentValue() {
        return installmentValue;
    }

    public boolean didClientReceiveInvoice() {
        return clientReceivedInvoice;
    }

    public String getClientReceivalMode() {
        return clientReceiveMode;
    }

    public boolean didClientPayInvoice() {
        return clientPayedInvoice;
    }

    public void setClientReceiveMode(String clientReceiveMode) {
        this.clientReceiveMode = clientReceiveMode;
    }

    public void setClientReceivedInvoice(boolean clientReceivedInvoice) {
        this.clientReceivedInvoice = clientReceivedInvoice;
    }

    public String payInvoice() {
        if (this.installmentValue == -1)
            return "Nothing to pay";

        this.clientPayedInvoice = true;
        this.clientReceivedInvoice = true;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = format.format(this.dueDate);

        return "Client paid " +
                this.installmentValue +
                "$ for the invoice that was due " +
                formattedDate;
    }
}
