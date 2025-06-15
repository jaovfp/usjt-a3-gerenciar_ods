package usjt.atividade.views.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Objects;

import static usjt.atividade.views.utils.ComponentFactory.createRoundedImage;

public class ImageUtils {

    public static ImageIcon loadImage(String url, int width,int height) {
        try {
            if (url == null || url.isBlank()) {
                return getDefaultProfileImage();
            }

            File imageFile = new File(url);
            BufferedImage originalImage = ImageIO.read(imageFile);
            BufferedImage roundedImage = createRoundedImage(originalImage,width, height);
            return new ImageIcon(roundedImage);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e);
            return getDefaultProfileImage();
        }
    }

    public static ImageIcon getDefaultProfileImage() {
        URL imageUrl = ImageUtils.class.getResource("/images/default_profile.png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            return scaleImageIcon(icon, 100, 100);
        } else {
            return new ImageIcon();
        }
    }

    public static String getDefaultProfileAbsolutePath() {
        try {
            URL resourceUrl = Objects.requireNonNull(ComponentFactory.class.getResource("/images/default_profile.png"));
            File file = new File(resourceUrl.toURI());
            return file.getAbsolutePath();
        }catch (Exception e){
            System.err.println(e);
            throw new RuntimeException("Erro ao obter default profile.");
        }
    }

    public static ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

}
