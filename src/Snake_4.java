public class Snake_4 {

    public int length = 3;
    public int direction = 2;
    public int countApples = 0;

    public int sX[] = new int[400];
    public int sY[] = new int[400];

    public Snake_4(int x1, int y1, int x2, int y2, int x3, int y3) {
        sX[0] = x1;
        sX[1] = x2;
        sX[2] = x3;
        sY[0] = y1;
        sY[1] = y2;
        sY[2] = y3;
    }

    public void move() {
        for (int l = length; l > 0; l--) {
            sX[l] = sX[l-1];
            sY[l] = sY[l-1];
        }

        //up
        if (direction == 0) sY[0]--;
        //down
        if (direction == 2) sY[0]++;
        //right
        if (direction == 1) sX[0]++;
        //left
        if (direction == 3) sX[0]--;
    }
}
