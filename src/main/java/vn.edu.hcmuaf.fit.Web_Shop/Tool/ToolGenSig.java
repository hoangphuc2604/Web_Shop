package vn.edu.hcmuaf.fit.Web_Shop.Tool;

import vn.edu.hcmuaf.fit.Web_Shop.DigitalSignature.GenSigOrder;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;
import javax.swing.*;

public class ToolGenSig extends JFrame{
    JTextArea txtHash, txtPriKey, txtSignature, txtPubKeyGen, txtPriKeyGen;
    JButton uploadPriKey, startGenSig, copySig, genKey, saveKey;
    JComboBox<String> cbAlgorithmSign, cbAlgorithmGenKey;
    JPanel pnGenKey, pnHash, pnPriKey, pnGenSig, pnBtnStartGenSig;

    public ToolGenSig() {
        setTitle("Tool tạo khoá và chữ ký điện tử");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        pnGenKey = new JPanel(new BorderLayout());
        pnGenKey.setBorder(BorderFactory.createTitledBorder("Tạo và lưu khoá public và khoá private"));
        JPanel pnGenKeyBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbAlgorithmGenKey = new JComboBox<>(new String[]{"DSA", "RSA"});
        genKey = new JButton("Tạo khoá");
        saveKey = new JButton("Lưu khoá public và private về máy");
        pnGenKeyBtn.add(new JLabel("Chọn thuật toán:"));
        pnGenKeyBtn.add(cbAlgorithmGenKey);
        pnGenKeyBtn.add(genKey);
        pnGenKeyBtn.add(saveKey);
        pnGenKey.add(pnGenKeyBtn, BorderLayout.NORTH);
        JPanel pnPubPriKey = new JPanel(new GridLayout(2,1,0,5));
        txtPubKeyGen = new JTextArea(3, 50);
        txtPubKeyGen.setLineWrap(true);
        JScrollPane scrollPubKey = new JScrollPane(txtPubKeyGen);
        scrollPubKey.setBorder(BorderFactory.createTitledBorder("Public key:"));
        pnPubPriKey.add(scrollPubKey);
        txtPriKeyGen = new JTextArea(3, 50);
        txtPriKeyGen.setLineWrap(true);
        JScrollPane scrollPriKey = new JScrollPane(txtPriKeyGen);
        scrollPriKey.setBorder(BorderFactory.createTitledBorder("Private key:"));
        pnPubPriKey.add(scrollPriKey);
        pnGenKey.add(pnPubPriKey, BorderLayout.CENTER);
        //
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
        cbAlgorithmSign = new JComboBox<>(new String[]{"DSA", "RSA"});
        uploadPriKey = new JButton("Tải lên khoá private");
        pnPriKeyBtn.add(uploadPriKey);
        pnPriKeyBtn.add(new JLabel("Chọn thuật toán:"));
        pnPriKeyBtn.add(cbAlgorithmSign);
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

        add(pnGenKey);
        add(pnHash);
        add(pnPriKey);
        add(pnGenSig);
        add(pnBtnStartGenSig);

        genKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String algo = cbAlgorithmGenKey.getSelectedItem().toString();
                    KeyPairGenerator keyGen;
                    if ("RSA".equals(algo)){
                        keyGen = KeyPairGenerator.getInstance("RSA");
                        keyGen.initialize(2048);
                    }
                    else {
                        keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
                        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
                        keyGen.initialize(1024, random);
                    }
                    KeyPair keyPair = keyGen.generateKeyPair();
                    String pubKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
                    String priKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

                    txtPubKeyGen.setText(pubKey);
                    txtPriKeyGen.setText(priKey);
                    JOptionPane.showMessageDialog(null, "Đã tạo khoá public và khoá private thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi tạo khoá: " + ex.getMessage());
                }
            }
        });

        saveKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (txtPriKeyGen.getText().isEmpty()){
                   JOptionPane.showMessageDialog(null, "Lỗi: Chưa tạo khoá, không thể lưu!");
                   return;
               }

               JFileChooser fileChooser = new JFileChooser();
               int option = fileChooser.showSaveDialog(null);
               if (option == JFileChooser.APPROVE_OPTION){
                   File file = fileChooser.getSelectedFile();
                   try {
                       FileWriter fwPri = new FileWriter(file.getAbsolutePath() + ".pri");
                       fwPri.write(txtPriKeyGen.getText());
                       fwPri.close();
                       FileWriter fwPub = new FileWriter(file.getAbsolutePath() + ".pub");
                       fwPub.write(txtPubKeyGen.getText());
                       fwPub.close();
                       JOptionPane.showMessageDialog(null, "Đã lưu các file khoá thành công!");
                   } catch (Exception ex) {
                       JOptionPane.showMessageDialog(null, "Lỗi lưu khoá: " + ex.getMessage());
                   }
               }
            }
        });

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
                String algo = cbAlgorithmSign.getSelectedItem().toString();

                if (hash.isEmpty() || priKey.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Không thể tạo chữ ký vì thiếu mã hash hoặc khoá private");
                    return;
                }

                priKey = priKey.replaceAll("\\s+", "");

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
