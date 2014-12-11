package com.github.nscala_time.time

import com.github.nscala_time.time.Imports._

trait DateRange[D] extends Serializable {
  val start: D
  val end: D
  val dateRange: Vector[D]

  protected def copy(start: D, end: D, step: Period): DateRange[D]

  def by(step: Period): DateRange[D] = copy(start, end, step)

  def length = dateRange.length

  def map[B, That](f: D => B): Vector[B] = {
    dateRange.map(f)
  }

  def foreach[U](f: D => U): Unit = {
    dateRange.foreach(f)
  }

  override def toString = {
    dateRange.toString
  }
}

class LocalDateRange(val start: LocalDate, val end: LocalDate, val step: Period)
  extends DateRange[LocalDate] {

  val dateRange = Iterator.iterate(start)(_ plus step).takeWhile(_ <= end).toVector

  override protected def copy(start: LocalDate, end: LocalDate, step: Period): LocalDateRange = new LocalDateRange(start, end, step)

}

class DateTimeRange(val start: DateTime, val end: DateTime, val step: Period)
  extends DateRange[DateTime] {

  val dateRange = Iterator.iterate(start)(_ plus step).takeWhile(_ <= end).toVector

  override protected def copy(start: DateTime, end: DateTime, step: Period): DateTimeRange = new DateTimeRange(start, end, step)
}

object DateRange {

  def apply(start: LocalDate, end: LocalDate, step: Period): LocalDateRange = new LocalDateRange(start, end, step)

  def apply(start: LocalDate, end: LocalDate): LocalDateRange = new LocalDateRange(start, end, 1 day)

  def apply(start: DateTime, end: DateTime, step: Period): DateTimeRange = new DateTimeRange(start, end, step)

  def apply(start: DateTime, end: DateTime): DateTimeRange = new DateTimeRange(start, end, 1 day)
}
