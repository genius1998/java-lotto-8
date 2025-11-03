package lotto.view;

import lotto.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.WinningRank;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatistics(LottoResult lottoResult, int purchaseAmount) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        Map<WinningRank, Integer> result = lottoResult.getResult();

        for (WinningRank rank : WinningRank.values()) {
            if (rank != WinningRank.NONE) {
                System.out.println(formatRank(rank, result.get(rank)));
            }
        }

        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);
        System.out.println("총 수익률은 " + formatProfitRate(profitRate) + "%입니다.");
    }

    private String formatRank(WinningRank rank, int count) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        String prize = formatter.format(rank.getPrizeMoney());
        return String.format("%s (%s원) - %d개", rank.getDescription(), prize, count);
    }

    private String formatProfitRate(double profitRate) {
        DecimalFormat formatter = new DecimalFormat("#,##0.0");
        return formatter.format(profitRate);
    }
}
