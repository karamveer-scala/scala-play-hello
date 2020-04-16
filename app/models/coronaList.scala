package models

import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

import org.apache.spark.sql.functions.{min, max,desc,sum,col,when}
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.collection.mutable.ListBuffer
import java.io.File
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date

case class Person(name: String)

object coronaList {
  
  
       
 
      val spark = SparkSession.builder()
    .appName("SparkByExamples.com")
    .master("local")
    .getOrCreate()
  
  val path ="public/files/corona.csv"
  
  
    val df = spark.read.format("csv")
        		        .option("delimeter", ",")
                    .option("quoteAll","true")
                    .option("escape","\"")
                    .option("parserLib", "UNIVOCITY")
                    .option("charToEscapeQuoteEscaping", "\0")
                    .option("comment","")
                    .option("header", true)
                    .option("inferSchema", true)
                    .option("ignoreLeadingWhiteSpace", true)
                    .option("ignoreTrailingWhiteSpace",true)
                    .option("nullValue", ",")
                    .option("nanValue", "NaN")
                    .option("positiveInf", "Inf")
                    .option("negativeInf", "-Inf")
                    .option("dateFormat", "yyyy-MM-dd")
                    .option("timestampFormat", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                    .option("maxColumns", "20480")
                    .option("maxCharsPerColumn", "-1")
                    .option("mode", "PERMISSIVE")
                    .option("multiLine", true)
                    .load(path).select("dateRep","cases","deaths","countriesAndTerritories")
    
           
  
  
 // get lastUpdated date from file 
def getfileCreation():String = {
                   val file: Path = Paths.get(path);
                   val attr: BasicFileAttributes =
                          Files.readAttributes(file, classOf[BasicFileAttributes])
          
                   val cretime=attr.creationTime()
                   val credate = new Date( cretime.toMillis() )
                  
                   val pattern: String = "E, dd MMM yyyy"
                   val simpleDateFormat: SimpleDateFormat = new SimpleDateFormat(pattern)
                
                 
                   val date= simpleDateFormat.format(credate)
                
              date  
      }
      
      
 // top five country
      
  def topfive() = {
    
       
               val selectedColumnName = df.columns(1) //pull the (q + 1)th column from the columns array
               val k= df.groupBy("countriesAndTerritories").agg(sum("cases") as "totalcases").orderBy(desc("totalcases")).limit(5)
               
                
               val r=k.withColumn("countriesAndTerritories",
                when(col("countriesAndTerritories") === "United_States_of_America", "USA") 
               .when(col("countriesAndTerritories") === "United_Kingdom", "UK")    
                    
                .otherwise(col("countriesAndTerritories"))
                           ).collect
                val topfive=r.map(r => r.toString()).toList
            
               
                
                var listper:List[Person]=null
                var listBuff = new ListBuffer[Person]()
                for(x <- topfive) {
                  val num=x.stripPrefix("[").stripSuffix("]").trim.split(",")(1)
                  val cou=x.stripPrefix("[").stripSuffix("]").trim.split(",")(0)
                
                  val sum= cou+" : "+num
                  
                  listBuff += Person(sum)
               
                }
                listper = listBuff.toList
              
             //  println("listper "+listper)
             listper
  }
  
  // all country
   def allCountry() = {
    
    
     val selectedColumnName = df.columns(1) //pull the (q + 1)th column from the columns array
     val k= df.groupBy("countriesAndTerritories").agg(sum("cases") as "totalcases",sum("deaths") as "totaldeaths").orderBy(desc("totalcases"))
     k.show()
      
      val r=k.withColumn("countriesAndTerritories",
                when(col("countriesAndTerritories") === "United_States_of_America", "USA") 
               .when(col("countriesAndTerritories") === "United_Kingdom", "UK")    
                    
                .otherwise(col("countriesAndTerritories"))
                           ).collect
      val topfive=r.map(r => r.toString()).toList
  
     
      
      var listper:List[String]=null
      var listBuff = new ListBuffer[String]()
      for(x <- topfive) {
        val num=x.stripPrefix("[").stripSuffix("]").trim.split(",")(1)
        val cou=x.stripPrefix("[").stripSuffix("]").trim.split(",")(0)
      
        println(x)
        
        val sum= cou+" : "+num
        
        listBuff += (sum)
     
      }
      listper = listBuff.toList
    
   
   topfive
  }
  
  
  
}
