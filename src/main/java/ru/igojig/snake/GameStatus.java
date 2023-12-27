package ru.igojig.snake;

public enum GameStatus{
    PLAYED("Игра"),
    BOMBED("Вы подорвались!"),
    SELF_EATED("Вы съели сами себя!"),
    OUT_OF_AREA("Вы вышли за пределы поля");

    final String status;

    GameStatus(String status){
        this.status=status;
    }

    public String getStatus() {
        return status;
    }
}
