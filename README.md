# Correios

Sistema para acessar dados dos Correios através da nova API REST.

![GitHub CI](https://github.com/t3wv/correios/actions/workflows/release-tag.yml/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=t3wv_correios&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=t3wv_correios)
[![Latest Release](https://img.shields.io/github/v/release/t3wv/correios)](https://github.com/t3wv/correios/releases/latest)

## Uso

1. Iniciar a conexão com a api usando suas credenciais e indicando se deve realizar a operação em produção

```java
final var api = new T3WCorreios("usuario", "token_api", "cartao_postagem", false / true);
```

<details>
<summary>Credenciais*</summary>
<i><b>usuario</b></i> = login nas ferramentas dos correios
<br>
<i><b>token_api</b></i> = token gerado para seu usuário em <a href="https://cwshom.correios.com.br/acesso-componentes">acesso-componentes</a>
<br>
<i><b>cartao_postagem</b></i> = cartão de postagem para acessa à api's restritas com os devidos acessos
<br>
<i><b>false/true</b></i> = Indicador se deve conectar à api em modo de produção
</details>

2. Exemplo registro de prepostagem

```java
final var remetente = new T3WCorreiosPessoa("NOME_REMETENTE", new T3WCorreiosEndereco("CEP", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "SIGLA_ESTADO")).setCpfCnpj("DOCUMENTO_DO_REMETENTE");
final var destinatario = new T3WCorreiosPessoa("NOME_DESTINATARIO", new T3WCorreiosEndereco("CEP", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "SIGLA_ESTADO"));
final var prepostagem = new T3WCorreiosPrepostagem(remetente, destinatario, "CODIGO_DO_SERVICO", "PESO_EM_GRAMAS", "CODIGO_FORMATO_OBJETO", "CIENTE_OBJETO_NAO_PROIBIDO");
final T3WCorreiosPrepostagem prepostagemEfetivada = api.criarPrepostagem(prepostagem);
```

<details>
<summary>Resultado:</summary>
<code>
{
  id= "XPTO_ABC123",
  idCorreios= "USUARIO_INFORMADO_NO_INSTANCIAMENTO",
  remetente= T3WCorreiosPessoa("NOME_REMETENTE"...),
  destinatario= T3WCorreiosPessoa("NOME_DESTINATARIO"...),
  codigoServico='CODIGO_DO_SERVICO',
  precoServico='null',
  precoPrePostagem='null',
  numeroNotaFiscal='null',
  numeroCartaoPostagem='',
  chaveNFe='null',
  listaServicoAdicional=null,
  itensDeclaracaoConteudo=null,
  pesoInformado='PESO_EM_GRAMAS',
  codigoFormatoObjetoInformado='CODIGO_FORMATO_OBJETO',
  alturaInformada='null',
  larguraInformada='null',
  comprimentoInformado='null',
  diametroInformado='null',
  ncmObjeto='null',
  rfidObjeto='null',
  cienteObjetoNaoProibido='1',
  idAtendimento='null',
  solicitarColeta='null',
  codigoObjeto='CODIGO_GERADO_AUTOMATICAMENTE',
  dataPrevistaPostagem='null',
  observacao='null',
  modalidadePagamento='1',
  logisticaReversa='N',
  dataValidadeLogReversa='null'
}
</code>
</details>

3. Exemplo cancelamento de prepostagem

```java
final var response_cancelamento = api.cancelarPrePostagem("USUARIO", "ID_PREPOSTAGEM"));
```

<details>
<summary>Resultado:</summary>
<code>
{resultadoCancelamento='Cancelamento realizado com sucesso!', mensagem='null', idRecibo='null'}
</code>
</details>

## Testes

A classe de testes `T3WCorreiosTest.java` contém implementações com valores genéricos do restante dos métodos implementados, podendo ser usada para testes/verificação de resultados.
Para sua utilização, recomenda-se a criação/configuração das seguintes variáveis de ambiente para facilitar o uso:

```
CORREIOS_USER_ID
CORREIOS_API_TOKEN
CORREIOS_CARTAO_POSTAGEM
CORREIOS_CNPJ
CORREIOS_CONTRATO
```

Sendo estes os valores que serão utilizados no instanciamento da classe de conexão com a api, citados previamente no passo 1 dos exemplos de uso.
