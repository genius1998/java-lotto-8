package lotto.domain;

import lotto.Lotto;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public WinningRank match(Lotto userLotto) {
        int matchCount = (int) userLotto.getNumbers().stream()
                .filter(winningLotto::contains)
                .count();

        boolean hasBonus = userLotto.contains(bonusNumber);
        return WinningRank.valueOf(matchCount, hasBonus);
    }
}
