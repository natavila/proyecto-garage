package ar.edu.unlam.tallerweb1.servicios;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.UnknownHostException;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.google.zxing.WriterException;

public interface ServicioQR {
	java.io.File generateQR( String text) throws WriterException, IOException;

	String devolverIp() throws UnknownHostException;

	String random();

	String directorioTrabajo() throws IOException;

	
}
