package sr.unasat.Admin.Controllers;

import sr.unasat.Admin.Builder.AccountBuilder;
import sr.unasat.Admin.Config.JPAConfiguration;
import sr.unasat.Admin.Currency.Currency;
import sr.unasat.Admin.Currency.EURO;
import sr.unasat.Admin.Currency.SRD;
import sr.unasat.Admin.Currency.USD;
import sr.unasat.Admin.DAO.AccountDAO;
import sr.unasat.Admin.DAO.AddDAO;
import sr.unasat.Admin.DAO.ClientDAO;
import sr.unasat.Admin.DTO.AccountDTO;
import sr.unasat.Admin.DTO.AccountIdDTO;
import sr.unasat.Admin.Decorator.ElectricityAdd;
import sr.unasat.Admin.Decorator.StandardAdds;
import sr.unasat.Admin.Decorator.WaterAdd;
import sr.unasat.Admin.Entities.Account;
import sr.unasat.Admin.Entities.Add;
import sr.unasat.Admin.Entities.Client;
import sr.unasat.Admin.Entities.TYPE;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("account")
public class AccountController {
    private AccountDAO accountDAO = new AccountDAO(JPAConfiguration.getEntityManager());
    private ClientDAO clientDAO = new ClientDAO(JPAConfiguration.getEntityManager());
    private AddDAO addDAO = new AddDAO(JPAConfiguration.getEntityManager());

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> retrieveAccountList(){
        return accountDAO.retrieveAccountList();
    }

    @Path("add")
    @POST
    public String add(AccountDTO accountDTO) {
        Client client = clientDAO.findClientById(accountDTO.getClient());

        TYPE type = null;
        if (accountDTO.getType() == 1) {
            type = TYPE.CHECKING;
        } else if (accountDTO.getType() == 2) {
            type = TYPE.SAVINGS;
        }

        List<Add> addList = new ArrayList<>();

//  Decorator
        sr.unasat.Admin.Decorator.Add standardAdds = new StandardAdds();
        switch (accountDTO.getAdds()) {
            case 1:
                Add electricity = addDAO.findAddById(accountDTO.getAdds());
                addList.add(electricity);
                standardAdds = new WaterAdd(standardAdds);
                break;
            case 2:
                Add water = addDAO.findAddById(accountDTO.getAdds());
                addList.add(water);
                standardAdds = new ElectricityAdd(standardAdds);
                break;
            case 3:
                addList = addDAO.retrieveAddList();
                standardAdds = new WaterAdd(new ElectricityAdd(standardAdds));
                break;
            default:
        }

//  Template
        Currency currency1 = null;
        if (accountDTO.getCurrency() == 1) {
            currency1 = new SRD();
        } else if (accountDTO.getCurrency() == 2) {
            currency1 = new USD();
        } else if (accountDTO.getCurrency() == 3) {
            currency1 = new EURO();
        }
        String currency2 = currency1.CurrencyChoice();
        AccountBuilder accountBuilder = new AccountBuilder(client, type, currency2, accountDTO.getBalance());
        accountBuilder.setAddList(addList);
//        Account newAccount = new Account(client, type, currency2, accountDTO.getBalance());
        Account newAccount = new Account(accountBuilder);

        accountDAO.insertAccount(newAccount);

        return "Account Added";
    }

    @Path("remove")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String removeAccount( AccountIdDTO id) {
        Account account = accountDAO.findAccountById(id.getId());
        accountDAO.deleteAccount(account);
        return "Account Removed!";
    }


}
