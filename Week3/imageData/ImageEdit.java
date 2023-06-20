public abstract class ImageEdit {
    private ImageData newImage;
    private int newHeight;
    private int newWidth;

    protected abstract int convertPixel(ImageData oldImage, int x, int y, int newHeight);

    protected abstract int getNewHeight(ImageData oldImage);

    protected abstract int getNewWidth(ImageData oldImage);

    public void editImage(ImageData oldImage) {
        newHeight = getNewHeight(oldImage);
        newWidth = getNewWidth(oldImage);
        newImage = new ImageData(newWidth, newHeight);
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                newImage.setPixel(x, y, convertPixel(oldImage, x, y, newHeight));
            }
        }
    }

    public String toString() {
        return newImage.toString();
    }
}
