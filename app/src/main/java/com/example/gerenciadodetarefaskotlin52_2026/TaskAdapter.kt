package com.example.gerenciadodetarefaskotlin52_2026

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

/**
 * TaskAdapter — Adaptador personalizado com ações de Editar e Dupla Confirmação de Exclusão.
 */
class TaskAdapter(
    context: Context,
    private val tasks: MutableList<Task>
) : ArrayAdapter<Task>(context, R.layout.item_task, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_task, parent, false)

        val task: Task = tasks[position]

        val checkBox: CheckBox = view.findViewById(R.id.checkboxTask)
        val textView: TextView = view.findViewById(R.id.textViewDescription)
        val buttonEdit: ImageView = view.findViewById(R.id.buttonEditTask)
        val buttonDelete: ImageView = view.findViewById(R.id.buttonDeleteTask)

        // Evita bugs de reciclagem nos Listeners do Checkbox
        checkBox.setOnCheckedChangeListener(null)

        textView.text = task.description
        checkBox.isChecked = task.isDone

        atualizarEstiloTexto(textView, task.isDone)

        // Evento do Checkbox
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            task.isDone = isChecked
            atualizarEstiloTexto(textView, isChecked)
        }

        // Ação 1: Editar tarefa com caixa de diálogo minimalista
        buttonEdit.setOnClickListener {
            exibirDialogEditar(task)
        }

        // Ação 2: Excluir tarefa com DUPLA CONFIRMAÇÃO
        buttonDelete.setOnClickListener {
            exibirDialogConfirmacaoExclusao(task)
        }

        // Alternância sutil de fundo
        view.setBackgroundColor(
            if (position % 2 == 0) 0xFFFFFFFF.toInt() else 0xFFF9FAFB.toInt()
        )

        return view
    }

    /**
     * Exibe um modal minimalista para edição do texto da tarefa.
     */
    private fun exibirDialogEditar(task: Task) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Editar tarefa")

        // Cria dinamicamente um EditText minimalista com margens limpas
        val input = EditText(context)
        input.setText(task.description)
        input.setSingleLine(true)
        input.setSelection(task.description.length) // Cursor no final do texto

        // Envolve o EditText em um container para aplicar espaçamento (padding/margem)
        val container = LinearLayout(context)
        container.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(60, 20, 60, 20) // Margens esquerda, topo, direita, baixo
        input.layoutParams = params
        container.addView(input)

        builder.setView(container)

        builder.setPositiveButton("Salvar") { _, _ ->
            val novoTexto = input.text.toString().trim()
            if (novoTexto.isNotEmpty()) {
                task.description = novoTexto
                notifyDataSetChanged() // Recarrega a View com a nova descrição
                Toast.makeText(context, "Tarefa atualizada.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "A descrição não pode ser vazia.", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    /**
     * Exibe um modal de confirmação antes de excluir a tarefa.
     */
    private fun exibirDialogConfirmacaoExclusao(task: Task) {
        AlertDialog.Builder(context)
            .setTitle("Excluir tarefa")
            .setMessage("Deseja realmente excluir esta tarefa?")
            .setPositiveButton("Excluir") { _, _ ->
                remove(task) // Remove a tarefa da lista e atualiza a interface
                Toast.makeText(context, "Tarefa excluída.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun atualizarEstiloTexto(textView: TextView, isDone: Boolean) {
        if (isDone) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView.setTextColor(0xFF9CA3AF.toInt())
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            textView.setTextColor(0xFF111827.toInt())
        }
    }
}
