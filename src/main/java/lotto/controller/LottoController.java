package lotto.controller;

import lotto.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine();
    }

    public void run() {
        int purchaseAmount = retry(inputView::getPurchaseAmount);
        List<Lotto> userLottos = lottoMachine.issueLottos(purchaseAmount);
        outputView.printPurchasedLottos(userLottos);

        WinningNumbers winningNumbers = retry(() -> {
            Lotto winningLotto = retry(inputView::getWinningLotto);
            int bonusNumber = retry(inputView::getBonusNumber);
            return new WinningNumbers(winningLotto, bonusNumber);
        });

        LottoResult lottoResult = new LottoResult(userLottos, winningNumbers);
        outputView.printStatistics(lottoResult, purchaseAmount);
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
