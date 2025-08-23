import models.ItemMenu
import models.ItemPedido
import models.Pedido
import models.StatusPedido

fun main() {

    val listaItensMenu = mutableListOf<ItemMenu>()
    val listaPedidos = mutableListOf<Pedido>()

    var proximoCodigoItem = 1
    var proximoCodigoPedido = 1

    var opcaoMenuPrincipal = -1
    var opcaoStatusPedido: Int

    println("======================================")
    println("===    SISTEMA LONDON RESTAURANT   ===")
    println("======================================\n")


    while (opcaoMenuPrincipal != 0) {

        println("┌─────────────────────────────────────┐")
        println("│            MENU PRINCIPAL           │")
        println("├─────────────────────────────────────┤")
        println("│ 1. Cadastrar Item                   │")
        println("│ 2. Atualizar Item                   │")
        println("│ 3. Criar Novo Pedido                │")
        println("│ 4. Atualizar Pedido                 │")
        println("│ 5. Consultar Pedidos                │")
        println("│ 0. Sair do Sistema                  │")
        println("└─────────────────────────────────────┘")
        print("\nEscolha uma opção: ")
        opcaoMenuPrincipal = readln().toInt()

        when(opcaoMenuPrincipal) {

            1 -> {
                println("\n═══════════════════════════════════════")
                println("         CADASTRAR ITEM NO MENU       ")
                  println("═══════════════════════════════════════")

                print("\nNome do item: ")
                val nomeDoItem = readln()

                print("Descrição do item: ")
                val descricaoDoItem = readln()

                print("Preço do item (R$): ")
                val precoDoItem = readln().toDouble()

                print("Quantidade em estoque: ")
                val quantidadeEmEstoque = readln().toInt()

                val novoItemMenu = ItemMenu(
                    codigo = proximoCodigoItem,
                    nome = nomeDoItem,
                    descricao = descricaoDoItem,
                    preco = precoDoItem,
                    estoque = quantidadeEmEstoque
                )

                listaItensMenu.add(novoItemMenu)
                proximoCodigoItem++

                println("\nItem cadastrado com sucesso!")
                println("─────────────────────────────────────────\n")
            }

            2 -> {
                if (listaItensMenu.isEmpty()) {
                    println("\n Nenhum item cadastrado\n")
                    continue
                }

                println("\n═══════════════════════════════════════")
                println("         ATUALIZAR ITEM DO MENU        ")
                  println("═══════════════════════════════════════")

                    println("\nItens disponíveis no menu:")
                    println("┌─────────────────────────────────────────┐")
                for (itemAtual in listaItensMenu) {
                    println("│ Código: ${itemAtual.codigo}")
                    println("│ Nome: ${itemAtual.nome}")
                    println("│ Descrição: ${itemAtual.descricao}")
                    println("│ Preço: R$ ${String.format("%.2f", itemAtual.preco)}")
                    println("│ Estoque: ${itemAtual.estoque} unidades")
                    println("├─────────────────────────────────────────┤")
                }
                    println("└─────────────────────────────────────────┘")

                println("\nDigite o código do item que deseja atualizar: ")
                val codigoItemEscolhido = readln().toInt()

                var itemEncontrado = false
                for (indiceItem in listaItensMenu.indices) {
                    val itemParaAtualizar = listaItensMenu[indiceItem]
                    if (itemParaAtualizar.codigo == codigoItemEscolhido) {
                        itemEncontrado = true
                        println("\nItem encontrado: ${itemParaAtualizar.nome}")

                        println("\nO que deseja atualizar?")
                        println("┌─────────────────────────┐")
                        println("│ 1. Nome                 │")
                        println("│ 2. Descrição            │")
                        println("│ 3. Preço                │")
                        println("│ 4. Estoque              │")
                        println("└─────────────────────────┘")
                        print("\nEscolha: ")

                        val campoParaAtualizar = readln().toInt()

                        val itemAtualizado = when (campoParaAtualizar) {
                            1 -> {
                                print("Novo nome: ")
                                val novoNome = readln()
                                itemParaAtualizar.copy(nome = novoNome)
                            }
                            2 -> {
                                print("Nova descrição: ")
                                val novaDescricao = readln()
                                itemParaAtualizar.copy(descricao = novaDescricao)
                            }
                            3 -> {
                                print("Novo preço (R$): ")
                                val novoPreco = readln().toDouble()
                                itemParaAtualizar.copy(preco = novoPreco)
                            }
                            4 -> {
                                print("Nova quantidade em estoque: ")
                                val novoEstoque = readln().toInt()
                                itemParaAtualizar.copy(estoque = novoEstoque)
                            }
                            else -> {
                                println("\nOpção inválida")
                                continue
                            }
                        }

                        listaItensMenu[indiceItem] = itemAtualizado
                        println("\n Item atualizado com sucesso!")
                        println("─────────────────────────────────────────\n")
                        break
                    }
                }

                if (!itemEncontrado) {
                    println("\n Item com código $codigoItemEscolhido não encontrado!")
                    println("─────────────────────────────────────────\n")
                }
            }

            3 -> {
                println("\n═══════════════════════════════════════")
                println("            CRIAR NOVO PEDIDO           ")
                  println("═══════════════════════════════════════")

                val novoPedido = Pedido(
                    codigo = proximoCodigoPedido,
                    itens = mutableListOf(),
                    totalPedido = 0.00,
                    cupom = false,
                    status = StatusPedido.ACEITO
                )
                proximoCodigoPedido++
                var continuarAdicionandoItens = true

                while (continuarAdicionandoItens) {
                            println("\nItens disponíveis no menu:")
                            println("┌─────────────────────────────────────────┐")

                    var temItensDisponiveis = false
                    for (itemMenu in listaItensMenu) {
                        if (itemMenu.estoque >= 1) {
                            temItensDisponiveis = true
                            println("│ Código: ${itemMenu.codigo}")
                            println("│ Nome: ${itemMenu.nome}")
                            println("│ Descrição: ${itemMenu.descricao}")
                            println("│ Preço: R$ ${String.format("%.2f", itemMenu.preco)}")
                            println("│ Estoque disponível: ${itemMenu.estoque} unidades")
                            println("├─────────────────────────────────────────┤")
                        }
                    }
                            println("└─────────────────────────────────────────┘")

                    if (!temItensDisponiveis) {
                        println("\nNenhum item disponível em estoque!")
                        break
                    }

                    print("\nDigite o código do item que deseja adicionar: ")
                    val codigoItemEscolhido = readln().toInt()

                    var itemEncontradoParaPedido = false
                    for (itemMenu in listaItensMenu) {
                        if (itemMenu.codigo == codigoItemEscolhido && itemMenu.estoque >= 1) {
                            itemEncontradoParaPedido = true
                            print("Digite a quantidade desejada: ")
                            val quantidadeDesejada = readln().toInt()

                            if (quantidadeDesejada > itemMenu.estoque) {
                                println("\nQuantidade solicitada ($quantidadeDesejada) maior que estoque disponível (${itemMenu.estoque})")
                            } else {
                                val novoItemPedido = ItemPedido(
                                    item = itemMenu,
                                    qtd = quantidadeDesejada
                                )
                                novoPedido.itens.add(novoItemPedido)
                                novoPedido.totalPedido += (novoItemPedido.qtd * novoItemPedido.item.preco)
                                itemMenu.estoque -= quantidadeDesejada

                                println("\nItem adicionado ao pedido!")
                            }

                            if (novoPedido.itens.isNotEmpty()) {
                                    println("\nResumo do pedido atual:")
                                    println("┌─────────────────────────────────────────┐")
                                for (itemPedido in novoPedido.itens) {
                                    println("│ ${itemPedido.item.nome}")
                                    println("│ Quantidade: ${itemPedido.qtd}")
                                    println("│ Subtotal: R$ ${String.format("%.2f", itemPedido.qtd * itemPedido.item.preco)}")
                                    println("├─────────────────────────────────────────┤")
                                }
                                    println("│ TOTAL ATUAL: R$ ${String.format("%.2f", novoPedido.totalPedido)}")
                                    println("└─────────────────────────────────────────┘")
                            }
                            break
                        }
                    }

                    if (!itemEncontradoParaPedido) {
                        println("\nItem com código $codigoItemEscolhido não encontrado ou sem estoque!")
                    }

                    print("\nDeseja adicionar mais itens ao pedido? (s/n): ")
                    val respostaContinuar = readln().lowercase()
                    if (respostaContinuar != "s" && respostaContinuar != "sim") {
                        continuarAdicionandoItens = false
                    }
                }

                if (novoPedido.itens.isNotEmpty()) {
                    println("\n═══════════════════════════════════════")
                    println("         FINALIZAÇÃO DO PEDIDO          ")
                      println("═══════════════════════════════════════")

                        println("\nResumo final do pedido:")
                        println("┌─────────────────────────────────────────┐")
                    for (itemPedido in novoPedido.itens) {
                        println("│ ${itemPedido.item.nome}")
                        println("│ Quantidade: ${itemPedido.qtd}")
                        println("│ Subtotal: R$ ${String.format("%.2f", itemPedido.qtd * itemPedido.item.preco)}")
                        println("├─────────────────────────────────────────┤")
                    }
                        println("│ SUBTOTAL: R$ ${String.format("%.2f", novoPedido.totalPedido)}")
                        println("└─────────────────────────────────────────┘")

                    print("\nDeseja aplicar cupom de desconto de 15%? (s/n): ")
                    val respostaCupom = readln().lowercase()
                    if (respostaCupom == "s" || respostaCupom == "sim") {
                        novoPedido.cupom = true
                        novoPedido.totalPedido -= (novoPedido.totalPedido * 0.15)
                        println("Cupom de 15% aplicado!")
                    }

                    println("\n═══════════════════════════════════════")
                    println("          PEDIDO CONFIRMADO             ")
                      println("═══════════════════════════════════════")

                    println("Código do Pedido: ${novoPedido.codigo}")
                    println("Status: ${novoPedido.status}")
                    if (novoPedido.cupom) {
                        println("Desconto aplicado: 15%")
                    }
                    println("TOTAL FINAL: R$ ${String.format("%.2f", novoPedido.totalPedido)}")
                    println("─────────────────────────────────────────\n")

                    listaPedidos.add(novoPedido)
                } else {
                    println("\nNenhum item foi adicionado ao pedido!")
                    println("─────────────────────────────────────────\n")
                }
            }

            4 -> {
                if (listaPedidos.isEmpty()) {
                    println("\nNão existem pedidos cadastrados no sistema!\n")
                    continue
                }

                println("\n═══════════════════════════════════════")
                println("       ATUALIZAR STATUS DO PEDIDO      ")
                  println("═══════════════════════════════════════")

                    println("\nPedidos disponíveis:")
                    println("┌─────────────────────────────────────────┐")
                for (pedidoAtual in listaPedidos) {
                    println("│ Código: ${pedidoAtual.codigo}")
                    println("│ Status Atual: ${pedidoAtual.status}")
                    println("│ Total: R$ ${String.format("%.2f", pedidoAtual.totalPedido)}")
                    println("├─────────────────────────────────────────┤")
                }
                    println("└─────────────────────────────────────────┘")

                print("\nInforme o código do pedido que deseja alterar: ")
                val codigoPedidoEscolhido = readln().toInt()

                var indicePedidoEncontrado = -1
                for (indicePedido in listaPedidos.indices) {
                    if (listaPedidos[indicePedido].codigo == codigoPedidoEscolhido) {
                        indicePedidoEncontrado = indicePedido
                        break
                    }
                }

                if (indicePedidoEncontrado == -1) {
                    println("\nPedido com código $codigoPedidoEscolhido não encontrado!")
                    println("─────────────────────────────────────────\n")
                    continue
                }

                println("\nEscolha o novo status para o pedido:")
                println("┌─────────────────────────────────────────┐")
                println("│ 1. ACEITO                               │")
                println("│ 2. FAZENDO                              │")
                println("│ 3. FEITO                                │")
                println("│ 4. ESPERANDO_ENTREGADOR                 │")
                println("│ 5. SAIU_PARA_ENTREGA                    │")
                println("│ 6. ENTREGUE                             │")
                println("└─────────────────────────────────────────┘")

                print("\nInforme o número do status: ")
                opcaoStatusPedido = readln().toInt()

                when (opcaoStatusPedido) {
                    1 -> {
                        listaPedidos[indicePedidoEncontrado].status = StatusPedido.ACEITO
                        println("\nStatus alterado para: ACEITO")
                    }
                    2 -> {
                        listaPedidos[indicePedidoEncontrado].status = StatusPedido.FAZENDO
                        println("\nStatus alterado para: FAZENDO")
                    }
                    3 -> {
                        listaPedidos[indicePedidoEncontrado].status = StatusPedido.FEITO
                        println("\nStatus alterado para: FEITO")
                    }
                    4 -> {
                        listaPedidos[indicePedidoEncontrado].status = StatusPedido.ESPERANDO_ENTREGADOR
                        println("\nStatus alterado para: ESPERANDO_ENTREGADOR")
                    }
                    5 -> {
                        listaPedidos[indicePedidoEncontrado].status = StatusPedido.SAIU_PARA_ENTREGA
                        println("\nStatus alterado para: SAIU_PARA_ENTREGA")
                    }
                    6 -> {
                        listaPedidos[indicePedidoEncontrado].status = StatusPedido.ENTREGUE
                        println("\nStatus alterado para: ENTREGUE")
                    }
                    else -> {
                        println("\nOpção de status inválida!")
                        continue
                    }
                }
                println("─────────────────────────────────────────\n")
            }

            5 -> {
                if (listaPedidos.isEmpty()) {
                    println("\nNão existem pedidos cadastrados no sistema!\n")
                    continue
                }

                println("\n═══════════════════════════════════════")
                println("       CONSULTAR PEDIDOS POR STATUS    ")
                  println("═══════════════════════════════════════")

                println("\nFiltrar pedidos por status:")
                println("┌─────────────────────────────────────────┐")
                println("│ 1. ACEITO                               │")
                println("│ 2. FAZENDO                              │")
                println("│ 3. FEITO                                │")
                println("│ 4. ESPERANDO_ENTREGADOR                 │")
                println("│ 5. SAIU_PARA_ENTREGA                    │")
                println("│ 6. ENTREGUE                             │")
                println("└─────────────────────────────────────────┘")

                print("\nInforme o número do status: ")
                opcaoStatusPedido = readln().toInt()

                val statusEscolhido = when (opcaoStatusPedido) {
                    1 -> StatusPedido.ACEITO
                    2 -> StatusPedido.FAZENDO
                    3 -> StatusPedido.FEITO
                    4 -> StatusPedido.ESPERANDO_ENTREGADOR
                    5 -> StatusPedido.SAIU_PARA_ENTREGA
                    6 -> StatusPedido.ENTREGUE
                    else -> {
                        println("\nOpção de status inválida!")
                        continue
                    }
                }

                            println("\nPedidos com status: $statusEscolhido")
                            println("┌─────────────────────────────────────────┐")

                var encontrouPedidosComStatus = false
                for (pedidoAtual in listaPedidos) {
                    if (pedidoAtual.status == statusEscolhido) {
                        encontrouPedidosComStatus = true

                            println("│ Código do Pedido: ${pedidoAtual.codigo}")
                            println("│ Status: ${pedidoAtual.status}")
                            println("│ Total: R$ ${String.format("%.2f", pedidoAtual.totalPedido)}")
                        if (pedidoAtual.cupom) {
                            println("│ Desconto aplicado: 15%")
                        }
                            println("│ Itens:")
                        for (itemPedido in pedidoAtual.itens) {
                            println("│   - ${itemPedido.item.nome} (Qtd: ${itemPedido.qtd})")
                        }
                            println("├─────────────────────────────────────────┤")
                    }
                }

                if (!encontrouPedidosComStatus) {
                            println("│Nenhum pedido encontrado com esse status │")
                }

                            println("└─────────────────────────────────────────┘\n")
            }

            0 -> {
                println("\n═══════════════════════════════════════")
                println("         ENCERRANDO O SISTEMA           ")
                println("═══════════════════════════════════════")
                println("  Obrigado por usar o London Restaurant!")
                println("───────────────────────────────────────\n")
            }

            else -> {
                println("\n  Opção inválida! Tente novamente.")
                println("─────────────────────────────────────────\n")
            }
        }
    }
}