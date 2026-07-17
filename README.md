# Gerenciador de Tarefas em Kotlin

Aplicativo simples para gerenciamento de tarefas cotidianas, desenvolvido em Kotlin para a plataforma Android. O projeto adota os princípios da arquitetura Model-View-ViewModel (MVVM) adaptada para uma estrutura simplificada, com componentes tradicionais de interface (Views XML) e suporte a Material Design minimalista.

## Funcionalidades

* **Adição de tarefas**: Criação de novos itens por meio de campo de texto e botão.
* **Status de conclusão**: Checkbox para marcar ou desmarcar tarefas, aplicando estilo visual correspondente (texto tachado e opacidade reduzida).
* **Edição de tarefas**: Diálogo com campo de texto para atualização rápida do nome da tarefa.
* **Exclusão de tarefas**: Botão de lixeira na linha do item com janela de dupla confirmação.

## Estrutura do Projeto e Funções no MVVM

* **Task.kt (Model)**: Modelo de dados (data class) que representa a entidade de uma tarefa, contendo identificador, descrição e status de conclusão.
* **activity_main.xml (View)**: Arquivo de layout que define a estrutura visual da tela utilizando LinearLayout vertical, contendo campos de entrada de dados, botões de ação e o ListView.
* **MainActivity.kt (View)**: Controlador de interface da View que orquestra o ciclo de vida da Activity, inicializando o adaptador, vinculando-o ao ListView e repassando os eventos de interação do usuário.
* **TaskAdapter.kt (ViewModel / Binder)**: Adaptador personalizado que herda de ArrayAdapter. Nesta arquitetura, ele desempenha a função de ponte entre o Model (`Task`) e a View (`item_task.xml`), aplicando as regras de transformação de dados para a apresentação visual (como o efeito tachado e o gerenciamento de estados do Checkbox).

## Bônus: Analogia com Backend PHP / Laravel

Para desenvolvedores com experiência em desenvolvimento web utilizando PHP e Laravel, a tabela abaixo correlaciona os componentes deste projeto com conceitos equivalentes:

| Componente Android | Função no MVVM | Equivalente Laravel/PHP | Função Arquitetural |
|---|---|---|---|
| activity_main.xml | View (Layout) | index.blade.php | View (Apresentação estática) |
| MainActivity.kt | View (UI Controller) | app/Http/Controllers/TaskController.php | Controller (Gerenciador de requisições e eventos) |
| TaskAdapter.kt | ViewModel (Binder) | @foreach + _row.blade.php | Template Engine / View Helper (Formatação e renderização) |
| Task.kt | Model | app/Models/Task.php | Model (Entidade de dados) |

### Diferença de Ciclo de Vida (Stateful vs Stateless)

No Laravel, o ciclo de vida de uma requisição HTTP é stateless: a cada requisição do usuário (como um clique de envio de dados), o Controller é criado, executa a ação, envia a resposta HTML ao navegador e é destruído da memória.

No Android, o ciclo de vida é stateful: a `MainActivity` permanece ativa na memória do dispositivo enquanto a tela do aplicativo estiver aberta. Devido a esse comportamento persistente, o estado da lista de tarefas (`taskList`) é mantido em memória RAM durante a sessão de uso, sem a necessidade imediata de conexões com bancos de dados locais.
