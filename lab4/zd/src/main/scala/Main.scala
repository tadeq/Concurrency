import scala.collection.mutable.ListBuffer

object Main {
  def placeInFoata(transaction: Char, foata: ListBuffer[ListBuffer[Char]], dependencies: List[(Char, Char)]): Unit = {
    val transactionDependencies = dependencies.filter(dep => {dep._1.equals(transaction) || dep._2.equals(transaction)})
    var dependentTo = -1
    var classNumber = -1
    for (foataClass <- foata) {
      classNumber += 1
      for (element <- foataClass) {
        if (transactionDependencies.exists(dep => dep._1.equals(element) || dep._2.equals(element)))
          dependentTo = classNumber
      }
    }
    if (dependentTo == foata.size - 1) {
      foata += ListBuffer.empty[Char]
      foata.last += transaction
    } else {
      foata(dependentTo + 1) += transaction
    }
  }

  def hasse(trace: String, dependencies: List[(Char, Char)]): ListBuffer[(Char, Char)] = {
    var min = ListBuffer.empty[Int]
    var graph = ListBuffer.empty[(Char, Char)]
    for (i <- trace.length - 1 to 0 by -1) {
      min += i
      for (n <- min) {
        if (n != i) {
          System.out.println(i + " " + trace(i) + " " + trace(n) + " " + min)
          if (dependencies.contains((trace(i), trace(n)))) {
            //System.out.println(i + " "+trace(i)+" "+trace(n) +" " + min)
            graph += Tuple2(trace(i), trace(n))
            min -= n
          }
        }
      }
    }
    System.out.println(min)
    graph
  }


  def main(args: Array[String]): Unit = {
    val w = "addacbbc"
    val alphabet = List('a', 'b', 'c', 'd')
    val transactions = List("x:=x+y", "y:=y+2z", "x:=3x+z", "z:=y-z")
    val parsedTransactions = transactions.map(TransactionParser.parse)
    var dependencies = ListBuffer.empty[(Char, Char)]
    var independencies = ListBuffer.empty[(Char, Char)]
    for (t1 <- parsedTransactions) {
      for (t2 <- parsedTransactions) {
        val i1 = alphabet(parsedTransactions.indexOf(t1))
        val i2 = alphabet(parsedTransactions.indexOf(t2))
        if ((t1._2 contains t2._1) || (t2._2 contains t1._1)) {
          dependencies += Tuple2(i1, i2)
        } else {
          independencies += Tuple2(i1, i2)
        }
      }
    }
    dependencies.foreach(d => System.out.println(d))
    System.out.println()
    independencies.foreach(i => System.out.println(i))
    System.out.println()
    var foata = ListBuffer.empty[ListBuffer[Char]]
    for (i <- 0 until w.length) {
      placeInFoata(w(i), foata, dependencies.toList)
    }
    foata.foreach(f => System.out.println(f.toList))
    System.out.println()
    val graph = hasse(w, dependencies.toList)
    System.out.println(graph)
  }
}