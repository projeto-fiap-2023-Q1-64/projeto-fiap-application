# Estrutura do projeto

## Estrutura base

O projeto está dividido em cinco contextos. Mesmo fazendo parte de um único projeto, os contextos estão desvinculados e se comunicam por APIs.

```
contexto
├── comanda
├── identificacao
├── pagamento
├── pedido
└── produto

directory: 5
```

Dentro de cada contexto, uma estrutura de diretórios que visa organizar as camadas
conforme o descrito na Clean Arquitecture.

* **EXTERNAL:** A camada mais externa da aquitetura, visa concentrar os recursos que irão efetuar a comunicação externa, como também é a camada reservada para as configurações e recursos dependentes de algum framework, como a disponibilização de APIs, acesso ao banco de dados e a configuração das injeções de dependências, etc.
* **ADAPTER:** A camada reservada para adaptadores que farão a ligação/tradução da comunicação da camada externa com os use cases, através de GATEWAYS, CONTROLLERS e PRESENTERS. Garantindo a conversão de dados da camada externa para entidades, mantendo assim o isolamento das camadas mais internas.
  * **GATEWAYS:** Os use cases utilizam para a sincronização de dados externos como acesso a banco de dados e o consumo de outras APIs.
  * **CONTROLLERS:** São responsáveis por receber solicitações externas ao domínio de negócio, permitindo efetuar operações com os use cases.
  * **PRESENTERS:** São responsáveis por cuidar da apresentação, porem no projeto não os utilizamos por não termos interfaces visuais.
* **USECASE:** A camada reservada para a implementação dos use cases que irão orquestrar as regras de negócio e se comunicará com o mundo externo através de adaptadores que serão injetados para seu uso.
* **ENTITY:** Esta é a camada mais interna onde as entidades de domínio se encontram.

```
contexto
├── comanda
|  ├── adapter
|  ├── entity
|  ├── external
|  └── usecase
├── identificacao
|  ├── adapter
|  ├── entity
|  ├── external
|  └── usecase
├── pagamento
|  ├── adapter
|  ├── entity
|  ├── external
|  └── usecase
├── pedido
|  ├── adapter
|  ├── entity
|  ├── external
|  └── usecase
└── produto
   ├── adapter
   ├── entity
   ├── external
   └── usecase

directory: 25
```

Abaixo pode ser visualizada a arvore da estrutura completa de diretórios e seus arquivos:

