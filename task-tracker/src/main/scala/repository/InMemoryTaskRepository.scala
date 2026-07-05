package repository

import enums.TaskStatus
import model.Task

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class InMemoryTaskRepository extends TaskRepository:
  private val tasks: mutable.ListBuffer[Task] = ListBuffer[Task]()

  override def findAll(statusFilter: Option[TaskStatus] = None): Array[Task] =
    tasks
      .filter(t => if statusFilter.isDefined then t.status == statusFilter.get else true)
      .toArray

  override def findByID(id: Int): Option[Task] =
    tasks.find(_.id == id)

  override def add(task: Task): Unit =
    tasks += task

  override def update(task: Task): Unit =
    val idx = tasks.indexWhere(_.id == task.id)
    if idx == -1 then throw new IndexOutOfBoundsException
    else tasks(idx) = task

  override def delete(task: Task): Unit =
    val idx = tasks.indexWhere(_.id == task.id)
    if idx == -1 then throw new IndexOutOfBoundsException
    else tasks.remove(idx)