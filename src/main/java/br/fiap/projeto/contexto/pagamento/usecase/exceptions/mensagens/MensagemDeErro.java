package br.fiap.projeto.contexto.pagamento.usecase.exceptions.mensagens;

public enum MensagemDeErro {

    PAGAMENTO_NAO_ENCONTRADO("Pagamento não encontrado! Verifique o código."),
    PEDIDO_PAGAMENTO_NAO_ENCONTRADO("Pagamento para este código de pedido não foi encontrado. Verifique o código."),

    STATUS_INVALIDO("Transição entre estados de pagamento inválida. Verifique e tente novamente."),
    ;


    private final String message;

    MensagemDeErro(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
