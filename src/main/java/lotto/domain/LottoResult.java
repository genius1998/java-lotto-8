package lotto.domain;

import lotto.Lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private static final int LOTTO_PRICE = 1000;
    private final Map<WinningRank, Integer> result;

    public LottoResult(List<Lotto> userLottos, WinningNumbers winningNumbers) {
        this.result = new EnumMap<>(WinningRank.class);
        for (WinningRank rank : WinningRank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : userLottos) {
            WinningRank rank = winningNumbers.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
    }

    public Map<WinningRank, Integer> getResult() {
        return result;
    }

    public double calculateProfitRate(int purchaseAmount) {
        double totalPrize = result.entrySet().stream()
                .mapToDouble(entry -> (double) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        return (totalPrize / purchaseAmount) * 100;
    }
}
