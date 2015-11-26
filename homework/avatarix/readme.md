#Avatarix

![<fhLogo>](<https://www.fh-joanneum.at/custom/images/logo_1001.png>)

![<fhIma>](<http://www.fh-joanneum.at/global/show_picture.asp?id=aaaaaaaaaajahgt>)

#General Information

Avatarix is a simple GUI developed in Scala. The application fetches all pictures of the IMA14 members from the Github website and displays them within this GUI. Additional information of the course members is provided by clicking on the profile picture, because the avatars are buttons as well.

The main logic of the program is the parser. This function makes a request to the WebService of the Github website. The given information is parsed via Json and then provided in the GUI. The GUI is made by using the Scenebuilder, a visual layout tool for designing JavaFX applications.

#Work Distribution

- Parser
	- Carina Herzog, Elza Karimova, Phuong Nguyen
- GUI
	- Josip Blazevic, Andreas Schneider
- Testing
	- Felix Graf, Timo Hasenbichler, Paul Körner
- Documentation
	- Christoph Fürbahs, Alexander Lichtenegger
- Presentation/Git Management
	- Michael Fuchs

#Parser

The basic parser implementation of Mr Ladstätter was extended by certain objects to get the specific data we want.
    
    object GitHub {
    
    case class User(login: String,
                    id: BigDecimal,
                    avatarUrl: URL,
                    htmlUrl: URL,
                    pubRepos: BigDecimal,
                    followers: BigDecimal,
                    following: BigDecimal
                    )
    
    object GithubUserProtocol extends DefaultJsonProtocol {
    
        implicit object GithubUserJsonFormat extends RootJsonFormat[User] {
        
        def write(user: User): JsValue =
            JsArray(
            JsString(user.login),
            JsNumber(user.id),
            JsString(user.avatarUrl.toString),
            JsString(user.htmlUrl.toString),
            JsNumber(user.pubRepos),
            JsNumber(user.followers),
            JsNumber(user.following)
            )
    
        def read(value: JsValue): User = {
            value match {
            case JsObject(m) =>
                val JsString(login) = m("login")
                val JsNumber(id) = m("id")
                val JsString(a_url) = m("avatar_url")
                val JsString(html_url) = m("html_url")
                val JsNumber(p_repos) = m("public_repos")
                val JsNumber(followers) = m("followers")
                val JsNumber(following) = m("following")
                User(login, id, new URL(a_url), new URL(html_url), p_repos , followers, following)
            case x =>
                deserializationError("GitHubUser expected.")
            }}}}}

#GUI

Attention!
import javafx.util.Duration necessary because there is the same type in the scala import package
necessary for the animations / parts in JavaFX.
the combination of fxml and css is responsible for the GUI design of the app.

    //controller contains the description of the functionality of the application
    class AvatarixController extends Initializable {
    //attributes are being initialized (everything with an ID)
    @FXML var userinfo: AnchorPane= _	//this anchor pane is the whole pane with the user information
    @FXML var asterix: AnchorPane=_	//this anchor pane is the whole ground pane with all listed users 
    @FXML var backbutton: Button = _
    @FXML var user0f: ImageView = _
    @FXML var user1f: ImageView = _
    @FXML var user2f: ImageView = _

    @FXML var username0: Label = _
    @FXML var username1: Label = _
    @FXML var username2: Label = _
    
    @FXML var githubname:Label = _
    @FXML var avatar:ImageView = _
    @FXML var fullname:Label = _
    @FXML var group:Label = _
    @FXML var publicrepos:Label = _
    @FXML var followers:Label = _
    @FXML var following:Label = _

#Animation

this function is responsible for the animation of information and is used to slide the infoscreen in and out.
The attributes defines, if the object (obj = in our case the info-screen) should slide in or out. (slideRight = true -> slide out, if false -> slide in)

    def anim(obj:AnchorPane, slideRight:Boolean):Unit = {
        //define the center of the app screen
        var xMitte = 300 
        var yMitte = 400
        
        var path: Path = new Path() //define new path
    
        if (slideRight) {
        path.getElements.add(new MoveTo(xMitte, yMitte))		        //add the start element to the path
        path.getElements().add(new CubicCurveTo(xMitte+50, yMitte, xMitte+200, yMitte, xMitte+700, yMitte))  //add a curve to the path with the coordinates, which should be on the curve path, always set the coordinates in a distance to the middle to prevent offset, if the screen size changes in the future
        } else {
        path.getElements.add(new MoveTo(xMitte+700, yMitte))			//same as before, only different startpoint
        path.getElements().add(new CubicCurveTo(xMitte+200, yMitte, xMitte+50, yMitte, xMitte, yMitte))		//same as before, only different coordinates
        }
    
        var pathTrans : PathTransition = new PathTransition()	//define a new path transition
        pathTrans.setDuration(new Duration(200))				//seth the length of the animation 
        pathTrans.setNode(obj)									//set the object, wich should be moved
        pathTrans.setPath(path)									//set the path, which should be animated
        pathTrans.setAutoReverse(false)							//set autoreverse to false
        pathTrans.play()										//start the animation
    }

#Buttons

define buttons to start the corresponding functions

    def goBack(): Unit = anim(userinfo,true)
    def user0(): Unit = setUserInfos("user0")
    def user1(): Unit = setUserInfos("user0")

#About this app

This app was created during the Software Engineering Basics cource with Mr Robert Ladstätter.

http://www.fh-joanneum.at/ima/?lan=en

http://www.fh-joanneum.at/aw/home/Studienangebot-Uebersicht/department-angewandte-informatik/ima/Studium/~uqs/IMA-lvdetails/?alvid=4352549966&lan=en

http://www.fh-joanneum.at/aw/home/Studienangebot-Uebersicht/department-angewandte-informatik/ima/Menschen/Team/~baqr/ima-teamdetails/?perid=-1025000000000005485&lan=en

last modified 2015-11-26