package sr.unasat.Admin.Controllers;

import sr.unasat.Admin.Config.JPAConfiguration;
import sr.unasat.Admin.DAO.ClientDAO;
import sr.unasat.Admin.DTO.ClientDTO;
import sr.unasat.Admin.Entities.Client;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("client")
public class ClientController {
    private ClientDAO clientDAO= new ClientDAO(JPAConfiguration.getEntityManager());

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> retrieveClientList(){
        return clientDAO.retrieveClientList();
    }

    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(Client client){
        clientDAO.insertClient(client);
        return "Customer: "+client.getFirstName()+" "+client.getLastName()+" added";
    }

    @Path("remove")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String remove(ClientDTO id) {
        Client client = clientDAO.findClientById(id.getId());
        clientDAO.deleteClient(client);
        return "Client has been deleted";
    }

    @Path("update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateClient(Client newClient) {
        Client client = clientDAO.findClientById(newClient.getId());
        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
        client.setDob(newClient.getDob());
        client.setGender(newClient.getGender());
        client.setEmail(newClient.getEmail());
        client.setPhoneNumber(newClient.getPhoneNumber());
        client.setDistrict((newClient.getDistrict()));
        client.setIdCard((newClient.getIdCard()));
        clientDAO.updateClient(client);
        return "Client Updated";
    }

    @Path("getClient")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Client getClient(ClientDTO clientDTO) {
         return clientDAO.findClientById(clientDTO.getId());
    }

}
