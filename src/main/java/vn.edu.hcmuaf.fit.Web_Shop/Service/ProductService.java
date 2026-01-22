package vn.edu.hcmuaf.fit.Web_Shop.Service;

import vn.edu.hcmuaf.fit.Web_Shop.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    public Product getDetailProduct(int id){
        return productDao.getProductByID(id);
    }
    public List<Product> getProductByCategory(int cid){
        return productDao.getProductsByCategory(cid);
    }
    public String getCategoryDescription(int cid){
        return productDao.getCategoryDescription(cid);
    }
    public int countProductsByCategory(int cid) {
        return productDao.countProductsByCategory(cid);
    }

    public List<Product> getProductsByCategory(int cid, String sortType, int index) {
        return productDao.getProductsByCategory(cid, sortType, index);
    }
}
