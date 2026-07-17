package com.example.gerenciadodetarefaskotlin52_2026

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity — Controlador principal da tela
 */
class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var listViewTasks: ListView

    private val taskList: MutableList<Task> = mutableListOf()
    private lateinit var taskAdapter: TaskAdapter
    private var nextId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        buttonAddTask = findViewById(R.id.buttonAddTask)
        listViewTasks = findViewById(R.id.listViewTasks)

        taskAdapter = TaskAdapter(this, taskList)
        listViewTasks.adapter = taskAdapter

        // Adiciona nova tarefa
        buttonAddTask.setOnClickListener {
            adicionarTarefa()
        }

        // Clique no item da lista também altera o estado do checkbox
        listViewTasks.setOnItemClickListener { _, _, position, _ ->
            val tarefaSelecionada = taskList[position]
            tarefaSelecionada.isDone = !tarefaSelecionada.isDone
            taskAdapter.notifyDataSetChanged()
        }
    }

    private fun adicionarTarefa() {
        val descricao = editTextTask.text.toString().trim()

        if (descricao.isEmpty()) {
            Toast.makeText(this, "Digite a descrição da tarefa.", Toast.LENGTH_SHORT).show()
            return
        }

        val novaTarefa = Task(id = nextId++, description = descricao)
        taskList.add(novaTarefa)
        taskAdapter.notifyDataSetChanged()
        
        editTextTask.text.clear()
        Toast.makeText(this, "Tarefa adicionada.", Toast.LENGTH_SHORT).show()
    }
}