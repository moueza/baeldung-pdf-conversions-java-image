package myPack;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class Main {

	private void generateImageFromPDF(String filename, String extension) {
		try {
			PDDocument document = PDDocument.load(new File(filename));
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (int page = 0; page < document.getNumberOfPages(); ++page) {
				BufferedImage bim;

				bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

				ImageIOUtil.writeImage(bim, String.format("src/output/pdf-%d.%s", page + 1, extension), 300);
			}
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.generateImageFromPDF("ADC_MOUEZA300919.pdf", "png");
	}
}
