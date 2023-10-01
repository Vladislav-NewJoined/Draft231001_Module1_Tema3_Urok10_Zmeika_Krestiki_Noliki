
public class Apple2 {

    public int posX2;
    public int posY2;

    public  Apple2(int x2, int y2) {
        posX2 = x2;
        posY2 = y2;
    }

    public void setRandomPosition2() {
        posX2 = Math.abs((int) (Math.random()* Task1_3_10_4.WIDTH-1));
        posY2 = Math.abs((int) (Math.random()* Task1_3_10_4.HEIGHT-1));
    }
}
