package models

import org.joda.time.DateTime
case class Query(text: String, created_at: DateTime, times: Int)