```
contexto
├── comanda
|  ├── adapter
|  |  ├── controller
|  |  |  ├── BuscaPorComandaControllerAdapter.java
|  |  |  ├── BuscaPorStatusFinalizadoComandaControllerAdapter.java
|  |  |  ├── BuscaPorStatusPreparacaoComandaControllerAdapter.java
|  |  |  ├── BuscaPorStatusRecebidoComandaControllerAdapter.java
|  |  |  ├── CriaComandaControllerAdapter.java
|  |  |  ├── FinalizaComandaControllerAdapter.java
|  |  |  ├── PreparaComandaControllerAdapter.java
|  |  |  ├── port
|  |  |  |  ├── IAtualizaComandaControllerAdapter.java
|  |  |  |  ├── IBuscaPorComandaControllerAdapter.java
|  |  |  |  ├── IBuscaPorStatusFinalizadoComandaControllerAdapter.java
|  |  |  |  ├── IBuscaPorStatusPreparacaoComandaControllerAdapter.java
|  |  |  |  ├── IBuscaPorStatusRecebidoComandaControllerAdapter.java
|  |  |  |  └── ICriarComandaControllerAdapter.java
|  |  |  └── rest
|  |  |     └── dto
|  |  |        ├── BuscaPorComandaDTO.java
|  |  |        ├── BuscaPorStatusComandaDTO.java
|  |  |        ├── ComandaDTO.java
|  |  |        ├── CriarComandaDTO.java
|  |  |        ├── FinalizaStatusComandaDTO.java
|  |  |        └── PreparaComandaDTO.java
|  |  └── gateway
|  |     ├── BuscaPorComandaGatewayAdapter.java
|  |     ├── BuscaPorStatusComandaGatewayAdapter.java
|  |     ├── CriaComandaGatewayAdapter.java
|  |     ├── FinalizaComandaGatewayAdapter.java
|  |     ├── PreparaComandaGatewayAdapter.java
|  |     └── BuscaPorComandaPorCodigoPedidoGatewayAdapter.java
|  ├── entity
|  |  ├── Comanda.java
|  |  └── enums
|  |     └── StatusComanda.java
|  ├── external
|  |  ├── api
|  |  |  ├── BuscaFinalizadoStatusComandaApiExternal.java
|  |  |  ├── BuscaPreparacaoStatusComandaApiExternal.java
|  |  |  ├── BuscaRecebidoComandaApiExternal.java
|  |  |  ├── CriarComandaApiExternal.java
|  |  |  ├── FinalizarComandaApiExternal.java
|  |  |  ├── PrepararComandaApiExternal.java
|  |  |  └── exception
|  |  |     ├── ComandaResponseException.java
|  |  |     └── handler
|  |  |        └── ComandaControllerExceptionHandler.java
|  |  ├── configuration
|  |  |  ├── BuscaPorComandaBeanConfigurationExternal.java
|  |  |  ├── BuscaPorStatusComandaBeanConfigurationExternal.java
|  |  |  ├── CriarComandaBeanConfigurationExternal.java
|  |  |  ├── FinalizarComandaBeanConfigurationExternal.java
|  |  |  ├── PostgresComandaConfiguration.java
|  |  |  ├── PostgresComandaDataLoader.java
|  |  |  ├── PrepararComandaBeanConfigurationExternal.java
|  |  |  └── BuscaComandaPorCodigoPedidoBeanConfigurationExternal.java
|  |  ├── integration
|  |  |  ├── ComandaPedidoIntegration.java
|  |  |  └── port
|  |  |     └── PedidoDTO.java
|  |  └── repository
|  |     ├── entity
|  |     |  └── ComandaEntity.java
|  |     └── postgres
|  |        └── SpringComandaRepository.java
|  └── usecase
|     ├── BuscaComandaUseCase.java
|     ├── BuscaFinalizadoStatusComandaUseCase.java
|     ├── BuscaPreparacaoStatusComandaUseCase.java
|     ├── BuscaRecebidoStatusComandaUseCase.java
|     ├── CriarComandaUseCase.java
|     ├── FinalizarComandaUseCase.java
|     ├── PrepararComandaUseCase.java
|     ├── port
|     |  ├── interfaces
|     |  |  ├── IAtualizarComandaUseCase.java
|     |  |  ├── IBuscaPorComandaUseCase.java
|     |  |  ├── IBuscaPorStatusFinalizadoComandaUseCase.java
|     |  |  ├── IBuscaPorStatusPreparacaoComandaUseCase.java
|     |  |  ├── IBuscaPorStatusRecebidoComandaUseCase.java
|     |  |  ├── ICriarComandaUseCase.java
|     |  |  └── IBuscaPorComandaPorCodigoPedidoUseCase.java
|     |  └── repository
|     |     ├── IAtualizarComandaRepositoryUseCase.java
|     |     ├── IBuscarPorComandaPorCodigoPedidoRepositoryUseCase.java
|     |     ├── IBuscarPorComandaRepositoryUseCase.java
|     |     ├── IBuscarPorStatusComandaRepositoryUseCase.java
|     |     └── ICriarComandaRepositoryUseCase.java
|     ├── exception
|     |  ├── EntradaInvalidaException.java
|     |  ├── ComandaNaoEncontradaException.java
|     |  ├── IntegracaoPedidoException.java
|     |  └── ComandaDuplicadaException.java
|     └── BuscaComandaPorCodigoPedidoUseCase.java
├── identificacao
|  ├── adapter
|  |  ├── controller
|  |  |  ├── ClienteRestAdapterController.java
|  |  |  ├── port
|  |  |  |  └── IClienteRestAdapterController.java
|  |  |  └── rest
|  |  |     ├── exception
|  |  |     |  ├── ClienteErrorHandler.java
|  |  |     |  └── MensagemErroDTO.java
|  |  |     ├── request
|  |  |     |  └── ClienteRequestDTO.java
|  |  |     └── response
|  |  |        └── ClienteResponseDTO.java
|  |  └── gateway
|  |     └── ClienteRepositoryAdapterGateway.java
|  ├── entity
|  |  ├── Cliente.java
|  |  └── vo
|  |     ├── Cpf.java
|  |     └── Email.java
|  ├── external
|  |  ├── api
|  |  |  └── ClienteApiController.java
|  |  ├── config
|  |  |  ├── BeansConfiguration.java
|  |  |  ├── PostgresClienteConfiguration.java
|  |  |  └── PostgresClienteDataLoader.java
|  |  └── repository
|  |     ├── entity
|  |     |  └── ClienteEntity.java
|  |     └── postgres
|  |        └── SpringClienteRepository.java
|  └── usecase
|     ├── GestaoClienteUseCase.java
|     ├── exception
|     |  ├── BaseException.java
|     |  ├── EntidadeNaoEncontradaException.java
|     |  └── EntradaInvalidaException.java
|     └── port
|        ├── IClienteRepositoryAdapterGateway.java
|        └── IGestaoClienteUsecase.java
├── pagamento
|  ├── adapter
|  |  ├── controller
|  |  |  ├── AtualizaStatusPagamentoRestAdapterController.java
|  |  |  ├── BuscaPagamentoRestAdapterController.java
|  |  |  ├── EnviaPagamentoAoGatewayRestAdapterController.java
|  |  |  ├── ProcessaNovoPagamentoRestAdapterController.java
|  |  |  └── rest
|  |  |     ├── port
|  |  |     |  ├── IAtualizaPagamentoRestAdapterController.java
|  |  |     |  ├── IBuscaPagamentoRestAdapterController.java
|  |  |     |  ├── IEnviaPagamentoGatewayRestAdapterController.java
|  |  |     |  └── IProcessaPagamentoRestAdapterController.java
|  |  |     ├── request
|  |  |     |  ├── PagamentoAEnviarAoGatewayDTORequest.java
|  |  |     |  ├── PagamentoDTORequest.java
|  |  |     |  ├── PagamentoStatusDTORequest.java
|  |  |     |  └── PedidoAPagarDTORequest.java
|  |  |     └── response
|  |  |        ├── PagamentoDTOResponse.java
|  |  |        ├── PagamentoNovoDTOResponse.java
|  |  |        └── PagamentoStatusDTOResponse.java
|  |  ├── gateway
|  |  |  ├── AtualizaStatusPagamentoRepositoryAdapterGateway.java
|  |  |  ├── BuscaPagamentoRepositoryAdapterGateway.java
|  |  |  ├── ProcessaNovoPagamentoRepositoryAdapterGateway.java
|  |  |  └── PagamentoPedidoIntegrationGateway.java
|  |  └── mapper
|  |     └── PagamentoPedidoMapper.java
|  ├── entity
|  |  ├── Pagamento.java
|  |  ├── enums
|  |  |  └── StatusPagamento.java
|  |  └── integration
|  |     ├── PagamentoPedido.java
|  |     └── PagamentoPedidoResponse.java
|  ├── external
|  |  ├── api
|  |  |  ├── PagamentoBuscaApiController.java
|  |  |  ├── PagamentoBuscaPedidosAPagarApiController.java
|  |  |  ├── PagamentoEnviaParaGatewayPagamentoApiController.java
|  |  |  ├── PagamentoProcessaNovoApiController.java
|  |  |  ├── PagamentoRetornoGatewayPagamentoApiController.java
|  |  |  └── exceptions
|  |  |     ├── ResourceExceptionHandler.java
|  |  |     └── StandardError.java
|  |  ├── configuration
|  |  |  ├── BeanPagamentoConfiguration.java
|  |  |  ├── PostgresPagamentoConfiguration.java
|  |  |  └── PostgresPagamentoDataLoader.java
|  |  ├── integration
|  |  |  ├── IPedidoIntegration.java
|  |  |  ├── port
|  |  |  |  ├── Pedido.java
|  |  |  |  └── PagamentoPedidoResponseDTO.java
|  |  |  ├── IPagamentoPedidoIntegration.java
|  |  |  └── exceptions
|  |  |     ├── InvalidOperationIntegrationException.java
|  |  |     └── PagamentoPedidoIntegrationException.java
|  |  └── repository
|  |     ├── entity
|  |     |  └── PagamentoEntity.java
|  |     └── postgres
|  |        ├── PostgresIPagamentoRepository.java
|  |        └── SpringPagamentoRepository.java
|  └── usecase
|     ├── AtualizaStatusPagamentoUseCase.java
|     ├── BuscaPagamentoUseCase.java
|     ├── EnviaPagamentoAoGatewayPagamentosUseCase.java
|     ├── ProcessaNovoPagamentoUseCase.java
|     ├── exceptions
|     |  ├── ResourceAlreadyInProcessException.java
|     |  ├── ResourceNotFoundException.java
|     |  ├── UnprocessablePaymentException.java
|     |  └── mensagens
|     |     └── MensagemDeErro.java
|     ├── port
|     |  ├── repository
|     |  |  ├── IAtualizaStatusPagamentoRepositoryAdapterGateway.java
|     |  |  ├── IBuscaPagamentoRepositoryAdapterGateway.java
|     |  |  ├── IPagamentoRepositoryAdapterGateway.java
|     |  |  ├── IProcessaNovoPagamentoRepositoryAdapterGateway.java
|     |  |  └── IPagamentoPedidoIntegrationGateway.java
|     |  └── usecase
|     |     ├── IAtualizaStatusPagamentoUsecase.java
|     |     ├── IBuscaPagamentoUseCase.java
|     |     ├── IEnviaPagamentoAoGatewayPagamentosUseCase.java
|     |     ├── IProcessaNovoPagamentoUseCase.java
|     |     └── IPagamentoPedidoIntegrationUseCase.java
|     └── PagamentoPedidoIntegrationUseCase.java
├── pedido
|  ├── adapter
|  |  ├── controller
|  |  |  ├── PedidoComandaIntegrationRestAdapterController.java
|  |  |  ├── PedidoManagementRestAdapterController.java
|  |  |  ├── PedidoPagamentoIntegrationRestAdapterController.java
|  |  |  ├── PedidoQueryRestAdapterController.java
|  |  |  ├── PedidoWorkFlowRestAdapterController.java
|  |  |  ├── port
|  |  |  |  ├── IPedidoComandaIntegrationRestAdapterController.java
|  |  |  |  ├── IPedidoManagementRestAdapterController.java
|  |  |  |  ├── IPedidoPagamentoIntegrationRestAdapterController.java
|  |  |  |  ├── IPedidoQueryRestAdapterController.java
|  |  |  |  └── IPedidoWorkFlowRestAdapterController.java
|  |  |  └── rest
|  |  |     └── response
|  |  |        └── PedidoDTO.java
|  |  ├── gateway
|  |  |  ├── PedidoClienteIntegrationAdapterGateway.java
|  |  |  ├── PedidoComandaIntegrationAdapterGateway.java
|  |  |  ├── PedidoPagamentoIntegrationAdapterGateway.java
|  |  |  ├── PedidoProdutoIntegrationAdapterGateway.java
|  |  |  └── PedidoRepositoryAdapterGateway.java
|  |  └── mapper
|  |     ├── ComandaMapper.java
|  |     ├── ItemPedidoMapper.java
|  |     ├── PagamentoMapper.java
|  |     ├── PedidoDtoMapper.java
|  |     ├── PedidoMapper.java
|  |     └── ProdutoMapper.java
|  ├── entity
|  |  ├── ItemPedido.java
|  |  ├── Pedido.java
|  |  ├── enums
|  |  |  ├── CategoriaProduto.java
|  |  |  ├── OperacaoProduto.java
|  |  |  ├── StatusComanda.java
|  |  |  ├── StatusPagamento.java
|  |  |  └── StatusPedido.java
|  |  └── integration
|  |     ├── ComandaPedido.java
|  |     ├── PagamentoPedido.java
|  |     └── ProdutoPedido.java
|  ├── external
|  |  ├── api
|  |  |  ├── PedidoComandaApiController.java
|  |  |  ├── PedidoManagementApiController.java
|  |  |  ├── PedidoPagamentoApiController.java
|  |  |  ├── PedidoQueryApiController.java
|  |  |  ├── PedidoWorkFlowApiController.java
|  |  |  └── exception
|  |  |     ├── PedidoResponseException.java
|  |  |     └── handler
|  |  |        └── PedidoControllerExceptionHandler.java
|  |  ├── configuration
|  |  |  ├── BeanPedidoConfiguration.java
|  |  |  ├── PostgresPedidoConfiguration.java
|  |  |  └── PostgresPedidoDataLoader.java
|  |  ├── integration
|  |  |  ├── PedidoClienteIntegration.java
|  |  |  ├── PedidoComandaIntegration.java
|  |  |  ├── PedidoPagamentoIntegration.java
|  |  |  ├── PedidoProdutoIntegration.java
|  |  |  └── port
|  |  |     ├── Cliente.java
|  |  |     ├── Comanda.java
|  |  |     ├── CriaComanda.java
|  |  |     ├── Pagamento.java
|  |  |     ├── Produto.java
|  |  |     └── NovoPagamento.java
|  |  └── repository
|  |     ├── entity
|  |     |  ├── ItemPedidoCodigo.java
|  |     |  ├── ItemPedidoEntity.java
|  |     |  └── PedidoEntity.java
|  |     └── postgres
|  |        └── SpringPedidoRepository.java
|  └── usecase
|     ├── AbstractPedidoUseCase.java
|     ├── PedidoComandaIntegrationUseCase.java
|     ├── PedidoManagementUseCase.java
|     ├── PedidoPagamentoIntegrationUseCase.java
|     ├── PedidoQueryUseCase.java
|     ├── PedidoStatusDataComparator.java
|     ├── PedidoWorkFlowUseCase.java
|     ├── enums
|     |  └── MensagemErro.java
|     ├── exception
|     |  ├── InvalidOperacaoProdutoException.java
|     |  ├── InvalidStatusException.java
|     |  ├── ItemNotFoundException.java
|     |  ├── NoItensException.java
|     |  ├── IntegrationPagamentoException.java
|     |  └── IntegrationProdutoException.java
|     └── port
|        ├── adaptergateway
|        |  ├── IPedidoClienteIntegrationAdapterGateway.java
|        |  ├── IPedidoComandaIntegrationAdapterGateway.java
|        |  ├── IPedidoPagamentoIntegrationAdapterGateway.java
|        |  ├── IPedidoProdutoIntegrationAdapterGateway.java
|        |  └── IPedidoRepositoryAdapterGateway.java
|        └── usecase
|           ├── IAbstractPedidoUseCase.java
|           ├── IPedidoComandaIntegrationUseCase.java
|           ├── IPedidoManagementUseCase.java
|           ├── IPedidoPagamentoIntegrationUseCase.java
|           ├── IPedidoQueryUseCase.java
|           └── IPedidoWorkFlowUseCase.java
└── produto
   ├── adapter
   |  ├── controller
   |  |  ├── ProdutoRestAdapterController.java
   |  |  ├── port
   |  |  |  └── IProdutoRestAdapterController.java
   |  |  └── rest
   |  |     ├── request
   |  |     |  └── ProdutoDTORequest.java
   |  |     └── response
   |  |        └── ProdutoDTOResponse.java
   |  └── gateway
   |     └── ProdutoRepositoryAdapterGateway.java
   ├── entity
   |  ├── Produto.java
   |  └── enums
   |     └── CategoriaProduto.java
   ├── external
   |  ├── api
   |  |  ├── ProdutoApiController.java
   |  |  └── exception
   |  |     ├── ProdutoResponseException.java
   |  |     └── handler
   |  |        └── ProdutoControllerExceptionHandler.java
   |  ├── configuration
   |  |  ├── BeanProdutoConfiguration.java
   |  |  ├── PostgresProdutoConfiguration.java
   |  |  └── PostgresProdutoDataLoader.java
   |  └── repository
   |     ├── entity
   |     |  └── ProdutoEntity.java
   |     └── postgres
   |        └── SpringProdutoRepository.java
   └── usecase
      ├── GestaoProdutoUseCase.java
      ├── exception
      |  ├── BaseException.java
      |  ├── EntradaInvalidaException.java
      |  └── ProdutoNaoEncontradoException.java
      └── port
         ├── IGestaoProdutoUseCase.java
         └── IProdutoRepositoryAdapterGateway.java

directory: 120 file: 257
```