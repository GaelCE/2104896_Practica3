package com.example._104896_practica3.Vista;

public class Cara {
    private String emoji;

    public Cara(int cara){
        switch (cara){
            case 1:
               emoji="\uD83E\uDD16";
               break;
            case 2:
                emoji="\uD83D\uDC7E";
                break;
            case 3:
                emoji="\uD83D\uDC3C";
                break;
            case 4:
                emoji="\uD83D\uDC7D";
                break;
            case 5:
                emoji="\uD83E\uDD21";
                break;
            case 6:
                emoji="\uD83D\uDC7F";
                break;
            case 7:
                emoji="\uD83D\uDC80";
                break;
            case 8:
                emoji="\uD83D\uDC7B";
                break;
            case 9:
                emoji="\uD83E\uDD8A";
                break;
            case 10:
                emoji="\uD83D\uDD75\uFE0F\u200D\u2642\uFE0F";
                break;
        }
    }

    public String getEmoji(){
        return emoji;
    }
}
