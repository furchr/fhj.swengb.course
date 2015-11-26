package fhj.swengb.avatarix


import javafx.application.Application
import javafx.scene.shape.{CubicCurveTo, MoveTo, Path}
import javafx.stage.Stage
import java.awt.Button
import java.beans.EventHandler
import java.net.URL
import java.rmi.activation.ActivationGroup_Stub
import java.util.ResourceBundle
import javafx.animation._
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.text
import javafx.scene.control.Label
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{AnchorPane, GridPane, BorderPane}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage
import javafx.util.Duration
import com.sun.javaws.jnl.JavaFXAppDesc
import fhj.swengb.{Students, Speakers}
import scala.util.control.NonFatal


//object launches the class described beneath
object Avatarix {
  def main(args: Array[String]) {
    Application.launch(classOf[Avatarix], args: _*)
  }
}


class Avatarix extends javafx.application.Application {


  val Fxml = "/fhj/swengb/avatarix/Avatarix.fxml"
  val Css = "fhj/swengb/avatarix/Avatarix.css"

  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Avatarix")
      loader.load[Parent]() // side effect
      val scene = new Scene(loader.getRoot[Parent]) //loads the default scene
      stage.setScene(scene)
      stage.setResizable(false) //window cannot be rescaled
      //stage.getScene.getStylesheets.add(Css)
      stage.show()
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

}

//controller contains the description of the functionality of the application
class AvatarixController extends Initializable {
  //attributes are being initialized (everything with an ID)
  @FXML var userinfo: AnchorPane= _
  @FXML var asterix: AnchorPane=_
  @FXML var backbutton: Button = _
  @FXML var user0f: ImageView = _
  @FXML var user1f: ImageView = _
  @FXML var user2f: ImageView = _
  @FXML var user3f: ImageView = _
  @FXML var user4f: ImageView = _
  @FXML var user5f: ImageView = _
  @FXML var user6f: ImageView = _
  @FXML var user7f: ImageView = _
  @FXML var user8f: ImageView = _
  @FXML var user9f: ImageView = _
  @FXML var user10f: ImageView = _
  @FXML var user11f: ImageView = _
  @FXML var username0: Label = _
  @FXML var username1: Label = _
  @FXML var username2: Label = _
  @FXML var username3: Label = _
  @FXML var username4: Label = _
  @FXML var username5: Label = _
  @FXML var username6: Label = _
  @FXML var username7: Label = _
  @FXML var username8: Label = _
  @FXML var username9: Label = _
  @FXML var username10: Label = _
  @FXML var username11: Label = _

  @FXML var githubname:Label = _
  @FXML var avatar:ImageView = _
  @FXML var fullname:Label = _
  @FXML var group:Label = _
  @FXML var publicrepos:Label = _
  @FXML var followers:Label = _
  @FXML var following:Label = _


  //initialize function executes the commands at startup for the main scene
  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    val avatarlistg1 = Students.studentGroup1.map(s => s.gitHubUser.avatarUrl.toString).toList
    val namesg1 = Students.studentGroup1.map(s => s.firstName + "\n" + s.secondName).toList

    //avatarlistg1 foreach println
    //namesg1 foreach println

    val uiimages:List[ImageView] = List(
      user0f,user1f,user2f,user3f,user4f,user5f,user6f,user7f,user8f,user9f,user10f,user11f
      )

    val uinames:List[Label] = List(
      username0,username1,username2,username3,username4,username5,username6,username7,username8,username9,username10,username11
    )

    /*
    val avatarlistg1 = List("ava1","ava2","ava3","ava4")
    val namesg1 = List("name1","name2","name3","name4")
    val uiimages = List("uiava1","uiava2","uiava3","uiava4")
    val uinames = List("uiname1","uiname2","uiname3","uiname4")
    */

    val combine = uiimages zip uinames zip avatarlistg1 zip namesg1
    def flatten2[A,B,C](t: ((A,B),C)) = (t._1._1, t._1._2, t._2)
    def flatten3[A,B,C,D](t: ((A,B),C,D)) = (t._1._1, t._1._2, t._2, t._3)

    for(e <- combine map flatten2 map flatten3) {
      e._1.setImage(new Image(e._3))
      e._2.setText(e._4)

      //println(e._1.toString + " " + e._2.toString + " " + e._3.toString + " " + e._4.toString)
      // println(e._1 + e._3 + e._2 + e._4)
    }

    // uncomment these 2 lines for first presentation
    //user0f.setImage(new Image(Students.jblazevic.gitHubUser.avatarUrl.toString))
    //username0.setText(Students.jblazevic.firstName.toString + "\n" + Students.jblazevic.secondName.toString)


