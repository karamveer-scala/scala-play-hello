package com.spark.corona


import org.apache.spark.sql.functions;

import org.apache.spark.sql.SparkSession


import org.apache.log4j.Logger
import org.apache.log4j.Level

 
import org.apache.tika.parser.pdf.PDFParser



import scala.collection.mutable.{HashMap, ListBuffer}
import org.apache.spark.sql.DataFrame 
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.input.PortableDataStream
import org.apache.tika.metadata._
import org.apache.tika.parser._

import java.io._

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.Tika;

import org.xml.sax.SAXException;

import org.apache.tika.parser.txt.TXTParser

import org.apache.tika.parser
import org.apache.tika.sax.BodyContentHandler
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.StructType

import org.apache.spark.sql.Row._
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date





object sample {
  val path ="public/files/corona.csv"
  
  def pdfString():(String,String) = {
    
     
   //detecting the file type
    val handler: BodyContentHandler = new BodyContentHandler(-1)
    val metadata: Metadata = new Metadata()
    val inputstream: FileInputStream = new FileInputStream(
      new File(path))
    val pcontext: ParseContext = new ParseContext()
//OOXml parser
			
			
	
   	
   // val pdfparser: PDFParser = new PDFParser()
     val pdfparser: TXTParser =  new TXTParser()
    pdfparser.parse(inputstream, handler, metadata, pcontext)
    
   
    
    val k=handler.toString.trim()
  
    
    
    val page= new File(path)
			
		//println("========length "+page.length())
  //  println("\n\n\nDATA::\n\n\n"+k)
			
//			
//			
//			
// println("Metadata of the document:")
//    val metadataNames: Array[String] = metadata.names()
    var crdate=metadata.get("created")
     println("\nCreated Date" + ": " + crdate)
//      println("\nDate" + ": " + metadata.get("date"))

       println("Title: " + metadata.get(Metadata.DATE))
        println("Title: " + metadata.get(Metadata.TITLE))
    
   
     val pattern: String = "E, dd MMM yyyy"
    val simpleDateFormat: SimpleDateFormat = new SimpleDateFormat(pattern)
  
     val cal = Calendar.getInstance()
         cal.add(Calendar.DATE, -1)
   val l_date=cal.getTime
   
  val date= simpleDateFormat.format(l_date)
   
 	 inputstream.close
    
 	 (k,date)
 	 
  }
  
  
  
}