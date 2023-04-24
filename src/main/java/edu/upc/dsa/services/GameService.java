package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.State;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/tracks", description = "Endpoint to Track Service")
@Path("/tracks")
public class GameService {

    private GameManager tm;

    public GameService() throws Exception {
        this.tm = GameManagerImpl.getInstance();
        tm.createGame(2, 2);
        tm.addUser("og9", "olivier", "Giroud");
        tm.addUser("kb9", "Karim", "Benzema");
        tm.addUser("km7", "Kylian", "Mbappé");
        tm.addUser("ag7", "Antoine", "Griezmann");
        tm.init("og9");
        tm.init("kb9");
        tm.init("km7");
        tm.init("ag7");
    }

    @GET
    @ApiOperation(value = "get the state of the game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Integer.class),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getState() {
        State s = this.tm.consultState();
        return Response.status(201).entity(s).build();
    }

    @GET
    @ApiOperation(value = "get player life", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Integer.class),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLife(@PathParam("id") String id) {
        Object i = this.tm.consultLifePoints(id);
        if (i == null) return Response.status(404).build();
        else  return Response.status(201).entity(i).build();
    }

    @GET
    @ApiOperation(value = "get team life", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Integer.class),
            @ApiResponse(code = 404, message = "Team not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") int j) {
        Object i = this.tm.consulLifePoints(j);
        if (i == null) return Response.status(404).build();
        else  return Response.status(201).entity(i).build();
    }

    @DELETE
    @ApiOperation(value = "finish the game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response finishGame() {
        this.tm.end();
        return Response.status(201).build();
    }


    @PUT
    @ApiOperation(value = "update the value of life", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTrack(@PathParam("id") String id, @PathParam("n") int n) {
        tm.refresh(id, n);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Buy a product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{productId}{playerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTrack(@PathParam("productId") String productId, @PathParam("playerId") String playerId) {
        tm.buyProduct(productId, playerId);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "init", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTrack(@PathParam("id") String id) throws Exception {
        tm.init(id);
        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/{n}{p}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(@PathParam("n") int n, @PathParam("p") int p) {
        tm.createGame(n,p);
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "add a new user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/{id}{name}{surname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(@PathParam("id") String id, @PathParam("name") String name, @PathParam("surname") String surname) throws Exception {
        tm.addUser(id, name, surname);
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "add a new product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/{id}{descr}{price}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(@PathParam("id") String id, @PathParam("descr") String descr, @PathParam("price") int price) throws Exception {
        tm.addProduct(id, descr, price);
        return Response.status(201).build();
    }
}