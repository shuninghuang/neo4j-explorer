package main.models

import org.joda.time.DateTime
import org.json4s.jackson.Json

case class Log(
              source: String,
              created_at: String,
              id_log: String,
              id_parent_log: Option[String],
              username: Option[String],
              diagnosed_as: String,
              query: String,
              feedback: Option[Boolean],
              session_id: Option[String],
              ip: Option[String],
              predefined_options: Option[String],
              answer: Option[String]
          ) {
  def to_nodes(): Unit = {
    def parseQA: Unit = {
      feedback match {
        case Some(fb) => id_parent_log match {
          case Some(parent_id) => {
            val last_node = s"get last node by $parent_id%s"
            last_node match {
              case "answer" =>
                val q = "find query by answer's parent id"
                s"create ANSWERS relationship between $q%s and $last_node%s"
              case "question" => s"update relationship "
            }
          }
          case None => println("unknown feedback")
        }
        case None => id_parent_log match {
          case Some(parent_id) => println(parent_id)
          case None =>
        }
      }
    }
    def parseScenario: Unit = {

    }
    def parseSmalltalk: Unit = {

    }
    diagnosed_as match {
      case "qa" => parseQA
      case "scenario" => parseScenario
      case "smalltalk" => parseSmalltalk
    }

  }

}

