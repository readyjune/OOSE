public class ImageData {

    private int[][] image;
    private int width;
    private int height;

    public ImageData(int width, int height) {
        image = new int[height][width];
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPixel(int x, int y, int value) {
        image[y][x] = value;
    }

    public int getPixel(int x, int y) {
        return image[y][x];
    }

    public String toString() {
        String rString = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rString += getPixel(x, y);
            }
            rString += "\n";
        }
        return rString;
    }
}