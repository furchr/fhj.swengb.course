package fhj.swengb

import java.net.URL

import spray.json._

/**
  * Created by lad on 13.11.15.
  */
object GitHub {

  case class User(login: String,
                  avatarUrl: URL,
                  htmlUrl: URL,
                  name: String,
                  //company: String,
                  pubRepos: BigDecimal,
                  followers: BigDecimal,
                  following: BigDecimal
                 )

  object GithubUserProtocol extends DefaultJsonProtocol {

    implicit object GithubUserJsonFormat extends RootJsonFormat[User] {
      def write(user: User): JsValue =
        JsArray(
          JsString(user.login),
          JsString(user.avatarUrl.toString),
          JsString(user.htmlUrl.toString),
          JsString(user.name),
          //JsString(user.company)
          JsNumber(user.pubRepos),
          JsNumber(user.followers),
          JsNumber(user.following)
        )


      def read(value: JsValue): User = {
        value match {
          case JsObject(m) =>
            val JsString(login) = m("login")
            val JsString(a_url) = m("avatar_url")
            val JsString(html_url) = m("html_url")
            val JsString(f_name) = m("name")
            //val JsString(company) = m("company")
            val JsNumber(p_repos) = m("public_repos")
            val JsNumber(followers) = m("followers")
            val JsNumber(following) = m("following")
            User(login, new URL(a_url), new URL(html_url), f_name, /*company,*/ p_repos , followers, following)
          case x =>
            deserializationError("GitHubUser expected.")
        }
      }

    }

  }

}
