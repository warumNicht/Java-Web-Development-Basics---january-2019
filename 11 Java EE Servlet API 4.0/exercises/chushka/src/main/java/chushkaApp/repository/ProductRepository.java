package chushkaApp.repository;


import chushkaApp.domain.entities.Product;

public interface ProductRepository extends GenericRepository<Product,String> {
    Product findByName(String name);
}
