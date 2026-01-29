package demo;

public class HelloWorld {
    // ANSI颜色代码
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";
    private static final String PURPLE = "\033[35m";
    private static final String CYAN = "\033[36m";
    private static final String WHITE = "\033[37m";
    private static final String BRIGHT_YELLOW = "\033[93m";
    private static final String BRIGHT_WHITE = "\033[97m";

    static void main(String[] args) {
        drawChristmasTree();
    }

    private static void drawChristmasTree() {
        // 打印顶部的星星
        System.out.println("        " + BRIGHT_YELLOW + "*" + RESET + "        ");
        System.out.println("       " + BRIGHT_YELLOW + "***" + RESET + "       ");

        // 第一层树冠
        for (int i = 1; i <= 3; i++) {
            printSpaces(7 - i);
            printColoredLine(2 * i - 1, GREEN, "o");
        }

        // 第二层树冠
        for (int i = 1; i <= 5; i++) {
            printSpaces(6 - i);
            printDecoratedLine(2 * i - 1, GREEN, "o");
        }

        // 第三层树冠
        for (int i = 1; i <= 7; i++) {
            printSpaces(5 - i);
            printDecoratedLine(2 * i - 1, GREEN, "o");
        }

        // 第四层树冠
        for (int i = 1; i <= 9; i++) {
            printSpaces(4 - i);
            printDecoratedLine(2 * i - 1, GREEN, "o");
        }

        // 树干
        for (int i = 1; i <= 4; i++) {
            printSpaces(5);
            System.out.println(BRIGHT_WHITE + "***" + RESET);
        }

        // 底部装饰
        System.out.println("    " + RED + "*********" + RESET + "    ");

        // 底部文字
        System.out.println("\n" + "     " + BRIGHT_YELLOW + "MERRY CHRISTMAS!" + RESET);
    }

    // 打印指定数量的空格
    private static void printSpaces(int count) {
        for (int i = 0; i < Math.max(0, count); i++) {
            System.out.print(" ");
        }
    }

    // 打印彩色的行
    private static void printColoredLine(int count, String color, String character) {
        System.out.print(color);
        for (int i = 0; i < count; i++) {
            System.out.print(character);
        }
        System.out.println(RESET);
    }

    // 打印带有装饰的彩色行
    private static void printDecoratedLine(int count, String color, String character) {
        System.out.print(color);
        for (int i = 0; i < count; i++) {
            // 随机添加装饰
            if (Math.random() < 0.15) {
                // 随机选择装饰颜色
                String[] decorations = {RED, YELLOW, BLUE, PURPLE, CYAN};
                String decorationColor = decorations[(int) (Math.random() * decorations.length)];
                System.out.print(decorationColor + "@" + color);
            } else {
                System.out.print(character);
            }
        }
        System.out.println(RESET);
    }
}