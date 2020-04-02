package com.bankservice;

public interface IAccount {

    long getSold();

    Invoice sendInvoice();

    Invoice createInvoice();

    void updateSold(long value);

    String getInstallmentsInfo();

    Account addToSold(long value);

    Invoice getLatestUnpaidInvoice();


}
