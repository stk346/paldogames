package hello.paldogames;

import hello.paldogames.controller.pageutils.PageUtils;

public class Test {

    @org.junit.jupiter.api.Test
    public void enumTest() {
        int startPage = PageUtils.START_PAGE_NUMBER.getNumbers();
        int boardsCountPerPage = PageUtils.BOARD_COUNTS_PER_PAGE.getNumbers();
        System.out.println("StartPage = " + startPage);
        System.out.println("boardsCountPerPage = " + boardsCountPerPage);
    }

}
