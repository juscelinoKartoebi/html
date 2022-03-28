package sr.unasat.Admin.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//import jdk.nashorn.internal.objects.annotations.Getter;
//import sr.unasat.Admin.Entities.Client;
//import sr.unasat.Admin.Entities.TYPE;


//@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO {
    private long accountNumber;
    private long client;
    private int type;
    private int currency;
    private double balance;
    private int Adds;

    @JsonCreator
    public AccountDTO(@JsonProperty("client") long client,@JsonProperty("type") int type,@JsonProperty("currency") int currency,@JsonProperty("balance") double balance,@JsonProperty("Adds") int adds) {
        this.client = client;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.Adds = adds;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getClient() {
        return client;
    }

    public void setClient(long client) {
        this.client = client;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAdds() {
        return Adds;
    }

    public void setAdds(int adds) {
        Adds = adds;
    }
}
