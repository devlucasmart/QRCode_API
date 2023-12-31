package com.devlucasmart.qrcode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

@Service
public class QRCodeGeneratorService {
    public static void generateQRCodeImage(String text, int width, int height, String filePath, Color foregroundColor, Color backgroundColor)
        throws WriterException, IOException {

                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

                BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                qrCodeImage.createGraphics();

                Graphics2D graphics = (Graphics2D) qrCodeImage.getGraphics();
                graphics.setColor(backgroundColor);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(foregroundColor);

                IntStream.range(0, width)
                        .forEach(i -> IntStream.range(0, height)
                                .filter(j -> bitMatrix.get(i, j))
                                .forEach(j -> graphics.fillRect(i, j, 1, 1)));

                File qrCodeFile = new File(filePath);
                ImageIO.write(qrCodeImage, "png", qrCodeFile);
            }
}

