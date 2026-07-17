package com.example.gerenciadodetarefaskotlin52_2026

/**
 * Task.kt — Modelo de dados (Model)
 * 
 * Alteramos o 'description' para 'var' para permitir a edição da tarefa.
 */
data class Task(
    val id: Int,
    var description: String,     // var permite edição (mutável)
    var isDone: Boolean = false  // var permite marcar/desmarcar (mutável)
) {
    override fun toString(): String = description
}
