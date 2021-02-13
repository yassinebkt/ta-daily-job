import java.util.Calendar

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext

object TADailyJob extends App{
  override def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("Tweets Analyzer dailyJob").setMaster("local[2]")
    val sc = new SparkContext(sparkConf)
    val accumalator = sc.longAccumulator("tweet count")

    val sqlContext = new SQLContext(sc)
    val cal = Calendar.getInstance
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH) + 1
    val day = cal.get(Calendar.DAY_OF_MONTH)

    val df = sqlContext.read.option("mergeSchema", "true").parquet("/input/tweets/parquet/year=" + year +"/month="+ month + "/day=" + day)
    df.count()
    df.show()
  }

}