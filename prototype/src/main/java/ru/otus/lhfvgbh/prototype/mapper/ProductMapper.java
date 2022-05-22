package ru.otus.lhfvgbh.prototype.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.otus.lhfvgbh.prototype.domain.Product;
import ru.otus.lhfvgbh.prototype.dto.ProductDTO;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDTO productDTO);

    @InheritInverseConfiguration
    ProductDTO fromProduct(Product product);

    List<Product> toProductList(List<ProductDTO> productDTOs);

    List<ProductDTO> fromProductList(List<Product> products);
}
