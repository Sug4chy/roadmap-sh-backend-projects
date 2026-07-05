package repository

import model.Task

trait TaskRepository:
  def findAll(): Array[Task]
  def findByID(id: Int): Option[Task]
  def add(task: Task): Unit
  def update(task: Task): Unit
  def delete(task: Task): Unit