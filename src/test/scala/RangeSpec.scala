import com.github.nscala_time.time.Imports._
import org.specs2.mutable.Specification

class RangeSpec extends Specification {

  "Range" should {
    "be created from DateTime, LocalDate" in {
      val date1 = "2014-01-01".toDateTime
      val date2 = "2014-12-31".toDateTime
      val range = (date1 to date2 by (1 month))

      range.length must equalTo(12)
    }
  }
}
