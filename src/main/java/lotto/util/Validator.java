package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Validator {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final int LOTTO_PRICE = 1000;

    public static int toNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "숫자를 입력해 주세요.");
        }
    }

    public static void validatePurchaseAmount(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액은 1,000원 단위로 입력해야 합니다.");
        }
    }

    public static List<Integer> toNumericList(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "숫자 목록을 올바른 형식으로 입력해 주세요.");
        }
    }
}
