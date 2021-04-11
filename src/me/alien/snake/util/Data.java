package me.alien.snake.util;

public class Data {
    public class GameStates{
        public static final int DIED = 0;
        public static final int VICTORY = 1;
        public static final int RUNING = 2;
        public static final int NOT_STARTED = 3;
    }

    public class Walls{
        public static final int DENGER = 0;
        public static final int SIDE_FLIPING = 1;
    }

    public static class Key{
        public static final int RIGHT = 0;
        public static final int DOWN = 1;
        public static final int LEFT = 2;
        public static final int UP = 3;

        public static String intToString(int key){
            if(key==UP) return "UP";
            if(key==DOWN) return "DOWN";
            if(key==RIGHT) return "RIGHT";
            if(key==LEFT) return "LEFT";
            return "Invalid";
        }
    }

}
