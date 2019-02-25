package chushkaApp.service;
import chushkaApp.domain.entities.Product;
import chushkaApp.domain.models.service.ProductServiceModel;
import chushkaApp.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel)  {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository.findAll().stream()
                .map(p->this.modelMapper.map(p,ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findProductByName(String name) {
        ProductServiceModel product = this.modelMapper.map(this.productRepository.findByName(name), ProductServiceModel.class);
        return product;
    }
}
