# 🍽️ London Restaurant - Sistema de Pedidos em Kotlin

Este projeto é um sistema de pedidos de comida para o **London Restaurant**, desenvolvido em **Kotlin**.  
O sistema opera exclusivamente via **console**, sem interface gráfica e **sem persistência em banco de dados** (todos os dados são mantidos em memória).  

O objetivo é permitir o **gerenciamento de itens e pedidos**, de forma interativa e linear, sem o uso de frameworks externos.

## Funcionalidades

O sistema oferece um **menu principal interativo**, com as seguintes opções:

1. **Cadastrar Item**  
   - Adicionar novos produtos ao menu do restaurante.  
   - Cada item possui: `Código`, `Nome`, `Descrição`, `Preço` e `Quantidade em estoque`.

2. **Atualizar Item**  
   - Modificar informações de itens já cadastrados.  
   - Pode alterar: Nome, Descrição, Preço ou Estoque.

3. **Criar Pedido**  
   - Montar um novo pedido a partir dos itens disponíveis.  
   - Permite adicionar múltiplos itens com quantidade.  
   - Opção de aplicar **cupom de desconto (15%)** antes da finalização.  
   - O pedido é automaticamente considerado como pago.  
   - Status inicial do pedido: **ACEITO**.

4. **Atualizar Pedido**  
   - Alterar o **status** de um pedido existente.  
   - Fluxo de status:  
     - `ACEITO → FAZENDO → FEITO → ESPERANDO_ENTREGADOR → SAIU_PARA_ENTREGA → ENTREGUE`

5. **Consultar Pedidos**  
   - Visualizar pedidos cadastrados.  
   - Opção de **filtrar por status**.

6. **Sair do Sistema**


## Regras de Negócio

* Cada item do menu possui um código único gerado automaticamente.

* Um pedido deve conter pelo menos 1 item.

* Cupom de desconto: 15% aplicado no total do pedido (opcional).

* O pagamento é implícito no momento da criação do pedido.

* Pedidos podem ser consultados e filtrados pelo status.


## Como Executar


1. Clone este repositório:

   ```
    https://github.com/seu-usuario/tia-lu-dev-mobile-londres.git
   ```

2. Abra o projeto no IntelliJ IDEA ou qualquer IDE compatível com Kotlin.