    /*
    user0f.setImage(new Image(Students.jblazevic.gitHubUser.avatarUrl.toString))
    username0.setText(Students.jblazevic.firstName.toString + "\n" + Students.jblazevic.secondName.toString)
    user1f.setImage(new Image(Students.mfuchs.gitHubUser.avatarUrl.toString))
    username1.setText(Students.mfuchs.firstName.toString + "\n" + Students.mfuchs.secondName.toString)
    user2f.setImage(new Image(Students.cfuerbahs.gitHubUser.avatarUrl.toString))
    username2.setText(Students.cfuerbahs.firstName.toString + "\n" + Students.cfuerbahs.secondName.toString)
    user3f.setImage(new Image(Students.fgraf.gitHubUser.avatarUrl.toString))
    username3.setText(Students.fgraf.firstName.toString + "\n" + Students.fgraf.secondName.toString)
    user4f.setImage(new Image(Students.thasenbichler.gitHubUser.avatarUrl.toString))
    username4.setText(Students.thasenbichler.firstName.toString + "\n" + Students.thasenbichler.secondName.toString)
    user5f.setImage(new Image(Students.cherzog.gitHubUser.avatarUrl.toString))
    username5.setText(Students.cherzog.firstName.toString + "\n" + Students.cherzog.secondName.toString)
    user6f.setImage(new Image(Students.ekarimova.gitHubUser.avatarUrl.toString))
    username6.setText(Students.ekarimova.firstName.toString + "\n" + Students.ekarimova.secondName.toString)
    user7f.setImage(new Image(Students.pkoerner.gitHubUser.avatarUrl.toString))
    username7.setText(Students.pkoerner.firstName.toString + "\n" + Students.pkoerner.secondName.toString)
    user8f.setImage(new Image(Students.alichtenegger.gitHubUser.avatarUrl.toString))
    username8.setText(Students.alichtenegger.firstName.toString + "\n" + Students.alichtenegger.secondName.toString)
    user9f.setImage(new Image(Students.pnguyen.gitHubUser.avatarUrl.toString))
    username9.setText(Students.pnguyen.firstName.toString + "\n" + Students.pnguyen.secondName.toString)
    user10f.setImage(new Image(Students.aschneider.gitHubUser.avatarUrl.toString))
    username10.setText(Students.aschneider.firstName.toString + "\n" + Students.aschneider.secondName.toString)
    user11f.setImage(new Image(Speakers.rladstaetter.gitHubUser.avatarUrl.toString))
    username11.setText(Speakers.rladstaetter.firstName.toString + "\n" + Speakers.rladstaetter.secondName.toString)
  */
  }



  val xDif = 1



  def animation(obj:AnchorPane):Unit =
  {
    Thread.sleep(30)
    obj.setTranslateX(obj.getTranslateX+xDif)
    if (obj.getTranslateX <=0) {animation(obj)}
    else {print(obj.getTranslateY.toString + obj.getTranslateY.toString)
    }
  }



  def anim(obj:AnchorPane, slideRight:Boolean):Unit = {
    var xMitte = 300
    var yMitte = 400
    var path: Path = new Path()

    if (slideRight) {
      path.getElements.add(new MoveTo(xMitte, yMitte))
      path.getElements().add(new CubicCurveTo(xMitte+50, yMitte, xMitte+200, yMitte, xMitte+700, yMitte))
    } else {
      path.getElements.add(new MoveTo(xMitte+700, yMitte))
      path.getElements().add(new CubicCurveTo(xMitte+200, yMitte, xMitte+50, yMitte, xMitte, yMitte))
    }

    var pathTrans : PathTransition = new PathTransition()
    pathTrans.setDuration(new Duration(200))
    pathTrans.setNode(obj)
    pathTrans.setPath(path)
    pathTrans.setAutoReverse(false)
    pathTrans.play()
  }

    // Username wird momentan noch statisch/hardcoded übergeben - funktioniert, aber wird noch ausgebessert
  def setUserInfos(userId:String): Unit = {
    githubname.setText(Students.jblazevic.gitHubUser.login.toString)

    //Bitte Hier die Strings ersetzen mit den Daten zu der richtigen User ID
    avatar.setImage(new Image(Students.jblazevic.gitHubUser.avatarUrl.toString))

    fullname.setText("Name: " + Students.jblazevic.firstName + " " + Students.jblazevic.secondName)
    group.setText("Group: " + Students.jblazevic.group.toString)
    publicrepos.setText("Public repos: " + Students.jblazevic.gitHubUser.pubRepos.toString)
    followers.setText("Followers: " + Students.jblazevic.gitHubUser.followers.toString)
    following.setText("Following: " + Students.jblazevic.gitHubUser.following.toString)

    //nur für die animation     -> nicht ändern
    anim(userinfo,false)
  }


  def goBack(): Unit = anim(userinfo,true)
  //Hier die richtigen User Infos übergeben! -> aus tabelle oda ka wie ihr sie gespeichert habts
  def user0(): Unit = setUserInfos("user0")

  def user1(): Unit = setUserInfos("user0")

  def user2(): Unit = setUserInfos("user0")

  def user3(): Unit = setUserInfos("user0")

  def user4(): Unit =setUserInfos("user0")

  def user5(): Unit = setUserInfos("user0")

  def user6(): Unit = setUserInfos("user0")

  def user7(): Unit = setUserInfos("user0")

  def user8(): Unit = setUserInfos("user0")

  def user9(): Unit = setUserInfos("user0")

  def user10(): Unit = setUserInfos("user0")

  def user11(): Unit = setUserInfos("user0")

}