package org.practice.morning_classes.day_4.rest_example;

import org.practice.morning_classes.day_4.rest_example.dao.ProductDao;
import org.practice.morning_classes.day_4.rest_example.dao.UserDao;
import org.practice.morning_classes.day_4.rest_example.entity.Product;
import org.practice.morning_classes.day_4.rest_example.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class ProductResource {
    ProductDao productDao = new ProductDao();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts(){
        return productDao.getAllProducts();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") int id){
        return productDao.getProduct(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(Product product) {
        productDao.addProduct(product); }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProduct(@PathParam("id") int id, Product product) {
        product.setId(id);
        productDao.updateProduct(product); }
    @DELETE
    @Path("/{id}")
    public void deleteProduct(@PathParam("id") int id) {
        productDao.deleteProduct(id); }
}
