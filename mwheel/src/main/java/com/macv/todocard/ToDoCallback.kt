package com.macv.todocard

interface ToDoCallback {
    fun onToDoClick(mPos : Int, toDoCardItem: ToDoCardItem)
}