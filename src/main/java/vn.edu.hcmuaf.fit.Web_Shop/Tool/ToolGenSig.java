package vn.edu.hcmuaf.fit.Web_Shop.Tool;

import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.GenSigOrder;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import javax.swing.*;

public class ToolGenSig extends JFrame{
    JTextArea txtHash, txtPriKey, txtSignature;
    JButton uploadPriKey, startGenSig, copySig;
    JComboBox<String> cbAlgorithm;
    JPanel pnHash, pnPriKey, pnGenSig, pnBtnStartGenSig;

    public ToolGenSig() {
        setTitle("Tool tạo chữ ký điện tử");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        pnHash = new JPanel(new BorderLayout());
        pnHash.setBorder(BorderFactory.createTitledBorder("Vui lòng nhập mã Hash đơn hàng của bạn vào đây:"));
        txtHash = new JTextArea(4, 50);
        txtHash.setLineWrap(true);
        pnHash.add(new JScrollPane(txtHash), BorderLayout.CENTER);
        //
        pnPriKey = new JPanel(new BorderLayout());
        pnPriKey.setBorder(BorderFactory.createTitledBorder("Vui lòng nhập hoặc tải lên file private key vào đây:"));
        txtPriKey = new JTextArea(5, 50);
        txtPriKey.setLineWrap(true);
        pnPriKey.add(new JScrollPane(txtPriKey), BorderLayout.CENTER);
        JPanel pnPriKeyBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbAlgorithm = new JComboBox<>(new String[]{"DSA", "RSA"});
        uploadPriKey = new JButton("Tải lên khoá private");
        pnPriKeyBtn.add(uploadPriKey);
        pnPriKeyBtn.add(new JLabel("Chọn thuật toán:"));
        pnPriKeyBtn.add(cbAlgorithm);
        pnPriKey.add(pnPriKeyBtn, BorderLayout.SOUTH);
        //
        pnGenSig = new JPanel(new BorderLayout());
        pnGenSig.setBorder(BorderFactory.createTitledBorder("Kết quả tạo chữ ký:"));
        txtSignature = new JTextArea(4, 50);
        txtSignature.setLineWrap(true);
        txtSignature.setEditable(false);
        pnGenSig.add(new JScrollPane(txtSignature), BorderLayout.CENTER);
        JPanel pnCopySig = new JPanel(new FlowLayout(FlowLayout.LEFT));
        copySig = new JButton("Copy chữ ký");
        pnCopySig.add(copySig);
        pnGenSig.add(pnCopySig, BorderLayout.SOUTH);
        //
        pnBtnStartGenSig = new JPanel();
        startGenSig = new JButton("Bắt đầu tạo chữ ký");
        pnBtnStartGenSig.add(startGenSig);

        add(pnHash);
        add(pnPriKey);
        add(pnGenSig);
        add(pnBtnStartGenSig);

        uploadPriKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Tải lên file khoá private");
                int option = fileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    try {
                        String content = new String(Files.readAllBytes(file.toPath()));
                        txtPriKey.setText(content);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Không thể đọc file này!");
                        ex.printStackTrace();
                    }
                }
            }
        });

        startGenSig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hash = txtHash.getText().trim();
                String priKey = txtPriKey.getText().trim();
                String algo = cbAlgorithm.getSelectedItem().toString();

                if (hash.isEmpty() || priKey.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Không thể tạo chữ ký vì thiếu mã hash hoặc khoá private");
                    return;
                }

                priKey = priKey.replace("-----BEGIN PRIVATE KEY-----", "")
                        .replace("-----END PRIVATE KEY-----", "")
                        .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                        .replace("-----END RSA PRIVATE KEY-----", "")
                        .replaceAll("\\s+", "");

                try {
                    String result = GenSigOrder.signData(hash, priKey, algo);
                    txtSignature.setText(result);
                    JOptionPane.showMessageDialog(null, "Đã tạo chữ ký thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi. Không thể tạo chữ ký!");
                    ex.printStackTrace();
                }
            }
        });

        copySig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String signature = txtSignature.getText();
                if (signature.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Không thể copy vì chưa tạo chữ ký!");
                } else {
                    StringSelection stringSelection = new StringSelection(signature);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(null, "Đã copy chữ ký thành công!");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ToolGenSig();
    }
}
