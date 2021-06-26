package ar.edu.unlam.tallerweb1.servicios;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.google.zxing.WriterException;
import com.itextpdf.layout.element.Image;

public interface ServicioQR {
	java.io.File generateQR( String text) throws WriterException, IOException;

	String decoder(java.io.File file) throws Exception;

	BufferedImage crearQR(String datos) throws WriterException;
}
