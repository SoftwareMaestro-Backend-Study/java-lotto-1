package lotto;

import lotto.lotto.LottoService;

public class Application {
    public static void main(String[] args) {
        LottoService lottoService = new LottoService();
        lottoService.run();
    }
}
