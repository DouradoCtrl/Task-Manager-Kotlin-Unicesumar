# Gerenciador de Tarefas em Kotlin

Aplicativo simples para gerenciamento de tarefas cotidianas, desenvolvido em Kotlin para a plataforma Android. O projeto utiliza a arquitetura clássica Model-View-Controller (MVC) com componentes tradicionais de interface (Views XML) e suporte a Material Design minimalista.

## Funcionalidades

* **Adição de tarefas**: Criação de novos itens por meio de campo de texto e botão.
* **Status de conclusão**: Checkbox para marcar ou desmarcar tarefas, aplicando estilo visual correspondente (texto tachado e opacidade reduzida).
* **Edição de tarefas**: Diálogo com campo de texto para atualização rápida do nome da tarefa.
* **Exclusão de tarefas**: Botão de lixeira na linha do item com janela de dupla confirmação.

## Estrutura do Projeto

* **activity_main.xml**: Arquivo de layout que define a estrutura visual da tela utilizando LinearLayout vertical, contendo campos de entrada de dados, botões de ação e o ListView.
* **Task.kt**: Modelo de dados (data class) que representa a entidade de uma tarefa, contendo identificador, descrição e status de conclusão.
* **TaskAdapter.kt**: Adaptador personalizado que herda de ArrayAdapter, responsável por inflar o layout de cada item da lista (item_task.xml) e gerenciar as ações diretas de edição e exclusão.
* **MainActivity.kt**: Controlador principal da tela que orquestra o ciclo de vida da Activity, inicializando o adaptador, vinculando-o ao ListView e tratando as entradas de dados do usuário.

## Bônus: Analogia com Backend PHP / Laravel

Para desenvolvedores com experiência em desenvolvimento web utilizando PHP e Laravel, a tabela abaixo correlaciona os componentes deste projeto Android com conceitos equivalentes:

| Componente Android | Equivalente Laravel/PHP | Função Arquitetural |
|---|---|---|
| activity_main.xml | index.blade.php | View (Camada de apresentação estática) |
| Task.kt | app/Models/Task.php | Model (Representação da entidade e dos dados) |
| TaskAdapter.kt | @foreach + _row.blade.php | Template Engine (Renderização de listas sob demanda) |
| MainActivity.kt | TaskController.php | Controller (Orquestrador de ações e fluxo de dados) |

### Diferença de Ciclo de Vida (Stateful vs Stateless)

No Laravel, o ciclo de vida de uma requisição HTTP é stateless: a cada requisição do usuário (como um clique de envio de dados), o Controller é criado, executa a ação, envia a resposta HTML ao navegador e é destruído da memória.

No Android, a `MainActivity` é stateful: ela permanece viva e ativa na memória do dispositivo enquanto a tela do aplicativo estiver aberta pelo usuário. Devido a esse comportamento de ciclo de vida persistente, o estado da lista de tarefas (`taskList`) é mantido em memória RAM diretamente na classe controladora durante a sessão de uso, sem a necessidade imediata de conexões com bancos de dados locais.
