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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
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
	            String fileType = "jpg";
	            int size = 300;
	            //UUID uuid = UUID.randomUUID();
	            String randomUUIDString = random();
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


       
      //Devuelve el Ip
	@Override
       public String devolverIp() throws UnknownHostException {
    	   String address= InetAddress.getLocalHost().getHostAddress();
    	   return address;
       }
       
       


	//Creo un metodo q genera un string random de hasta 16 de longitud
	@Override
	public String random() {
		UUID uuid = UUID.randomUUID();
		String s = Long.toString(uuid.getMostSignificantBits(), 36);
		return s;
	}
	
	
	
 }
	 
	 
	 
	
	
	
	
	
