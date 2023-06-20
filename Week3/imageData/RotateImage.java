public class RotateImage extends ImageEdit {

    protected int convertPixel(ImageData oldImage, int x, int y, int newHeight) {
        return oldImage.getPixel(newHeight - y - 1, x);
    }

    protected int getNewHeight(ImageData oldImage) {
        return oldImage.getWidth();
    }

    protected int getNewWidth(ImageData oldImage) {
        return oldImage.getHeight();
    }
}
