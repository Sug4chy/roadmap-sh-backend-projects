package model

import enums.TaskStatus

import java.time.Instant

case class Task(id: Int, description: String, status: TaskStatus, createdAt: Instant, updatedAt: Instant):
  override def toString: String =
    s"$id: $description. $status"