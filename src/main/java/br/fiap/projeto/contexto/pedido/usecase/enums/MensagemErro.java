package br.fiap.projeto.contexto.pedido.usecase.enums;

public enum MensagemErro {
    PEDIDO_NOT_FOUND("Pedido não encontrado!"),
    STATUS_UPDATE_ERROR( "Erro na atualização do status!"),
    COMANDA_CREATE_ERROR( "Erro na criação da comanda!"),
    COMANDA_INTEGRATION_ERROR( "Erro na integração com a Comanda!"),
    PRODUTO_NOT_FOUND( "Produto não encontrado!"),
    ITEM_NOT_FOUND_IN_LIST( "Item não encontrado na lista"),
    INVALID_OPERATION( "Operação inválida!"),
    PEDIDO_NOT_APPROVED( "Pedido não aprovado!"),
    INVALID_STATUS( "Status do pedido inválido para esta operação!"),
    PRODUTO_INTEGRATION_ERROR("Erro na integração com o Produto!"),
    PRODUTO_EXIST_IN_THE_ORDER("O produto já existe no pedido, utilize a função adicionar quantidade."),
    PAGAMENTO_INTEGRATION_ERROR( "Erro na integração com a Pagamento!");

    private final String message;

    MensagemErro(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
