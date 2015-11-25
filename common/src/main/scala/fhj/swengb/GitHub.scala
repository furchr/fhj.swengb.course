package fhj.swengb

import java.net.URL

import spray.json._

/**
  * Created by lad on 13.11.15.
  */
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
        }
      }

    }

  }

}
