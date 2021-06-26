package ar.edu.unlam.tallerweb1.servicios;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.layout.element.Image;
import com.mysql.cj.xdevapi.Result;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioQRImpl implements ServicioQR{
	
	
       @Override
	 public File generateQR( String text) throws WriterException, IOException {
		
	            String content = text;
	            String filePath = "C:\\Users\\Gaston Mica y juan\\eclipse-workspace\\proyecto-garage\\src\\main\\webapp\\imagenes\\";
	            String fileType = "png";
	            int size = 200;
	            UUID uuid = UUID.randomUUID();
	            String randomUUIDString = uuid.toString();
	            QRCodeWriter qrcode = new QRCodeWriter();
	            BitMatrix matrix = qrcode.encode(content, BarcodeFormat.QR_CODE, size, size);
	            File qrFile = new File(filePath + randomUUIDString + "." + fileType);
	            File dire = new File (randomUUIDString + "." + fileType);
	            
	            int matrixWidth = matrix.getWidth();
	            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
	            image.createGraphics();
	            
	            Graphics2D graphics = (Graphics2D) image.getGraphics();
	            graphics.setColor(Color.WHITE);
	            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
	            graphics.setColor(Color.BLACK);
	            
	            for (int b = 0; b < matrixWidth; b++) {
	                for (int j = 0; j < matrixWidth; j++) {
	                    if (matrix.get(b, j)) {
	                        graphics.fillRect(b, j, 1, 1);
	                    }
	                }
	            }
	            
	            ImageIO.write(image, fileType, qrFile);
	            return dire;
	        }


@Override
public String decoder(File file) throws Exception {

    FileInputStream inputStream = new FileInputStream(file);

    BufferedImage image = ImageIO.read(inputStream);

    int width = image.getWidth();
    int height = image.getHeight();
    int[] pixels = new int[width * height];

    LuminanceSource source = new BufferedImageLuminanceSource(image);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

    // decode the barcode
    QRCodeReader reader = new QRCodeReader();
    com.google.zxing.Result result = reader.decode(bitmap);
    return new String(((com.google.zxing.Result) result).getText());
			}



				
	
		@Override	
		public BufferedImage crearQR(String datos) throws WriterException {
			BitMatrix matrix;
			Integer ancho=300;
			Integer altura=300;
			Writer escritor = new QRCodeWriter();
			matrix = escritor.encode(datos, BarcodeFormat.QR_CODE, ancho, altura);
         
    BufferedImage imagen = new BufferedImage(ancho, altura, BufferedImage.TYPE_INT_RGB);
         
    for(int y = 0; y < altura; y++) {
        for(int x = 0; x < ancho; x++) {
            int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
            imagen.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
        }
    }
         
    return imagen;        
}

	 
	    }
	 
	 
	 
	
	
	
	
	
