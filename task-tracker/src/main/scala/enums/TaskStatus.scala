package enums

import io.circe.{Decoder, Encoder, Json}

import scala.util.Try

enum TaskStatus(val s: String) {
  case ToDo extends TaskStatus("to-do")
  case InProgress extends TaskStatus("in-progress")
  case Done extends TaskStatus("done")
}

object TaskStatus {
  def fromString(s: String): TaskStatus =
    TaskStatus.values.find(_.s == s).getOrElse(throw new IllegalArgumentException)

  implicit val encodeTaskStatus: Encoder[TaskStatus] = Encoder.instance(t => Json.fromString(t.s))

  implicit val decodeTaskStatus: Decoder[TaskStatus] = Decoder.decodeString.emapTry(s => Try(fromString(s)))
}