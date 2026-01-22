package vn.edu.hcmuaf.fit.Web_Shop.Service;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.ProductHomeDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;
import java.util.List;

public class ProductHomeService {
    ProductHomeDao prDao = new ProductHomeDao();

    public List<Product> getHomeDiscountProducts(){
        return prDao.getDiscountProducts(8);
    }
    public List<Product> getHomeRecommendedProducts(){
        return prDao.getRecommendedProducts(8);
    }
    public List<Product> getAllDiscountProducts(){
        return prDao.getDiscountProducts(100);
    }
    public List<Product> getAllRecommendedProducts(){
        return prDao.getRecommendedProducts(100);
    }
}
