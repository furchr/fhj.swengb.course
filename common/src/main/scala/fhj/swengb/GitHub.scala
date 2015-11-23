package fhj.swengb

import java.net.URL

import spray.json._

/**
  * Created by lad on 13.11.15.
  */
object GitHub {

  case class User(login: String, avatarUrl: URL, htmlUrl: URL, fullname: String, email: String, blogURL: String, pubRepos: String, pubGists: String, createdOn: String, updOn: String )

  object GithubUserProtocol extends DefaultJsonProtocol {

    implicit object GithubUserJsonFormat extends RootJsonFormat[User] {
      def write(user: User): JsValue =
        JsArray(
          JsString(user.login),
          JsString(user.avatarUrl.toString),
          JsString(user.htmlUrl.toString),
          JsString(user.fullname),
          JsString(user.email),
          JsString(user.blogURL),
          JsString(user.pubRepos),
          JsString(user.pubGists),
          JsString(user.createdOn),
          JsString(user.updOn)
        )


      def read(value: JsValue): User = {
        value match {
          case JsObject(m) =>
            val JsString(login) = m("login")
            val JsString(a_url) = m("avatar_url")
            val JsString(html_url) = m("html_url")
            val JsString(f_name) = m("name")
            val JsString(e_mail) = m("email")
            val JsString(blog_url) = m("blog")
            val JsString(p_repos) = m("public_repos")
            val JsString(p_gists) = m("public_gists")
            val JsString(cr_on) = m("created_at")
            val JsString(upd_on) = m("updated_at")
            User(login, new URL(a_url), new URL(html_url), f_name, e_mail, blog_url, p_repos, p_gists, cr_on, upd_on)
          case x =>
            deserializationError("GitHubUser expected.")
        }
      }

    }

  }

}
