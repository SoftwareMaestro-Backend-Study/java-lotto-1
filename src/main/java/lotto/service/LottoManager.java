package lotto.service;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchasingMoney;
import lotto.domain.generator.IssuedLottoGenerator;
import lotto.domain.generator.WinningLottoGenerator;
import lotto.domain.result.ProfitRate;
import lotto.domain.result.WinningDetail;
import lotto.util.Convertor;
import lotto.util.Input;
import lotto.util.Output;

public class LottoManager implements Manager {

    @Override
    public void run() {
        PurchasingMoney purchasingMoney = PurchasingMoney.from(Convertor.toInteger(Input.inputPurchasingMoney()));
        Lottos issuedLottos = purchaseLotto(purchasingMoney);

        Lotto winningLotto = Lotto.from(WinningLottoGenerator.from(Input.inputWinningLotto()));
        BonusNumber bonusNumber = BonusNumber.from(winningLotto, Convertor.toInteger(Input.inputBonusNumber()));

        compareLotto(purchasingMoney, issuedLottos, winningLotto, bonusNumber);
    }

    private Lottos purchaseLotto(PurchasingMoney purchasingMoney) {
        Lottos issuedLotto = Lottos.from(IssuedLottoGenerator.create(), purchasingMoney);
        Output.printIssuedLotto(issuedLotto);
        return issuedLotto;
    }

    private void compareLotto(PurchasingMoney purchasingMoney, Lottos issuedLotto, Lotto winningLotto, BonusNumber bonusNumber) {
        WinningDetail winningDetail = WinningDetail.from(winningLotto.compare(issuedLotto, bonusNumber));
        ProfitRate profitRate = ProfitRate.from(winningDetail.getTotalPrize(), purchasingMoney.getMoney());
        Output.printWinningDetail(winningDetail.getResult(), profitRate.getValue());
    }
}
