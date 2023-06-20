public class InvertImage extends ImageEdit {

    protected int convertPixel(ImageData oldImage, int x, int y, int newHeight) {
        return ~oldImage.getPixel(x, y);
    }

    protected int getNewHeight(ImageData oldImage) {
        return oldImage.getHeight();
    }

    protected int getNewWidth(ImageData oldImage) {
        return oldImage.getWidth();
    }
}
