package Excecoes;

import ENUM.RegraServico;

public class ValidacaoServico extends RuntimeException {
    RegraServico errorCode;

    public ValidacaoServico(RegraServico errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RegraServico getErrorCode() {
        return errorCode;
    }
}
