package fhj.swengb

import java.io.InputStream
import java.time.LocalDate


object Projects {

  case class Project(name: String, description: String, team: Seq[Student])

}

object Events {

  sealed trait EventStatus

  case object PastEvent extends EventStatus

  case object FutureEvent extends EventStatus

  trait Event {
    def date: LocalDate

    def missingStudents: Set[Student]
  }

  def sortByDate: (Event, Event) => Boolean = {
    (a, b) => a.date.compareTo(b.date) < 0
  }


}


object Speakers {

  val rladstaetter = Speaker("Robert", "Ladstätter", "rladstaetter", 0)
  val jblauensteiner: Speaker = Speaker("Johann", "Blauensteiner", "johblau", 0)
  val psalhofer: Speaker = Speaker("Peter", "Salhofer", "AnotherCodeArtist", 0)
  val speakers = Set(rladstaetter, jblauensteiner, psalhofer)

}

object Students {

  // generated by generateSource(...)
  val abajric: Student = Student("Amar", "Bajric", "amarbajric", 2)

  val jblazevic: Student = Student("Josip", "Blazevic", "jbtastic", 1)

  val cdirnbauer: Student = Student("Christoph", "Dirnbauer", "dirnbaue14", 3)

  val dfolk: Student = Student("Daniel", "Folk", "Folkdani13", 3)

  val mfuchs: Student = Student("Michael", "Fuchs", "deKilla", 1)

  val cfuerbahs: Student = Student("Christoph", "Fürbahs", "furchr", 1)

  val fgraf: Student = Student("Felix", "Graf", "Graf-Carello", 1)

  val thasenbichler: Student = Student("Timo", "Hasenbichler", "timoooo", 1)

  val cherzog: Student = Student("Carina", "Herzog", "carinaher", 1)

  val ghoxha: Student = Student("Granit", "Hoxha", "hoxhagra14", 2)

  val shysi: Student = Student("Steven", "Hysi", "Lionade", 2)

  val jjohansson: Student = Student("Julia", "Johansson", "julia08", 2)

  val dkandlhofer: Student = Student("Daniel", "Kandlhofer", "danielkandlhofer", 2)

  val ekarimova: Student = Student("Elza", "Karimova", "elsakarimova", 1)

  val ekeck: Student = Student("Elke", "Keck", "elkekeck", 2)

  val mknaller: Student = Student("Markus", "Knaller", "knalla66", 2)

  val pkoerner: Student = Student("Paul", "Körner", "McKorleone", 1)

  val clagger: Student = Student("Christian", "Lagger", "clagger", 2)

  val sleitner: Student = Student("Stefan", "Leitner", "loete", 2)

  val alichtenegger: Student = Student("Alexander", "Lichtenegger", "AlexanderLichtenegger", 1)

  val gmeizenitsch: Student = Student("Georg", "Meizenitsch", "meizenit14", 2)

  val pnguyen: Student = Student("Phuong", "Nguyen", "nguyenhuyphuong2603", 3)

  val tortmann: Student = Student("Thomas", "Ortmann", "tortmann", 3)

  val rrobatsch: Student = Student("René", "Robatsch", "robatsch14", 3)

  val aschneider: Student = Student("Andreas", "Schneider", "Zerberuss", 1)

  val fschopper: Student = Student("Fabian", "Schopper", "schopperf", 3)

  val aseebacher: Student = Student("Andreas", "Seebacher", "andseeb", 3)

  val vskerbinz: Student = Student("Verena", "Skerbinz", "verisker", 2)

  val nspalek: Student = Student("Nina", "Spalek", "NSpalek", 3)

  val wsteinkellner: Student = Student("Wolfgang", "Steinkellner", "steinkel14", 2)

  val hvidal: Student = Student("Hector", "Vidal", "MikVidal", 3)

  val mwageneder: Student = Student("Maximilian", "Wageneder", "mwageneder", 3)

  val myildiz: Student = Student("Marcel", "Yildiz", "myildiz", 3)

  val lzefferer: Student = Student("Lukas", "Zefferer", "zefferel14", 3)

  val mzsifkovits: Student = Student("Markus", "Zsifkovits", "mzsif", 3)


  // generated by generateSource END

  def mkStudents(): Set[Student] = {
    val src: InputStream = getClass.getResourceAsStream("/fhj/swengb/students.csv")
    assert(src != null, "Could not find student definition file")
    (for (l <- io.Source.fromInputStream(src).getLines()) yield {
      val Array(group, secondName, firstName, githubUsername) = l.split(",")
      Student(firstName, secondName, githubUsername, group.toInt)
    }).toSet
  }

  def compareBySecondname(a: Student, b: Student): Boolean = {
    a.secondName.compareTo(b.secondName) < 0
  }


  def toScala(s: Student): String = {
    s""" val ${s.userId}: Student = Student("${s.firstName}", "${s.secondName}", "${s.githubUsername}", ${s.group})
        |
     """.stripMargin
  }

  def generateSource(): String = {
    "  // generated by generateSource(...) START\n" +
      sortedStudents.map(toScala).mkString + "\n" +
      "  // generated by generateSource(...) END"
  }

  val students: Set[Student] = mkStudents()

  lazy val sortedStudents = mkStudents().toSeq.sortWith(compareBySecondname)
  lazy val studentGroup1 = students.filter(_.group == 1)
  lazy val studentGroup2 = students.filter(_.group == 2)
  lazy val studentGroup3 = students.filter(_.group == 3)

  def main(args: Array[String]) {
    val source: String = generateSource()
    println(source)
  }

}







