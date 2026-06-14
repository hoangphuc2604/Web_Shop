package vn.edu.hcmuaf.fit.Web_Shop.Service;
import vn.edu.hcmuaf.fit.Web_Shop.Dao.UserKeyDao;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.VerSigOrder;
import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.SHA256;
import vn.edu.hcmuaf.fit.Web_Shop.Model.Order;
import vn.edu.hcmuaf.fit.Web_Shop.Model.OrderItem;
import vn.edu.hcmuaf.fit.Web_Shop.Model.UserKey;
public class OrderVerifierService {

    public static void verifyOrderIntegrity(Order order) {
        try {
            // Ràng buộc ON DELETE SET NULL của DB sẽ làm key_id = 0 nếu khóa bị xóa cứng
            if (order.getKeyId() <= 0 || order.getDigitalSig() == null) {
                order.setFake(true);
                return;
            }

            UserKey key = UserKeyDao.getKeyById(order.getKeyId());
            if (key == null) {
                order.setFake(true);
                return;
            }

            boolean isTimeViolated = false;
            if ("REVOKED".equals(key.getStatus()) && key.getRevokedAt() != null && order.getOrderDate() != null) {
                isTimeViolated = order.getOrderDate().after(key.getRevokedAt());
            }

            StringBuilder dataBuilder = new StringBuilder();
            dataBuilder.append(order.getTotalAmount());
            for (OrderItem item : order.getItems()) {
                dataBuilder.append(item.getProduct().getId())
                        .append(item.getQuantity())
                        .append(item.getUnitPrice());
            }

            SHA256 sha256 = new SHA256();
            String currentHash = sha256.checkSum(dataBuilder.toString());

            boolean isHashMatched = false;
            if(order.getOrderHash() != null){
                isHashMatched = currentHash.equals(order.getOrderHash());
            }

            boolean isSigValid = false;
            if(order.getOrderHash() != null && key.getPublicKey() != null){
                isSigValid = VerSigOrder.verifyBase64(order.getOrderHash(), order.getDigitalSig(), key.getPublicKey(), key.getAlgorithm());
            }
            order.setFake(!isHashMatched || !isSigValid || isTimeViolated);

        } catch (Exception e) {
            e.printStackTrace();
            order.setFake(true);
        }
    }
}
