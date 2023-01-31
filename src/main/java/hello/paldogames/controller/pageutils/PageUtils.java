package hello.paldogames.controller.pageutils;

public enum PageUtils {

    BOARD_COUNTS_PER_PAGE(10),
    START_PAGE_NUMBER(1);

    private final int numbers;
    PageUtils(int numbers) {
        this.numbers = numbers;
    }

    public int getNumbers() {
        return numbers;
    }
}
