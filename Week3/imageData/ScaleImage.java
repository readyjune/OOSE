public class ScaleImage extends ImageEdit {

    protected int convertPixel(ImageData oldImage, int x, int y, int newHeight) {
        return oldImage.getPixel(x * 2, y * 2);
    }

    protected int getNewHeight(ImageData oldImage) {
        return oldImage.getHeight() / 2;
    }

    protected int getNewWidth(ImageData oldImage) {
        return oldImage.getWidth() / 2;
    }
}
