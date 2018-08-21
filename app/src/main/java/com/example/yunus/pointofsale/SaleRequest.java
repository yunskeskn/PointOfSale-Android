package com.example.yunus.pointofsale;

import com.google.gson.annotations.SerializedName;

public class SaleRequest {

    @SerializedName("merchant_no")
    private String merchant_no;

    @SerializedName("terminal_no")
    private String terminal_no;

    @SerializedName("amount")
    private String amount;

    public SaleRequest(String amount) {
        this.merchant_no = "6706598320";
        this.terminal_no = "67001985";
        this.amount = amount;
    }

    public String getMerchant_no() {
        return merchant_no;
    }

    public void setMerchant_no(String merchant_no) {
        this.merchant_no = merchant_no;
    }

    public String getTerminal_no() {
        return terminal_no;
    }

    public void setTerminal_no(String terminal_no) {
        this.terminal_no = terminal_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
