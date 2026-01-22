package vn.edu.hcmuaf.fit.Web_Shop.Service;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    public Product getDetailProduct(int id){
        return productDao.getProductByID(id);
    }
}
