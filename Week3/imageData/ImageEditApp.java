public class ImageEditApp {

    public static void main(String[] args) {
        ImageData image = new ImageData(4, 8);
        ImageEdit scaled = new ScaleImage();
        ImageEdit rotated = new RotateImage();
        ImageEdit inverted = new InvertImage();
        scaled.editImage(image);
        rotated.editImage(image);
        inverted.editImage(image);
        System.out.println(image);
        System.out.println();
        System.out.println(scaled);
        System.out.println();
        System.out.println(rotated);
        System.out.println();
        System.out.println(inverted);
    }
}