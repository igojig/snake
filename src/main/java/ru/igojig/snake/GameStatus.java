package ru.igojig.snake;

public enum GameStatus{
    PLAYED("Игра"),
    BOMBED("Вы подорвались!"),
    SELF_EATED("Вы съели сами себя!"),
    OUT_OF_ARAE("Вы вышли за пределы поля");

    String status;

    GameStatus(String status){
        this.status=status;
    }

    public String getStatus() {
        return status;
    }
}