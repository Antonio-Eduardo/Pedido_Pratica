package exceptions;

import enums.RegraServico;

public class CaixaLimiteExcedido extends ValidacaoServico {
    public CaixaLimiteExcedido() {
        super(RegraServico.CAIXA_LIMITE_EXCEDIDO, "Limite do caixa foi excedido, tente em outro caixa.");
    }
}
