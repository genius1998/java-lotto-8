package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.Lotto;
import lotto.util.Validator;

import java.util.List;

public class InputView {

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        int amount = Validator.toNumeric(input);
        Validator.validatePurchaseAmount(amount);
        return amount;
    }

    public Lotto getWinningLotto() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        List<Integer> numbers = Validator.toNumericList(input);
        return new Lotto(numbers);
    }

    public int getBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        return Validator.toNumeric(input);
    }
}
