# Task-Manager-Kotlin-Unicesumar

## 🧠 A Analogia Central (resumida)

| Android | Laravel/PHP | Papel |
|---|---|---|
| `activity_main.xml` | `index.blade.php` | View — o que o usuário vê |
| `Task.kt` | `app/Models/Task.php` | Model — o que é uma tarefa |
| `TaskAdapter.kt` | `@foreach` + `_row.blade.php` | Template Engine — como cada item é renderizado |
| `MainActivity.kt` | `TaskController.php` | Controller — quem orquestra tudo |

O detalhe mais importante para quem vem do PHP: no Laravel, cada requisição HTTP cria e destrói o Controller (stateless). No Android, a `MainActivity` fica viva em memória enquanto a tela está aberta — por isso o `taskList` mantém os dados sem banco de dados. Isso é o ciclo de vida de Activities, o conceito central do Android.

│ Bônus implementado: O app inclui exclusão direta clicando no ícone da lixeira com dupla confirmação e a opção de edição de tarefas com diálogo interativo.
